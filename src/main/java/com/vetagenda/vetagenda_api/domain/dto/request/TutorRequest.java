package com.vetagenda.vetagenda_api.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TutorRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String cpf;

    private String telefone;

    @Email
    private String email;

}
