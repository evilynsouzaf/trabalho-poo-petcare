package estoque;

import notificacao.Notificador;

import java.util.ArrayList;
import java.util.List;

public class Estoque {

    private List<ItemEstoque> itens;
    private Notificador notificador;

    public Estoque(Notificador notificador) {

        if (notificador == null) {
            throw new IllegalArgumentException(
                    "O notificador deve ser informado."
            );
        }

        this.itens = new ArrayList<>();
        this.notificador = notificador;
    }

    public void adicionarItem(ItemEstoque item) {

        if (item == null) {
            throw new IllegalArgumentException(
                    "O item não pode ser nulo."
            );
        }

        if (buscarItemPorId(item.getId()) != null) {
            throw new IllegalArgumentException(
                    "Já existe um item com o ID " +
                            item.getId() + "."
            );
        }

        itens.add(item);
    }

    public void registrarEntrada(
            int id,
            int qtd,
            String resp
    ) {

        if (qtd <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade deve ser maior que zero."
            );
        }

        ItemEstoque item = buscarItemPorId(id);

        if (item == null) {
            throw new IllegalArgumentException(
                    "Item com ID " + id +
                            " não encontrado."
            );
        }

        item.darEntrada(qtd, resp);

        verificarAlertaItem(item);
    }

    public boolean registrarSaida(
            int id,
            int qtd,
            String resp
    ) {

        if (qtd <= 0) {
            return false;
        }

        ItemEstoque item = buscarItemPorId(id);

        if (item == null) {
            return false;
        }

        boolean sucesso =
                item.darSaida(qtd, resp);

        if (sucesso) {
            verificarAlertaItem(item);
        }

        return sucesso;
    }

    public boolean reservarParaProced(String ag) {

        if (ag == null || ag.isBlank()) {
            return false;
        }

        for (ItemEstoque item : itens) {

            if (item.getQuantidade() > 0) {

                boolean reservado =
                        item.reservar(1);

                if (reservado) {

                    notificador.enviarAlertaEstoque(
                            "1 unidade de " +
                                    item.getNome() +
                                    " reservada para o procedimento " +
                                    ag
                    );

                    verificarAlertaItem(item);

                    return true;
                }
            }
        }

        return false;
    }

    public void verificarAlertas() {

        for (ItemEstoque item : itens) {
            verificarAlertaItem(item);
        }
    }

    public ItemEstoque buscarItem(String nome) {

        if (nome == null || nome.isBlank()) {
            return null;
        }

        for (ItemEstoque item : itens) {

            if (item.getNome()
                    .equalsIgnoreCase(nome.trim())) {

                return item;
            }
        }

        return null;
    }

    public List<ItemEstoque> getItens() {
        return new ArrayList<>(itens);
    }

    public List<ItemEstoque> rastrearControlados() {

        List<ItemEstoque> controlados =
                new ArrayList<>();

        for (ItemEstoque item : itens) {

            if (item.isControlado()) {
                controlados.add(item);
            }
        }

        return controlados;
    }

    public void alertarVencimentos() {

        for (ItemEstoque item : itens) {

            if (item.isVencido()) {

                notificador.enviarAlertaEstoque(
                        "Item vencido: " +
                                item.getNome() +
                                " | Lote: " +
                                item.getLote()
                );
            }
        }
    }

    public void gerarRelatorioRastreb() {

        System.out.println(
                "===== RELATÓRIO DE RASTREABILIDADE ====="
        );

        if (itens.isEmpty()) {

            System.out.println(
                    "Nenhum item cadastrado no estoque."
            );

            return;
        }

        for (ItemEstoque item : itens) {

            System.out.println(
                    "ID: " +
                            item.getId() +

                            " | Nome: " +
                            item.getNome() +

                            " | Quantidade: " +
                            item.getQuantidade() +

                            " | Lote: " +
                            item.getLote() +

                            " | Controlado: " +
                            item.isControlado()
            );
        }
    }

    public void exibir() {

        System.out.println("===== ESTOQUE =====");

        if (itens.isEmpty()) {

            System.out.println(
                    "Estoque vazio."
            );

            return;
        }

        for (ItemEstoque item : itens) {

            item.exibir();

            System.out.println(
                    "-------------------------"
            );
        }
    }

    private ItemEstoque buscarItemPorId(int id) {

        for (ItemEstoque item : itens) {

            if (item.getId() == id) {
                return item;
            }
        }

        return null;
    }

    private void verificarAlertaItem(
            ItemEstoque item
    ) {

        if (item.isAbaixoMinimo()) {

            notificador.enviarAlertaEstoque(
                    "Estoque abaixo do mínimo: " +
                            item.getNome() +
                            " | Quantidade atual: " +
                            item.getQuantidade() +
                            " | Mínimo: " +
                            item.getQuantidadeMinima()
            );
        }

        if (item.isVencido()) {

            notificador.enviarAlertaEstoque(
                    "Item vencido: " +
                            item.getNome() +
                            " | Lote: " +
                            item.getLote()
            );
        }
    }
}
