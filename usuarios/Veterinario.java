/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios;

/**
 *
 * @author SEDU ES
 */
public class Veterinario extends Usuario {

    private String crmv;
    private String especialidade;
    private boolean disponivel;

    public Veterinario(int id, String nome, String email, String senha, String crmv, String esp) {
        super(id, nome, email, senha, "VET");
        this.crmv = crmv;
        this.especialidade = esp;
        this.disponivel = true;
    }

    public String getCrmv() {
        return crmv;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean v) {
        this.disponivel = v;
    }

    public void registrarLaudo(int id, String texto) {
        System.out.println("Laudo " + id + " registrado: " + texto);
    }

    public void emitirPrescricao(int id, String rx) {
        System.out.println("Prescricao " + id + " emitida: " + rx);
    }

    public void consultarAgenda() {
        System.out.println("Consultando agenda do veterinario " + getNome());
    }

    public void visualizarLogs() {
        System.out.println("Visualizando logs do veterinario " + getNome());
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("[VETERINARIO] CRMV: " + crmv + " - Especialidade: " + especialidade);
    }
}