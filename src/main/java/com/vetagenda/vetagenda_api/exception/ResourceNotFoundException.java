package com.vetagenda.vetagenda_api.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException (String mensagem) {
        super(mensagem);
    }

}
