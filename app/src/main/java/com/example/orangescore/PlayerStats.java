package com.example.orangescore;

public class PlayerStats {
    String player_name, player_team;
    int player_points, player_rebounds, player_assists;

    public PlayerStats(String player_name, int player_points, int player_rebounds, int player_assists, String player_team) {
        this.player_name = player_name;
        this.player_points = player_points;
        this.player_rebounds = player_rebounds;
        this.player_assists = player_assists;
        this.player_team = player_team;
    }

    /** Getters And Setters for Player */
    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public int getPlayer_points() {
        return player_points;
    }

    public void setPlayer_points(int player_points) {
        this.player_points = player_points;
    }

    public int getPlayer_rebounds() {
        return player_rebounds;
    }

    public void setPlayer_rebounds(int player_rebounds) {
        this.player_rebounds = player_rebounds;
    }

    public int getPlayer_assists() {
        return player_assists;
    }

    public void setPlayer_assists(int player_assists) {
        this.player_assists = player_assists;
    }
}