package com.vetagenda.vetagenda_api.controller;

import com.vetagenda.vetagenda_api.domain.dto.request.AnimalRequest;
import com.vetagenda.vetagenda_api.domain.dto.response.AgendamentoResponse;
import com.vetagenda.vetagenda_api.domain.dto.response.AnimalResponse;
import com.vetagenda.vetagenda_api.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/animais")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping
    @Operation(summary = "Cria um novo animal")
    public ResponseEntity<AnimalResponse> cadastrarAnimal (@RequestBody @Valid AnimalRequest animalRequest) {
        AnimalResponse animalResponse = animalService.cadastrarAnimal(animalRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(animalResponse.getId())
                .toUri();
        return ResponseEntity.created(location).body(animalResponse);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um animal existente")
    public ResponseEntity<AnimalResponse> atualizarAnimal (@PathVariable Long id,
                                                         @RequestBody @Valid AnimalRequest animalRequest) {
        return ResponseEntity.ok(animalService.atualizarAnimal(animalRequest, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um animal")
    public ResponseEntity<Void> deletarAnimal (@PathVariable Long id) {
        animalService.deletarAnimal(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    @Operation(summary = "Busca no banco um animal pelo id")
    public ResponseEntity<AnimalResponse> buscarAnimalPorId (@PathVariable Long id) {
        return ResponseEntity.ok(animalService.buscarAnimalPorId(id));
    }

    // Listar todos os animais
    @GetMapping
    @Operation(summary = "Lista todos os animais existentes")
    public ResponseEntity<List<AnimalResponse>> listarAnimais() {
        List<AnimalResponse> lista = animalService.listarTodosAnimais();

        return ResponseEntity.ok(lista);
    }

    // Histórico do animal
    @GetMapping("/{id}/historico")
    @Operation(summary = "Lista o histórico dos animais existentes pelo id")
    public ResponseEntity<List<AgendamentoResponse>> historicoConsultasAnimal(@PathVariable Long id) {
        List<AgendamentoResponse> lista = animalService.historicoConsultasAnimal(id);

        return ResponseEntity.ok(lista);
    }

}
