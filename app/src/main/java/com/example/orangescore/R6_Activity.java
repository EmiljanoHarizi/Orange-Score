package com.example.orangescore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class R6_Activity extends AppCompatActivity {
    Button logout;
    private TextView home, away, player;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r6);
        home = findViewById(R.id.team1);
        player = findViewById(R.id.t1player1);
        away = findViewById(R.id.team2);
        //ΠΕΡΑΣΜΑ ΤΩΝ ΤΙΜΩΝ ΤΗΣ ΒΑΣΗΣ ΣΤΑ TEXTVIEWS
        /*firebaseAuth= FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        DocumentReference documentReference = firestore.collection("matches").document("homeTeam");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                String h = value.getString("homeTeam");
                String a = value.getString("awayTeam");
                home.setText(h);
                away.setText(a);
            }
        });*/

        logout = findViewById(R.id.logout_btn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }
}
