package com.vetagenda.vetagenda_api.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationError extends StandartError {
    private List<FieldErrorDesc> errors =  new ArrayList<>();

    public void addError (String field, String message) {
        FieldErrorDesc fieldError = new FieldErrorDesc();
        fieldError.setField(field);
        fieldError.setMessage(message);
        this.errors.add(fieldError);
    }
}
