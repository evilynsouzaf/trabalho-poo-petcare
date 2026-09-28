package financeiro;

import agendamento.Notificador;
import usuarios.Tutor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Fatura {

    private int id;
    private Tutor tutor;
    private double valor;
    private String dataEmissao;
    private String dataVencimento;

    private String status;

    private String descricaoServico;
    private Notificador notificador;

    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Fatura(
            int id,
            Tutor tutor,
            double valor,
            String desc,
            Notificador notif
    ) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "O ID da fatura deve ser maior que zero."
            );
        }

        if (tutor == null) {
            throw new IllegalArgumentException(
                    "O tutor deve ser informado."
            );
        }

        if (valor <= 0) {
            throw new IllegalArgumentException(
                    "O valor da fatura deve ser maior que zero."
            );
        }

        if (desc == null || desc.isBlank()) {
            throw new IllegalArgumentException(
                    "A descrição do serviço deve ser informada."
            );
        }

        if (notif == null) {
            throw new IllegalArgumentException(
                    "O notificador deve ser informado."
            );
        }

        this.id = id;
        this.tutor = tutor;
        this.valor = valor;
        this.descricaoServico = desc;
        this.notificador = notif;

        this.dataEmissao =
                LocalDate.now().format(FORMATO_DATA);

        this.dataVencimento =
                LocalDate.now()
                        .plusDays(30)
                        .format(FORMATO_DATA);

        this.status = "PENDENTE";
    }

    public void emitir() {

        if (valor <= 0) {
            throw new IllegalStateException(
                    "Não é possível emitir uma fatura com valor inválido."
            );
        }

        if (descricaoServico == null ||
                descricaoServico.isBlank()) {

            throw new IllegalStateException(
                    "A fatura não possui descrição do serviço."
            );
        }

        if ("PAGO".equals(status)) {
            throw new IllegalStateException(
                    "Uma fatura paga não pode ser emitida novamente."
            );
        }

        atualizarStatusVencimento();

        if ("VENCIDO".equals(status)) {
            throw new IllegalStateException(
                    "Não é possível emitir uma fatura vencida."
            );
        }

        status = "PENDENTE";

        System.out.println(
                "Fatura #" + id + " emitida com sucesso."
        );


        notificador.enviarFatura(this);
    }

    public String gerarBoleto() {

        atualizarStatusVencimento();

        if (!"PENDENTE".equals(status)) {
            throw new IllegalStateException(
                    "Só é possível gerar boleto para uma "
                            + "fatura pendente."
            );
        }

        String codigoBoleto =
                "BOLETO-" + id + "-" +
                        String.format("%.2f", valor);

        return codigoBoleto;
    }

    public String gerarLinkPagamento() {

        atualizarStatusVencimento();

        if (!"PENDENTE".equals(status)) {
            throw new IllegalStateException(
                    "Só é possível gerar link de pagamento "
                            + "para uma fatura pendente."
            );
        }

        return "https://pagamento.exemplo/fatura/" + id;
    }

    public void registrarPagamento() {

        atualizarStatusVencimento();

        if ("PAGO".equals(status)) {
            throw new IllegalStateException(
                    "A fatura já está paga."
            );
        }

        if ("VENCIDO".equals(status)) {
            throw new IllegalStateException(
                    "A fatura está vencida e não pode "
                            + "ser paga por este método."
            );
        }

        if (!"PENDENTE".equals(status)) {
            throw new IllegalStateException(
                    "A fatura não está disponível para pagamento."
            );
        }

        status = "PAGO";

        System.out.println(
                "Pagamento da fatura #" + id +
                        " registrado com sucesso."
        );

        notificador.enviarAlerta(
                "Tutor",
                "Pagamento da fatura #" + id +
                        " registrado."
        );
    }

    public boolean confirmarPagOnline() {

        atualizarStatusVencimento();

        if ("PAGO".equals(status)) {
            return false;
        }

        if ("VENCIDO".equals(status)) {
            return false;
        }

        if (!"PENDENTE".equals(status)) {
            return false;
        }

        boolean pagamentoConfirmado = true;

        if (pagamentoConfirmado) {

            status = "PAGO";

            System.out.println(
                    "Pagamento online da fatura #" + id +
                            " confirmado."
            );

            notificador.enviarAlerta(
                    "Tutor",
                    "Pagamento online da fatura #" +
                            id + " confirmado."
            );

            return true;
        }

        return false;
    }

    public boolean isPendente() {

        atualizarStatusVencimento();

        return "PENDENTE".equals(status);
    }

    public double getValor() {
        return valor;
    }

    public String getStatus() {

        atualizarStatusVencimento();

        return status;
    }

    public void exibir() {

        atualizarStatusVencimento();

        System.out.println("===== FATURA =====");
        System.out.println("ID: " + id);
        System.out.println("Tutor: " + tutor);
        System.out.println(
                "Valor: R$ " +
                        String.format("%.2f", valor)
        );
        System.out.println(
                "Data de emissão: " +
                        dataEmissao
        );
        System.out.println(
                "Data de vencimento: " +
                        dataVencimento
        );
        System.out.println(
                "Status: " + status
        );
        System.out.println(
                "Serviço: " +
                        descricaoServico
        );

        notificador.enviarFatura(this);
    }

    private void atualizarStatusVencimento() {

        if ("PAGO".equals(status)) {
            return;
        }

        try {

            LocalDate vencimento =
                    LocalDate.parse(
                            dataVencimento,
                            FORMATO_DATA
                    );

            if (LocalDate.now().isAfter(vencimento)) {

                status = "VENCIDO";
            }

        } catch (DateTimeParseException e) {

            throw new IllegalStateException(
                    "A data de vencimento da fatura é inválida."
            );
        }
    }
}
