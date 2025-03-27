package usuarios;

public class Cliente extends User {
    public Cliente(String nome, String email, String telefone, String cpf, String senha) {
        super(nome, email, telefone, cpf, senha, UserType.CLIENTE);
    }
}
