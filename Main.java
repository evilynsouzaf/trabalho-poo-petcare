import clinico.Animal;
import clinico.Consulta;
import clinico.Vacina;
import financeiro.Fatura;
import financeiro.LogAuditoria;
import financeiro.Relatorio;
import agendamento.Agendamento;
import notificacao.Notificador;
import estoque.Estoque;
import estoque.ItemEstoque;
import usuarios.Tutor;
import usuarios.Veterinario;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== SISTEMA PETCARE ===");

        // 1. Usuários
        Tutor tutor = new Tutor(
                1,
                "João Silva",
                "joao@email.com",
                "1234",
                "123.456.789-00",
                "99999-9999"
        );

        Veterinario veterinario = new Veterinario(
                2,
                "Ana Veterinária",
                "ana@petcare.com",
                "1234",
                "CRMV-123",
                "Clínica Geral"
        );

        boolean login = veterinario.login("ana@petcare.com", "1234");
        System.out.println("[USUARIO] Login: ana@petcare.com — Perfil: "
                + veterinario.getPerfil() + " | Sucesso: " + login);

        // 2. Animal
        Animal animal = new Animal(
                1,
                "Rex",
                "Cão",
                "Labrador",
                tutor
        );
        tutor.adicionarAnimal(animal);
        animal.exibir();

        // 3. Histórico clínico
        Consulta consulta = new Consulta(
                1,
                "30/09/2026",
                "Consulta de rotina",
                veterinario,
                "Saudável",
                "Manter alimentação atual"
        );

        Vacina vacina = new Vacina(
                2,
                "30/09/2026",
                "Vacinação anual",
                veterinario,
                "V10",
                "LOTE-001",
                "30/09/2027"
        );

        animal.getHistorico().adicionarConsulta(consulta);
        animal.getHistorico().adicionarVacina(vacina);
        animal.getHistorico().exibir();

        // 4. Notificação + agendamento
        Notificador notificador = new Notificador(
                "EMAIL",
                tutor.getEmail()
        );

        Agendamento agendamento = new Agendamento(
                1,
                "01/10/2026 10:00",
                "CONSULTA",
                animal,
                veterinario,
                notificador
        );

        agendamento.agendar();
        System.out.println("[AGENDAMENTO] Tipo: " + agendamento.getTipo()
                + " | Status: " + agendamento.getStatus());

        // 5. Estoque
        ItemEstoque dipirona = new ItemEstoque(
                1,
                "Dipirona",
                50,
                10,
                "LOTE-DIP-01"
        );

        Estoque estoque = new Estoque(notificador);
        estoque.adicionarItem(dipirona);
        dipirona.exibir();

        // 6. Fatura
        Fatura fatura = new Fatura(
                1,
                tutor,
                150.00,
                "Consulta veterinária",
                notificador
        );
        System.out.println("[FATURA] Valor: R$ "
                + String.format("%.2f", fatura.getValor())
                + " | Status: " + fatura.getStatus());

        // 7. Relatório — parte da Pessoa 5
        Relatorio relatorio = new Relatorio(9, 2026);
        relatorio.setTotalAtendimentos(2);
        relatorio.setFaturamentoTotal(150.00);
        relatorio.setTotalDespesas(40.00);
        relatorio.setTaxaRetorno(50.00);
        relatorio.setTempMedioAtend(45.0);
        relatorio.setProcedMaisRealizado("Consulta");
        relatorio.gerar();
        relatorio.exibir();

        // 8. Log de auditoria — parte da Pessoa 5
        LogAuditoria log = new LogAuditoria(
                veterinario,
                "AJUSTE_ESTOQUE",
                "ItemEstoque"
        );
        log.setIdEntidade(dipirona.getId());
        log.setIpOrigem("127.0.0.1");
        log.registrar();
        log.exibir();

        System.out.println("=== FIM DO TESTE ===");
    }
}
