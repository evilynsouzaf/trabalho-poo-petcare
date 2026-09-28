package clinico;

import usuarios.Veterinario;

public class Exame extends RegistroClinico {
    private String tipoExame;
    private String resultado;

    public Exame(int id, String data, String descricao, Veterinario veterinario, String tipoExame, String resultado) {
        super(id, data, descricao, veterinario);
        this.tipoExame = tipoExame;
        this.resultado = resultado;
    }

    public String getTipoExame() { return tipoExame; }
    public String getResultado() { return resultado; }

    @Override
    public void exibir() {
        System.out.println("[EXAME] ID: " + getId() + " | Tipo: " + tipoExame +
                           " | Vet: " + getVeterinario().getNome() +
                           " | Resultado: " + resultado);
    }
}