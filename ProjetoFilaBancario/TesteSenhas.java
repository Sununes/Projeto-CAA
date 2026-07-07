public class TesteSenhas {
    public static void main(String[] args) {

        Senha s1 = new Senha("P001", Senha.TipoSenha.Prioritaria, Senha.TipoServico.Abertura_Conta,Senha.FormaRetiradaSenha.Presencial);

        Senha s2 = new Senha("N001", Senha.TipoSenha.Normal, Senha.TipoServico.Pedido_Credito, Senha.FormaRetiradaSenha.Online);

        Senha s3 = new Senha("N002", Senha.TipoSenha.Normal,  Senha.TipoServico.Atendimento_Geral,Senha.FormaRetiradaSenha.Presencial);

        s1.marcarEmAtendimento();
        s2.marcarComoAtendida();
        s3.marcarAusencia();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}