package dev.matheushbmelo.bootcamp_deloitte.historico.v3;

// Primeira versão da entidade de usuario
// A partir da migração para Spring Boot foram adicionados novos atributos e anotações do Spring Data/Lombok
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
