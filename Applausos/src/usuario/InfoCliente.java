package usuario;

public class InfoCliente extends User {
    public InfoCliente(String nome, String email, String telefone, String cpf, String senha) {
        super(nome, email, telefone, cpf, senha, UserType.CLIENTE);
    }
}