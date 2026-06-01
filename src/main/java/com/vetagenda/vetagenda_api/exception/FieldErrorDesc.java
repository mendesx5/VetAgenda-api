package com.vetagenda.vetagenda_api.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldErrorDesc {
    String field;
    String message;
}
