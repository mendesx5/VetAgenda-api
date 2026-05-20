package com.vetagenda.vetagenda_api.domain.entity;

import com.vetagenda.vetagenda_api.domain.enums.StatusAgendamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb-agendamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;

    // Preenchido só quando CANCELADO
    private String motivoCancelamento;

    private String observacoes;

    @ManyToOne
    private AnimalEntity animal;

    @ManyToOne
    private VeterinarioEntity veterinario;


}
