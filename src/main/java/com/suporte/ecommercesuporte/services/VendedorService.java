package com.suporte.ecommercesuporte.services;

import com.suporte.ecommercesuporte.dtos.VendedorDTO;
import com.suporte.ecommercesuporte.models.Vendedor;
import com.suporte.ecommercesuporte.repositories.VendedorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendedorService {

    private final VendedorRepository vendedorRepository;
    private final ModelMapper modelMapper;

    public VendedorService(VendedorRepository vendedorRepository, ModelMapper modelMapper) {
        this.vendedorRepository = vendedorRepository;
        this.modelMapper = modelMapper;
    }

    public VendedorDTO criarVendedor(VendedorDTO vendedorDTO) {
        Vendedor vendedor = modelMapper.map(vendedorDTO, Vendedor.class);
        vendedor = vendedorRepository.save(vendedor);
        return modelMapper.map(vendedor, VendedorDTO.class);
    }

    public VendedorDTO atualizarVendedor(Long id, VendedorDTO vendedorDTO) {
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vendedor não encontrado com o id: " + id));

        vendedor.setNome(vendedorDTO.getNome());
        vendedor.setEmail(vendedorDTO.getEmail());
        vendedor.setSenha(vendedorDTO.getSenha());

        vendedor = vendedorRepository.save(vendedor);

        return modelMapper.map(vendedor, VendedorDTO.class);
    }

    public List<VendedorDTO> listarTodosVendedores() {
        List<Vendedor> vendedores = vendedorRepository.findAll();
        return vendedores.stream()
                .map(vendedor -> modelMapper.map(vendedor, VendedorDTO.class))
                .collect(Collectors.toList());
    }

    public VendedorDTO buscarVendedorPorId(Long id) {
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vendedor não encontrado com o id: " + id));
        return modelMapper.map(vendedor, VendedorDTO.class);
    }

    public void deletarVendedor(Long id) {
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vendedor não encontrado com o id: " + id));
        vendedorRepository.deleteById(id);
    }

}
