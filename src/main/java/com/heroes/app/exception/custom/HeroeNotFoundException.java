package com.heroes.app.exception.custom;

import com.heroes.app.exception.HeroeExceptionEnum;
import com.heroes.app.exception.HeroeGenericException;

public class HeroeNotFoundException extends HeroeGenericException {

    public HeroeNotFoundException() {
        super(HeroeExceptionEnum.HEROE_NOT_FOUND.errorCode,
                HeroeExceptionEnum.HEROE_NOT_FOUND.errorDescription);
    }
}
