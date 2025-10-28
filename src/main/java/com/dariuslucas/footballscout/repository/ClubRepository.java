package com.dariuslucas.footballscout.repository;

import com.dariuslucas.footballscout.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Integer> {
    boolean existsByClubName(String name);
}
