package com.suporte.ecommercesuporte.dtos;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CategoriaDTO {

    private Long id;

    @NotBlank(message = "O nome da categoria é obrigatório.")
    @Size(min = 3, max = 50, message = "O nome da categoria deve ter entre 3 e 50 caracteres.")
    @Pattern(regexp = "[a-zA-ZÀ-ÿ ]*", message = "O nome da categoria deve conter apenas letras e espaços.")
    @Column(name = "nome_categoria", nullable = false)
    private String nome;

    // Construtor padrão
    public CategoriaDTO() {
    }

    // Construtor completo
    public CategoriaDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Método getter para o ID da categoria
    public Long getId() {
        return id;
    }

    // Método setter para o ID da categoria
    public void setId(Long id) {
        this.id = id;
    }

    // Método getter para o nome da categoria
    public String getNome() {
        return nome;
    }

    // Método setter para o nome da categoria
    public void setNome(String nome) {
        this.nome = nome;
    }
}
