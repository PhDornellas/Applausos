package usuario;

public class InfoMembroElenco extends User{
    public InfoMembroElenco(String nome, String email, String telefone, String cpf, String senha){
        super(nome, email, telefone, cpf, senha, UserType.MEMBRO_ELENCO);
    }
    
}
