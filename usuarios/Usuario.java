/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package usuarios;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private String perfil;
    private boolean ativo;
    private String dataCadastro;

    public Usuario(int id, String nome, String email, String senha, String perfil) {
        this.id = id;
        setNome(nome); 
        setEmail(email); 
        setSenha(senha);
        this.perfil = perfil;
        this.ativo = true;
        this.dataCadastro = "2026-03-25";
    }

    public boolean login(String email, String senha) {
        return this.email.equals(email) && this.senha.equals(senha) && ativo;
    }

    public void logout() {
        System.out.println("Usuário " + nome + " fez logout.");
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo.");
        }
        this.nome = nome;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.email = email;
    }

    public void setSenha(String senha) {
        if (senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser vazia.");
        }
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void desativar() {
        this.ativo = false;
    }

    public void exibir() {
        System.out.println("[USUARIO] ID: " + id + " | Nome: " + nome + " - Perfil: " + perfil);
    }
}