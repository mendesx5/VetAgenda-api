package com.vetagenda.vetagenda_api.domain.dto.response;

import com.vetagenda.vetagenda_api.domain.entity.AnimalEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalResponse {

    private Long id;

    private String name;

    private String especie;

    private String raca;

    private LocalDate dataNascimento;

    private Double peso;

    private String nomeTutor;

    public AnimalResponse(AnimalEntity animalSalvo) {
    }
}
