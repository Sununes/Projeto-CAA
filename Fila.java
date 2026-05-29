public class Fila {
    private No inicio;
    private No fim;
    private int tamanho;
    public Fila() {
        inicio = null ;
        fim = null;
        tamanho = 0;

        // Adicionar senha
    }
    public boolean filaVazia(){
         return inicio == null;
    }
    public void adicionarSenha(Senha senha){
        No novop = new No(senha);
        if(filaVazia()){
            inicio = novo;
            fim = novo;
        }else{
            fim.prox = novo;
            fim = novo;
        }
         tamanho ++;
        }
        //Atender Proximo
        public Senha atenderProximo(){
            if(filaVazia()){
                return null;
            }
            Senha senha = inicio.senha;
            senha.marcarEmAtendimento();
            inicio = inicio.prox;

            if(inicio == null){
                fim = null;
            }
            tamanho --;
            return senha;
        
        tamanho ++;
        }
        public int verPosiçao(String numeroSenha){
            int posicao = 1;
            No auxiliar = inicio;

            while(auxiliar !=  null){
                if(auxiliar.senha.getNumerosenha().equalsIgnoreCase(numeroSenha)){
                    return posicao;
                }
                posicao ++;
                auxiliar = auxiliar.prox;
            }
            return -1;
        }
        //listar filas
        public String listarFilas(){
            if(filaVazia()){
                return "fila vazia";

            }
            String texto = "";
            No auxiliar = inicio;

            while(auxiliar != null){
                texto += auxiliar.senha + "\n";
                auxiliar = auxiliar.prox;
            }
            return texto;
        }
        //Remover Senha
        public  Senha remover(){
            if(filaVazia()){
                return null;
            }
            Senha senha = inicio.senha;
            inicio = inicio.senha;
            if(inicio == null){
                fim = null;
        }
        return senha;
    }
    public boolean cancelarSenha(String numeroSenha){
           if(filaVazia()){
            return false;
           }
    }

}
