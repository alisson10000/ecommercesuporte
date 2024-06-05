package com.suporte.ecommercesuporte.services;

import com.suporte.ecommercesuporte.dtos.UsuarioDTO;
import com.suporte.ecommercesuporte.models.Role;
import com.suporte.ecommercesuporte.models.Usuario;
import com.suporte.ecommercesuporte.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.Set;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class UsuarioService {

    @Qualifier("modelMapper")
    private final ModelMapper modelMapper;
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(ModelMapper modelMapper, UsuarioRepository usuarioRepository) {
        this.modelMapper = modelMapper;
        this.usuarioRepository = usuarioRepository;
    }

    // Método para converter entidade Usuario em DTO UsuarioDTO
    public UsuarioDTO converterParaDTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    // Método para converter DTO UsuarioDTO em entidade Usuario
    public Usuario converterParaEntidade(UsuarioDTO usuarioDTO) {
        return modelMapper.map(usuarioDTO, Usuario.class);
    }

    // Método para verificar se um usuário existe pelo ID
    public boolean existePorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        return usuarioOptional.isPresent(); // Retorna true se o usuário existe, false caso contrário
    }

    // Método para salvar um novo usuário
    public UsuarioDTO criarUsuarioDTO(UsuarioDTO usuarioDTO) {
        Usuario usuario = converterParaEntidade(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return converterParaDTO(usuario);
    }

    // Método para atualizar um usuário existente
    public UsuarioDTO atualizar(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = buscarPorId(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setRoles(usuarioDTO.getRoles());
        usuario = usuarioRepository.save(usuario);
        return converterParaDTO(usuario);
    }
    
    
 // outros métodos e injeções de dependência...

    // Método para atualizar as roles do usuário
    public void atualizarRoles(Long usuarioId, Set<Role.Type> novasRoles) {
        // Verifique se o usuário existe
        Usuario usuario = buscarPorId(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

        // Atualize as roles do usuário
        usuario.setRoles(novasRoles);

        // Salve as alterações no banco de dados
        usuarioRepository.save(usuario);
    }
    
    
    
    
    
    
    
    
    
    

    // Método para buscar um usuário por ID e retornar DTO
    public UsuarioDTO buscarPorIdDTO(Long id) {
        Usuario usuario = buscarPorId(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
        return converterParaDTO(usuario);
    }

    // Método para buscar um usuário por ID
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Método para remover um usuário por ID
    public void deletar(Long id) {
        Usuario usuario = buscarPorId(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
        usuarioRepository.delete(usuario);
    }

    // Método para listar todos os usuários
    public List<UsuarioDTO> listarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                       .map(this::converterParaDTO)
                       .collect(Collectors.toList());
    }
}
