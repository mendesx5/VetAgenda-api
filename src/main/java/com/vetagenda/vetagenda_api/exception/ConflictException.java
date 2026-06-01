package com.vetagenda.vetagenda_api.exception;

public class ConflictException extends RuntimeException {

    // Erro 409
    public ConflictException (String mensagem) {
        super(mensagem);
    }

}
