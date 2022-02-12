package com.heroes.app.facade;

import com.heroes.app.controller.dto.HeroeRequestDTO;
import com.heroes.app.controller.dto.HeroeResponseDTO;
import com.heroes.app.mapper.HeroeMapper;
import com.heroes.app.service.HeroeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class HeroeFacade {

    private HeroeService service;
    private HeroeMapper mapper;

    @Autowired
    public HeroeFacade(HeroeService service,
                       HeroeMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public List<HeroeResponseDTO> getAllHeroes(Map<String, String> params) {
        return mapper.toListDto(service.getAllHeroes(params));
    }

    public HeroeResponseDTO getHeroeById(Long id) {
        return mapper.toDto(service.getHeroeById(id));
    }

    public HeroeResponseDTO saveHeroe(HeroeRequestDTO request){
        return mapper.toDto(service.save(mapper.toEntity(request, null)));
    }

    public HeroeResponseDTO updateHeroe(HeroeRequestDTO request, Long heroeId) {
        return mapper.toDto(service.update(mapper.toEntity(request, heroeId)));
    }

    public void deleteHeroe(Long heroeId) {
        service.delete(heroeId);
    }
}
