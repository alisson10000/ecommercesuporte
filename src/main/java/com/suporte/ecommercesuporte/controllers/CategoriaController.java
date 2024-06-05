package com.suporte.ecommercesuporte.controllers;

import com.suporte.ecommercesuporte.dtos.CategoriaDTO;
import com.suporte.ecommercesuporte.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> getAllCategorias() {
        List<CategoriaDTO> categorias = categoriaService.findAll();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable Long id) {
        CategoriaDTO categoria = categoriaService.findById(id);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<CategoriaDTO>> createCategorias(@RequestBody List<CategoriaDTO> categoriaDTOs) {
        List<CategoriaDTO> categoriasCriadas = categoriaService.create(categoriaDTOs);
        return new ResponseEntity<>(categoriasCriadas, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<List<CategoriaDTO>> updateCategorias(@RequestBody List<CategoriaDTO> categoriaDTOs) {
        List<CategoriaDTO> categoriasAtualizadas = categoriaService.update(categoriaDTOs);
        return ResponseEntity.ok().body(categoriasAtualizadas);
    }


 // Método no controller para deletar uma categoria pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoria(@PathVariable Long id) {
        // Chama o método delete do serviço diretamente, passando o ID
        categoriaService.delete(id);
        return ResponseEntity.ok("Categoria deletada com sucesso");
    }


}

