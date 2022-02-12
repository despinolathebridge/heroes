package com.heroes.app.service;

import com.heroes.app.entity.Heroe;

import java.util.List;

public interface HeroeService {
    List<Heroe> getAllHeroes();
    Heroe getHeroeById(Long id);
    Heroe update(Heroe heroe);
    Heroe save(Heroe heroe);
    void delete(Long id);
}
