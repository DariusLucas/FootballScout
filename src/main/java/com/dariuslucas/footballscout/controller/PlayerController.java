package com.dariuslucas.footballscout.controller;

import com.dariuslucas.footballscout.domain.Club;
import com.dariuslucas.footballscout.domain.Player;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {

    @GetMapping
    public List<Player> getPlayers() {

        Club realMadrid = new Club(1, "Real Madrid", "Spain");
        Club barcelona = new Club(2, "Barcelona", "Spain");


        Player ronaldo = new Player(1, "Cristiano Ronaldo", "Portugal", "7", 1000, 950, 300, realMadrid);
        Player benzema = new Player(2, "Karim Benzema", "France", "9", 500, 300, 150, realMadrid);
        Player messi = new Player(3, "Lionel Messi", "Argentina", "10", 900, 800, 400, barcelona);





        return Arrays.asList(ronaldo, benzema, messi);
    }
}