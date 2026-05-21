package com.vetagenda.vetagenda_api.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb-animail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String especie;

    private String raca;

    private LocalDate dataNascimento;

    private Double peso;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private TutorEntity tutor;

    @OneToMany(mappedBy = "animal")
    private List<AgendamentoEntity>  agendamentos;

}
