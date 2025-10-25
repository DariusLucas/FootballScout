package com.dariuslucas.footballscout.config;

import com.dariuslucas.footballscout.domain.Club;
import com.dariuslucas.footballscout.domain.Player;
import com.dariuslucas.footballscout.repository.ClubRepository;
import com.dariuslucas.footballscout.repository.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedData(ClubRepository clubRepository, PlayerRepository playerRepository) {
        return args -> {
            // === ONLY RUN IF DATABASE IS EMPTY ===
            if (clubRepository.count() == 0) {

                // --- CLUBS ---
                Club realMadrid = new Club();
                realMadrid.setClubName("Real Madrid");
                realMadrid.setCountry("Spain");

                Club barcelona = new Club();
                barcelona.setClubName("Barcelona");
                barcelona.setCountry("Spain");

                Club manUnited = new Club();
                manUnited.setClubName("Manchester United");
                manUnited.setCountry("England");

                clubRepository.save(realMadrid);
                clubRepository.save(barcelona);
                clubRepository.save(manUnited);

                // --- PLAYERS ---
                Player ronaldo = new Player();
                ronaldo.setName("Cristiano Ronaldo");
                ronaldo.setNationality("Portugal");
                ronaldo.setShirtNumber("7");
                ronaldo.setGamesPlayed(1000);
                ronaldo.setGoals(950);
                ronaldo.setAssists(300);
                ronaldo.setClub(realMadrid);

                Player benzema = new Player();
                benzema.setName("Karim Benzema");
                benzema.setNationality("France");
                benzema.setShirtNumber("9");
                benzema.setGamesPlayed(500);
                benzema.setGoals(300);
                benzema.setAssists(150);
                benzema.setClub(realMadrid);

                Player messi = new Player();
                messi.setName("Lionel Messi");
                messi.setNationality("Argentina");
                messi.setShirtNumber("10");
                messi.setGamesPlayed(900);
                messi.setGoals(800);
                messi.setAssists(400);
                messi.setClub(barcelona);

                Player bruno = new Player();
                bruno.setName("Bruno Fernandes");
                bruno.setNationality("Portugal");
                bruno.setShirtNumber("8");
                bruno.setGamesPlayed(300);
                bruno.setGoals(120);
                bruno.setAssists(110);
                bruno.setClub(manUnited);

                playerRepository.save(ronaldo);
                playerRepository.save(benzema);
                playerRepository.save(messi);
                playerRepository.save(bruno);

                System.out.println("Dummy data seeded successfully.");
            }
        };
    }
}