package com.example.orangescore;

import java.io.Serializable;

public class Player implements Serializable {
    private boolean isChecked = false;
    String player_name, player_photo, player_position, player_team;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Player() {

    }

    public Player(String player_name, String player_photo, String player_position, String player_team) {
        this.player_name = player_name;
        this.player_photo = player_photo;
        this.player_position = player_position;
        this.player_team = player_team;
    }


    /** Getter & Setter for player's name */
    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    /** Getter & Setter for the link of player's photo */
    public String getPlayer_photo() {
        return player_photo;
    }

    public void setPlayer_photo(String player_photo) {
        this.player_photo = player_photo;
    }

    /** Getter & Setter for player's position */
    public String getPlayer_position() {
        return player_position;
    }

    public void setPlayer_position(String player_position) {
        this.player_position = player_position;
    }

    /** Getter & Setter for player's team */
    public String getPlayer_team() {
        return player_team;
    }

    public void setPlayer_team(String player_team) {
        this.player_team = player_team;
    }

}

