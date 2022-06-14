package com.example.orangescore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class R1_Activity extends AppCompatActivity {

    RecyclerView home_recyclerView, away_recyclerView;
    private ArrayList<Player> homePlayers_List, awayPlayers_List;
    private TeamHomeAdapter home_Adapter;
    private TeamAwayAdapter away_Adapter;
    FirebaseFirestore DB;
    Button stats_tracking;

    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(R1_Activity.this, Admin_Start_Activity.class));
        finish();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r1);

        /** Initialising Variables */
        stats_tracking = findViewById(R.id.track_stats_btn);
        DB = FirebaseFirestore.getInstance();

        /** Populating recycler view that shows the players of home team */
        home_recyclerView = findViewById(R.id.players_home_recyclerView);

        home_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        home_recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        homePlayers_List = new ArrayList<>();
        home_Adapter = new TeamHomeAdapter(this, homePlayers_List);
        home_recyclerView.setAdapter(home_Adapter);

        RetrieveHomePlayersData();

        /** Populating recycler view that shows the players of away team */
        away_recyclerView = findViewById(R.id.players_away_recyclerView);

        away_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        away_recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        awayPlayers_List = new ArrayList<>();
        away_Adapter = new TeamAwayAdapter(this, awayPlayers_List);
        away_recyclerView.setAdapter(away_Adapter);

        /** Button saves the currently selected players into 2 separate lists and starts the stats tracking activity for those players*/
        stats_tracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Getting a list both for selected players of the Home Team & selected players of Away Team,
                 * and showing the user which players are selected in short length toast*/
                if (home_Adapter.getSelectedHomePlayers().size() > 0
                        && home_Adapter.getSelectedHomePlayers().size() <5
                        && away_Adapter.getSelectedAwayPlayers().size() > 0
                        && away_Adapter.getSelectedAwayPlayers().size() <5) {
                    startActivity(new Intent(getApplicationContext(), R2_Activity.class));
                    finish();
                    StringBuilder homeStrBuilder = new StringBuilder();
                    StringBuilder awayStrBuilder = new StringBuilder();

                    for (int i = 0; i < home_Adapter.getSelectedHomePlayers().size(); i++) {
                        homeStrBuilder.append(home_Adapter.getSelectedHomePlayers().get(i).getPlayer_name());
                        homeStrBuilder.append(" ");
                        homeStrBuilder.append(home_Adapter.getSelectedHomePlayers().get(i).getPlayer_position());
                        homeStrBuilder.append("\n");

                        ShowToast(homeStrBuilder.toString().trim());
                    }
                    for (int i = 0; i < away_Adapter.getSelectedAwayPlayers().size(); i++) {
                        awayStrBuilder.append(away_Adapter.getSelectedAwayPlayers().get(i).getPlayer_name());
                        homeStrBuilder.append(" ");
                        awayStrBuilder.append(away_Adapter.getSelectedAwayPlayers().get(i).getPlayer_position());
                        awayStrBuilder.append("\n");

                        ShowToast(awayStrBuilder.toString().trim());
                    }
                } else {
                    ShowToast("Not enough or No players selected");
                }
            }
        });

        RetrieveAwayPlayersData();
    }

    private void ShowToast(String msg) {
        Toast.makeText(this, msg ,Toast.LENGTH_SHORT).show();
    }

    /** Get's all players in the currently selected home team */
    private void RetrieveHomePlayersData() {
        Bundle bundle = getIntent().getExtras();
        String homePlayers = bundle.getString("pass_home_team_name");

        DB.collection("players").whereEqualTo("player_team", homePlayers).orderBy("player_name", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("Firestore error", error.getMessage());
                    return;
                }
                for (DocumentChange homedc: value.getDocumentChanges()) {
                    if (homedc. getType() == DocumentChange.Type.ADDED) {
                        homePlayers_List.add(homedc.getDocument().toObject(Player.class));
                    }
                    home_Adapter.notifyDataSetChanged();
                }
            }
        });
    }

    /** Get's all players data in the currently selected away team */
    private void RetrieveAwayPlayersData() {
        Bundle bundle = getIntent().getExtras();
        String awayPlayers = bundle.getString("pass_away_team_name");

        DB.collection("players").whereEqualTo("player_team", awayPlayers).orderBy("player_name", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("Firestore error", error.getMessage());
                    return;
                }
                for (DocumentChange awaydc: value.getDocumentChanges()) {
                    if (awaydc. getType() == DocumentChange.Type.ADDED) {
                        awayPlayers_List.add(awaydc.getDocument().toObject(Player.class));
                    }
                    away_Adapter.notifyDataSetChanged();
                }
            }

        });
    }
}