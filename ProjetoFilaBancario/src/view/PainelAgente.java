package src.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelAgente extends JFrame {
    private JComboBox<Integer> comboGuiche;
    private JLabel lblStatusFila;
    private JLabel lblSenhaSendoAtendida;
    private JButton btnChamarProximo;
    
    // Construtor da Interface
    public PainelAgente() {
        setTitle("Painel do Agente Bancário");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Topo: Identificação do Guichê
        JPanel painelTopo = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        painelTopo.setBackground(new Color(240, 240, 240));
        
        JLabel lblGuiche = new JLabel("Selecione seu balcão:");
        lblGuiche.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Simula guichês de 1 a 5
        Integer[] guiches = {1, 2, 3};
        comboGuiche = new JComboBox<>(guiches);
        comboGuiche.setFont(new Font("Arial", Font.PLAIN, 14));
        
        painelTopo.add(lblGuiche);
        painelTopo.add(comboGuiche);
        add(painelTopo, BorderLayout.NORTH);

        // Centro: Monitoramento e Ação de Chamar
        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new BoxLayout(painelCentro, BoxLayout.Y_AXIS));
        painelCentro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Bloco de Monitoramento do Tamanho da Fila
        lblStatusFila = new JLabel(String.format("Clientes aguardando: %d normais | %d prioritários", TotemImpressao.getTamanhoFilaNormal(), TotemImpressao.getTamanhoFilaPrioritaria()));
        lblStatusFila.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblStatusFila.setFont(new Font("Arial", Font.ITALIC, 13));
        
        // Espaçador
        painelCentro.add(lblStatusFila);
        painelCentro.add(Box.createRigidArea(new Dimension(0, 30)));

        // Botão principal de Chamar Próximo
        btnChamarProximo = new JButton("CHAMAR PRÓXIMO");
        btnChamarProximo.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnChamarProximo.setFont(new Font("Arial", Font.BOLD, 18));
        btnChamarProximo.setBackground(new Color(40, 167, 69)); // Verde operacional
        btnChamarProximo.setForeground(Color.WHITE);
        btnChamarProximo.setFocusPainted(false);
        btnChamarProximo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnChamarProximo.setPreferredSize(new Dimension(250, 60));
        btnChamarProximo.setMaximumSize(new Dimension(250, 60));
        
        painelCentro.add(btnChamarProximo);
        painelCentro.add(Box.createRigidArea(new Dimension(0, 40)));

        // Bloco Inferior: Mostrar qual senha o agente está atendendo agora
        JLabel lblTituloAtendimento = new JLabel("Em atendimento neste balcão:", SwingConstants.CENTER);
        lblTituloAtendimento.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTituloAtendimento.setFont(new Font("Arial", Font.PLAIN, 12));
        
        lblSenhaSendoAtendida = new JLabel("Nenhum", SwingConstants.CENTER);
        lblSenhaSendoAtendida.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSenhaSendoAtendida.setFont(new Font("Arial", Font.BOLD, 48));
        lblSenhaSendoAtendida.setForeground(new Color(0, 102, 204));

        painelCentro.add(lblTituloAtendimento);
        painelCentro.add(lblSenhaSendoAtendida);
        add(painelCentro, BorderLayout.CENTER);

        // Ação do Botão
        btnChamarProximo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acaoChamarProximo();
            }
        });
    }

    private void acaoChamarProximo() {
        int guicheSelecionado = (int) comboGuiche.getSelectedItem();

        String senhaChamada = TotemImpressao.chamarProximaSenha(guicheSelecionado);

        if (senhaChamada != null) {
            lblSenhaSendoAtendida.setText(senhaChamada);
            int qtdNormal = TotemImpressao.getTamanhoFilaNormal();
            int qtdPrioritaria = TotemImpressao.getTamanhoFilaPrioritaria();
            lblStatusFila.setText(String.format("Clientes aguardando: %d normais | %d prioritários", qtdNormal, qtdPrioritaria));
            JOptionPane.showMessageDialog(this,
                String.format("Senha %s chamada para o guichê %d", senhaChamada, guicheSelecionado),
                "Senha Chamada",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                "Não há clientes em espera no momento.",
                "Fila Vazia",
                JOptionPane.WARNING_MESSAGE);
        }
    }
}