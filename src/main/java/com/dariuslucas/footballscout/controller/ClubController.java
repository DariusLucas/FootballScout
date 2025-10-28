package com.dariuslucas.footballscout.controller;

import com.dariuslucas.footballscout.domain.Club;
import com.dariuslucas.footballscout.dto.response.ClubResponse;
import com.dariuslucas.footballscout.service.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clubs")
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public List<ClubResponse> getClubs() {
        return clubService.getClubs();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getClubByID(@PathVariable Integer id) {
        try {
            ClubResponse getById = clubService.getClubById(id);
            return ResponseEntity.ok(getById);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<?> addClub(@RequestBody Club club) {
        try {
            ClubResponse created = clubService.addClub(club);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch  (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}