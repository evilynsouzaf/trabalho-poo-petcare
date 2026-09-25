/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios;

/**
 *
 * @author SEDU ES
 */
public class Recepcionista extends Usuario {

    private String ramal;
    private String turno;

    public Recepcionista(int id, String nome, String email, String ramal) {
        super(id, nome, email, "123456", "RECEP");
        this.ramal = ramal;
        this.turno = "Manha";
    }

    public void cadastrarTutor(Tutor t) {
        System.out.println("Tutor cadastrado.");
    }

    public void cadastrarAnimal(clinico.Animal a) {
        System.out.println("Animal cadastrado.");
    }

    public boolean agendarConsulta(agendamento.Agendamento ag) {
        return true;
    }

    public void cancelarConsulta(int id) {
        System.out.println("Consulta cancelada.");
    }

    public Tutor buscarTutor(String termo) {
        return null;
    }

    public clinico.Animal buscarAnimal(String termo) {
        return null;
    }

    @Override
    public void exibir() {
        System.out.println("[RECEPCIONISTA] " + getNome());
    }
}