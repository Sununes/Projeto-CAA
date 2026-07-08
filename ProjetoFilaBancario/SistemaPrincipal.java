import src.model.Fila;
import src.model.Senha;

public class SistemaPrincipal {

    private Fila fila;
    private int contadorNormal;
    private int contadorPrioritaria;

    public SistemaPrincipal() {
        this.fila = new Fila();
        this.contadorNormal = 1;
        this.contadorPrioritaria = 1;
    }

    public Senha gerarSenha(Senha.TipoSenha tipoSenha, Senha.TipoServico tipoServico,Senha.FormaRetiradaSenha formaRetiradaSenha) {

        String numeroSenha;

        if (tipoSenha == Senha.TipoSenha.Prioritaria) {
            numeroSenha = "P" + contadorPrioritaria;
            contadorPrioritaria++;
        } else {
            numeroSenha = "N" + contadorNormal;
            contadorNormal++;
        }

        Senha senha = new Senha(numeroSenha, tipoSenha, tipoServico, formaRetiradaSenha);

        fila.adicionarSenha(senha);

        return senha;
    }

    public Senha chamarProximo() {
        return fila.atenderProximo();
    }

    public int verPosicao(String numeroSenha) {
        return fila.verPosicao(numeroSenha);
    }
    public int getTotalSenhas() {
    return fila.getTamanho();
}

    public String listarSenhas() {
        return fila.listarFilas();
    }

    public boolean cancelarSenha(String numeroSenha) {
        return fila.cancelarSenha(numeroSenha);
    }

    public boolean filaVazia() {
        return fila.filaVazia();
    }
}