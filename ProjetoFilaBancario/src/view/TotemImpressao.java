package src.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import src.dao.SenhaDAO;
import src.model.Fila;
import src.model.Senha;

public class TotemImpressao extends JFrame {

    private static final Fila filaNormal = new Fila();
    private static final Fila filaPrioritaria = new Fila();
    private static int proximoNumeroNormal = 1;
    private static int proximoNumeroPrioritario = 1;
    private static String senhaEmAtendimento;
    private static final SenhaDAO senhaDAO = new SenhaDAO();
    private static int guicheEmAtendimento = 1;
    private static GuiTV tvExibicao;

    public TotemImpressao() {
        setTitle("Impressora de Senhas");
        setSize(450, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel painelCabecalho = new JPanel(new GridLayout(2, 1));
        painelCabecalho.setBackground(Color.WHITE);

        JLabel lblBemVindo = new JLabel("BEM-VINDO AO BANCO", SwingConstants.CENTER);
        lblBemVindo.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel lblInstrucao = new JLabel("Por favor, selecione seu tipo de atendimento:", SwingConstants.CENTER);
        lblInstrucao.setFont(new Font("Arial", Font.PLAIN, 14));

        painelCabecalho.add(lblBemVindo);
        painelCabecalho.add(lblInstrucao);
        add(painelCabecalho, BorderLayout.NORTH);

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

        btnNormal.addActionListener(e -> acaoGerarSenha("Normal"));
        btnPrioritario.addActionListener(e -> acaoGerarSenha("Prioritária"));

        new Thread(this::carregarSenhasPersistidas, "CarregamentoSenhas").start();
    }

    public static void registrarGuiTV(GuiTV tv) {
        tvExibicao = tv;
        atualizarTela();
    }

    public static Fila getFilaNormal() {
        return filaNormal;
    }

    public static Fila getFilaPrioritaria() {
        return filaPrioritaria;
    }

    public static String chamarProximaSenha(int guiche) {
        guicheEmAtendimento = guiche;

        Senha senhaPrioritaria = filaPrioritaria.atenderProximo();
        if (senhaPrioritaria != null) {
            senhaEmAtendimento = senhaPrioritaria.getNumeroSenha();
            senhaDAO.atualizarAtendimento(senhaEmAtendimento, guiche);
            atualizarTela();
            return senhaEmAtendimento;
        }

        Senha senhaNormal = filaNormal.atenderProximo();
        senhaEmAtendimento = senhaNormal != null ? senhaNormal.getNumeroSenha() : null;
        if (senhaEmAtendimento != null) {
            senhaDAO.atualizarAtendimento(senhaEmAtendimento, guiche);
        }
        atualizarTela();
        return senhaEmAtendimento;
    }

    public static int getTamanhoFilaNormal() {
        return filaNormal.getTamanho();
    }

    public static int getTamanhoFilaPrioritaria() {
        return filaPrioritaria.getTamanho();
    }

    private static String obterProximaSenha() {
        Senha senhaPrioritaria = filaPrioritaria.verProximo();
        if (senhaPrioritaria != null) {
            return senhaPrioritaria.getNumeroSenha();
        }

        Senha senhaNormal = filaNormal.verProximo();
        return senhaNormal != null ? senhaNormal.getNumeroSenha() : "Nenhuma";
    }

    private static void atualizarTela() {
        if (tvExibicao != null) {
            String senhaAtual = senhaEmAtendimento != null ? senhaEmAtendimento : "Aguardando";
            tvExibicao.atualizarPainel(senhaAtual, guicheEmAtendimento, obterProximaSenha());
        }
    }

    private void carregarSenhasPersistidas() {
        try {
            List<String> senhasNormais = senhaDAO.buscarSenhasPorStatusETipo("Aguardando", "Normal");
            for (String codigo : senhasNormais) {
                adicionarSenhaPersistida(codigo, "Normal");
            }

            List<String> senhasPrioritarias = senhaDAO.buscarSenhasPorStatusETipo("Aguardando", "Prioritária");
            for (String codigo : senhasPrioritarias) {
                adicionarSenhaPersistida(codigo, "Prioritária");
            }
        } catch (Exception e) {
            System.err.println("Não foi possível carregar as senhas persistidas.");
        }
    }

    private void adicionarSenhaPersistida(String codigoSenha, String tipo) {
        Fila filaSelecionada = "Prioritária".equals(tipo) ? filaPrioritaria : filaNormal;
        Senha senha = new Senha(
            codigoSenha,
            "Prioritária".equals(tipo) ? Senha.TipoSenha.Prioritaria : Senha.TipoSenha.Normal,
            null,
            null
        );
        filaSelecionada.adicionarSenha(senha);
        atualizarContador(codigoSenha);
    }

    private void atualizarContador(String codigoSenha) {
        if (codigoSenha.startsWith("P")) {
            try {
                int numero = Integer.parseInt(codigoSenha.substring(1));
                if (numero >= proximoNumeroPrioritario) {
                    proximoNumeroPrioritario = numero + 1;
                }
            } catch (NumberFormatException ignored) {
            }
        } else if (codigoSenha.startsWith("N")) {
            try {
                int numero = Integer.parseInt(codigoSenha.substring(1));
                if (numero >= proximoNumeroNormal) {
                    proximoNumeroNormal = numero + 1;
                }
            } catch (NumberFormatException ignored) {
            }
        }
    }

    private void btnBotoesDesign(JButton botao) {
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void acaoGerarSenha(String tipo) {
        Fila filaSelecionada = "Prioritária".equals(tipo) ? filaPrioritaria : filaNormal;
        int numeroAtual = "Prioritária".equals(tipo) ? proximoNumeroPrioritario++ : proximoNumeroNormal++;
        String prefixo = "Prioritária".equals(tipo) ? "P" : "N";
        String numeroSenha = prefixo + numeroAtual;

        Senha senha = new Senha(
            numeroSenha,
            "Prioritária".equals(tipo) ? Senha.TipoSenha.Prioritaria : Senha.TipoSenha.Normal,
            null,
            null
        );

        filaSelecionada.adicionarSenha(senha);
        senhaDAO.salvarSenha(senha.getNumeroSenha(), tipo);
        atualizarTela();

        int pessoasNaFrente = contarPessoasNaFrente(filaSelecionada);
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
            senha.getNumeroSenha(), tipo, pessoasNaFrente
        );

        JOptionPane.showMessageDialog(this, mensagemTalao, "Imprimindo Senha...", JOptionPane.INFORMATION_MESSAGE);
    }

    private int contarPessoasNaFrente(Fila fila) {
        String resumo = fila.listarFilas();
        if (resumo == null || resumo.equals("fila vazia")) {
            return 0;
        }

        String[] senhas = resumo.split("\\n");
        return Math.max(0, senhas.length - 1);
    }
}