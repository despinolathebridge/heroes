package com.heroes.app.repository;

import com.heroes.app.entity.Heroe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroeRepository extends JpaRepository<Heroe, Long> {
    List<Heroe> findByNameContainsIgnoreCase(String name);
}
