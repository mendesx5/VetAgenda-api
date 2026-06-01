package com.vetagenda.vetagenda_api.controller;

import com.vetagenda.vetagenda_api.domain.dto.request.VeterinarioRequest;
import com.vetagenda.vetagenda_api.domain.dto.response.AgendamentoResponse;
import com.vetagenda.vetagenda_api.domain.dto.response.VeterinarioResponse;
import com.vetagenda.vetagenda_api.service.VeterinarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/veterinarios")
@RequiredArgsConstructor
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    @PostMapping
    @Operation(summary = "Cria um novo veterinário")
    public ResponseEntity<VeterinarioResponse> cadastrarVeterinario (@RequestBody @Valid VeterinarioRequest veterinarioRequest) {
        VeterinarioResponse veterinarioResponse = veterinarioService.cadastrarVeterinario(veterinarioRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(veterinarioResponse.getId())
                .toUri();
        return ResponseEntity.created(location).body(veterinarioResponse);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um veterinário existente")
    public ResponseEntity<VeterinarioResponse> atualizarVeterinario (@PathVariable Long id,
                                                         @RequestBody @Valid VeterinarioRequest veterinarioRequest) {
        return ResponseEntity.ok(veterinarioService.atualizarVeterinario(id, veterinarioRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um veterinário existente")
    public ResponseEntity<Void> deletarVeterinario (@PathVariable Long id) {
        veterinarioService.deletarVeterinario(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    @Operation(summary = "Busca no banco um veterinário pelo id")
    public ResponseEntity<VeterinarioResponse> buscarVeterinarioPorId (@PathVariable Long id) {
        return ResponseEntity.ok(veterinarioService.buscarVeterinarioPorId(id));
    }

    // Listar todos os veterinários
    @GetMapping
    @Operation(summary = "Lista todos os veterinários existentes")
    public ResponseEntity<List<VeterinarioResponse>> listarVeterinarios() {
        List<VeterinarioResponse> lista = veterinarioService.listarTodosVeterinarios();

        return ResponseEntity.ok(lista);
    }

    // Listar agendamentos do veterinário pelo id
    @GetMapping("/{id}/agenda")
    @Operation(summary = "Lista todos os veterinários existentes")
    public ResponseEntity<List<AgendamentoResponse>> agendamentosPorVeterinario(@PathVariable Long id) {
        List<AgendamentoResponse> lista = veterinarioService.agendamentosPorVeterinario(id);

        return ResponseEntity.ok(lista);
    }
}
