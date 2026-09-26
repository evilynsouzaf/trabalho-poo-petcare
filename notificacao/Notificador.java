package notificacao;

import java.util.List;
import clinico.Animal;
import estoque.ItemEstoque;
import financeiro.Fatura;
import agendamento.Agendamento;

public class Notificador {

    private String canal;
    private String destinatario;
    private boolean ativo;

    public Notificador(String canal, String destinatario) {
        this.canal = canal;
        this.destinatario = destinatario;
        this.ativo = true;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void enviarConfirmacao(Agendamento ag) {
        System.out.println("[NOTIFICADOR] " + canal + " -> " + destinatario + ": Consulta confirmada!");
    }

    public void enviarCancelamento(Agendamento ag) {
        System.out.println("[NOTIFICADOR] " + canal + " -> " + destinatario + ": Agendamento cancelado.");
    }

    public void enviarReagendamento(Agendamento ag) {
        System.out.println("[NOTIFICADOR] " + canal + " -> " + destinatario + ": Agendamento reagendado.");
    }

    public void enviarLembreteVacina(Animal a, String dataReforco) {
        System.out.println("[NOTIFICADOR] " + canal + " -> " + destinatario + ": Lembrete de vacina para " + a.getNome());
    }

    public void enviarAlertaEstoque(ItemEstoque item) {
        System.out.println("[NOTIFICADOR] " + canal + " -> " + destinatario + ": Estoque baixo de " + item.getNome());
    }

    public void enviarFatura(Fatura f) {
        System.out.println("[NOTIFICADOR] " + canal + " -> " + destinatario + ": Fatura enviada.");
    }

    public void enviarAlerta(String destino, String mensagem) {
        System.out.println("[NOTIFICADOR] " + canal + " -> " + destino + ": " + mensagem);
    }

    public void enviarCampanha(List<String> lista, String mensagem) {
        for (String destino : lista) {
            System.out.println("[NOTIFICADOR] " + canal + " -> " + destino + ": " + mensagem);
        }
    }
}