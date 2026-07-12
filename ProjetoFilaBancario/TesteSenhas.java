import javax.swing.SwingUtilities;

import src.view.GuiTV;
import src.view.PainelAgente;
import src.view.TotemImpressao;

public class TesteSenhas {
    public static void main(String[] args) {
        // 1. Aqui continua a lógica de inicialização do seu sistema (ex: suas filas)
        System.out.println("Inicializando o Sistema de Gestão Bancário...");
        
        // 2. Executa a Interface Gráfica de forma segura na thread do Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Instancia e exibe a Televisão (Painel)
                GuiTV tv = new GuiTV();
                tv.setVisible(true);
                // Instancia e exibe o Totem de Impressão
                TotemImpressao totem = new TotemImpressao();
                totem.setVisible(true);

                // 3. Tela do Agente (Fica no computador interna do Caixa)
                PainelAgente agente = new PainelAgente();
                agente.setVisible(true);
                agente.setLocation(500, 250);
                
                // Opcional: Se quiser que as janelas não fiquem exatamente uma em cima da outra na tela
                //totem.setLocation(tv.getX() - 250, tv.getY()); 
            }
        });
    }
}