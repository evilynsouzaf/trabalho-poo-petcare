package clinico;

import usuarios.Veterinario;

public abstract class RegistroClinico {
    private int id;
    private String data;
    private String descricao;
    private Veterinario veterinario;

    public RegistroClinico(int id, String data, String descricao, Veterinario veterinario) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que zero.");
        }
        if (data == null || data.trim().isEmpty()) {
            throw new IllegalArgumentException("Data inválida.");
        }
        if (veterinario == null) {
            throw new IllegalArgumentException("Veterinário é obrigatório.");
        }
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.veterinario = veterinario;
    }

    public int getId() { return id; }
    public String getData() { return data; }
    public String getDescricao() { return descricao; }
    public Veterinario getVeterinario() { return veterinario; }

    public abstract void exibir();
}