package com.heroes.app.service.impl;

import com.heroes.app.entity.Heroe;
import com.heroes.app.exception.custom.HeroeNotFoundException;
import com.heroes.app.repository.HeroeRepository;
import com.heroes.app.service.HeroeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HeroeServiceImpl implements HeroeService {

    private static String NAME_KEY = "name";
    private HeroeRepository repository;

    @Autowired
    public HeroeServiceImpl(HeroeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Heroe> getAllHeroes(Map<String, String> params) {

        if (Optional.ofNullable(params.get(NAME_KEY)).isPresent()) {
            return repository.findByNameContainsIgnoreCase(params.get(NAME_KEY));
        }

        return repository.findAll();
    }

    @Override
    @Cacheable(cacheNames="heroes", key="#id")
    public Heroe getHeroeById(Long id) {
        return repository.findById(id).orElseThrow(HeroeNotFoundException::new);
    }

    @Override
    @CachePut(cacheNames = "heroes", key = "#heroe.id")
    public Heroe update(Heroe heroe) {
        Heroe heroeToUpdate = getHeroeById(heroe.getId());
        heroeToUpdate.setName(heroe.getName());

        return repository.save(heroeToUpdate);
    }

    @Override
    public Heroe save(Heroe heroe) {
        return repository.save(heroe);
    }

    @Override
    @CacheEvict(cacheNames = "heroes", key = "#id")
    public void delete(Long id) {
        repository.delete(getHeroeById(id));
    }
}
