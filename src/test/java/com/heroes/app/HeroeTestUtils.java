package com.heroes.app;

import com.heroes.app.controller.dto.HeroeRequestDTO;
import com.heroes.app.controller.dto.HeroeResponseDTO;
import com.heroes.app.entity.Heroe;

public class HeroeTestUtils {

    public static Heroe createHeroeDummy(){
        return Heroe.builder()
                .id(10L)
                .name("dummy")
                .build();
    }

    public static HeroeResponseDTO createHeroeResponseDummy(){
        return HeroeResponseDTO.builder()
                .id(10L)
                .name("dummy")
                .build();
    }

    public static HeroeRequestDTO createHeroeRequestDummy(){
        return HeroeRequestDTO.builder()
                .name("dummy")
                .build();
    }

    public static String getToken(){
        return "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6InJhbmRvbSIsInVzZXJOYW1lIjoiZHVtbXkifQ.4Kwikj6Vh1CS-QtgZO0Z1CgQB_QSi6MVebCgbFdeyoY";
    }
}
