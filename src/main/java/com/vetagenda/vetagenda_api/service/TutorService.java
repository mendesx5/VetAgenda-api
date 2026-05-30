package com.vetagenda.vetagenda_api.service;

import com.vetagenda.vetagenda_api.domain.dto.request.TutorRequest;
import com.vetagenda.vetagenda_api.domain.dto.response.TutorResponse;
import com.vetagenda.vetagenda_api.domain.entity.TutorEntity;
import com.vetagenda.vetagenda_api.exception.ResourceNotFoundException;
import com.vetagenda.vetagenda_api.repository.TutorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;

    // Cadastrar novos tutores de animais
    @Transactional
    public TutorResponse cadastrarTutor (TutorRequest tutorRequest) {

        TutorEntity tutor = new TutorEntity();
        tutor.setName(tutorRequest.getName());
        tutor.setCpf(tutorRequest.getCpf());
        tutor.setTelefone(tutorRequest.getTelefone());
        tutor.setEmail(tutorRequest.getEmail());

        TutorEntity tutorSalvo = tutorRepository.save(tutor);

        TutorResponse response = new TutorResponse();
        response.setId((tutorSalvo.getId()));
        response.setName(tutorSalvo.getName());
        response.setCpf(tutorSalvo.getCpf());
        response.setTelefone(tutorSalvo.getTelefone());
        response.setEmail(tutorSalvo.getEmail());

        return response;
    }

    // Atualizar tutor:
    @Transactional
    public TutorResponse atualizarTutor (Long id, TutorRequest tutorRequest) {
        TutorEntity tutor = tutorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tutor não encontrado"));

        tutor.setName(tutorRequest.getName());
        tutor.setCpf(tutorRequest.getCpf());
        tutor.setTelefone(tutorRequest.getTelefone());
        tutor.setEmail(tutorRequest.getEmail());

        TutorEntity tutorSalvo = tutorRepository.save(tutor);

        return new TutorResponse(tutorSalvo);
    }

    // Remover tutor:
    @Transactional
    public void deletarTutor(Long id) {
        TutorEntity tutor = tutorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tutor não encontrado"));

        tutorRepository.delete(tutor);
    }

    // Buscar tutores por ID
    public TutorResponse buscarTutorPorId (Long id) {
        TutorEntity tutor = tutorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tutor não encontrado"));

        TutorResponse response = new TutorResponse();
        response.setId((tutor.getId()));
        response.setName(tutor.getName());
        response.setCpf(tutor.getCpf());
        response.setTelefone(tutor.getTelefone());
        response.setEmail(tutor.getEmail());

        return response;
    }

    // Listar todos os Tutores e suas informações
    public List<TutorResponse> listarTodosTutores() {
        return tutorRepository.findAll().stream()
                .map(tutorEntity -> {
                    TutorResponse response = new TutorResponse();
                    response.setId((tutorEntity.getId()));
                    response.setName(tutorEntity.getName());
                    response.setCpf(tutorEntity.getCpf());
                    response.setTelefone(tutorEntity.getTelefone());
                    response.setEmail(tutorEntity.getEmail());
                    return response;
                })
                .collect(Collectors.toList());

    }

    // Listar Animais associados ao tutor por ID:


}
