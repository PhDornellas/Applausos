package usuario;

public class InfoAdministradorSite extends User {
    public InfoAdministradorSite(String nome, String email, String telefone, String cpf, String senha) {
        super(nome, email, telefone, cpf, senha, UserType.ADMINISTRADOR_SITE);
    }
}