package com.heroes.app.exception;

import lombok.Data;

@Data
public abstract class HeroeGenericException extends RuntimeException{

    private String errorCode;
    private String errorDescription;

    public HeroeGenericException(String errorCode,
                                 String errorDescription){
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }
}
