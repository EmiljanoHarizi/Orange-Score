package com.example.orangescore;

public class Player {
    String player_team, player_name, player_position, player_photo;

    public Player() {

    }

    public Player(String player_team, String plr_name, String plr_position, String plr_photo) {
        this.player_team = player_team;
        this.player_name = plr_name;
        this.player_position = plr_position;
        this.player_photo = plr_photo;
    }

    public String getPlayer_team() {
        return player_team;
    }

    public void setPlayer_team(String player_team) {
        this.player_team = player_team;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getPlayer_position() {
        return player_position;
    }

    public void setPlayer_position(String player_position) {
        this.player_position = player_position;
    }

    public String getPlayer_photo() {
        return player_photo;
    }

    public void setPlayer_photo(String player_photo) {
        this.player_photo = player_photo;
    }
}
