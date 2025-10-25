package com.dariuslucas.footballscout.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Club {
    private Integer id;
    private String clubName;
    private String country;
    /*private List<Player> players;*/

    public Club(Integer id, String clubName, String country) {
        this.id = id;
        this.clubName = clubName;
        this.country = country;
        /*this.players = new ArrayList<>();*/
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getClubName() { return clubName; }
    public void setClubName(String clubName) { this.clubName = clubName; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    /*public List<Player> getPlayers() { return players; }
    public void setPlayers(List<Player> players) { this.players = players; }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Club)) return false;
        Club club = (Club) o;
        return Objects.equals(id, club.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
