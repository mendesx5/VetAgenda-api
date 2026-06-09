package com.vetagenda.vetagenda_api.service;

import com.vetagenda.vetagenda_api.domain.dto.request.UsuarioRequest;
import com.vetagenda.vetagenda_api.domain.dto.response.UsuarioResponse;
import com.vetagenda.vetagenda_api.domain.entity.UsuarioEntity;
import com.vetagenda.vetagenda_api.exception.ResourceNotFoundException;
import com.vetagenda.vetagenda_api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // Listar todos os funcionários e suas informações
    public List<UsuarioResponse> listarTodosFuncionarios() {
        return usuarioRepository.findAll().stream()
                .map(usuarioEntity -> {
                    UsuarioResponse response = new UsuarioResponse();
                    response.setId((usuarioEntity.getId()));
                    response.setLogin(usuarioEntity.getLogin());
                    response.setAtivo(usuarioEntity.getAtivo());
                    response.setRole(usuarioEntity.getRole());
                    return response;
                })
                .collect(Collectors.toList());
    }

    // Alternar status de conta dos funcionários para Ativo ou Desativado
    @Transactional
    public UsuarioResponse alternarStatusUsuario(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (usuario.getAtivo() == true) {
            usuario.setAtivo(false);
        } else {
            usuario.setAtivo(true);
        }
        UsuarioEntity usuarioAtualizado = usuarioRepository.save(usuario);

        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuarioAtualizado.getId());
        response.setLogin(usuarioAtualizado.getLogin());
        response.setAtivo(usuarioAtualizado.getAtivo());
        response.setRole(usuarioAtualizado.getRole());

        return response;
    }

    // Redefinição de senha dos funcionários
    @Transactional
    public UsuarioResponse redefinirSenha(Long id, UsuarioRequest usuarioRequest) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        usuario.setPassword(passwordEncoder.encode(usuarioRequest.getPassword()));

        UsuarioEntity usuarioSalvo = usuarioRepository.save(usuario);

        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuarioSalvo.getId());
        response.setLogin(usuarioSalvo.getLogin());
        response.setAtivo(usuarioSalvo.getAtivo());
        response.setRole(usuarioSalvo.getRole());

        return response;
    }
}
