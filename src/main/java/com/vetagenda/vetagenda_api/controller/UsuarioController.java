package com.vetagenda.vetagenda_api.controller;

import com.vetagenda.vetagenda_api.domain.dto.request.UsuarioRequest;
import com.vetagenda.vetagenda_api.domain.dto.response.UsuarioResponse;
import com.vetagenda.vetagenda_api.repository.UsuarioRepository;
import com.vetagenda.vetagenda_api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository repository;
    private final UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Lista todos os funcionários existentes")
    public ResponseEntity<List<UsuarioResponse>> listarTodosFuncionários() {
        List<UsuarioResponse> lista = usuarioService.listarTodosFuncionarios();

        return ResponseEntity.ok(lista);
    }

    @PatchMapping("/{id}/toggle-status")
    @Operation(summary = "Muda o status do usuário de ativo para desativado e vice-versa (true -> false ou false -> true)")
    public ResponseEntity<UsuarioResponse> alternarStatusUsuario (@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.alternarStatusUsuario(id));
    }

    @PutMapping("/{id}/redefinir-senha")
    @Operation(summary = "Muda a senha dos funcionários")
    public ResponseEntity<UsuarioResponse> redefinirSenha (@PathVariable Long id,
                                                         @RequestBody @Valid UsuarioRequest usuarioRequest) {
        return ResponseEntity.ok(usuarioService.redefinirSenha(id, usuarioRequest));
    }

}
