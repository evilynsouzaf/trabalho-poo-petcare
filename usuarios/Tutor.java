/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios;

/**
 *
 * @author SEDU ES
 */
public class Tutor extends Usuario {

    private String cpf;
    private String telefone;
    private String endereco;
    private java.util.List<clinico.Animal> animais;

    public Tutor(int id, String nome, String email, String senha, String cpf, String tel) {
        super(id, nome, email, senha, "TUTOR");
        this.cpf = cpf;
        this.telefone = tel;
        this.endereco = "Nao informado";
        this.animais = new java.util.ArrayList<>();
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public java.util.List<clinico.Animal> getAnimais() {
        return animais;
    }

    public void adicionarAnimal(clinico.Animal a) {
        animais.add(a);
    }

    public void verHistoricoAnimal(int id) {
        for (clinico.Animal a : animais) {
            if (a.getId() == id) {
                a.getHistorico().exibir();
            }
        }
    }

    public java.util.List<financeiro.Fatura> verFaturas() {
        return new java.util.ArrayList<>();
    }

    public clinico.Animal buscarAnimal(String nome) {
        for (clinico.Animal a : animais) {
            if (a.getNome().equals(nome)) {
                return a;
            }
        }
        return null;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("[TUTOR] CPF: " + cpf + " - Telefone: " + telefone);
    }
}