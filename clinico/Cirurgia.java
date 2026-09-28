package clinico;

import usuarios.Veterinario;

public class Cirurgia extends RegistroClinico {
    private String tipoCirurgia;
    private String recomendacaoPos;

    public Cirurgia(int id, String data, String descricao, Veterinario veterinario, 
                    String tipoCirurgia, String recomendacaoPos) 
    {
        super(id, data, descricao, veterinario);
        this.tipoCirurgia = tipoCirurgia;
        this.recomendacaoPos = recomendacaoPos;
    }

    public String getTipoCirurgia() { return tipoCirurgia; }
    public String getRecomendacaoPos() { return recomendacaoPos; }

    @Override
    public void exibir() {
        System.out.println("[CIRURGIA] ID: " + getId() + " | Tipo: " + tipoCirurgia +
                           " | Vet: " + getVeterinario().getNome() +
                           " | Pos-Op: " + recomendacaoPos);
    }
}
