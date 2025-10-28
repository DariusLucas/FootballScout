package com.dariuslucas.footballscout.service;

import com.dariuslucas.footballscout.domain.Club;
import com.dariuslucas.footballscout.domain.Player;
import com.dariuslucas.footballscout.dto.response.PlayerResponse;
import com.dariuslucas.footballscout.dto.summary.ClubSummary;
import com.dariuslucas.footballscout.repository.ClubRepository;
import com.dariuslucas.footballscout.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;

    public PlayerService(PlayerRepository playerRepository, ClubRepository clubRepository) {
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
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

    @Transactional
    public PlayerResponse updatePlayer(Integer id, Player playerDetails) {
        Player existingPlayer = playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Player with id " + id + " does not exist"));

        if (playerRepository.existsByNameAndIdNot(playerDetails.getName(), id)) {
            throw new IllegalArgumentException("Player with name '" + playerDetails.getName() + "' already exists");
        }

        existingPlayer.setName(playerDetails.getName());
        existingPlayer.setNationality(playerDetails.getNationality());
        existingPlayer.setShirtNumber(playerDetails.getShirtNumber());
        existingPlayer.setGamesPlayed(playerDetails.getGamesPlayed());
        existingPlayer.setGoals(playerDetails.getGoals());
        existingPlayer.setAssists(playerDetails.getAssists());

        if(playerDetails.getClub() != null && playerDetails.getClub().getId() != null) {
            Club club = clubRepository.findById(playerDetails.getClub().getId()).orElseThrow(() -> new IllegalArgumentException("Club with id " + playerDetails.getClub().getId() + " does not exist"));
            existingPlayer.setClub(club);
        } else
            existingPlayer.setClub(null);

        Player savedPlayer = playerRepository.save(existingPlayer);

        return new PlayerResponse(
                savedPlayer.getId(),
                savedPlayer.getName(),
                savedPlayer.getNationality(),
                savedPlayer.getShirtNumber(),
                savedPlayer.getGamesPlayed(),
                savedPlayer.getGoals(),
                savedPlayer.getAssists(),
                savedPlayer.getClub() != null ?
                        new ClubSummary(
                                savedPlayer.getClub().getId(), savedPlayer.getClub().getClubName(), savedPlayer.getClub().getCountry()
                        ) : null
        );
    }

    @Transactional
    public void deletePlayer(Integer id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Player with id " + id + " not found"));
        playerRepository.delete(player);
    }
}