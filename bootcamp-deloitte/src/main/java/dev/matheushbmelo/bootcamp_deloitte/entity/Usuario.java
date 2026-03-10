package dev.matheushbmelo.bootcamp_deloitte.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_USERS")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nome;
    public String email;
    public String cpf;
    public String telefone;

    public Usuario() {
    }

    public Usuario(Long id, String nome,String email, String cpf,  String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Nome: '" + nome + '\'' +
                ", Email: '" + email + '\'' +
                ", Cpf: '" + cpf + '\'' +
                ", Telefone: '" + telefone + '\'';
    }
}
