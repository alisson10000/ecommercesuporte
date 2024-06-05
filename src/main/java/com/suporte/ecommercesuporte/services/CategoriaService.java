package com.suporte.ecommercesuporte.services;

import com.suporte.ecommercesuporte.dtos.CategoriaDTO;
import com.suporte.ecommercesuporte.models.Categoria;
import com.suporte.ecommercesuporte.repositories.CategoriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final ModelMapper modelMapper;
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(ModelMapper modelMapper, CategoriaRepository categoriaRepository) {
        this.modelMapper = modelMapper;
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaDTO> findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return convertToDTO(categoria);
    }

    public List<CategoriaDTO> create(List<CategoriaDTO> categoriaDTOs) {
        List<Categoria> categorias = categoriaDTOs.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        List<Categoria> categoriasSalvas = categoriaRepository.saveAll(categorias);
        return categoriasSalvas.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CategoriaDTO> update(List<CategoriaDTO> categoriaDTOs) {
        List<CategoriaDTO> categoriasAtualizadas = categoriaDTOs.stream()
                .map(this::updateCategoria)
                .collect(Collectors.toList());
        return categoriasAtualizadas;
    }

    private CategoriaDTO updateCategoria(CategoriaDTO categoriaDTO) {
        Long id = categoriaDTO.getId();
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        categoria.setNome(categoriaDTO.getNome());
        Categoria categoriaAtualizada = categoriaRepository.save(categoria);
        return convertToDTO(categoriaAtualizada);
    }

 // Método no service para deletar uma categoria pelo ID
    public void delete(Long id) {
        // Busca a categoria pelo ID
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        // Exclui a categoria
        categoriaRepository.delete(categoria);
    }

    private CategoriaDTO convertToDTO(Categoria categoria) {
        return modelMapper.map(categoria, CategoriaDTO.class);
    }

    private Categoria convertToEntity(CategoriaDTO categoriaDTO) {
        return modelMapper.map(categoriaDTO, Categoria.class);
    }
}
