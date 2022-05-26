package com.example.orangescore;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class LeagueStartActivity extends AppCompatActivity {
    TextView home, away;
    Spinner home_pick, away_pick;
    Button make_match;
    ImageView home_picture, away_picture;

    Bitmap homeBitmap, awayBitmap;

    ArrayList<String> homeList;
    ArrayList<String> awayList;

    ArrayAdapter<String> adapter_homeList;
    ArrayAdapter<String> adapter_awayList;

    QuerySnapshot queryHome;
    QuerySnapshot queryAway;

    FirebaseFirestore DB = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_start);

        /** Initialising Variables */
        home = findViewById(R.id.team_home);
        away = findViewById(R.id.team_away);

        home_pick = findViewById(R.id.home_team_spinner);
        away_pick = findViewById(R.id.away_team_spinner);

        make_match = findViewById(R.id.save_league_btn);

        home_picture = findViewById(R.id.home_imageView);
        away_picture = findViewById(R.id.away_imageView);

        homeList = new ArrayList<>();
        awayList = new ArrayList<>();

        /** Make spinners to select teams */
        adapter_homeList = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, homeList);
        adapter_homeList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        home_pick.setAdapter(adapter_homeList);

        home_pick.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(getApplicationContext(), adapter_homeList.getItem(i), Toast.LENGTH_SHORT).show();
                Log.e("ID TEAM", queryHome.getDocuments().get(i).getId());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter_awayList = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, awayList);
        adapter_awayList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        away_pick.setAdapter(adapter_awayList);

        away_pick.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(getApplicationContext(), adapter_awayList.getItem(i), Toast.LENGTH_SHORT).show();
                Log.e("ID TEAM", queryAway.getDocuments().get(i).getId());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        getDataHome();
        getDataAway();
    }

    private void getDataHome() {
        DB.collection("teams").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                queryHome = queryDocumentSnapshots;
                if (queryDocumentSnapshots.size()>0){
                    homeList.clear();
                    for (DocumentSnapshot doc: queryDocumentSnapshots) {
                        homeList.add(doc.getString("team_name"));
                    }
                    adapter_homeList.notifyDataSetChanged();
                }else{
                    Toast.makeText(getApplicationContext(), "Data not available", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataAway() {
        DB.collection("teams").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                queryAway = queryDocumentSnapshots;
                if (queryDocumentSnapshots.size()>0){
                    awayList.clear();
                    for (DocumentSnapshot doc: queryDocumentSnapshots) {
                        awayList.add(doc.getString("team_name"));
                    }
                    adapter_awayList.notifyDataSetChanged();
                }else{
                    Toast.makeText(getApplicationContext(), "Data not available", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
