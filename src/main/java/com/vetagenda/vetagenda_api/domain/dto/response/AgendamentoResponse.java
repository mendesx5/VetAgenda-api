package com.vetagenda.vetagenda_api.domain.dto.response;

import com.vetagenda.vetagenda_api.domain.enums.StatusAgendamento;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgendamentoResponse {

    private Long id;

    private String nomeAnimal;

    private String nomeVeterinario;

    private StatusAgendamento status;

    private LocalDateTime dataHora;

    // private String observacoes;
}
