package com.dariuslucas.footballscout.service;

import com.dariuslucas.footballscout.dto.response.PlayerResponse;
import com.dariuslucas.footballscout.dto.summary.ClubSummary;
import com.dariuslucas.footballscout.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerResponse> getPlayers() {
        return playerRepository.findAll().stream()
                .map(p -> new PlayerResponse(
                        p.getId(),
                        p.getName(),
                        p.getNationality(),
                        p.getShirtNumber(),
                        p.getGamesPlayed(),
                        p.getGoals(),
                        p.getAssists(),
                        p.getClub() != null
                                ? new ClubSummary(p.getClub().getId(), p.getClub().getClubName(), p.getClub().getCountry())
                                : null
                ))
                .toList();
    }
}