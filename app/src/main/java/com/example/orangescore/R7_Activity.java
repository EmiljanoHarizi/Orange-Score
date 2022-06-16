package com.example.orangescore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

public class R7_Activity extends AppCompatActivity {

    Button logout_button;
    Button r8_button;
    RecyclerView teamlist;
    FirebaseFirestore fb;
     private FirestoreRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r7);

        logout_button = findViewById(R.id.logout_btn);

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        r8_button = findViewById(R.id.r8_btn);

        r8_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), R8_Activity.class));
                finish();
            }
        });

        fb = FirebaseFirestore.getInstance();
        teamlist = findViewById(R.id.team_list);

        Query query = fb.collection("teams");
        FirestoreRecyclerOptions <Teams> options = new FirestoreRecyclerOptions.Builder<Teams>().setQuery(query,Teams.class).build();
         adapter = new FirestoreRecyclerAdapter<Teams, TeamsViewHolder>(options) {
            @NonNull
            @Override
            public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_r7,parent,false);
                return new TeamsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull TeamsViewHolder holder, int position, @NonNull Teams model) {
                    holder.teamname.setText(model.getTeam_name());
                    holder.games.setText(model.getGames_played());
                    holder.wins.setText(model.getWins());
                    holder.loss.setText(model.getLoss());
                    holder.points.setText(model.getPoints());

                    String imageUri = null;
                    imageUri = model.getTeam_logo();
                    Picasso.get().load(imageUri).into(holder.logo);

            }
        };

        teamlist.setHasFixedSize(true);
        teamlist.setLayoutManager(new LinearLayoutManager(this));
        teamlist.setAdapter(adapter);
    }

    private class TeamsViewHolder extends RecyclerView.ViewHolder{

        private TextView teamname;
        private TextView games;
        private TextView wins;
        private TextView loss;
        private TextView points;
        private ImageView logo;

        public TeamsViewHolder(@NonNull View itemView) {
            super(itemView);
            teamname = itemView.findViewById(R.id.teamname);
            games= itemView.findViewById(R.id.games);
            wins= itemView.findViewById(R.id.wins);
            loss= itemView.findViewById(R.id.loss);
            points = itemView.findViewById(R.id.points);
            logo= itemView.findViewById(R.id.logo);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}