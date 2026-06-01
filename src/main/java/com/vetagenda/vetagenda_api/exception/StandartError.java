package com.vetagenda.vetagenda_api.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class StandartError {

    private Integer status;
    private String erro;
    private String mensagem;
    private LocalDateTime timestamp;


}
