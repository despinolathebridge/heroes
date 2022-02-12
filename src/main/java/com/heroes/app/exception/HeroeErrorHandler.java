package com.heroes.app.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class HeroeErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {HeroeGenericException.class})
    public ResponseEntity<HeroeErrorResponseDTO> handleHeroeNotFoundException(HeroeGenericException ex) {
        log.error("[ErrorHandler:NotFoundException] " + ex.getErrorDescription());
        return new ResponseEntity<>(
                HeroeErrorResponseDTO.builder()
                        .errorCode(ex.getErrorCode())
                        .errorDescription(ex.getErrorDescription())
                        .build(), HttpStatus.NOT_FOUND);
    }
}
