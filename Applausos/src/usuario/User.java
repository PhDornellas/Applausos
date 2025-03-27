package usuario;

public abstract class User {
    protected String nome;
    protected String email;
    protected String telefone;
    protected String cpf;
    protected String senha;
    protected UserType tipo;

    public User(String nome, String email, String telefone, String cpf, String senha, UserType tipo) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.senha = senha;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public UserType getTipo() {
        return tipo;
    }
}
