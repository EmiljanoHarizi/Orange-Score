package com.example.orangescore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class R10_Activity extends AppCompatActivity {
    Button logout;
    ArrayList<PlayerStats> playersList = new ArrayList<>();
    /** Το IP ίσως θα χρειαστει αλλαγή για να ανοιξει, αναλόγως την IP του υπολογιστη */
    private final String myIP = "192.168.1.6";
    String playerStatsURL = "http://" + myIP + "/OrangeScore/fetchPlayers.php";

    /** Για να συμπληρώσω τα Table */
    private final int[][] player_list_stats = {
            {R.id.player1, R.id.playerPoints1, R.id.playerRebounds1, R.id.playerAssists1},
            {R.id.player2, R.id.playerPoints2, R.id.playerRebounds2, R.id.playerAssists2},
            {R.id.player3, R.id.playerPoints3, R.id.playerRebounds3, R.id.playerAssists3},
            {R.id.player4, R.id.playerPoints4, R.id.playerRebounds4, R.id.playerAssists4},
            {R.id.player5, R.id.playerPoints5, R.id.playerRebounds5, R.id.playerAssists5}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r10);
        //Αν δεν κάνει η database
        //ManualPlayerData();

        //Παίρνω τα στατιστικά απο την βάση δεδομένων
        try {
            GetDataFromDB();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Logout Button
        logout = findViewById(R.id.logoutBtn2);
        logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        });

        Button points = findViewById(R.id.pointsBtn);
        Button total = findViewById(R.id.totalBtn);
        Button rebounds = findViewById(R.id.reboundsBtn);
        Button assists = findViewById(R.id.assistsBtn);


        points.setOnClickListener(v -> {
            points.setBackgroundColor(getResources().getColor(R.color.redBtn));
            total.setBackgroundColor(getResources().getColor(R.color.grayBtn));
            rebounds.setBackgroundColor(getResources().getColor(R.color.grayBtn));
            assists.setBackgroundColor(getResources().getColor(R.color.grayBtn));
            ((TextView) findViewById(R.id.titleTxt)).setText("ΠΕΡΙΣΣΟΤΕΡΟΙ ΠΟΝΤΟΙ");

            Collections.sort(playersList,new SortByPoints());

            for(int i = 0; i < 5; i++){
                ((TextView) findViewById(player_list_stats[i][0])).setText(playersList.get(i).player_name);
                ((TextView) findViewById(player_list_stats[i][1])).setText(Integer.toString(playersList.get(i).player_points));
                ((TextView) findViewById(player_list_stats[i][2])).setText(Integer.toString(playersList.get(i).player_rebounds));
                ((TextView) findViewById(player_list_stats[i][3])).setText(Integer.toString(playersList.get(i).player_assists));
            }
        });

        total.setOnClickListener(v -> {
            points.setBackgroundColor(getResources().getColor(R.color.grayBtn));
            total.setBackgroundColor(getResources().getColor(R.color.redBtn));
            rebounds.setBackgroundColor(getResources().getColor(R.color.grayBtn));
            assists.setBackgroundColor(getResources().getColor(R.color.grayBtn));
            ((TextView) findViewById(R.id.titleTxt)).setText("ΚΑΛΥΤΕΡΗ ΠΕΝΤΑΔΑ");

            Collections.sort(playersList,new SortByTotal());

            for(int i = 0; i < 5; i++){
                ((TextView) findViewById(player_list_stats[i][0])).setText(playersList.get(i).player_name);
                ((TextView) findViewById(player_list_stats[i][1])).setText(Integer.toString(playersList.get(i).player_points));
                ((TextView) findViewById(player_list_stats[i][2])).setText(Integer.toString(playersList.get(i).player_rebounds));
                ((TextView) findViewById(player_list_stats[i][3])).setText(Integer.toString(playersList.get(i).player_assists));
            }
        });

        rebounds.setOnClickListener(v -> {
            points.setBackgroundColor(getResources().getColor(R.color.grayBtn));
            total.setBackgroundColor(getResources().getColor(R.color.grayBtn));
            rebounds.setBackgroundColor(getResources().getColor(R.color.redBtn));
            assists.setBackgroundColor(getResources().getColor(R.color.grayBtn));
            ((TextView) findViewById(R.id.titleTxt)).setText("ΠΕΡΙΣΣΟΤΕΡΑ ΡΙΜΠΑΟΥΝΤ");

            Collections.sort(playersList,new SortByRebounds());

            for(int i = 0; i < 5; i++){
                ((TextView) findViewById(player_list_stats[i][0])).setText(playersList.get(i).player_name);
                ((TextView) findViewById(player_list_stats[i][1])).setText(Integer.toString(playersList.get(i).player_points));
                ((TextView) findViewById(player_list_stats[i][2])).setText(Integer.toString(playersList.get(i).player_rebounds));
                ((TextView) findViewById(player_list_stats[i][3])).setText(Integer.toString(playersList.get(i).player_assists));
            }
        });

        assists.setOnClickListener(v -> {
            points.setBackgroundColor(getResources().getColor(R.color.grayBtn));
            total.setBackgroundColor(getResources().getColor(R.color.grayBtn));
            rebounds.setBackgroundColor(getResources().getColor(R.color.grayBtn));
            assists.setBackgroundColor(getResources().getColor(R.color.redBtn));
            ((TextView) findViewById(R.id.titleTxt)).setText("ΠΕΡΙΣΣΟΤΕΡΕΣ ΑΣΙΣΤ");

            Collections.sort(playersList,new SortByAssists());

            for(int i = 0; i < 5; i++){
                ((TextView) findViewById(player_list_stats[i][0])).setText(playersList.get(i).player_name);
                ((TextView) findViewById(player_list_stats[i][1])).setText(Integer.toString(playersList.get(i).player_points));
                ((TextView) findViewById(player_list_stats[i][2])).setText(Integer.toString(playersList.get(i).player_rebounds));
                ((TextView) findViewById(player_list_stats[i][3])).setText(Integer.toString(playersList.get(i).player_assists));
            }
        });
    }


    private void GetDataFromDB() throws Exception {
        OkHttpHandler ok = new OkHttpHandler();
        /** Βάζω τους παίκτες στη λίστα */
        playersList = ok.getPlayerStats(playerStatsURL);

        /** Κάνω sort με βάση το σύνολο των πόντων/ριμπάουντ/ασιστ */
        Collections.sort(playersList,new SortByTotal());

        /** Βάζω τους top 5 παίκτες στα TextView */
        for(int i = 0; i < 5; i++){
            ((TextView) findViewById(player_list_stats[i][0])).setText(playersList.get(i).player_name);
            ((TextView) findViewById(player_list_stats[i][1])).setText(Integer.toString(playersList.get(i).player_points));
            ((TextView) findViewById(player_list_stats[i][2])).setText(Integer.toString(playersList.get(i).player_rebounds));
            ((TextView) findViewById(player_list_stats[i][3])).setText(Integer.toString(playersList.get(i).player_assists));
        }
    }

    /** Ενναλακτικά αν δεν λειτουργεί η βάση δεδομένων */
    private void ManualPlayerData(){

        /** Ενδεικτικά κάποιοι παικτες για να διαλεξω την καλύτερη πεντάδα */
//        playersList.add(new PlayerStats("ΒΕΖΕΝΚΟΦ",312,139, 37));
//        playersList.add(new PlayerStats("ΤΖΟΥΣΤΟΝ",227,176, 22));
//        playersList.add(new PlayerStats("ΠΑΠΑΓΙΑΝΝΗΣ",213,165, 27));
//        playersList.add(new PlayerStats("ΧΑΜΕΡ",282,127, 43));
//        playersList.add(new PlayerStats("ΚΑΟΥΑΝ",359,69, 131));
//        playersList.add(new PlayerStats("ΛΟΒ",309,62, 56));
//        playersList.add(new PlayerStats("ΟΥΑΙΤ",271,125, 28));
//        playersList.add(new PlayerStats("ΓΚΡΑΝΤ",257,129, 36));
//        playersList.add(new PlayerStats("ΓΚΑΡΕΤ",234,142, 14));
//        playersList.add(new PlayerStats("ΜΟΥΡΑΤΟΣ",271,74, 152));
//        playersList.add(new PlayerStats("ΣΛΟΥΚΑΣ",203,33, 107));
//        playersList.add(new PlayerStats("ΑΓΡΑΒΑΝΗΣ",262,131, 43));
//        playersList.add(new PlayerStats("ΚΡΟΥΜΠΑΛΙ",226,203, 36));

        /** Κάνω sort με βάση το σύνολο τον πόντων/ριμπάουντ/ασιστ */
        Collections.sort(playersList,new SortByTotal());

        /** Βάζω τους top 5 παίκτες στα TextView */
        for(int i = 0; i < 5; i++){
            ((TextView) findViewById(player_list_stats[i][0])).setText(playersList.get(i).player_name);
            ((TextView) findViewById(player_list_stats[i][1])).setText(Integer.toString(playersList.get(i).player_points));
            ((TextView) findViewById(player_list_stats[i][2])).setText(Integer.toString(playersList.get(i).player_rebounds));
            ((TextView) findViewById(player_list_stats[i][3])).setText(Integer.toString(playersList.get(i).player_assists));
        }

    }

}

/** Για sort */
class SortByTotal implements Comparator<PlayerStats>{

    @Override
    public int compare(PlayerStats ps1, PlayerStats ps2) {
        return ps2.getPlayerTotal() - ps1.getPlayerTotal();
    }
}

class SortByRebounds implements Comparator<PlayerStats>{

    @Override
    public int compare(PlayerStats ps1, PlayerStats ps2) {
        return ps2.player_rebounds - ps1.player_rebounds;
    }
}

class SortByPoints implements Comparator<PlayerStats>{

    @Override
    public int compare(PlayerStats ps1, PlayerStats ps2) {
        return ps2.player_points - ps1.player_points;
    }
}

class SortByAssists implements Comparator<PlayerStats>{

    @Override
    public int compare(PlayerStats ps1, PlayerStats ps2) {
        return ps2.player_assists - ps1.player_assists;
    }
}