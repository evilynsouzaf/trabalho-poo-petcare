package financeiro;

import usuarios.Usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Registra ações importantes realizadas por usuários do sistema.
 */
public class LogAuditoria {

    private static int proximoId = 1;
    private static final List<LogAuditoria> registros = new ArrayList<>();
    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private int id;
    private String dataHora;
    private Usuario usuario;
    private String acao;
    private String entidadeAfetada;
    private int idEntidade;
    private String ipOrigem;

    public LogAuditoria(Usuario usuario, String acao, String entidade) {
        if (usuario == null) {
            throw new IllegalArgumentException("O usuário é obrigatório.");
        }
        if (acao == null || acao.isBlank()) {
            throw new IllegalArgumentException("A ação é obrigatória.");
        }
        if (entidade == null || entidade.isBlank()) {
            throw new IllegalArgumentException("A entidade afetada é obrigatória.");
        }

        this.id = proximoId++;
        this.usuario = usuario;
        this.acao = acao.trim();
        this.entidadeAfetada = entidade.trim();
        this.idEntidade = 0;
        this.ipOrigem = "Não informado";
        this.dataHora = "Não registrado";
    }

    /**
     * Registra o log com a data e hora da ação.
     */
    public void registrar() {
        this.dataHora = LocalDateTime.now().format(FORMATO);

        if (!registros.contains(this)) {
            registros.add(this);
        }

        System.out.println("[LOG] " + dataHora
                + " | Usuario: " + usuario.getNome()
                + " | Ação: " + acao);
    }

    public String getAcao() {
        return acao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getDataHora() {
        return dataHora;
    }

    public List<LogAuditoria> buscarPorUsuario(Usuario u) {
        List<LogAuditoria> encontrados = new ArrayList<>();

        if (u == null) {
            return encontrados;
        }

        for (LogAuditoria log : registros) {
            if (log.usuario.getId() == u.getId()) {
                encontrados.add(log);
            }
        }

        return encontrados;
    }

    public List<LogAuditoria> buscarPorEntidade(String e) {
        List<LogAuditoria> encontrados = new ArrayList<>();

        if (e == null || e.isBlank()) {
            return encontrados;
        }

        for (LogAuditoria log : registros) {
            if (log.entidadeAfetada.equalsIgnoreCase(e.trim())) {
                encontrados.add(log);
            }
        }

        return encontrados;
    }

    public void exibir() {
        System.out.println("===== LOG DE AUDITORIA =====");
        System.out.println("ID: " + id);
        System.out.println("Data/Hora: " + dataHora);
        System.out.println("Usuário: " + usuario.getNome());
        System.out.println("Ação: " + acao);
        System.out.println("Entidade: " + entidadeAfetada);
        System.out.println("ID da entidade: " + idEntidade);
        System.out.println("IP de origem: " + ipOrigem);
    }

    public int getId() {
        return id;
    }

    public String getEntidadeAfetada() {
        return entidadeAfetada;
    }

    public int getIdEntidade() {
        return idEntidade;
    }

    public String getIpOrigem() {
        return ipOrigem;
    }

    public void setIdEntidade(int idEntidade) {
        if (idEntidade < 0) {
            throw new IllegalArgumentException("ID da entidade não pode ser negativo.");
        }
        this.idEntidade = idEntidade;
    }

    public void setIpOrigem(String ipOrigem) {
        if (ipOrigem == null || ipOrigem.isBlank()) {
            throw new IllegalArgumentException("IP de origem inválido.");
        }
        this.ipOrigem = ipOrigem.trim();
    }
}
