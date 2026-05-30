package com.vetagenda.vetagenda_api.controller;

import com.vetagenda.vetagenda_api.domain.dto.request.AgendamentoRequest;
import com.vetagenda.vetagenda_api.domain.dto.request.TutorRequest;
import com.vetagenda.vetagenda_api.domain.dto.response.AgendamentoResponse;
import com.vetagenda.vetagenda_api.domain.dto.response.TutorResponse;
import com.vetagenda.vetagenda_api.service.AgendamentoService;
import com.vetagenda.vetagenda_api.service.TutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    public final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoResponse> criarAgendamento (@RequestBody @Valid AgendamentoRequest agendamentoRequest) {
        AgendamentoResponse agendamentoResponse = agendamentoService.criarAgendamento(agendamentoRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(agendamentoResponse.getId())
                .toUri();
        return ResponseEntity.ok(agendamentoResponse);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarAgendamento (@PathVariable Long id) {
        agendamentoService.deletarAgendamento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoResponse>> listarTodosAgendamentos() {
        List<AgendamentoResponse> lista = agendamentoService.listarTodosAgendamentos();
        return ResponseEntity.ok(lista);
    }
}
