package com.vetagenda.vetagenda_api.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String especie;

    private String raca;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private Double peso;

    @NotNull
    private Long tutorId;

}
