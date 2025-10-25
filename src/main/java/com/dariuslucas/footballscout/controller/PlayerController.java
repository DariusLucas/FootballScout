package com.dariuslucas.footballscout.controller;

import com.dariuslucas.footballscout.dto.response.PlayerResponse;
import com.dariuslucas.footballscout.service.PlayerService;
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
}