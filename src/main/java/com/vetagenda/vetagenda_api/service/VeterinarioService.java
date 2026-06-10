package com.vetagenda.vetagenda_api.service;

import com.vetagenda.vetagenda_api.domain.dto.request.VeterinarioRequest;
import com.vetagenda.vetagenda_api.domain.dto.response.AgendamentoResponse;
import com.vetagenda.vetagenda_api.domain.dto.response.VeterinarioResponse;
import com.vetagenda.vetagenda_api.domain.entity.AgendamentoEntity;
import com.vetagenda.vetagenda_api.domain.entity.UsuarioEntity;
import com.vetagenda.vetagenda_api.domain.entity.VeterinarioEntity;
import com.vetagenda.vetagenda_api.exception.ResourceNotFoundException;
import com.vetagenda.vetagenda_api.repository.AgendamentoRepository;
import com.vetagenda.vetagenda_api.repository.UsuarioRepository;
import com.vetagenda.vetagenda_api.repository.VeterinarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;
    private final AgendamentoRepository agendamentoRepository;
    private final UsuarioRepository usuarioRepository;

    // Cadastrar veterinários:
    @Transactional
    public VeterinarioResponse cadastrarVeterinario(VeterinarioRequest veterinarioRequest) {
        VeterinarioEntity veterinario = new VeterinarioEntity();
        veterinario.setName(veterinarioRequest.getName());
        veterinario.setCrmv(veterinarioRequest.getCrmv());
        veterinario.setEspecialidade(veterinarioRequest.getEspecialidade());

        if (veterinarioRequest.getUsuarioId() != null) {
            UsuarioEntity usuario = usuarioRepository.findById(veterinarioRequest.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Conta de usuário informada não existe."));
            veterinario.setUsuario(usuario);
        }

        VeterinarioEntity veterinarioSalvo = veterinarioRepository.save(veterinario);

        VeterinarioResponse response = new VeterinarioResponse();
        response.setId((veterinarioSalvo.getId()));
        response.setName(veterinarioSalvo.getName());
        response.setCrmv(veterinarioSalvo.getCrmv());
        response.setEspecialidade(veterinarioSalvo.getEspecialidade());

        return response;
    }

    // Atualizar:
    @Transactional
    public VeterinarioResponse atualizarVeterinario(Long id, VeterinarioRequest veterinarioRequest) {
        VeterinarioEntity veterinario = veterinarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));

        veterinario.setName(veterinarioRequest.getName());
        veterinario.setCrmv(veterinarioRequest.getCrmv());
        veterinario.setEspecialidade(veterinarioRequest.getEspecialidade());

        VeterinarioEntity veterinarioSalvo = veterinarioRepository.save(veterinario);

        return new VeterinarioResponse(veterinarioSalvo);
    }

    // Remover:
    @Transactional
    public void deletarVeterinario(Long id) {
        VeterinarioEntity veterinario = veterinarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));

        veterinarioRepository.delete(veterinario);
    }

    // Buscar por ID:
    public VeterinarioResponse buscarVeterinarioPorId(Long id) {

        // Caso o id não corresponda a um veterinário cadastrado
        VeterinarioEntity veterinario = veterinarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));

        VeterinarioResponse response = new VeterinarioResponse();
        response.setId(veterinario.getId());
        response.setName(veterinario.getName());
        response.setCrmv(veterinario.getCrmv());
        response.setEspecialidade(veterinario.getEspecialidade());
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
                    response.setEspecialidade(veterinarioEntity.getEspecialidade());
                    return response;
                })
                .collect(Collectors.toList());
    }

    // Listar agendamentos do veterinário
    public List<AgendamentoResponse> agendamentosPorVeterinario(Long id) {
        veterinarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));

        List<AgendamentoEntity> agendamentos = agendamentoRepository.findByVeterinarioId(id);

        return agendamentos.stream()
                .map(agendamentoEntity ->  {
                    AgendamentoResponse response = new AgendamentoResponse();
                    response.setId(agendamentoEntity.getId());
                    response.setNomeAnimal(agendamentoEntity.getAnimal().getName());
                    response.setNomeVeterinario(agendamentoEntity.getVeterinario().getName());
                    response.setStatus(agendamentoEntity.getStatus());
                    response.setDataHora(agendamentoEntity.getDataHora());
                    return response;
                })
                .collect(Collectors.toList());
    }
}
