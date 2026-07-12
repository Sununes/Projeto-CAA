package src.view;
import javax.swing.*;
import java.awt.*;

public class GuiTV extends JFrame {
    private JLabel lblSenhaAtual;
    private JLabel lblProximaSenha;
    private JLabel lblGuiche;

    public GuiTV() {
        setTitle("Painel de Atendimento - Banco");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Topo: Cabeçalho do Banco
        JPanel painelTopo = new JPanel();
        painelTopo.setBackground(new Color(25, 25, 112)); // Azul Escuro
        JLabel lblTitulo = new JLabel("ATENDIMENTO BANCÁRIO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        painelTopo.add(lblTitulo);
        add(painelTopo, BorderLayout.NORTH);

        // Centro: Senha Atual em Destaque
        JPanel painelCentro = new JPanel(new GridLayout(2, 1));
        painelCentro.setBackground(Color.BLACK);

        lblSenhaAtual = new JLabel("N-005", SwingConstants.CENTER);
        lblSenhaAtual.setFont(new Font("Impact", Font.BOLD, 120));
        lblSenhaAtual.setForeground(Color.GREEN);

        lblGuiche = new JLabel("VÁ AO Balcão 03", SwingConstants.CENTER);
        lblGuiche.setFont(new Font("Arial", Font.BOLD, 36));
        lblGuiche.setForeground(Color.WHITE);

        painelCentro.add(lblSenhaAtual);
        painelCentro.add(lblGuiche);
        add(painelCentro, BorderLayout.CENTER);

        // Rodapé: Próxima Senha da Fila
        JPanel painelRodape = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        painelRodape.setBackground(new Color(220, 220, 220));
        
        JLabel lblTextoProxima = new JLabel("Próxima Senha:");
        lblTextoProxima.setFont(new Font("Arial", Font.BOLD, 22));
        
        lblProximaSenha = new JLabel("P-002"); // Exemplo de prioritária vindo a seguir
        lblProximaSenha.setFont(new Font("Arial", Font.BOLD, 26));
        lblProximaSenha.setForeground(Color.RED);

        painelRodape.add(lblTextoProxima);
        painelRodape.add(lblProximaSenha);
        add(painelRodape, BorderLayout.SOUTH);
    }

    // Método para atualizar a TV quando a fila andar
    public void atualizarPainel(String senhaAtual, int guiche, String proximaSenha) {
        lblSenhaAtual.setText(senhaAtual);
        lblGuiche.setText("VÁ AO Balcão " + guiche);
        lblProximaSenha.setText(proximaSenha);
        
        // Alerta sonoro (Beep básico do sistema para chamar atenção)
        Toolkit.getDefaultToolkit().beep();
    }

}