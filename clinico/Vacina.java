package clinico;

import usuarios.Veterinario;

public class Vacina extends RegistroClinico {
    private String nomeVacina;
    private String lote;
    private String proximaDose;

    public Vacina(int id, String data, String descricao, Veterinario veterinario, String nomeVacina, String lote, String proximaDose) {
        super(id, data, descricao, veterinario);
        this.nomeVacina = nomeVacina;
        this.lote = lote;
        this.proximaDose = proximaDose;
    }

    public String getNomeVacina() { return nomeVacina; }
    public String getLote() { return lote; }
    public String getProximaDose() { return proximaDose; }

    @Override
    public void exibir() {
        System.out.println("[VACINA] ID: " + getId() + " | Vacina: " + nomeVacina +
                           " | Lote: " + lote + " | Vet: " + getVeterinario().getNome());
    }
}