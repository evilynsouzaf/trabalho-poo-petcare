package estoque;

public class ItemEstoque {

    private int id;
    private String nome;
    private int quantidade;
    private int quantidadeMinima;
    private String lote;
    private String validade;
    private boolean controlado;
    private String responsavelRetirada;
    private String categoria;

    public ItemEstoque(int id, String nome, int quantidade, int quantidadeMinima, String lote) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
        this.lote = lote;
    }

    public void darEntrada(int qtd, String responsavel) {
        this.quantidade = this.quantidade + qtd;
    }

    public boolean darSaida(int qtd, String responsavel) {
        if (qtd > quantidade) {
            return false;
        }
        this.quantidade = this.quantidade - qtd;
        return true;
    }

    public boolean reservar(int qtd) {
        if (qtd > quantidade) {
            return false;
        }
        this.quantidade = this.quantidade - qtd;
        return true;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public boolean isAbaixoMinimo() {
        return quantidade < quantidadeMinima;
    }

    public boolean isControlado() {
        return controlado;
    }

    public void setControlado(boolean controlado) {
        if (controlado && (lote == null || validade == null || responsavelRetirada == null)) {
            throw new IllegalStateException("Item controlado precisa de lote, validade e responsável.");
        }
        this.controlado = controlado;
    }

    public boolean isVencido() {
        if (validade == null) {
            return false;
        }
        return validade.compareTo("2026-09-26") < 0;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getResponsavelRetirada() {
        return responsavelRetirada;
    }

    public void setResponsavelRetirada(String responsavelRetirada) {
        this.responsavelRetirada = responsavelRetirada;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void exibir() {
        System.out.println("[ESTOQUE] " + nome + " | Qtd: " + quantidade + " | Mínimo: " + quantidadeMinima);
    }
}