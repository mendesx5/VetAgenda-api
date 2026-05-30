package com.vetagenda.vetagenda_api.domain.dto.response;

import com.vetagenda.vetagenda_api.domain.entity.TutorEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TutorResponse {

    private Long id;

    private String name;

    private String cpf;

    private String telefone;

    private String email;

    public TutorResponse(TutorEntity tutorSalvo) {}
}
