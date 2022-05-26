package com.example.orangescore.ui;

public class LeagueTeam {
    String teamName, teamCountry, teamCity, teamLogo;

    public LeagueTeam(String teamName, String teamCountry, String teamCity, String teamLogo) {
        this.teamName = teamName;
        this.teamCountry = teamCountry;
        this.teamCity = teamCity;
        this.teamLogo = teamLogo;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamCountry() {
        return teamCountry;
    }

    public void setTeamCountry(String teamCountry) {
        this.teamCountry = teamCountry;
    }

    public String getTeamCity() {
        return teamCity;
    }

    public void setTeamCity(String teamCity) {
        this.teamCity = teamCity;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public void setTeamLogo(String teamLogo) {
        this.teamLogo = teamLogo;
    }

    @Override
    public String toString() {
        return teamName;
    }
}

