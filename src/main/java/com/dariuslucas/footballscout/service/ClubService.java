package com.dariuslucas.footballscout.service;

import com.dariuslucas.footballscout.domain.Club;
import com.dariuslucas.footballscout.dto.response.ClubResponse;
import com.dariuslucas.footballscout.dto.summary.PlayerSummary;
import com.dariuslucas.footballscout.repository.ClubRepository;
import jakarta.transaction.Transactional;
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

    public ClubResponse getClubById(Integer id) {
        return clubRepository.findById(id)
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
                                )).toList()
                )).orElseThrow(() -> new IllegalArgumentException("Invalid club id " + id));
    }

    @Transactional
    public ClubResponse addClub(Club club) {
        if (clubRepository.existsByClubName(club.getClubName())) {
            throw new IllegalArgumentException("Club with name " + club.getClubName() + " already exists");
        }

        Club savedClub = clubRepository.save(club);

        return new ClubResponse(
                savedClub.getId(),
                savedClub.getClubName(),
                savedClub.getCountry(),
                savedClub.getPlayers().stream()
                        .map(p -> new PlayerSummary(
                                p.getId(),
                                p.getName(),
                                p.getShirtNumber(),
                                p.getNationality()
                        )).toList()
        );
    }
}