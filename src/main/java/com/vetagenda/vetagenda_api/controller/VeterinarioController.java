package com.vetagenda.vetagenda_api.controller;

import com.vetagenda.vetagenda_api.domain.dto.request.VeterinarioRequest;
import com.vetagenda.vetagenda_api.domain.dto.response.VeterinarioResponse;
import com.vetagenda.vetagenda_api.service.VeterinarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/veterinario")
@RequiredArgsConstructor
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    @PostMapping
    public ResponseEntity<VeterinarioResponse> cadastrarVeterinario (@RequestBody @Valid VeterinarioRequest veterinarioRequest) {
        VeterinarioResponse veterinarioResponse = veterinarioService.cadastrarVeterinario(veterinarioRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(veterinarioResponse.getId())
                .toUri();
        return ResponseEntity.created(location).body(veterinarioResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeterinarioResponse> atualizarVeterinario (@PathVariable Long id,
                                                         @RequestBody @Valid VeterinarioRequest veterinarioRequest) {
        return ResponseEntity.ok(veterinarioService.atualizarVeterinario(id, veterinarioRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVeterinario (@PathVariable Long id) {
        veterinarioService.deletarVeterinario(id);
        return ResponseEntity.noContent().build();
    }

    // Listar todos os veterinários
    @GetMapping
    public ResponseEntity<List<VeterinarioResponse>> listarVeterinarios() {
        List<VeterinarioResponse> lista = veterinarioService.listarTodosVeterinarios();

        return ResponseEntity.ok(lista);
    }
}
