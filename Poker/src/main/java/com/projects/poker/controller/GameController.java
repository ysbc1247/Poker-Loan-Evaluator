package com.projects.poker.controller;

import com.projects.poker.dto.GameDTO;
import com.projects.poker.dto.LoanDTO;
import com.projects.poker.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<GameDTO> startGame() {
        GameDTO gameDTO = gameService.startGame();
        return new ResponseEntity<>(gameDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameDTO> endGame(@PathVariable(value = "id") Long id) {
        GameDTO gameDTO = gameService.endGame(id);
        return ResponseEntity.ok().body(gameDTO);
    }

    @GetMapping("/{id}/loans")
    public ResponseEntity<List<LoanDTO>> getLoansByGameId(@PathVariable(value = "id") Long id) {
        List<LoanDTO> loanDTOs = gameService.getLoansByGameId(id);
        return ResponseEntity.ok().body(loanDTOs);
    }
}
