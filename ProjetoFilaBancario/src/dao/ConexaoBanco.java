package src.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
    // Ajuste o utilizador e a palavra-passe conforme a sua configuração local do MySQL
    private static final String URL = "jdbc:mysql://localhost:3606/banco_caa?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConexao() {
        try {
            // Regista o driver do MySQL (opcional em versões modernas do JDBC, mas seguro)
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Erro: Driver JDBC não encontrado. Adicione o conector .jar!");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Erro ao conectar à base de dados MySQL.");
            e.printStackTrace();
            return null;
        }
    }
}