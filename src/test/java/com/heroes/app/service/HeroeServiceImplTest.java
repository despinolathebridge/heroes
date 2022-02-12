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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        assertEquals(heroe, heroeDummy);
    }

    @Test
    void updateHeroeOK() {
        Heroe heroeDummy = HeroeTestUtils.createHeroeDummy();

        when(repository.findById(heroeDummy.getId()))
                .thenReturn(Optional.of(heroeDummy));

        when(repository.save(heroeDummy))
                .thenReturn(heroeDummy);

        Heroe heroe = service.update(heroeDummy);

        assertEquals(heroe, heroeDummy);
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

        assertEquals(heroe, heroeDummy);
    }

    @Test
    void getHeroeByIdError() {
        when(repository.findById(any()))
                .thenThrow(HeroeNotFoundException.class);

        assertThrows(HeroeNotFoundException.class, () -> service.getHeroeById(any()));
    }

    @Test
    void getAllHeroesOK() {
        Heroe heroeDummy = HeroeTestUtils.createHeroeDummy();
        Heroe heroeDummy2 = HeroeTestUtils.createHeroeDummy();

        when(repository.findAll())
                .thenReturn(List.of(heroeDummy, heroeDummy2));

        List<Heroe> heroes = service.getAllHeroes();

        assertEquals(heroes, List.of(heroeDummy, heroeDummy2));
    }
}
