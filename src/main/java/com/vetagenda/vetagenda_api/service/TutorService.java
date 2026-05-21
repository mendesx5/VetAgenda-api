package com.vetagenda.vetagenda_api.service;

import com.vetagenda.vetagenda_api.domain.dto.request.TutorRequest;
import com.vetagenda.vetagenda_api.domain.dto.response.TutorResponse;
import com.vetagenda.vetagenda_api.domain.entity.TutorEntity;
import com.vetagenda.vetagenda_api.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;

    // Cadastrar novos tutores de animais
    public TutorResponse cadastrarTutor (TutorRequest tutorRequest) {

        TutorEntity tutor = new TutorEntity();
        tutor.setName(tutorRequest.getName());
        tutor.setCpf(tutorRequest.getCpf());
        tutor.setTelefone(tutorRequest.getTelefone());
        tutor.setEmail(tutorRequest.getEmail());

        TutorEntity tutorSalvo = tutorRepository.save(tutor);

        TutorResponse response = new TutorResponse();
        response.setId((tutor.getId()));
        response.setName(tutorRequest.getName());
        response.setCpf(tutorRequest.getCpf());
        response.setTelefone(tutorRequest.getTelefone());
        response.setEmail(tutorRequest.getEmail());

        return response;
    }

    // Buscar tutores por ID
    public TutorResponse buscarPorId (Long id) {

        // Caso o id não corresponda a um cutor cadastrado
        TutorEntity tutor = tutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado"));

        TutorResponse response = new TutorResponse();
        response.setId((tutor.getId()));
        response.setName(tutor.getName());
        response.setCpf(tutor.getCpf());
        response.setTelefone(tutor.getTelefone());
        response.setEmail(tutor.getEmail());

        return response;
    }

    // Listar todos os Tutores e suas informações
    public List<TutorResponse> listarTodos () {

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

}
