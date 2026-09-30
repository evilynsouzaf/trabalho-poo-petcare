package clinico;

import java.util.ArrayList;
import java.util.List;

public class HistoricoClinico {

    private int idAnimal;
    private List<Consulta> consultas;
    private List<Vacina> vacinas;
    private List<Cirurgia> cirurgias;
    private List<Exame> exames;
    private boolean finalizado;

    public HistoricoClinico(int idAnimal) {
        this.idAnimal = idAnimal;
        this.consultas = new ArrayList<>();
        this.vacinas = new ArrayList<>();
        this.cirurgias = new ArrayList<>();
        this.exames = new ArrayList<>();
        this.finalizado = false;
    }

    public void adicionarConsulta(Consulta c) {
        verificarFinalizado();
        if (c == null) {
            throw new IllegalArgumentException("Consulta não pode ser nula.");
        }
        consultas.add(c);
    }

    public void adicionarVacina(Vacina v) {
        verificarFinalizado();
        if (v == null) {
            throw new IllegalArgumentException("Vacina não pode ser nula.");
        }
        vacinas.add(v);
    }

    public void adicionarCirurgia(Cirurgia c) {
        verificarFinalizado();
        if (c == null) {
            throw new IllegalArgumentException("Cirurgia não pode ser nula.");
        }
        cirurgias.add(c);
    }

    public void adicionarExame(Exame e) {
        verificarFinalizado();
        if (e == null) {
            throw new IllegalArgumentException("Exame não pode ser nulo.");
        }
        exames.add(e);
    }

    public void finalizar() {
        this.finalizado = true;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public List<Consulta> getConsultas() {
        return new ArrayList<>(consultas);
    }

    public List<Vacina> getVacinas() {
        return new ArrayList<>(vacinas);
    }

    public List<Cirurgia> getCirurgias() {
        return new ArrayList<>(cirurgias);
    }

    public List<Exame> getExames() {
        return new ArrayList<>(exames);
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void exibir() {
        System.out.println("[HISTORICO] Consultas: " + consultas.size()
                + " | Vacinas: " + vacinas.size()
                + " | Cirurgias: " + cirurgias.size());
    }

    private void verificarFinalizado() {
        if (finalizado) {
            throw new IllegalStateException("Histórico finalizado.");
        }
    }
}
