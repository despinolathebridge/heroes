package com.heroes.app.exception;

import lombok.Data;

@Data
public abstract class HeroeGenericException extends RuntimeException{

    private final String errorCode;
    private final String errorDescription;

    protected HeroeGenericException(String errorCode,
                                 String errorDescription){
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }
}
