package src.controller;

public class AtendimentoController {
    // Código para a classe AtendimentoController.java (Camada Controller)

public String chamarProximaSenha(int guiche) {
    String senhaChamada = null;
    
    // 1. REGRA DE NEGÓCIO: Todos os prioritários primeiro!
    // O algoritmo verifica a fila prioritária (Tempo constante O(1))
    if (!filaPrioritaria.isEmpty()) {
        senhaChamada = filaPrioritaria.dequeue(); // Retira o 1º prioritário
    } 
    // 2. Só entra aqui se a fila prioritária estiver VAZIA
    else if (!filaNormal.isEmpty()) {
        senhaChamada = filaNormal.dequeue();      // Retira o 1º normal
    }
    
    // Se conseguiu tirar alguém de alguma das filas
    if (senhaChamada != null) {
        
        // --- Integração com a Base de Dados (DAO) ---
        // senhaDAO.atualizarAtendimento(senhaChamada, guiche);
        
        // --- Integração com a Televisão (View TV) ---
        String proximaDaFila = descobrirProximaDaFila();
        // viewTV.atualizarPainel(senhaChamada, guiche, proximaDaFila);
        
        System.out.println("Senha " + senhaChamada + " chamada para o Guiché " + guiche);
    }
    
    // Devolve a senha para o ecrã do Agente Bancário atualizar
    return senhaChamada;
}

// Método auxiliar para a TV saber quem vem a seguir (usando peek, sem remover da fila)
private String descobrirProximaDaFila() {
    if (!filaPrioritaria.isEmpty()) {
        return filaPrioritaria.peek(); // Espreita a prioritária primeiro
    } else if (!filaNormal.isEmpty()) {
        return filaNormal.peek();      // Espreita a normal se não houver prioritários
    }
    return "Nenhuma";
}
}
