package agendamento;

import clinico.Animal;
import usuarios.Veterinario;
import notificacao.Notificador;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Agendamento {

    private int id;
    private String dataHora;
    private String tipo;
    private String status;
    private Animal animal;
    private Veterinario veterinario;
    private String sala;
    private Notificador notificador;
    private List<String> historico;

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern(
                    "dd/MM/yyyy HH:mm"
            );

    private static final List<String> TIPOS_PERMITIDOS =
            List.of(
                    "CONSULTA",
                    "EXAME",
                    "CIRURGIA"
            );


    public Agendamento(
            int id,
            String dataHora,
            String tipo,
            Animal animal,
            Veterinario veterinario,
            Notificador notificador
    ) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "O ID deve ser maior que zero."
            );
        }

        if (dataHora == null ||
                dataHora.isBlank()) {

            throw new IllegalArgumentException(
                    "A data e hora devem ser informadas."
            );
        }

        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException(
                    "O tipo do agendamento deve ser informado."
            );
        }

        String tipoFormatado =
                tipo.trim().toUpperCase();

        if (!TIPOS_PERMITIDOS.contains(tipoFormatado)) {
            throw new IllegalArgumentException(
                    "Tipo inválido. Tipos permitidos: "
                            + TIPOS_PERMITIDOS
            );
        }

        if (animal == null) {
            throw new IllegalArgumentException(
                    "O animal deve ser informado."
            );
        }

        if (veterinario == null) {
            throw new IllegalArgumentException(
                    "O veterinário deve ser informado."
            );
        }

        if (notificador == null) {
            throw new IllegalArgumentException(
                    "O notificador deve ser informado."
            );
        }

        this.id = id;
        this.dataHora = dataHora.trim();
        this.tipo = tipoFormatado;
        this.animal = animal;
        this.veterinario = veterinario;
        this.notificador = notificador;

        this.status = "AGENDADO";
        this.sala = "";
        this.historico = new ArrayList<>();
    }

    public boolean agendar() {

        if ("CANCELADO".equals(status)) {
            return false;
        }

        if ("CONCLUIDO".equals(status)) {
            return false;
        }

        if (!validarHorario()) {
            return false;
        }

        if (!validarVeterinario()) {
            return false;
        }

        if (!reservarRecursos()) {
            return false;
        }

        status = "AGENDADO";

        historico.add(
                "Agendamento criado em " + dataHora
        );

        notificador.enviarConfirmacao(this);

        return true;
    }

    public void cancelar(String motivo) {

        if ("CANCELADO".equals(status)) {
            throw new IllegalStateException(
                    "O agendamento já está cancelado."
            );
        }

        if ("CONCLUIDO".equals(status)) {
            throw new IllegalStateException(
                    "Não é possível cancelar um agendamento concluído."
            );
        }

        if (motivo == null || motivo.isBlank()) {
            throw new IllegalArgumentException(
                    "O motivo do cancelamento deve ser informado."
            );
        }

        status = "CANCELADO";

        historico.add(
                "Agendamento cancelado: " + motivo
        );

        notificador.enviarCancelamento(this);
    }

    public void reagendar(String novaData) {

        if ("CANCELADO".equals(status)) {
            throw new IllegalStateException(
                    "Não é possível reagendar um agendamento cancelado."
            );
        }

        if ("CONCLUIDO".equals(status)) {
            throw new IllegalStateException(
                    "Não é possível reagendar um agendamento concluído."
            );
        }

        if (novaData == null ||
                novaData.isBlank()) {

            throw new IllegalArgumentException(
                    "A nova data deve ser informada."
            );
        }

        String dataAnterior = dataHora;

        this.dataHora = novaData.trim();

        if (!validarHorario()) {
            this.dataHora = dataAnterior;

            throw new IllegalArgumentException(
                    "A nova data e horário são inválidos."
            );
        }

        historico.add(
                "Agendamento reagendado para: "
                        + novaData
        );

        notificador.enviarReagendamento(this);
    }

    public boolean validarHorario() {

        if (dataHora == null ||
                dataHora.isBlank()) {

            return false;
        }

        try {

            LocalDateTime data =
                    LocalDateTime.parse(
                            dataHora,
                            FORMATO
                    );

            LocalTime horario =
                    data.toLocalTime();

            LocalTime inicio =
                    LocalTime.of(8, 0);

            LocalTime fim =
                    LocalTime.of(18, 0);

            if (data.isBefore(LocalDateTime.now())) {
                return false;
            }

            if (horario.isBefore(inicio) ||
                    horario.isAfter(fim)) {

                return false;
            }

            if (data.getDayOfWeek().getValue() == 7) {
                return false;
            }

            return true;

        } catch (DateTimeParseException e) {

            return false;
        }
    }

    public boolean validarVeterinario() {

        if (veterinario == null) {
            return false;
        }

        return true;
    }

    public boolean reservarRecursos() {

        if (animal == null) {
            return false;
        }

        if (veterinario == null) {
            return false;
        }

        if (sala == null || sala.isBlank()) {
            sala = "Sala 01";
        }

        return true;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getHistorico() {
        return new ArrayList<>(historico);
    }

    public void notificarTutor() {

        if (notificador == null) {
            throw new IllegalStateException(
                    "Notificador não configurado."
            );
        }

        notificador.enviarConfirmacao(this);
    }


    public void exibir() {

        System.out.println(
                "===== AGENDAMENTO ====="
        );

        System.out.println(
                "ID: " + id
        );

        System.out.println(
                "Data/Hora: " + dataHora
        );

        System.out.println(
                "Tipo: " + tipo
        );

        System.out.println(
                "Status: " + status
        );

        System.out.println(
                "Sala: " + sala
        );
    }

    public int getId() {
        return id;
    }

    public String getDataHora() {
        return dataHora;
    }

    public String getTipo() {
        return tipo;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {

        if (sala == null || sala.isBlank()) {
            throw new IllegalArgumentException(
                    "A sala deve ser informada."
            );
        }

        this.sala = sala.trim();
    }
}
