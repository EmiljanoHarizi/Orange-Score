package com.example.orangescore;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.common.collect.Table;
import com.google.firebase.firestore.model.FieldIndex;

import java.util.ArrayList;
import java.util.Collections;

public class R9_Activity<Team1> extends AppCompatActivity {
    ArrayList<PlayerStats> playersList = new ArrayList<>();
    Button Team1, Team2, totalTeam;
    private final String myIP = "tim.alwaysdata.net";
    //private final String myIP = "192.168.1.6";

    private int[][] player_list_stats = {
            {R.id.player1, R.id.playerpoints1,R.id.playerrebound1,R.id.playerassist1},
            {R.id.player2, R.id.playerpoints2,R.id.playerrebound2,R.id.playerassist2},
            {R.id.player3, R.id.playerpoints3,R.id.playerrebound3,R.id.playerassist3},
            {R.id.player4, R.id.playerpoints4,R.id.playerrebound4,R.id.playerassist4},
            {R.id.player5, R.id.playerpoints5,R.id.playerrebound5,R.id.playerassist5},
            {R.id.player6, R.id.playerpoints6,R.id.playerrebound6,R.id.playerassist6},
            {R.id.player7, R.id.playerpoints7,R.id.playerrebound7,R.id.playerassist7},
            {R.id.player8, R.id.playerpoints8,R.id.playerrebound8,R.id.playerassist8},
            {R.id.player9, R.id.playerpoints9,R.id.playerrebound9,R.id.playerassist9},
            {R.id.player10, R.id.playerpoints10,R.id.playerrebound10,R.id.playerassist10}
    };

    private int[] tables = {R.id.tableRow17,R.id.tableRow16,R.id.tableRow18,R.id.tableRow19,R.id.tableRow20,R.id.tableRow22,R.id.tableRow21,R.id.tableRow24,R.id.tableRow23,R.id.tableRow25};
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r9);

        //ManualPlayerData();

        try {
            fetchDataFromDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Button toR10Btn = findViewById(R.id.toR10Btn);
        toR10Btn.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), R10_Activity.class));
            finish();
        });

        totalTeam = findViewById(R.id.Sunolo);
        totalTeam.setOnClickListener(v -> {
            int j = 0;
            for(int i=0; i < playersList.size(); i++){
                visible_tables(i);
                populate_tables(j, i);
                j++;
            }
        });


        Team1 = findViewById(R.id.Team1);
        Team1.setOnClickListener(v -> {
            invisible_tables();
            int j = 0;
            for(int i=0; i < playersList.size(); i++){
                if(playersList.get(i).player_team.equals("PAOK")) {
                    populate_tables(j,i);
                    visible_tables(j);
                    j++;
                }
            }
        });

        Team2 = findViewById(R.id.Team2);
        Team2.setOnClickListener(v -> {
            invisible_tables();
            int j = 0;
            for(int i=0; i < playersList.size(); i++){
                if(playersList.get(i).player_team.equals("AEK")) {
                    populate_tables(j, i);
                    visible_tables(j);
                    j++;
                }
            }
        });

    }

    private void fetchDataFromDB() throws Exception {
        String url = "https://"+myIP+"/fetchPlayersGame.php";

        OkHttpHandler ok = new OkHttpHandler();
        playersList = ok.fetchPlayerStatsGame(url);

        for(int i=0; i < playersList.size(); i++){
            ((TextView) findViewById(player_list_stats[i][0])).setText(playersList.get(i).player_name);
            ((TextView) findViewById(player_list_stats[i][1])).setText(Integer.toString(playersList.get(i).player_points));
            ((TextView) findViewById(player_list_stats[i][2])).setText(Integer.toString(playersList.get(i).player_rebounds));
            ((TextView) findViewById(player_list_stats[i][3])).setText(Integer.toString(playersList.get(i).player_assists));
        }

        String team1 = playersList.get(0).player_team;
        ((TextView) findViewById(R.id.Team1Txt)).setText(team1);
        ((TextView) findViewById(R.id.Team1)).setText(team1);
        for(int i = 1; i < playersList.size(); i++){
            if(!team1.equals(playersList.get(i).player_team)){
                ((TextView) findViewById(R.id.Team2Txt)).setText(playersList.get(i).player_team);
                ((TextView) findViewById(R.id.Team2)).setText(playersList.get(i).player_team);
                break;
            }
        }


    }

    private void populate_tables(int j, int i){
        ((TextView) findViewById(player_list_stats[j][0])).setText(playersList.get(i).player_name);
        ((TextView) findViewById(player_list_stats[j][1])).setText(Integer.toString(playersList.get(i).player_points));
        ((TextView) findViewById(player_list_stats[j][2])).setText(Integer.toString(playersList.get(i).player_rebounds));
        ((TextView) findViewById(player_list_stats[j][3])).setText(Integer.toString(playersList.get(i).player_assists));
    }

    private void invisible_tables(){
        for(int i = 0; i < 10; i++){
            ((TextView) findViewById(player_list_stats[i][0])).setVisibility(View.GONE);
            ((TextView) findViewById(player_list_stats[i][1])).setVisibility(View.GONE);
            ((TextView) findViewById(player_list_stats[i][2])).setVisibility(View.GONE);
            ((TextView) findViewById(player_list_stats[i][3])).setVisibility(View.GONE);
        }

    }
    private void visible_tables(int i){
        ((TextView) findViewById(player_list_stats[i][0])).setVisibility(View.VISIBLE);
        ((TextView) findViewById(player_list_stats[i][1])).setVisibility(View.VISIBLE);
        ((TextView) findViewById(player_list_stats[i][2])).setVisibility(View.VISIBLE);
        ((TextView) findViewById(player_list_stats[i][3])).setVisibility(View.VISIBLE);
    }

    private void ManualPlayerData(){

        playersList.add(new PlayerStats("VEZENKOV", 8 , 10, 3,"1"));
        playersList.add(new PlayerStats("DORSEY",19,6,1,"1"));
        playersList.add(new PlayerStats("LOVE", 14,1,1,"2"));
        playersList.add(new PlayerStats("LOUNTZIS",9,3,2,"1"));
        playersList.add(new PlayerStats("GIANKOVITS",12,8,1,"2"));
        playersList.add(new PlayerStats("JEAN-CHARLES",16,5,1,"1"));
        playersList.add(new PlayerStats("GREENE",11,1,2,"2"));
        playersList.add(new PlayerStats("CHRISTODOULOU",3,2,1,"2"));
        playersList.add(new PlayerStats("PRINTEZIS",2,1,0,"1"));
        playersList.add(new PlayerStats("DILEO",5,1,1,"2"));

        for(int i=0; i < playersList.size(); i++){
            ((TextView) findViewById(player_list_stats[i][0])).setText(playersList.get(i).player_name);
            ((TextView) findViewById(player_list_stats[i][1])).setText(Integer.toString(playersList.get(i).player_points));
            ((TextView) findViewById(player_list_stats[i][2])).setText(Integer.toString(playersList.get(i).player_rebounds));
            ((TextView) findViewById(player_list_stats[i][3])).setText(Integer.toString(playersList.get(i).player_assists));
        }

    }
}