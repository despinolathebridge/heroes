package com.heroes.app;

import com.heroes.app.entity.Heroe;

public class HeroeTestUtils {

    public static Heroe createHeroeDummy(){
        return Heroe.builder()
                .id(10L)
                .name("dummy")
                .build();
    }
}
