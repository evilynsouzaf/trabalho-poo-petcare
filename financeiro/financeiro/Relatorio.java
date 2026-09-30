package financeiro;

/**
 * Representa o relatório estatístico mensal da clínica.
 *
 * Os dados são mantidos no próprio objeto porque o projeto atual
 * não possui uma camada de banco de dados/serviço para alimentar
 * o relatório automaticamente.
 */
public class Relatorio {

    private static int proximoId = 1;

    private int id;
    private int mes;
    private int ano;
    private int totalAtendimentos;
    private double faturamentoTotal;
    private double totalDespesas;
    private double taxaRetorno;
    private double tempMedioAtend;
    private String procedMaisRealizado;

    public Relatorio(int mes, int ano) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("O mês deve estar entre 1 e 12.");
        }
        if (ano < 2000) {
            throw new IllegalArgumentException("Ano inválido.");
        }

        this.id = proximoId++;
        this.mes = mes;
        this.ano = ano;
        this.totalAtendimentos = 0;
        this.faturamentoTotal = 0.0;
        this.totalDespesas = 0.0;
        this.taxaRetorno = 0.0;
        this.tempMedioAtend = 0.0;
        this.procedMaisRealizado = "Não informado";
    }

    /**
     * Gera/atualiza o relatório com os dados que já foram informados.
     * A base atual do projeto não possui uma fonte de dados centralizada
     * para realizar cálculos automáticos.
     */
    public void gerar() {
        if (totalAtendimentos < 0) {
            totalAtendimentos = 0;
        }
        if (faturamentoTotal < 0) {
            faturamentoTotal = 0.0;
        }
        if (totalDespesas < 0) {
            totalDespesas = 0.0;
        }
        if (taxaRetorno < 0) {
            taxaRetorno = 0.0;
        }
        if (tempMedioAtend < 0) {
            tempMedioAtend = 0.0;
        }
        if (procedMaisRealizado == null || procedMaisRealizado.isBlank()) {
            procedMaisRealizado = "Não informado";
        }
    }

    public String getEstatisticas() {
        return "Atendimentos: " + totalAtendimentos
                + " | Faturamento: R$ " + String.format("%.2f", faturamentoTotal)
                + " | Despesas: R$ " + String.format("%.2f", totalDespesas)
                + " | Taxa de retorno: " + String.format("%.2f", taxaRetorno) + "%"
                + " | Tempo médio: " + String.format("%.2f", tempMedioAtend) + " min"
                + " | Procedimento mais realizado: " + procedMaisRealizado;
    }

    public double calcularFaturamento() {
        return faturamentoTotal;
    }

    public double calcularTaxaRetorno() {
        return taxaRetorno;
    }

    public double calcularTempMedio() {
        return tempMedioAtend;
    }

    public String getProcedMaisRealizado() {
        return procedMaisRealizado;
    }

    /**
     * Na versão atual, exportar é representado pela preparação/exibição
     * do conteúdo do relatório. Não existe ainda um requisito de arquivo
     * ou formato de exportação no restante do projeto.
     */
    public void exportar() {
        gerar();
        System.out.println("[RELATORIO] Exportação preparada: " + getEstatisticas());
    }

    public void exibir() {
        gerar();
        System.out.println("===== RELATÓRIO =====");
        System.out.println("ID: " + id);
        System.out.println("Período: " + String.format("%02d/%d", mes, ano));
        System.out.println(getEstatisticas());
    }

    public int getId() {
        return id;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public int getTotalAtendimentos() {
        return totalAtendimentos;
    }

    public double getFaturamentoTotal() {
        return faturamentoTotal;
    }

    public double getTotalDespesas() {
        return totalDespesas;
    }

    public double getTaxaRetorno() {
        return taxaRetorno;
    }

    public double getTempMedioAtend() {
        return tempMedioAtend;
    }

    public void setTotalAtendimentos(int totalAtendimentos) {
        if (totalAtendimentos < 0) {
            throw new IllegalArgumentException("Total de atendimentos não pode ser negativo.");
        }
        this.totalAtendimentos = totalAtendimentos;
    }

    public void setFaturamentoTotal(double faturamentoTotal) {
        if (faturamentoTotal < 0) {
            throw new IllegalArgumentException("Faturamento não pode ser negativo.");
        }
        this.faturamentoTotal = faturamentoTotal;
    }

    public void setTotalDespesas(double totalDespesas) {
        if (totalDespesas < 0) {
            throw new IllegalArgumentException("Total de despesas não pode ser negativo.");
        }
        this.totalDespesas = totalDespesas;
    }

    public void setTaxaRetorno(double taxaRetorno) {
        if (taxaRetorno < 0 || taxaRetorno > 100) {
            throw new IllegalArgumentException("A taxa de retorno deve estar entre 0 e 100%.");
        }
        this.taxaRetorno = taxaRetorno;
    }

    public void setTempMedioAtend(double tempMedioAtend) {
        if (tempMedioAtend < 0) {
            throw new IllegalArgumentException("O tempo médio não pode ser negativo.");
        }
        this.tempMedioAtend = tempMedioAtend;
    }

    public void setProcedMaisRealizado(String procedMaisRealizado) {
        if (procedMaisRealizado == null || procedMaisRealizado.isBlank()) {
            throw new IllegalArgumentException("O procedimento deve ser informado.");
        }
        this.procedMaisRealizado = procedMaisRealizado.trim();
    }
}
