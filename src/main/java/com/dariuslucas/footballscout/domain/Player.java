package com.dariuslucas.footballscout.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String nationality;
    private String shirtNumber;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    private Integer goals;
    private Integer assists;
    private Integer gamesPlayed;

    // Default constructor
    public Player() {}

    // GETTERS & SETTERS
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public String getShirtNumber() { return shirtNumber; }
    public void setShirtNumber(String shirtNumber) { this.shirtNumber = shirtNumber; }
    public Club getClub() { return club; }
    public void setClub(Club club) { this.club = club; }
    public Integer getGoals() { return goals; }
    public void setGoals(Integer goals) { this.goals = goals; }
    public Integer getAssists() { return assists; }
    public void setAssists(Integer assists) { this.assists = assists; }
    public Integer getGamesPlayed() { return gamesPlayed; }
    public void setGamesPlayed(Integer gamesPlayed) { this.gamesPlayed = gamesPlayed; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}