package com.suporte.ecommercesuporte.services;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import com.suporte.ecommercesuporte.dtos.ProdutoDTO;
import com.suporte.ecommercesuporte.models.Produto;
import com.suporte.ecommercesuporte.repositories.CategoriaRepository;
import com.suporte.ecommercesuporte.repositories.ProdutoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ModelMapper modelMapper;
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

  
    public ProdutoService(ModelMapper modelMapper, ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.modelMapper = modelMapper;
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public ProdutoDTO findById(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
        return convertToDTO(produto);
    }

    public List<ProdutoDTO> findAll() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public List<ProdutoDTO> create(List<ProdutoDTO> produtoDTOs) {
        List<Produto> produtos = produtoDTOs.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        List<Produto> produtosSalvos = produtoRepository.saveAll(produtos);
        return produtosSalvos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    
    public List<ProdutoDTO> update(List<ProdutoDTO> produtoDTOs) {
        List<ProdutoDTO> produtosAtualizados = produtoDTOs.stream()
                .map(this::updateProduto)
                .collect(Collectors.toList());
        return produtosAtualizados;
    }
    
    private ProdutoDTO updateProduto(ProdutoDTO produtoDTO) {
        Long id = produtoDTO.getId();
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));

        modelMapper.map(produtoDTO, produto);
        produto.setCategoria(categoriaRepository.findById(produtoDTO.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada")));

        Produto produtoAtualizado = produtoRepository.save(produto);
        return convertToDTO(produtoAtualizado);
    }
    
    
    
    public void delete(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
        produtoRepository.delete(produto);
    }

    private ProdutoDTO convertToDTO(Produto produto) {
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    private Produto convertToEntity(ProdutoDTO produtoDTO) {
        return modelMapper.map(produtoDTO, Produto.class);
    }
}
