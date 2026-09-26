package clinico;

import usuarios.Tutor;

public class Animal {

    private int id;
    private String nome;
    private String especie;
    private String raca;
    private String dataNascimento;
    private double peso;
    private Tutor tutor;
    private HistoricoClinico historico;

    public Animal(int id, String nome, String especie, String raca, Tutor tutor) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.tutor = tutor;
        this.historico = new HistoricoClinico(id);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaca() {
        return raca;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public HistoricoClinico getHistorico() {
        return historico;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("Peso deve ser maior que zero.");
        }
        this.peso = peso;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int calcularIdade() {
        if (dataNascimento == null || dataNascimento.length() < 4) {
            return 0;
        }
        int anoNascimento = Integer.parseInt(dataNascimento.substring(0, 4));
        int anoAtual = 2026;
        return anoAtual - anoNascimento;
    }

    public void exibir() {
        System.out.println("[ANIMAL] " + nome + " | " + raca + " | Tutor: " + tutor.getNome());
    }
}