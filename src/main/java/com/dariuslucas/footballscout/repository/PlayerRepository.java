package com.dariuslucas.footballscout.repository;

import com.dariuslucas.footballscout.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
