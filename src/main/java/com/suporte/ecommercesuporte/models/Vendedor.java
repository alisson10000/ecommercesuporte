package com.suporte.ecommercesuporte.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "vendedores")
public class Vendedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vendedor")
    private Long id;

    @NotBlank(message = "O nome do vendedor é obrigatório.")
    @Size(min = 3, max = 50, message = "O nome do vendedor deve ter entre 3 e 50 caracteres.")
    @Column(name = "nome_vendedor", nullable = false)
    private String nome;

    @NotBlank(message = "O email do vendedor é obrigatório.")
    @Email(message = "O email deve ser válido.")
    @Column(name = "email_vendedor", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "A senha do vendedor é obrigatória.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
    @Column(name = "senha_vendedor", nullable = false)
    private String senha;

    public Vendedor() {}

    public Vendedor(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
