package usuario;

public class InfoAdministradorPeca extends User {
    public InfoAdministradorPeca(String nome, String email, String telefone, String cpf, String senha) {
        super(nome, email, telefone, cpf, senha, UserType.ADMINISTRADOR_PECA);
    }
}
