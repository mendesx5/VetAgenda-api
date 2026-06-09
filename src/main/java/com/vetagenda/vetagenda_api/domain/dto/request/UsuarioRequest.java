package com.vetagenda.vetagenda_api.domain.dto.request;

import com.vetagenda.vetagenda_api.domain.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequest {

    @NotBlank
    private String login; // email

    @NotBlank
    private String password;

    private Boolean ativo;

    private UserRole role;
}
