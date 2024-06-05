package com.suporte.ecommercesuporte.models;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "produtos",
       indexes = @Index(name = "idx_categoria_id_categoria", columnList = "categoria_id_categoria"))
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_produto", nullable = false)
    private Long id;

    @NotNull(message = "O nome do produto é obrigatório")
    @Size(max = 100, message = "O nome do produto não pode exceder 100 caracteres")
    @Pattern(regexp = "^[\\p{L} \\p{P}]*$", message = "O nome só pode conter letras e espaços")
    @Column(name = "nome_produto", nullable = false)
    private String nome;

    @NotNull(message = "A descrição do produto é obrigatória")
    @Size(max = 500, message = "A descrição do produto não pode exceder 500 caracteres")
    @Column(name = "descricao_produto", nullable = false)
    private String descricao;

    @NotNull(message = "O preço do produto é obrigatório")
    @Min(value = 0, message = "O preço não pode ser negativo")
    @Max(value = 1000000, message = "O preço não pode exceder 1.000.000")
    @Column(name = "preco_produto", nullable = false)
    private double preco;

    @NotNull(message = "O estoque do produto é obrigatório")
    @Min(value = 0, message = "O estoque não pode ser negativo")
    @Max(value = 1000000, message = "O estoque não pode exceder 1.000.000")
    @Column(name = "estoque_produto", nullable = false)
    private int estoque;

    @ManyToOne
    @JoinColumn(name = "categoria_id_categoria")
    private Categoria categoria;

    public Produto() {}

    public Produto(String nome, String descricao, double preco, int estoque) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
