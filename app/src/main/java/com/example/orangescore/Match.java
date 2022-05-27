package com.example.orangescore;

public class Match {
    private String homeTeam;
    private String awayTeam;
    private String matchDate;


    public Match() {
    }

    public Match(String homeTeam, String awayTeam, String matchDate) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchDate = matchDate;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }
}
