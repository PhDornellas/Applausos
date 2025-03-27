package usuarios;

public class AdministradorPeca extends User {
    public AdministradorPeca(String nome, String email, String telefone, String cpf, String senha) {
        super(nome, email, telefone, cpf, senha, UserType.ADMINISTRADOR_PECA);
    }
}
