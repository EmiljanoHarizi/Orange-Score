package com.example.orangescore;

 public class Match {
     String homeTeam, awayTeam, matchDate;

    public Match() {
    }

     public Match(String homeTeam, String awayTeam, String matchDate) {
         this.homeTeam = homeTeam;
         this.awayTeam = awayTeam;
         this.matchDate = matchDate;
     }

     /** Getter & Setter for the name of home team*/
    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    /** Getter & Setter for the name of away team*/
    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    /** Getter & Setter for the match date*/
    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) { this.matchDate = matchDate; }

 }
