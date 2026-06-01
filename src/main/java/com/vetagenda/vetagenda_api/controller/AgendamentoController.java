package com.vetagenda.vetagenda_api.controller;

import com.vetagenda.vetagenda_api.domain.dto.request.AgendamentoRequest;
import com.vetagenda.vetagenda_api.domain.dto.response.AgendamentoResponse;
import com.vetagenda.vetagenda_api.service.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Cria um novo agendamento",
            description = "Valida se o animal e o veterinário existem, e garante que não haja conflito de horário para o médico escolhido.")
    public ResponseEntity<AgendamentoResponse> criarAgendamento (@RequestBody @Valid AgendamentoRequest agendamentoRequest) {
        AgendamentoResponse agendamentoResponse = agendamentoService.criarAgendamento(agendamentoRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(agendamentoResponse.getId())
                .toUri();
        return ResponseEntity.ok(agendamentoResponse);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um agendamento")
    public ResponseEntity<Void> deletarAgendamento (@PathVariable Long id) {
        agendamentoService.deletarAgendamento(id);
        return ResponseEntity.noContent().build();
    }

    // Listar todos os agendamentos
    //@GetMapping
    //@Operation(summary = "Lista todos os agendamentos existentes")
    //public ResponseEntity<List<AgendamentoResponse>> listarTodosAgendamentos() {
    //    List<AgendamentoResponse> lista = agendamentoService.listarTodosAgendamentos();
    //    return ResponseEntity.ok(lista);
    //}

    // Buscar por ID:
    @GetMapping("{id}")
    @Operation(summary = "Busca no banco um agendamento pelo id")
    public ResponseEntity<AgendamentoResponse> getAgendamento(@PathVariable Long id) {
        return ResponseEntity.ok(agendamentoService.buscarAgendamentoPorId(id));
    }

    // Mudar status dos agendamentos:
    @PatchMapping("/{id}/agendar")
    @Operation(summary = "Muda o status de agendamento para AGENDADO")
    public ResponseEntity<AgendamentoResponse> atualizarAgendamentoAgendado (@PathVariable Long id) {
        return ResponseEntity.ok(agendamentoService.atualizarAgendamentoAgendado(id));
    }
    @PatchMapping("/{id}/concluir")
    @Operation(summary = "Muda o status de agendamento para CONCLUIDO")
    public ResponseEntity<AgendamentoResponse> atualizarAgendamentoConcluido (@PathVariable Long id) {
        return ResponseEntity.ok(agendamentoService.atualizarAgendamentoConcluido(id));
    }
    @PatchMapping("/{id}/cancelar")
    @Operation(summary = "Muda o status de agendamento para CANCELADO")
    public ResponseEntity<AgendamentoResponse> atualizarAgendamentoCancelado (@PathVariable Long id) {
        return ResponseEntity.ok(agendamentoService.atualizarAgendamentoCancelado(id));
    }
}
