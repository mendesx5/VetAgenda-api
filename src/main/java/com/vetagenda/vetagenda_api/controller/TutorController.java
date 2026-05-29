package com.vetagenda.vetagenda_api.controller;

import com.vetagenda.vetagenda_api.domain.dto.request.TutorRequest;
import com.vetagenda.vetagenda_api.domain.dto.response.TutorResponse;import com.vetagenda.vetagenda_api.service.TutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tutores")
@RequiredArgsConstructor
public class TutorController {

    private final TutorService tutorService;

    @PostMapping
    public ResponseEntity<TutorResponse> cadastrarTutor (@RequestBody @Valid TutorRequest tutorRequest) {
        TutorResponse tutorResponse = tutorService.cadastrarTutor(tutorRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tutorResponse.getId())
                .toUri();
        return ResponseEntity.created(location).body(tutorResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TutorResponse> atualizarTutor (@PathVariable Long id,
                                                         @RequestBody @Valid TutorRequest tutorRequest) {
        return ResponseEntity.ok(tutorService.atualizarTutor(id, tutorRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTutor (@PathVariable Long id) {
        tutorService.deletarTutor(id);
        return ResponseEntity.noContent().build();
    }

    // Listar todos os tutores:

    @GetMapping
    public ResponseEntity<List<TutorResponse>> listarTutores () {
        List<TutorResponse> lista = tutorService.listarTodosTutores();

        return ResponseEntity.ok(lista);
    }
}
