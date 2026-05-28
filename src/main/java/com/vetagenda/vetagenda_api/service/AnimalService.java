package com.vetagenda.vetagenda_api.service;

import com.vetagenda.vetagenda_api.domain.dto.request.AnimalRequest;
import com.vetagenda.vetagenda_api.domain.dto.response.AnimalResponse;
import com.vetagenda.vetagenda_api.domain.entity.AnimalEntity;
import com.vetagenda.vetagenda_api.domain.entity.TutorEntity;
import com.vetagenda.vetagenda_api.repository.AnimalRepository;
import com.vetagenda.vetagenda_api.repository.TutorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final TutorRepository tutorRepository;

    // Cadastrar animal
    @Transactional
    public AnimalResponse cadastrarAnimal (AnimalRequest animalRequest) {
        //Buscar e validar o tutor por ID
        TutorEntity tutor = tutorRepository.findById(animalRequest.getTutorId())
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado com ID: " + animalRequest.getTutorId() ));

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

    // Atualizar animal
    @Transactional
    public AnimalResponse atualizarAnimal (AnimalRequest animalRequest, Long id) {
        AnimalEntity animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));

        animal.setName(animalRequest.getName());
        animal.setEspecie(animalRequest.getEspecie());
        animal.setRaca(animalRequest.getRaca());
        animal.setDataNascimento(animalRequest.getDataNascimento());
        animal.setPeso(animalRequest.getPeso());

        AnimalEntity animalSalvo = animalRepository.save(animal);

        return new AnimalResponse(animalSalvo);
    }

    // Remover animal
    @Transactional
    public void deletarAnimal (Long id) {
        AnimalEntity animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado!"));

        animalRepository.delete(animal);
    }

    // Buscar animal por id
    public AnimalResponse buscarAnimalPorId(Long id) {
        AnimalEntity animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado!"));

        AnimalResponse response = new AnimalResponse();
        response.setId(animal.getId());
        response.setName(animal.getName());
        response.setEspecie(animal.getEspecie());
        response.setRaca(animal.getRaca());
        response.setDataNascimento(animal.getDataNascimento());
        response.setPeso(animal.getPeso());
        return response;
    }

    // Listar todos os animais
    public List<AnimalResponse> listarTodosAnimais() {
        return animalRepository.findAll().stream()
                .map(animalEntity -> {
                    AnimalResponse response = new AnimalResponse();
                    response.setId(animalEntity.getId());
                    response.setName(animalEntity.getName());
                    response.setEspecie(animalEntity.getEspecie());
                    response.setRaca(animalEntity.getRaca());
                    response.setDataNascimento(animalEntity.getDataNascimento());
                    response.setPeso(animalEntity.getPeso());
                    return response;
                })
                .collect(Collectors.toList());
    }

    // Histórico de consultas do animal
}
