package com.vetagenda.vetagenda_api.domain.dto.request;

import com.vetagenda.vetagenda_api.domain.enums.Especialidade;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VeterinarioRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String crmv;

    private Especialidade especialidade;

}
