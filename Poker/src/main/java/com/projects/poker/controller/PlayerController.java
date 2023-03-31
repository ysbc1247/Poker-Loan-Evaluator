package com.projects.poker.controller;

import com.projects.poker.domain.Player;
import com.projects.poker.dto.PlayerDTO;
import com.projects.poker.repository.PlayerRepository;
import com.projects.poker.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping("/new")
    public ResponseEntity<PlayerDTO> createPlayer(@Valid @ModelAttribute PlayerDTO playerDTO) {
        PlayerDTO createdPlayerDTO = playerService.createPlayer(playerDTO);
        return new ResponseEntity<>(createdPlayerDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable("id") Long id) {
        PlayerDTO playerDTO = playerService.getPlayerById(id);
        return new ResponseEntity<>(playerDTO, HttpStatus.OK);
    }

    @GetMapping("/valid")
    public Boolean validatePassword(@RequestParam Long id, @RequestParam String password){
        return playerService.validPassword(id, password);
    }

    @GetMapping("")
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        List<PlayerDTO> playerDTOs = playerService.getAllPlayers();
        return new ResponseEntity<>(playerDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}/cash")
    public ResponseEntity<Double> getPlayerCash(@PathVariable("id") Long id){
        return new ResponseEntity<>(playerService.getPlayerBalance(playerRepository.findById(id).orElse(null)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable("id") Long id, @Valid @ModelAttribute PlayerDTO playerDTO) {
        PlayerDTO updatedPlayerDTO = playerService.updatePlayer(id, playerDTO);
        return new ResponseEntity<>(updatedPlayerDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable("id") Long id) {
        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
