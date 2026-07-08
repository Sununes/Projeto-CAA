package src.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JanelaClient extends JFrame {

    private JComboBox<String> cbTipo;
    private JComboBox<String> cbServico;
    private JComboBox<String> cbModo;
    private JButton btnRetirar;

    private static int contadorNormal = 1;
    private static int contadorPrioridade = 1;

    public JanelaClient(){
         setTitle("Sistema Bancario - Cliente");
         setSize(500, 500);
         setLocationRelativeTo(null);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


         Color azul = new Color(13, 71, 161);
         Color azulClaro = new Color(25, 188, 210);

         JPanel painelPrincipal = new JPanel();
         painelPrincipal.setBackground(Color.WHITE);
         painelPrincipal.setLayout(new BorderLayout());

         JPanel topo = new JPanel();
         topo.setBackground(azul);
         topo.setPreferredSize(new Dimension(500, 70));

         JLabel Titulo = new JLabel("Sistema Bancario");
         Titulo.setForeground(Color.WHITE);
         Titulo.setFont(new Font("Arial", Font.BOLD, 24));

         topo.add(Titulo);

         //=====CENTRO======
         JPanel centro = new JPanel();
         centro.setBackground(Color.WHITE);
         centro.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
         centro.setLayout(new GridLayout(8, 1, 10, 10));

         JLabel lblTipo = new JLabel("Tipo de Atendimento");
            lblTipo.setFont(new Font("Arial", Font.BOLD, 16));

         cbTipo = new JComboBox<>();
         cbTipo.addItem("Normal");
         cbTipo.addItem("Prioritaria");

         JLabel lblServico = new JLabel("servico");
         lblServico.setFont(new Font("Arial", Font.BOLD, 16));

         cbServico = new JComboBox<>();
         cbServico.addItem("Atendimento Geral");
         cbServico.addItem("Abertura de conta");

         cbServico.addItem("Credito");

         JLabel lblModo = new JLabel("Modo de atendimento");
         lblModo.setFont(new Font("Arial", Font.BOLD, 16));

         cbModo = new JComboBox<>();
         cbModo.addItem("Presencial");
         cbModo.addItem("Online");

         btnRetirar = new JButton("RETIRAR SENHA");
         btnRetirar.setBackground(azulClaro);
         btnRetirar.setForeground(Color.WHITE);
         btnRetirar.setFont(new Font("Arial", Font.BOLD,18));
         btnRetirar.setFocusPainted(false);


         centro.add(lblTipo);
         centro.add(cbTipo);
         centro.add(lblServico);
         centro.add(cbServico);
         centro.add(lblModo);
         centro.add(cbModo);
         centro.add(new JLabel(""));
         centro.add(btnRetirar);


         //Rodape
         JPanel rodape = new JPanel();
         rodape.setBackground(azul);

         JLabel info = new JLabel("Banco Digital @ 2026");
         info.setForeground(Color.WHITE);

         rodape.add(info);

         painelPrincipal.add(topo, BorderLayout.NORTH);
         painelPrincipal.add(centro, BorderLayout.CENTER);
         painelPrincipal.add(rodape, BorderLayout.SOUTH);

         add(painelPrincipal);

         btnRetirar.addActionListener(e-> gerarSenha());
    }

    private void gerarSenha() {

        String tipo = cbTipo.getSelectedItem().toString();
        String servico = cbServico.getSelectedItem().toString();
        String modo = cbModo.getSelectedItem().toString();

        String senha;

        if (tipo.equals("Normal")) {
            senha = "N" + String.format("%03d", contadorNormal++);
        } else {
            senha = "P" + String.format("%03d", contadorPrioridade++);
        }
        JOptionPane.showMessageDialog(this, "senha gerada com sucesso!\n\n" + "senha:" + senha + "\ntipo:" + tipo
                + "\nservico:" + servico + "\nModo:" + modo, "senha Emitida", JOptionPane.INFORMATION_MESSAGE);

    }

}
