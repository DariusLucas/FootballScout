package com.dariuslucas.footballscout.service;

import com.dariuslucas.footballscout.domain.Player;
import com.dariuslucas.footballscout.dto.response.PlayerResponse;
import com.dariuslucas.footballscout.dto.summary.ClubSummary;
import com.dariuslucas.footballscout.repository.PlayerRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public PlayerResponse addPlayer(Player player) {
        if (playerRepository.existsByName(player.getName())) {
            throw new IllegalArgumentException("Player with name '" + player.getName() + "' already exists");
        }
        Player savedPlayer = playerRepository.save(player);

        return new PlayerResponse(
                savedPlayer.getId(),
                savedPlayer.getName(),
                savedPlayer.getNationality(),
                savedPlayer.getShirtNumber(),
                savedPlayer.getGamesPlayed(),
                savedPlayer.getGoals(),
                savedPlayer.getAssists(),
                savedPlayer.getClub() != null ? new ClubSummary(savedPlayer.getClub().getId(), savedPlayer.getClub().getClubName(), savedPlayer.getClub().getCountry()) : null

        );
    }


    public PlayerResponse getPlayerById(Integer id) {
        return playerRepository.findById(id).
                map(p -> new PlayerResponse(
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
                )).orElseThrow(() -> new IllegalArgumentException("Player with id " + id + " does not exist"));

    }
}