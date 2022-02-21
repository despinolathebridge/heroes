package com.heroes.app.mapper;

import com.heroes.app.controller.dto.HeroeRequestDTO;
import com.heroes.app.controller.dto.HeroeResponseDTO;
import com.heroes.app.entity.Heroe;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HeroeMapper {

    public Heroe toEntity(HeroeRequestDTO dto, Long id) {
        return Heroe.builder()
                .id(id)
                .name(dto.getName())
                .build();
    }

    public HeroeResponseDTO toDto(Heroe entity) {
        return HeroeResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public List<HeroeResponseDTO> toListDto(List<Heroe> heroes) {
        return heroes.stream().map(this::toDto).collect(Collectors.toList());
    }
}
