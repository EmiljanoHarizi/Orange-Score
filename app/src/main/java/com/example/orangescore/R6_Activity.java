package com.example.orangescore;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class R6_Activity extends AppCompatActivity {
    Button logout;
    Button next;
    /*FirebaseFirestore db;
    RecyclerView pList;
    RecyclerView tList;
    private FirestoreRecyclerAdapter adp;
    private FirestoreRecyclerAdapter adt;*/

    //ΕΙΧΑ ΓΡΑΨΕΙ ΤΗΝ ΣΕΛΙΔΑ ΜΟΥ ΩΣΤΕ ΝΑ ΕΜΦΑΝΙΖΟΝΤΑΙ ΟΛΑ ΜΕΣΑ ΑΠΟ RECYCLEVIEW
    //ME LAYOUTS: R6_LIST, R6_RECYCLEVIEW
    //, ΑΛΛΑ ΔΕΝ ΦΟΡΤΩΝΟΝΤΑΝ ΚΑΙ ΜΕ ΠΕΤΟΥΣΕ ΣΥΝΕΧΕΙΑ ΕΞΩ, ΟΠΟΤΕ ΕΒΑΛΑ ΤΙΣ ΕΝΤΟΛΕΣ
    //ΠΟΥ ΗΤΑΝ ΣΧΕΤΙΚΕΣ ΜΕ ΤΗΝ ΚΛΗΣΗ ΣΕ ΣΧΟΛΙΑ ΚΑΙ ΕΦΤΙΑΞΑ ΕΝΑ ΝΕΟ LAYOUT
    //ΣΤΟ ΟΠΟΙΟ ΕΙΝΑΙ ΜΟΡΦΟΠΟΙΗΜΕΝΗ Η ΣΕΛΙΔΑ




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r6);
        logout = findViewById(R.id.btn_logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
        next = findViewById(R.id.btn_next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), R7_Activity.class));
                finish();
            }
        });/*
        db = FirebaseFirestore.getInstance();

        pList = findViewById(R.id.play_list);
        tList = findViewById(R.id.team_list);

        //Querys
        Query queryp = db.collection("players");
        Query queryt = db.collection("teams");
        //Recycler options
        FirestoreRecyclerOptions<R6_Players> optionsp = new FirestoreRecyclerOptions.Builder<R6_Players>().setQuery(queryp, R6_Players.class).build();
        FirestoreRecyclerOptions<R6_Teams> optionst = new FirestoreRecyclerOptions.Builder<R6_Teams>().setQuery(queryt, R6_Teams.class).build();

        adp = new FirestoreRecyclerAdapter<R6_Players, playersViewHolder>(optionsp) {
           @Override
            protected void onBindViewHolder(@NonNull playersViewHolder holder, int position, @NonNull R6_Players model) {
                holder.listname.setText(model.getPlayer_name());
                holder.listpnt.setText(model.getPnt());
                holder.listrib.setText(model.getRib());
                holder.listpos.setText(model.getPos());
                holder.listep.setText(model.getEp());
                holder.listap.setText(model.getAp());
                holder.listfl.setText(model.getFl());
                holder.listkl.setText(model.getKl());
                holder.listko.setText(model.getKo());
            }
        };

        adt = new FirestoreRecyclerAdapter<R6_Teams, teamViewHolder>(optionst) {
           @Override
            protected void onBindViewHolder(@NonNull teamViewHolder holder, int position, @NonNull R6_Teams model) {
                holder.listname.setText(model.getTeam_name());
                holder.listpnt.setText(model.getPnt());
                holder.listrib.setText(model.getRib());
                holder.listpos.setText(model.getPos());
                holder.listep.setText(model.getEp());
                holder.listap.setText(model.getAp());
                holder.listfl.setText(model.getFl());
                holder.listkl.setText(model.getKl());
                holder.listko.setText(model.getKo());
            }
        };

        pList.setHasFixedSize(true);
        pList.setLayoutManager(new LinearLayoutManager(this));
        pList.setAdapter(adp);
        tList.setHasFixedSize(true);
        tList.setLayoutManager(new LinearLayoutManager(this));
        tList.setAdapter(adt);
    }

    private class playersViewHolder extends  RecyclerView.ViewHolder{
        private TextView listname;
        private TextView listpnt;
        private TextView listrib;
        private TextView listpos;
        private TextView listep;
        private TextView listap;
        private TextView listfl;
        private TextView listkl;
        private TextView listko;

        public playersViewHolder(@NonNull View itemView) {
            super(itemView);

            listname = itemView.findViewById(R.id.namelist);
            listpnt = itemView.findViewById(R.id.point);
            listrib = itemView.findViewById(R.id.riba);
            listpos = itemView.findViewById(R.id.asis);
            listep = itemView.findViewById(R.id.posi);
            listap = itemView.findViewById(R.id.epi);
            listfl = itemView.findViewById(R.id.apo);
            listkl = itemView.findViewById(R.id.kle);
            listko = itemView.findViewById(R.id.kop);

        }
    }

    private class teamViewHolder extends  RecyclerView.ViewHolder{
        private TextView listname;
        private TextView listpnt;
        private TextView listrib;
        private TextView listpos;
        private TextView listep;
        private TextView listap;
        private TextView listfl;
        private TextView listkl;
        private TextView listko;

        public teamViewHolder(@NonNull View itemView) {
            super(itemView);

            listname = itemView.findViewById(R.id.namelist);
            listpnt = itemView.findViewById(R.id.point);
            listrib = itemView.findViewById(R.id.riba);
            listpos = itemView.findViewById(R.id.asis);
            listep = itemView.findViewById(R.id.posi);
            listap = itemView.findViewById(R.id.epi);
            listfl = itemView.findViewById(R.id.apo);
            listkl = itemView.findViewById(R.id.kle);
            listko = itemView.findViewById(R.id.kop);

        }
    }
    @Override
    protected void onStop(){
        super.onStop();
        adp.stopListening();
    }

    @Override
    protected void onStart(){
        super.onStart();
        adp.startListening();
    }*/
}}
