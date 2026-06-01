package com.vetagenda.vetagenda_api.exception;

public class ResourceNotFoundException extends RuntimeException {

    // Erro 404
    public ResourceNotFoundException (String mensagem) {
        super(mensagem);
    }

}
