package usuarios;

public class AdministradorSite extends User {
    public AdministradorSite(String nome, String email, String telefone, String cpf, String senha) {
        super(nome, email, telefone, cpf, senha, UserType.ADMINISTRADOR_SITE);
    }
}
