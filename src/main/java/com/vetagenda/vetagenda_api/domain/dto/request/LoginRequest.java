package com.vetagenda.vetagenda_api.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

}
