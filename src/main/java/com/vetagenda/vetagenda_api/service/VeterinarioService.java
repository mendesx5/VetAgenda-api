package com.vetagenda.vetagenda_api.service;

import com.vetagenda.vetagenda_api.domain.dto.request.VeterinarioRequest;
import com.vetagenda.vetagenda_api.domain.dto.response.VeterinarioResponse;
import com.vetagenda.vetagenda_api.domain.entity.VeterinarioEntity;
import com.vetagenda.vetagenda_api.repository.VeterinarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    // Cadastrar veterinários:
    @Transactional
    public VeterinarioResponse cadastrarVeterinario(VeterinarioRequest veterinarioRequest) {
        VeterinarioEntity veterinario = new VeterinarioEntity();
        veterinario.setName(veterinarioRequest.getName());
        veterinario.setCrmv(veterinarioRequest.getCrmv());

        VeterinarioEntity veterinarioSalvo = veterinarioRepository.save(veterinario);

        VeterinarioResponse response = new VeterinarioResponse();
        response.setId((veterinarioSalvo.getId()));
        response.setName(veterinarioSalvo.getName());
        response.setCrmv(veterinarioSalvo.getCrmv());

        return response;
    }

    // Atualizar:
    @Transactional
    public VeterinarioResponse atualizarVeterinario(Long id, VeterinarioRequest veterinarioRequest) {
        VeterinarioEntity veterinario = veterinarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));

        veterinario.setName(veterinarioRequest.getName());
        veterinario.setCrmv(veterinarioRequest.getCrmv());

        VeterinarioEntity veterinarioSalvo = veterinarioRepository.save(veterinario);

        return new VeterinarioResponse(veterinarioSalvo);
    }

    // Remover:
    @Transactional
    public void deletarVeterinario(Long id) {
        VeterinarioEntity veterinario = veterinarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));

        veterinarioRepository.delete(veterinario);
    }

    // Buscar por ID:
    public VeterinarioResponse buscarVeterinarioPorId(Long id) {

        // Caso o id não corresponda a um veterinário cadastrado
        VeterinarioEntity veterinario = veterinarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));

        VeterinarioResponse response = new VeterinarioResponse();
        response.setId(veterinario.getId());
        response.setName(veterinario.getName());
        response.setCrmv(veterinario.getCrmv());
        return response;
    }

    // Listar todos:
    public List<VeterinarioResponse> listarTodosVeterinarios() {
        return veterinarioRepository.findAll().stream()
                .map(veterinarioEntity -> {
                    VeterinarioResponse response = new VeterinarioResponse();
                    response.setId((veterinarioEntity.getId()));
                    response.setName(veterinarioEntity.getName());
                    response.setCrmv(veterinarioEntity.getCrmv());
                    return response;
                })
                .collect(Collectors.toList());
    }

    // Listar agendamentos do veterinário

}
