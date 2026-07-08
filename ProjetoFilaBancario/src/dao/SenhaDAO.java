package src.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SenhaDAO {

    // 1. Salva a senha quando ela é gerada no Totem
    public void salvarSenha(String codigoSenha, String tipo) {
        String sql = "INSERT INTO historico_senhas (codigo_senha, tipo, status) VALUES (?, ?, 'Aguardando')";
        
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, codigoSenha);
            stmt.setString(2, tipo);
            stmt.executeUpdate();
            System.out.println("Senha " + codigoSenha + " salva no MySQL com sucesso.");
            
        } catch (SQLException e) {
            System.err.println("Erro ao salvar senha no banco.");
            e.printStackTrace();
        }
    }

    // 2. Atualiza o status quando o Agente Bancário chama a senha
    public void atualizarAtendimento(String codigoSenha, int guiche) {
        String sql = "UPDATE historico_senhas SET status = 'Atendido', guiche = ? WHERE codigo_senha = ? AND status = 'Aguardando'";
        
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, guiche);
            stmt.setString(2, codigoSenha);
            stmt.executeUpdate();
            System.out.println("Status da senha " + codigoSenha + " atualizado para Atendido.");
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar atendimento no banco.");
            e.printStackTrace();
        }
    }

    // 3. Atualiza o status se a senha for cancelada
    public void cancelarSenha(String codigoSenha) {
        String sql = "UPDATE historico_senhas SET status = 'Cancelado' WHERE codigo_senha = ? AND status = 'Aguardando'";
        
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, codigoSenha);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Erro ao cancelar senha no banco.");
            e.printStackTrace();
        }
    }

    // 4. Recupera senhas pendentes ao iniciar o sistema (evita perder a fila se o sistema cair)
    public List<String> buscarSenhasPorStatusETipo(String status, String tipo) {
        List<String> senhas = new ArrayList<>();
        String sql = "SELECT codigo_senha FROM historico_senhas WHERE status = ? AND tipo = ? ORDER BY id ASC";
        
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, status);
            stmt.setString(2, tipo);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                senhas.add(rs.getString("codigo_senha"));
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao recuperar senhas do banco.");
            e.printStackTrace();
        }
        return senhas;
    }
}