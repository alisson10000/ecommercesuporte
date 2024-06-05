package com.suporte.ecommercesuporte.controllers;

import com.suporte.ecommercesuporte.dtos.ProdutoDTO;

import com.suporte.ecommercesuporte.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<List<ProdutoDTO>> createProdutos(@RequestBody List<ProdutoDTO> produtoDTOs) {
        List<ProdutoDTO> novosProdutos = produtoService.create(produtoDTOs);
        return ResponseEntity.status(HttpStatus.CREATED).body(novosProdutos);
    }

    @PutMapping
    public ResponseEntity<List<ProdutoDTO>> updateProdutos(@RequestBody List<ProdutoDTO> produtoDTOs) {
        List<ProdutoDTO> produtosAtualizados = produtoService.update(produtoDTOs);
        return ResponseEntity.ok().body(produtosAtualizados);
    }

    // Método no controller para deletar um produto pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        // Chama o método delete do serviço, passando o ID do produto
        produtoService.delete(id);
        // Retorna uma resposta indicando que a exclusão foi bem-sucedida
        return ResponseEntity.ok().build();
    }

}
