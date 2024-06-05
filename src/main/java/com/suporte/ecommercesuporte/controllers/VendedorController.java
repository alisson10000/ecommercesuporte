package com.suporte.ecommercesuporte.controllers;

import com.suporte.ecommercesuporte.dtos.VendedorDTO;
import com.suporte.ecommercesuporte.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @PostMapping
    public ResponseEntity<VendedorDTO> criarVendedor(@RequestBody VendedorDTO vendedorDTO) {
        VendedorDTO novoVendedor = vendedorService.criarVendedor(vendedorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVendedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendedorDTO> atualizarVendedor(@PathVariable Long id, @RequestBody VendedorDTO vendedorDTO) {
        VendedorDTO vendedorAtualizado = vendedorService.atualizarVendedor(id, vendedorDTO);
        return ResponseEntity.ok(vendedorAtualizado);
    }

    @GetMapping
    public ResponseEntity<List<VendedorDTO>> listarTodosVendedores() {
        List<VendedorDTO> vendedores = vendedorService.listarTodosVendedores();
        return ResponseEntity.ok(vendedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendedorDTO> buscarVendedorPorId(@PathVariable Long id) {
        VendedorDTO vendedor = vendedorService.buscarVendedorPorId(id);
        return ResponseEntity.ok(vendedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarVendedor(@PathVariable Long id) {
        vendedorService.deletarVendedor(id);
        return ResponseEntity.ok("Vendedor deletado com sucesso");
    }

}
