package com.vetagenda.vetagenda_api.domain.dto.response;

import com.vetagenda.vetagenda_api.domain.entity.VeterinarioEntity;
import com.vetagenda.vetagenda_api.domain.enums.Especialidade;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VeterinarioResponse {

    private Long id;

    private String name;

    private String crmv;

    private Especialidade especialidade;

    public VeterinarioResponse(VeterinarioEntity veterinarioSalvo) {
    }
}
