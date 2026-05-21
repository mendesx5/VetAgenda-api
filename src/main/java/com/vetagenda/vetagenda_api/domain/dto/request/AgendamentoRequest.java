package com.vetagenda.vetagenda_api.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgendamentoRequest {

    @NotNull
    private Long animalId;

    @NotNull
    private Long veterinarioId;

    @NotNull
    private LocalDateTime dataHora;

    private String observacoes;

}
