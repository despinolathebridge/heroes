package com.heroes.app.service;

import com.heroes.app.HeroeTestUtils;
import com.heroes.app.entity.Heroe;
import com.heroes.app.exception.custom.HeroeNotFoundException;
import com.heroes.app.repository.HeroeRepository;
import com.heroes.app.service.impl.HeroeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HeroeServiceImplTest {

    @Mock
    private HeroeRepository repository;

    @InjectMocks
    private HeroeServiceImpl service;

    @Test
    void saveHeroeOK() {
        Heroe heroeDummy = HeroeTestUtils.createHeroeDummy();

        when(repository.save(heroeDummy))
                .thenReturn(heroeDummy);

        Heroe heroe = service.save(heroeDummy);

        assertThat(heroe).isEqualTo(heroeDummy);
    }

    @Test
    void updateHeroeOK() {
        Heroe heroeDummy = HeroeTestUtils.createHeroeDummy();

        when(repository.findById(heroeDummy.getId()))
                .thenReturn(Optional.of(heroeDummy));

        when(repository.save(heroeDummy))
                .thenReturn(heroeDummy);

        Heroe heroe = service.update(heroeDummy);

        assertThat(heroe).isEqualTo(heroeDummy);
    }

    @Test
    void deleteHeroeOK() {
        Heroe heroeDummy = HeroeTestUtils.createHeroeDummy();

        when(repository.findById(heroeDummy.getId()))
                .thenReturn(Optional.of(heroeDummy));

        doNothing().when(repository).delete(heroeDummy);

        service.delete(heroeDummy.getId());

        verify(repository, times(1)).delete(heroeDummy);
    }

    @Test
    void getHeroeByIdOK() {
        Heroe heroeDummy = HeroeTestUtils.createHeroeDummy();

        when(repository.findById(heroeDummy.getId()))
                .thenReturn(Optional.of(heroeDummy));

        Heroe heroe = service.getHeroeById(heroeDummy.getId());

        assertThat(heroe).isEqualTo(heroeDummy);
    }

    @Test
    void getHeroeByIdError() {
        when(repository.findById(any()))
                .thenThrow(HeroeNotFoundException.class);

        assertThrows(HeroeNotFoundException.class, () -> service.getHeroeById(10L));
    }

    @Test
    void getAllHeroesOK() {
        Map<String, String> queryMap = new HashMap<>();
        Heroe heroeDummy = HeroeTestUtils.createHeroeDummy();
        Heroe heroeDummy2 = HeroeTestUtils.createHeroeDummy();

        when(repository.findAll())
                .thenReturn(List.of(heroeDummy, heroeDummy2));

        List<Heroe> heroes = service.getAllHeroes(queryMap);

        assertThat(heroes).isEqualTo(List.of(heroeDummy, heroeDummy2));
        verify(repository, times(1)).findAll();
    }

    @Test
    void getAllHeroesWithNameQueryParamOK() {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("name", "man");
        Heroe heroeDummy = HeroeTestUtils.createHeroeDummy();
        Heroe heroeDummy2 = HeroeTestUtils.createHeroeDummy();

        when(repository.findByNameContainsIgnoreCase("man"))
                .thenReturn(List.of(heroeDummy, heroeDummy2));

        List<Heroe> heroes = service.getAllHeroes(queryMap);

        assertThat(heroes).isEqualTo(List.of(heroeDummy, heroeDummy2));
        verify(repository, times(1)).findByNameContainsIgnoreCase("man");
    }
}
