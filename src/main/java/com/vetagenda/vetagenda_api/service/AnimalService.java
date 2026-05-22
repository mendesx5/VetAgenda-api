package com.vetagenda.vetagenda_api.service;

import com.vetagenda.vetagenda_api.domain.dto.request.AnimalRequest;
import com.vetagenda.vetagenda_api.domain.dto.request.TutorRequest;
import com.vetagenda.vetagenda_api.domain.dto.response.AnimalResponse;
import com.vetagenda.vetagenda_api.domain.entity.AnimalEntity;
import com.vetagenda.vetagenda_api.domain.entity.TutorEntity;
import com.vetagenda.vetagenda_api.repository.AnimalRepository;
import com.vetagenda.vetagenda_api.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final TutorRepository tutorRepository;

    // Cadastrar animal (com tutor_id no body)
    public AnimalResponse cadastrarAnimal (AnimalRequest animalRequest, Long tutorId) {
        //Buscar e validar o tutor por ID
        TutorEntity tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado com ID: " + tutorId));
        //
        AnimalEntity animal = new AnimalEntity();
        animal.setName(animalRequest.getName());
        animal.setEspecie(animalRequest.getEspecie());
        animal.setRaca(animalRequest.getRaca());
        animal.setDataNascimento(animalRequest.getDataNascimento());
        animal.setPeso(animalRequest.getPeso());
        animal.setTutor(tutor);

        AnimalEntity animalSalvo = animalRepository.save(animal);

        AnimalResponse response = new AnimalResponse();
        response.setId(animalSalvo.getId());
        response.setName(animalSalvo.getName());
        response.setEspecie(animalSalvo.getEspecie());
        response.setRaca(animalSalvo.getRaca());
        response.setDataNascimento(animalSalvo.getDataNascimento());
        response.setPeso(animalSalvo.getPeso());

        return response;
    }


    // Listar todos os animais

    // Buscar animal por id

    // Histórico de concultas do animal

    // Atualizar animal

    // Remover animal (sem remover o tutor a qual ele está ligado)

}
