package com.example.orangescore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class R1adminActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Match> matchList;
    MatchRecyclerViewAdapter matchAdapter;
    private FirebaseFirestore DB = FirebaseFirestore.getInstance();

    ProgressDialog progressDialog;

    FloatingActionButton add_teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        add_teams = findViewById(R.id.add_teams_btn);

        recyclerView = findViewById(R.id.matches_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(matchAdapter);

        matchList = new ArrayList<Match>();
        matchAdapter = new MatchRecyclerViewAdapter(R1adminActivity.this, matchList);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data.....");
        progressDialog.show();


        add_teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LeagueStartActivity.class));
                finish();
            }
        });

        EventChangeListener();

    }

    private void EventChangeListener() {
        DB.collection("matches").orderBy("matchDate", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Log.e("Firestore error", error.getMessage());
                    return;
                }

                for(DocumentChange dc: value.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        matchList.add(dc.getDocument().toObject(Match.class));
                    }

                    matchAdapter.notifyDataSetChanged();
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }
            }
        });
    }


}