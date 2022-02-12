package com.heroes.app.service;

import com.heroes.app.entity.Heroe;

import java.util.List;
import java.util.Map;

public interface HeroeService {
    List<Heroe> getAllHeroes(Map<String, String> params);
    Heroe getHeroeById(Long id);
    Heroe update(Heroe heroe);
    Heroe save(Heroe heroe);
    void delete(Long id);
}
