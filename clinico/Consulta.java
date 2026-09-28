package clinico;

import usuarios.Veterinario;

public class Consulta extends RegistroClinico {
    private String diagnostico;
    private String receita;

    public Consulta(int id, String data, String descricao, Veterinario veterinario, String diagnostico, String receita) {
        super(id, data, descricao, veterinario);
        this.diagnostico = diagnostico;
        this.receita = receita;
    }

    public String getDiagnostico() { return diagnostico; }
    public String getReceita() { return receita; }

    @Override
    public void exibir() {
        System.out.println("[CONSULTA] ID: " + getId() + " | Data: " + getData() +
                           " | Vet: " + getVeterinario().getNome() +
                           " | Diagnóstico: " + diagnostico);
    }
}