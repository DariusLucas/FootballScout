package com.dariuslucas.footballscout.domain;

import java.util.Objects;

public class Player {


    private Integer id;
    private String name;
    private String nationality;
    private String shirtNumber;
    private Club club;
    private Integer goals;
    private Integer assists;
    private Integer gamesPlayed;

    public Player(Integer id, String name, String nationality, String shirtNumber, Integer gamesPlayed, Integer goals,Integer assists,  Club club) {
        this.gamesPlayed = gamesPlayed;
        this.assists = assists;
        this.goals = goals;
        this.club = club;
        this.shirtNumber = shirtNumber;
        this.nationality = nationality;
        this.name = name;
        this.id = id;
    }

    public Integer getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(Integer gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public String getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(String shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
