package com.vetagenda.vetagenda_api.controller;

import com.vetagenda.vetagenda_api.domain.dto.request.AnimalRequest;
import com.vetagenda.vetagenda_api.domain.dto.response.AnimalResponse;
import com.vetagenda.vetagenda_api.service.AnimalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/animal")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<AnimalResponse> cadastrarAnimal (@RequestBody @Valid AnimalRequest animalRequest) {
        AnimalResponse animalResponse = animalService.cadastrarAnimal(animalRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(animalResponse.getId())
                .toUri();
        return ResponseEntity.created(location).body(animalResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponse> atualizarAnimal (@PathVariable Long id,
                                                         @RequestBody @Valid AnimalRequest animalRequest) {
        return ResponseEntity.ok(animalService.atualizarAnimal(animalRequest, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAnimal (@PathVariable Long id) {
        animalService.deletarAnimal(id);
        return ResponseEntity.noContent().build();
    }

}
