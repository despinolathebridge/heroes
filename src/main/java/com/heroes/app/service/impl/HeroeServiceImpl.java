package com.heroes.app.service.impl;

import com.heroes.app.entity.Heroe;
import com.heroes.app.repository.HeroeRepository;
import com.heroes.app.service.HeroeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroeServiceImpl implements HeroeService {

    private HeroeRepository repository;

    @Autowired
    private HeroeServiceImpl(HeroeRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Heroe> getAllHeroes() {
        return repository.findAll();
    }

    @Override
    public Heroe getHeroeById(Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
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
    public void delete(Long id) {
        repository.delete(getHeroeById(id));
    }
}
