/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios;

/**
 *
 * @author SEDU ES
 */
public class Administrador extends Usuario {

    private int nivelAcesso;
    private String departamento;

    public Administrador(int id, String nome, String email, String senha) {
        super(id, nome, email, senha, "ADMIN");
        this.nivelAcesso = 1;
        this.departamento = "Geral";
    }

    public boolean excluirRegistro(int id, String tipo) {
        return true;
    }

    public void ajustarEstoque(estoque.ItemEstoque item, int qtd) {
        System.out.println("Estoque ajustado.");
    }

    public financeiro.Relatorio gerarRelatorio(int mes, int ano) {
        return null;
    }

    public void gerenciarUsuario(Usuario u) {
        System.out.println("Usuário gerenciado.");
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("[ADMIN] Nível: " + nivelAcesso + " - Departamento: " + departamento);
    }
}