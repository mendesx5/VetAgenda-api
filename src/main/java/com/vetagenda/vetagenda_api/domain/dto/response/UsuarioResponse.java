package com.vetagenda.vetagenda_api.domain.dto.response;

import com.vetagenda.vetagenda_api.domain.entity.UsuarioEntity;
import com.vetagenda.vetagenda_api.domain.enums.UserRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponse    {

    private Long id;

    private String login; // email

    private Boolean ativo;

    private UserRole role;

    public UsuarioResponse(UsuarioEntity usuarioSalvo) {
    }
}
