package com.example.orangescore;



    public class Teams {
        String  games_played;
        String  loss;
        String  points;
        String  team_name;
        String  wins;
        String team_logo;

        public Teams() {
        }

        public Teams(String games_played, String loss, String points, String team_name, String wins, String team_logo) {
            this.games_played = games_played;
            this.loss = loss;
            this.points = points;
            this.team_name = team_name;
            this.wins = wins;
            this.team_logo = team_logo;
        }

        public String getGames_played() {
            return games_played;
        }

        public void setGames_played(String games_played) {

            this.games_played = games_played;
        }

        public String getLoss() {

            return loss;
        }

        public void setLoss(String loss) {

            this.loss = loss;
        }

        public String getPoints() {

            return points;
        }

        public void setPoints(String points) {

            this.points = points;
        }

        public String getTeam_name() {

            return team_name;
        }

        public void setTeam_name(String team_name) {

            this.team_name = team_name;
        }

        public String getWins() {
            return wins;
        }

        public void setWins(String wins) {

            this.wins = wins;
        }

        public String getTeam_logo() {
            return team_logo;
        }

        public void setTeam_logo(String team_logo) {
            this.team_logo = team_logo;
        }
    }

