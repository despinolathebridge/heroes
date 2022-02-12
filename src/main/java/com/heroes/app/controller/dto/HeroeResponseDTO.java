package com.heroes.app.controller.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HeroeResponseDTO {
    private Long id;
    private String name;
}
