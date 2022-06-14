package com.example.orangescore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LeagueStartActivity extends AppCompatActivity {
    private TextView home, away, date;
    private Spinner home_pick, away_pick;
    private ImageView home_picture, away_picture;
    private EditText edit_date;
    private String homeImage,awayImage;


    private static final String TAG = "LeagueStartActivity";

    ArrayList<String> homeList;
    ArrayList<String> awayList;

    ArrayAdapter<String> adapter_homeList;
    ArrayAdapter<String> adapter_awayList;

    QuerySnapshot queryHome;
    QuerySnapshot queryAway;

    private FirebaseFirestore DB = FirebaseFirestore.getInstance();

    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LeagueStartActivity.this, Admin_Start_Activity.class));
        finish();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_start);

        /** Initialising Variables */
        home = findViewById(R.id.team_home);
        away = findViewById(R.id.team_away);
        date = findViewById(R.id.matchDate_textView);
        edit_date = findViewById(R.id.matchDate_editText);

        home_pick = findViewById(R.id.home_team_spinner);
        away_pick = findViewById(R.id.away_team_spinner);

        home_picture = findViewById(R.id.home_imageView);
        away_picture = findViewById(R.id.away_imageView);

        homeList = new ArrayList<>();
        awayList = new ArrayList<>();

        /** Selecting a team from the spinner sets it as Home Team */
        adapter_homeList = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, homeList);
        adapter_homeList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        home_pick.setAdapter(adapter_homeList);

        home_pick.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                Log.e("ID TEAM", queryHome.getDocuments().get(i).getId());
                homeImage = "https://upload.wikimedia.org/wikipedia/en/2/22/AEK_NEW_LOGO_3_STARS.png";
                Picasso.get().load(homeImage).into(home_picture);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        /** Selecting a team from the spinner sets it as Away Team */
        adapter_awayList = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, awayList);
        adapter_awayList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        away_pick.setAdapter(adapter_awayList);

        away_pick.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                Log.e("ID TEAM", queryAway.getDocuments().get(i).getId());
                awayImage = "https://upload.wikimedia.org/wikipedia/en/thumb/7/7f/Olympiacos_BC_logo.svg/1200px-Olympiacos_BC_logo.svg.png";
                Picasso.get().load(awayImage).into(away_picture);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        getDataHome();
        getDataAway();
    }

    /** Gets team data and creates a list of the teams in the league to pick as a home team*/
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

    /** Gets team data and creates a list of the teams in the league to pick as an away team*/
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

    /** Adds match data to our database*/
    public void CreateMatch(View view) {

        String selected_team_home = home_pick.getSelectedItem().toString();
        String selected_team_away = away_pick.getSelectedItem().toString();
        String match_date = edit_date.getText().toString();


        Match newMatch = new Match(selected_team_home, selected_team_away, match_date);

        Toast.makeText(this, "Added match " + selected_team_home + "VS " + selected_team_away , Toast.LENGTH_LONG).show();
        DB.collection("matches").add(newMatch).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "Match has been added successfully");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error adding match");
            }
        });

    }

}
