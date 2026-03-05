public class Usuario {
    public String nome;
    public String email;
    public String senha;

    public Usuario() {
    }

    public Usuario(String nome,String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Nome: '" + nome + '\'' +
                ", Email: '" + email + '\'' +
                ", Senha: '" + senha + '\'';
    }
}
