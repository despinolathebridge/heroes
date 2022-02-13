package com.heroes.app.exception.custom;

import com.heroes.app.exception.HeroeExceptionEnum;
import com.heroes.app.exception.HeroeGenericException;

public class HeroeInvalidJWTException extends HeroeGenericException {

    public HeroeInvalidJWTException() {
        super(HeroeExceptionEnum.INVALID_JWT.errorCode,
                HeroeExceptionEnum.INVALID_JWT.errorDescription);
    }
}
