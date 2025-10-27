package com.dariuslucas.footballscout.controller;

import com.dariuslucas.footballscout.domain.Player;
import com.dariuslucas.footballscout.dto.response.PlayerResponse;
import com.dariuslucas.footballscout.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<PlayerResponse> getPlayers() {
        return playerService.getPlayers();
    }

    @GetMapping("{id}")
        public ResponseEntity<?> getPlayerById(@PathVariable Integer id) {
        try {
            PlayerResponse getById = playerService.getPlayerById(id);
            return new ResponseEntity<>(getById, HttpStatus.OK);
        } catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createPlayer(@RequestBody Player player) {
        try {
            PlayerResponse created = playerService.addPlayer(player);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updatePlayerById(
            @PathVariable Integer id,
            @RequestBody Player player
    ) {
        try {
            PlayerResponse updated = playerService.updatePlayer(id, player);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}