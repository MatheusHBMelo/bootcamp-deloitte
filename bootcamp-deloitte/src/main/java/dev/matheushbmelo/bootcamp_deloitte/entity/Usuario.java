package dev.matheushbmelo.bootcamp_deloitte.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_USERS")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nome;
    @Column(unique = true)
    public String email;
    @Column(unique = true)
    public String cpf;
    @Column(unique = true)
    public String telefone;

    @Override
    public String toString() {
        return "Nome: '" + nome + '\'' +
                ", Email: '" + email + '\'' +
                ", Cpf: '" + cpf + '\'' +
                ", Telefone: '" + telefone + '\'';
    }
}
