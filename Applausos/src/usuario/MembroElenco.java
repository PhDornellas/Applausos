package usuario;

public class MembroElenco extends User{
    public MembroElenco(String nome, String email, String telefone, String cpf, String senha){
        super(nome, email, telefone, cpf, senha, UserType.MEMBRO_ELENCO);
    }
    
}
