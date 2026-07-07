import java.time.LocalDateTime;

public class Senha {
    public enum  TipoSenha { // Normal, Prioritária
        Normal,
        Prioritaria
    }
    public enum TipoServico {
        Atendimento_Geral,
        Abertura_Conta,
        Pedido_Credito,
        Outros_Servicos
    }

    public enum FormaRetiradaSenha{
        Presencial,
        Online
    }

    public enum EstadoSenha {
        Em_Espera,
        Em_Atendimento,
        Atendida,
        Cancelada,
        Ausente
    }
    private String numeroSenha; 
    private TipoSenha tipoSenha;
    private TipoServico tipoServico; //Atendimento geral, Abertura de conta, pedido credito, Outros Assuntos;
    private FormaRetiradaSenha formaRetiradaSenha; // Presencial, Online
    private LocalDateTime  dataHoraCriacao; // Hora em que a senha foi criada
    private LocalDateTime  dataHoraAtendimento; // Hora em que a senha foi atendida
    private int tentativasDeChamadas; // Número de tentativas de chamadas para o atendiemnto
    private EstadoSenha estado; // Em espera, Em atendimento, Cancelada;

    public Senha (String numeroSenha, TipoSenha tipoSenha, TipoServico tipoServico, FormaRetiradaSenha formaRetiradaSenha) {
        this.numeroSenha = numeroSenha;
        this.tipoSenha = tipoSenha;
        this.tipoServico = tipoServico;
        this.formaRetiradaSenha = formaRetiradaSenha;
        this.dataHoraCriacao =LocalDateTime.now ();
        this.dataHoraAtendimento = null;
        this.tentativasDeChamadas = 0; 
        this.estado = EstadoSenha.Em_Espera; 
    }

    public String getNumeroSenha() {
        return numeroSenha;
    }

    public TipoSenha getTipoSenha() {
        return tipoSenha;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public FormaRetiradaSenha getFormaRetiradaSenha() {
        return formaRetiradaSenha;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public LocalDateTime getDataHoraAtendimento() {
        return dataHoraAtendimento;
    }

    public int getTentativasDeChamadas() {
        return tentativasDeChamadas;
    }

    public EstadoSenha getEstado(){
        return estado;
    }

    public void setNumeroSenha(String numeroSenha) {
        this.numeroSenha = numeroSenha;
    }
    public void SetTipoSenha(TipoSenha tipoSenha) {
        this.tipoSenha = tipoSenha;
    }
    public void SetTipoServico( TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }
    public void SetFormaRetiradaSenha(FormaRetiradaSenha formaRetiradaSenha) {
        this.formaRetiradaSenha = formaRetiradaSenha;
    }

    public void aumentarTentativasChamadas() {
        this.tentativasDeChamadas = tentativasDeChamadas + 1;
    }

    public void marcarEmAtendimento() {
        this.estado = EstadoSenha.Em_Atendimento;
        this.dataHoraAtendimento = LocalDateTime.now();

    }
    public void marcarComoAtendida() {
        this.estado = EstadoSenha.Atendida;
    }
    public void marcarAusencia() {
        if (tentativasDeChamadas >= 3) {
            this.estado = EstadoSenha.Ausente;
        }
    }

     public void marcarCancelado() {
        this.estado = EstadoSenha.Cancelada;
    }
    


@Override
public String toString () {
    return "numeroSenha:" +numeroSenha+
           "\n tipoSenha:" +tipoSenha+
           "\n tipoServico:" +tipoServico+
           "\n formaRetiradaSenha:" +formaRetiradaSenha+
           "\n dataHoraCriacao:" +dataHoraCriacao+
           "\n dataHoraAtendimento:" +dataHoraAtendimento+
           "\n tentativasDeChamadas:" +tentativasDeChamadas+
           "\n estado:" +estado;

}
}