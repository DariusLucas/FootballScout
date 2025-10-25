package com.dariuslucas.footballscout.service;

import com.dariuslucas.footballscout.dto.response.ClubResponse;
import com.dariuslucas.footballscout.dto.summary.PlayerSummary;
import com.dariuslucas.footballscout.repository.ClubRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService {

    private final ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public List<ClubResponse> getClubs() {
        return clubRepository.findAll().stream()
                .map(c -> new ClubResponse(
                        c.getId(),
                        c.getClubName(),
                        c.getCountry(),
                        c.getPlayers().stream()
                                .map(p -> new PlayerSummary(
                                        p.getId(),
                                        p.getName(),
                                        p.getShirtNumber(),
                                        p.getNationality()
                                ))
                                .toList()
                ))
                .toList();
    }
}