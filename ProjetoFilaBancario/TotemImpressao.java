import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TotemImpressao extends JFrame {

    public TotemImpressao() {
        setTitle("Totem de Autoatendimento");
        setSize(450, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Cabeçalho instrutivo
        JPanel painelCabecalho = new JPanel(new GridLayout(2, 1));
        painelCabecalho.setBackground(Color.WHITE);
        
        JLabel lblBemVindo = new JLabel("BEM-VINDO AO BANCO", SwingConstants.CENTER);
        lblBemVindo.setFont(new Font("Arial", Font.BOLD, 24));
        
        JLabel lblInstrucao = new JLabel("Por favor, selecione seu tipo de atendimento:", SwingConstants.CENTER);
        lblInstrucao.setFont(new Font("Arial", Font.PLAIN, 14));
        
        painelCabecalho.add(lblBemVindo);
        painelCabecalho.add(lblInstrucao);
        add(painelCabecalho, BorderLayout.NORTH);

        // Corpo: Botões de seleção de Senha
        JPanel painelBotoes = new JPanel(new GridLayout(2, 1, 10, 20));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton btnNormal = new JButton("Atendimento Normal");
        btnNormal.setFont(new Font("Arial", Font.BOLD, 20));
        btnNormal.setBackground(new Color(70, 130, 180));
        btnNormal.setForeground(Color.WHITE);

        JButton btnPrioritario = new JButton("<html><center>Atendimento Prioritário<br><font size='3'>(Idosos, Grávidas, PCD)</font></center></html>");
        btnPrioritario.setFont(new Font("Arial", Font.BOLD, 20));
        btnPrioritario.setBackground(new Color(220, 53, 69));
        btnBotoesDesign(btnNormal);
        btnBotoesDesign(btnPrioritario);

        painelBotoes.add(btnNormal);
        painelBotoes.add(btnPrioritario);
        add(painelBotoes, BorderLayout.CENTER);

        // Ações dos Botões (Integração com a lógica do seu projeto)
        btnNormal.addActionListener(e -> acaoGerarSenha("Normal"));
        btnPrioritario.addActionListener(e -> acaoGerarSenha("Prioritária"));
    }

    private void btnBotoesDesign(JButton botao) {
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // Método que simula a lógica de negócio e "imprime" o talão
    private void acaoGerarSenha(String tipo) {
        // Aqui vocês chamarão o método do grupo (ex: Sistema.gerarSenha(tipo))
        String senhaGerada = tipo.equals("Normal") ? "N-006" : "P-003"; 
        int pessoasNaFrente = 4; // Exemplo retornado pela fila

        // Caixa de diálogo simulando o talão impresso saindo da máquina
        String mensagemTalao = String.format(
            "===============================\n" +
            "         BANCO CAA - TALÃO     \n" +
            "===============================\n" +
            " SENHA: %s\n" +
            " TIPO: Atendimento %s\n" +
            "-------------------------------\n" +
            " Pessoas na sua frente: %d\n" +
            "===============================\n" +
            "Por favor, aguarde no painel.", 
            senhaGerada, tipo, pessoasNaFrente
        );

        JOptionPane.showMessageDialog(this, mensagemTalao, "Imprimindo Senha...", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TotemImpressao().setVisible(true));
    }
}