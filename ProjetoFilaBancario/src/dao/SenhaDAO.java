package src.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SenhaDAO {

    private void criarTabelaSeNaoExistir() {
        Connection conn = ConexaoBanco.getConexao();
        if (conn == null) {
            return;
        }

        String sql = "CREATE TABLE IF NOT EXISTS historico_senhas ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "codigo_senha VARCHAR(20) NOT NULL,"
                + "tipo VARCHAR(20) NOT NULL,"
                + "status VARCHAR(20) NOT NULL DEFAULT 'Aguardando',"
                + "guiche INT NULL"
                + ")";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela historico_senhas.");
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ignored) {
            }
        }
    }

    public void salvarSenha(String codigoSenha, String tipo) {
        criarTabelaSeNaoExistir();
        String sql = "INSERT INTO historico_senhas (codigo_senha, tipo, status) VALUES (?, ?, 'Aguardando')";

        Connection conn = ConexaoBanco.getConexao();
        if (conn == null) {
            return;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigoSenha);
            stmt.setString(2, tipo);
            stmt.executeUpdate();
            System.out.println("Senha " + codigoSenha + " salva no MySQL com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar senha no banco.");
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ignored) {
            }
        }
    }

    public void atualizarAtendimento(String codigoSenha, int guiche) {
        criarTabelaSeNaoExistir();
        String sql = "UPDATE historico_senhas SET status = 'Atendido', guiche = ? WHERE codigo_senha = ? AND status = 'Aguardando'";

        Connection conn = ConexaoBanco.getConexao();
        if (conn == null) {
            return;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, guiche);
            stmt.setString(2, codigoSenha);
            stmt.executeUpdate();
            System.out.println("Status da senha " + codigoSenha + " atualizado para Atendido.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar atendimento no banco.");
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ignored) {
            }
        }
    }

    public void cancelarSenha(String codigoSenha) {
        criarTabelaSeNaoExistir();
        String sql = "UPDATE historico_senhas SET status = 'Cancelado' WHERE codigo_senha = ? AND status = 'Aguardando'";

        Connection conn = ConexaoBanco.getConexao();
        if (conn == null) {
            return;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigoSenha);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao cancelar senha no banco.");
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ignored) {
            }
        }
    }

    public List<String> buscarSenhasPorStatusETipo(String status, String tipo) {
        criarTabelaSeNaoExistir();
        List<String> senhas = new ArrayList<>();
        String sql = "SELECT codigo_senha FROM historico_senhas WHERE status = ? AND tipo = ? ORDER BY id ASC";

        Connection conn = ConexaoBanco.getConexao();
        if (conn == null) {
            return senhas;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setString(2, tipo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                senhas.add(rs.getString("codigo_senha"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao recuperar senhas do banco.");
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ignored) {
            }
        }
        return senhas;
    }
}