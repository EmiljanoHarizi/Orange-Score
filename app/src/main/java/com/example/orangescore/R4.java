package com.example.orangescore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class R4 extends AppCompatActivity {

    EditText team, name, position, photo;
    String team_str, name_str, position_str, photo_str;
    Button button_submit, logout_button;

    private FirebaseFirestore DB = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r4);

        logout_button = findViewById(R.id.logout_btn);
        button_submit = findViewById(R.id.submit_btn);

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginTabFragment.class));
                finish();
            }
        });
    }

    public void onSubmitClick(View view) {
        team = findViewById(R.id.teamEditText);
        name = findViewById(R.id.nameEditText);
        position = findViewById(R.id.positionEditText);
        photo = findViewById(R.id.photoEditText);

        team_str = team.getText().toString();
        name_str = name.getText().toString();
        position_str = position.getText().toString();
        photo_str = photo.getText().toString();

        Player p = new Player(team_str, name_str, position_str, photo_str);
        Log.d("R4", "Submitted player" + p.getPlayer_name()  + "from team" + p.getPlayer_team());

        DB.collection("players").add(p).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("R4", "Player entry added successfully");
                Toast.makeText(R4.this, "Player has been added ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}