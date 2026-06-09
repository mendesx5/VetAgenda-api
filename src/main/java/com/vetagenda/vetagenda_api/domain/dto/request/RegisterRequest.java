package com.vetagenda.vetagenda_api.domain.dto.request;

import com.vetagenda.vetagenda_api.domain.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @NotNull
    private UserRole role;

}
