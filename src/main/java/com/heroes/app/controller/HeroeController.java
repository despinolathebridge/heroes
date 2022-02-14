package com.heroes.app.controller;

import com.heroes.app.controller.dto.HeroeRequestDTO;
import com.heroes.app.controller.dto.HeroeResponseDTO;
import com.heroes.app.facade.HeroeFacade;
import com.heroes.app.utils.TimeTracker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/heroes")
public class HeroeController {

    private HeroeFacade facade;

    @Autowired
    public HeroeController(HeroeFacade facade) {
        this.facade = facade;
    }

    @TimeTracker
    @GetMapping
    public ResponseEntity<List<HeroeResponseDTO>> getAllHeroes(@RequestParam Map<String,String> params){
        return ResponseEntity.ok().body(this.facade.getAllHeroes(params));
    }

    @TimeTracker
    @GetMapping("/{heroeId}")
    public ResponseEntity<HeroeResponseDTO> getHeroeById(@PathVariable(value = "heroeId") Long heroeId) {
        return ResponseEntity.ok().body(this.facade.getHeroeById(heroeId));
    }

    @TimeTracker
    @PostMapping
    public ResponseEntity<HeroeResponseDTO> saveHeroe(@RequestBody HeroeRequestDTO request) {
        return ResponseEntity.created(URI.create("/heroes")).body(this.facade.saveHeroe(request));
    }

    @TimeTracker
    @PutMapping("/{heroeId}")
    public ResponseEntity<HeroeResponseDTO> updateHeroe(@PathVariable(value = "heroeId") Long heroeId,
                                                      @RequestBody HeroeRequestDTO request) {
        return ResponseEntity.ok().body(this.facade.updateHeroe(request, heroeId));
    }

    @TimeTracker
    @DeleteMapping("/{heroeId}")
    public ResponseEntity<Void> deleteHeroe(@PathVariable(value = "heroeId") Long heroeId) {
        this.facade.deleteHeroe(heroeId);
        return ResponseEntity.ok().body(null);
    }
}
