package com.vetagenda.vetagenda_api.service;

import com.vetagenda.vetagenda_api.domain.dto.request.AgendamentoRequest;
import com.vetagenda.vetagenda_api.domain.dto.response.AgendamentoResponse;
import com.vetagenda.vetagenda_api.domain.entity.AgendamentoEntity;
import com.vetagenda.vetagenda_api.domain.entity.AnimalEntity;
import com.vetagenda.vetagenda_api.domain.entity.VeterinarioEntity;
import com.vetagenda.vetagenda_api.exception.ConflictException;
import com.vetagenda.vetagenda_api.exception.ResourceNotFoundException;
import com.vetagenda.vetagenda_api.repository.AgendamentoRepository;
import com.vetagenda.vetagenda_api.repository.AnimalRepository;
import com.vetagenda.vetagenda_api.repository.VeterinarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private AgendamentoRepository agendamentoRepository;
    private VeterinarioRepository veterinarioRepository;
    private AnimalRepository animalRepository;

    // EM DESENVOLVIMENTO!!!

    // Criar agendamento:
    @Transactional
    public AgendamentoResponse criarAgendamento(AgendamentoRequest agendamentoRequest) {
        // Verificar se existe ou não o Animal e o Veterinário
        AnimalEntity animalEntity = animalRepository.findById(agendamentoRequest.getAnimalId())
                .orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado"));
        VeterinarioEntity veterinarioEntity = veterinarioRepository.findById(agendamentoRequest.getVeterinarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));

        // Regra de negócio: (checagem de conflito de horário)
        Boolean checagem = agendamentoRepository.existsByVeterinarioIdAndDataHora(
                agendamentoRequest.getVeterinarioId(),
                agendamentoRequest.getDataHora()
        );

        if (checagem) {
            throw new ConflictException("Este horário de atendimento já está preenchido!");
        }

        AgendamentoEntity agendamento = new AgendamentoEntity();
        agendamento.setAnimal(animalEntity);
        agendamento.setVeterinario(veterinarioEntity);
        agendamento.setStatus(agendamentoRequest.getStatus());
        agendamento.setDataHora(agendamentoRequest.getDataHora());

        AgendamentoEntity agendamentoSalvo = agendamentoRepository.save(agendamento);

        AgendamentoResponse agendamentoResponse = new AgendamentoResponse();
        agendamentoResponse.setId(agendamentoSalvo.getId());
        agendamentoResponse.setNomeAnimal(agendamentoSalvo.getAnimal().getName());
        agendamentoResponse.setNomeVeterinario(agendamentoSalvo.getVeterinario().getName());
        agendamentoResponse.setStatus(agendamentoSalvo.getStatus());
        agendamentoResponse.setDataHora(agendamentoSalvo.getDataHora());

        return agendamentoResponse;
    }

    // Remover agendamento:
    // Listar todos:
    // Mudar status

}
