package com.heroes.app.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HeroeErrorResponseDTO {
    private String errorCode;
    private String errorDescription;
}
