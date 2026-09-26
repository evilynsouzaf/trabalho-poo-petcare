package clinico;

import java.util.List;
import java.util.ArrayList;

public class HistoricoClinico {

    private int idAnimal;
    private List<Consulta> consultas;
    private List<Vacina> vacinas;
    private List<Cirurgia> cirurgias;
    private List<Exame> exames;
    private List<Tratamento> tratamentos;
    private boolean finalizado;

    public HistoricoClinico(int idAnimal) {
        this.idAnimal = idAnimal;
        this.consultas = new ArrayList<>();
        this.vacinas = new ArrayList<>();
        this.cirurgias = new ArrayList<>();
        this.exames = new ArrayList<>();
        this.tratamentos = new ArrayList<>();
        this.finalizado = false;
    }

    public void adicionarConsulta(Consulta c) {
        if (finalizado) {
            throw new IllegalStateException("Histórico finalizado.");
        }
        consultas.add(c);
    }

    public void adicionarVacina(Vacina v) {
        if (finalizado) {
            throw new IllegalStateException("Histórico finalizado.");
        }
        vacinas.add(v);
    }

    public void adicionarCirurgia(Cirurgia c) {
        if (finalizado) {
            throw new IllegalStateException("Histórico finalizado.");
        }
        cirurgias.add(c);
    }

    public void adicionarExame(Exame e) {
        if (finalizado) {
            throw new IllegalStateException("Histórico finalizado.");
        }
        exames.add(e);
    }

    public void adicionarTratamento(Tratamento t) {
        if (finalizado) {
            throw new IllegalStateException("Histórico finalizado.");
        }
        tratamentos.add(t);
    }

    public void finalizar() {
        this.finalizado = true;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public List<Vacina> getVacinas() {
        return vacinas;
    }

    public void exibir() {
        System.out.println("[HISTORICO] Consultas: " + consultas.size()
                + " | Vacinas: " + vacinas.size()
                + " | Cirurgias: " + cirurgias.size());
    }
}