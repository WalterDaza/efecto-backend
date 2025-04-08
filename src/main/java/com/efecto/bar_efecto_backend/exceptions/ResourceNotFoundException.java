package com.efecto.bar_efecto_backend.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND) // indica que cuando se lanza esta excepción, Spring devolverá un HTTP 404 (Recurso no encontrado).
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        //Super llama al contructor de la superclase (RuntimeException)
        //Luego ese mensaje se pasa al constructor de RuntimeException usando super(...).
        super(String.format("%s no encontrado con %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
