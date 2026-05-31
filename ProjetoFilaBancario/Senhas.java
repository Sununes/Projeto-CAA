public class Senhas {
    private String numeroSenha; 
    private String tipoSenha; // Normal, Prioritária
    private String tipoServico; //Atendimento geral, Abertura de conta, pedido credito, Outros Assuntos;
    private String formaAtendimento; // Presencial, Online
    private String data; 
    private String horaCriacao; // Hora em que a senha foi criada
    private String horaAtendimento; // Hora em que a senha foi atendida
    private int tentativasDeChamadas; // Número de tentativas de chamadas para o atendiemnto
    private String estado; // Em espera, Em atendimento, Cancelada;

    public Senhas (String numeroSenha, String tipoSenha, String TipoServico, String formaAtendimento,String data, String horaCriacao, String horaAtendimento, int tentativasDeChamadas, String estado) {
        this.numeroSenha = numeroSenha;
        this.tipoSenha = tipoSenha;
        this.tipoServico = TipoServico;
        this.formaAtendimento = formaAtendimento;
        this.data = data;
        this.horaCriacao = horaCriacao;
        this.horaAtendimento = horaAtendimento;
        this.tentativasDeChamadas = 0; 
        this.estado = "Em espera"; 
    }

    public String getNumeroSenha() {
        return numeroSenha;
    }

    public String getTipoSenha() {
        return tipoSenha;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public String getFormaAtendimento() {
        return formaAtendimento;
    }
    public String getData() {
        return data;
    }

    public String getHoraCriacao() {
        return horaCriacao;
    }

    public String getHoraAtendimento() {
        return horaAtendimento;
    }

    public int getTentativasDeChamadas() {
        return tentativasDeChamadas;
    }

    public String getEstado() {
        return estado;
    }

    public void numeroSenha(int numeroSenha) {
        this.numeroSenha = numeroSenha + 1;
    }
    public void tipoSenha(String tipoSenha) {
        this.tipoSenha = tipoSenha;
    }
    public void tipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }
    public void formaAtendimento(String formaAtendimento) {
        this.formaAtendimento = formaAtendimento;
    }
     public void formaData(String data) {
        this.data = data;
    }
    public void HoraCriacao(String horaCriacao) {
        this.horaCriacao = horaCriacao;
    }
    public void horaAtendimento( String horaAtendimento) {
        this.horaAtendimento = horaAtendimento;
    }


    public void aumentarTentativasChamadas() {
        this.tentativasDeChamadas = tentativasDeChamadas + 1;
    }
    
    public void MarcarEmAtendimento() {
        this.estado = "Em atendimento";
    }
    public void MarcarComoAtendida() {
        this.estado = "Atendida";
    }
    public void verificarAusencia() {
        if (tentativasDeChamadas >= 3) {
            this.estado = "Cancelada";
        }
    }

     public void cancelarSenha() {
        this.estado = "Cancelada";
    }
    int verificarTempoEspera;
    public void verificarTempoEspera() {
     verificarTempoEspera = horaAtendimento - horaCriacao;
    if (verificarTempoEspera > 30) {
        this.estado = "Cancelada";

    } 
}

@Override
public String toString () {
    return "numeroSenha:" +numeroSenha+
           "/n tipoSenha:" +tipoSenha+
           "/n tipoServico:" +tipoServico+
           "/n formaAtendimento:" +formaAtendimento+
           "/n data:" +data+
           "/n horaCriacao:" +horaCriacao+
           "/n horaAtendimento:" +horaAtendimento+
           "/n tentativasDeChamadas:" +tentativasDeChamadas+
           "/n estado:" +estado;

}
}



