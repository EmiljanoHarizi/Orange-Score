package com.example.orangescore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class R6_Activity extends AppCompatActivity {
    Button logout;
    //ΚΑΠΟΙΑ ΑΠΟ ΤΑ ΠΑΡΑΚΑΤΩ ΣΗΜΕΙΑ ΕΧΟΥΝ ΓΡΑΦΤΕΙ ΩΣ ΣΧΟΛΙΑ, ΓΙΑΤΙ ΑΛΛΙΩΣ ΔΕΝ ΕΜΦΑΝΙΖΟΝΤΑΝ Η ΣΕΛΙΔΑ ΜΟΥ(αυτα που αφορουν τη βαση)
    //ΟΙ ΜΕΤΑΒΛΗΤΕΣ ΠΟΥ ΘΑ ΧΡΕΙΑΣΤΩ ΓΙΑ ΝΑ ΠΕΡΑΣΩ ΤΙΣ ΑΝΤΙΣΤΟΙΧΕΣ ΤΙΜΕΣ ΣΤΑ TEXTVIEWS ΓΙΑ ΤΙΣ ΟΜΑΔΕΣ ΚΑΙ ΤΟΥΣ ΠΑΙΧΤΕΣ
    /*RecyclerView homerec, awayrec;
    private TeamHomeAdapter homead;
    private TeamAwayAdapter awayad;
    private String player;
    private ArrayList<Player> hplayers;
    private ArrayList<Player> aplayers;
    FirebaseFirestore db;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r6);

        //ΔΗΛΩΣΗ ΜΕΤΑΒΛΗΤΩΝ
        logout = findViewById(R.id.logout_btn);

        //Ο ΧΡΗΣΤΗΣ ΔΙΑΛΕΓΕΙ ΠΟΙΟ ΜΑΤΣ ΘΑ ΔΕΙ ΚΑΙ ΜΕΤΑ ΑΠΟ ΤΟ ΕΠΙΛΕΓΜΕΝΟ ΜΑΤΣ ΕΜΦΑΝΙΖΟΝΤΑΙ ΟΙ ΠΑΡΑΚΑΤΩ ΠΛΗΡΟΦΟΡΙΕΣ ΩΣ ΠΕΡΑΣΜΑ
        /*db = FirebaseFirestore.getInstance();
        TextView home = findViewById(R.id.team1);
        TextView away = findViewById(R.id.team2);

        TextView hplayer1 = findViewById(R.id.t1player1);
        TextView hplayer2 = findViewById(R.id.t1player2);
        TextView hplayer3 = findViewById(R.id.t1player3);
        TextView hplayer4 = findViewById(R.id.t1player4);
        TextView hplayer5 = findViewById(R.id.t1player5);
        TextView aplayer1 = findViewById(R.id.t2player1);
        TextView aplayer2 = findViewById(R.id.t2player2);
        TextView aplayer3 = findViewById(R.id.t2player3);
        TextView aplayer4 = findViewById(R.id.t2player4);
        TextView aplayer5 = findViewById(R.id.t2player5);

        TextView hpnt1 = findViewById(R.id.pnt1);
        TextView hpnt2 = findViewById(R.id.pnt2);
        TextView hpnt3 = findViewById(R.id.pnt3);
        TextView hpnt4 = findViewById(R.id.pnt4);
        TextView hpnt5 = findViewById(R.id.pnt5);
        TextView hpnt6 = findViewById(R.id.pnt6);
        TextView apnt1 = findViewById(R.id.pnt7);
        TextView apnt2 = findViewById(R.id.pnt8);
        TextView apnt3 = findViewById(R.id.pnt9);
        TextView apnt4 = findViewById(R.id.pnt10);
        TextView apnt5 = findViewById(R.id.pnt11);
        TextView apnt6 = findViewById(R.id.pnt12);

        TextView hrb1 = findViewById(R.id.rb1);
        TextView hrb2 = findViewById(R.id.rb2);
        TextView hrb3 = findViewById(R.id.rb3);
        TextView hrb4 = findViewById(R.id.rb4);
        TextView hrb5 = findViewById(R.id.rb5);
        TextView hrb6 = findViewById(R.id.rb6);
        TextView arb1 = findViewById(R.id.rb7);
        TextView arb2 = findViewById(R.id.rb8);
        TextView arb3 = findViewById(R.id.rb9);
        TextView arb4 = findViewById(R.id.rb10);
        TextView arb5 = findViewById(R.id.rb11);
        TextView arb6 = findViewById(R.id.rb12);

        TextView has1 = findViewById(R.id.as1);
        TextView has2 = findViewById(R.id.as2);
        TextView has3 = findViewById(R.id.as3);
        TextView has4 = findViewById(R.id.as4);
        TextView has5 = findViewById(R.id.as5);
        TextView has6 = findViewById(R.id.as6);
        TextView aas1 = findViewById(R.id.as7);
        TextView aas2 = findViewById(R.id.as8);
        TextView aas3 = findViewById(R.id.as9);
        TextView aas4 = findViewById(R.id.as10);
        TextView aas5 = findViewById(R.id.as11);
        TextView aas6 = findViewById(R.id.as12);

        TextView hpos1 = findViewById(R.id.pos1);
        TextView hpos2 = findViewById(R.id.pos2);
        TextView hpos3 = findViewById(R.id.pos3);
        TextView hpos4 = findViewById(R.id.pos4);
        TextView hpos5 = findViewById(R.id.pos5);
        TextView hpos6 = findViewById(R.id.pos6);
        TextView apos1 = findViewById(R.id.pos7);
        TextView apos2 = findViewById(R.id.pos8);
        TextView apos3 = findViewById(R.id.pos9);
        TextView apos4 = findViewById(R.id.pos10);
        TextView apos5 = findViewById(R.id.pos11);
        TextView apos6 = findViewById(R.id.pos12);

        TextView hep1 = findViewById(R.id.ep1);
        TextView hep2 = findViewById(R.id.ep2);
        TextView hep3 = findViewById(R.id.ep3);
        TextView hep4 = findViewById(R.id.ep4);
        TextView hep5 = findViewById(R.id.ep5);
        TextView hep6 = findViewById(R.id.ep6);
        TextView aep1 = findViewById(R.id.ep7);
        TextView aep2 = findViewById(R.id.ep8);
        TextView aep3 = findViewById(R.id.ep9);
        TextView aep4 = findViewById(R.id.ep10);
        TextView aep5 = findViewById(R.id.ep11);
        TextView aep6 = findViewById(R.id.ep12);

        TextView hap1 = findViewById(R.id.ap1);
        TextView hap2 = findViewById(R.id.ap2);
        TextView hap3 = findViewById(R.id.ap3);
        TextView hap4 = findViewById(R.id.ap4);
        TextView hap5 = findViewById(R.id.ap5);
        TextView hap6 = findViewById(R.id.ap6);
        TextView aap1 = findViewById(R.id.ap7);
        TextView aap2 = findViewById(R.id.ap8);
        TextView aap3 = findViewById(R.id.ap9);
        TextView aap4 = findViewById(R.id.ap10);
        TextView aap5 = findViewById(R.id.ap11);
        TextView aap6 = findViewById(R.id.ap12);

        TextView hfl1 = findViewById(R.id.fl1);
        TextView hfl2 = findViewById(R.id.fl2);
        TextView hfl3 = findViewById(R.id.fl3);
        TextView hfl4 = findViewById(R.id.fl4);
        TextView hfl5 = findViewById(R.id.fl5);
        TextView hfl6 = findViewById(R.id.fl6);
        TextView afl1 = findViewById(R.id.fl7);
        TextView afl2 = findViewById(R.id.fl8);
        TextView afl3 = findViewById(R.id.fl9);
        TextView afl4 = findViewById(R.id.fl10);
        TextView afl5 = findViewById(R.id.fl11);
        TextView afl6 = findViewById(R.id.fl12);

        TextView hkl1 = findViewById(R.id.kl1);
        TextView hkl2 = findViewById(R.id.kl2);
        TextView hkl3 = findViewById(R.id.kl3);
        TextView hkl4 = findViewById(R.id.kl4);
        TextView hkl5 = findViewById(R.id.kl5);
        TextView hkl6 = findViewById(R.id.kl6);
        TextView akl1 = findViewById(R.id.kl7);
        TextView akl2 = findViewById(R.id.kl8);
        TextView akl3 = findViewById(R.id.kl9);
        TextView akl4 = findViewById(R.id.kl10);
        TextView akl5 = findViewById(R.id.kl11);
        TextView akl6 = findViewById(R.id.kl12);

        TextView hko1 = findViewById(R.id.ko1);
        TextView hko2 = findViewById(R.id.ko2);
        TextView hko3 = findViewById(R.id.ko3);
        TextView hko4 = findViewById(R.id.ko4);
        TextView hko5 = findViewById(R.id.ko5);
        TextView hko6 = findViewById(R.id.ko6);
        TextView ako1 = findViewById(R.id.ko7);
        TextView ako2 = findViewById(R.id.ko8);
        TextView ako3 = findViewById(R.id.ko9);
        TextView ako4 = findViewById(R.id.ko10);
        TextView ako5 = findViewById(R.id.ko11);
        TextView ako6 = findViewById(R.id.ko12);

        StringBuilder homebuilder = new StringBuilder();
        StringBuilder awaybuilder = new StringBuilder();

        hplayers = new ArrayList<>();
        aplayers = new ArrayList<>();
        homead = new TeamHomeAdapter(this, hplayers);
        homerec.setAdapter(homead);
        awayad = new TeamAwayAdapter(this, aplayers);
        awayrec.setAdapter(awayad);
        //ΠΕΡΝΑΩ ΣΤΑ TEXTVIEW ΤΩΝ ΠΑΙΧΤΩΝ ΜΟΥ ΤΑ ΟΝΟΜΑΤΑ ΤΟΥΣ ΓΙΑ ΤΗΝ ΟΜΑΔΑ ΠΟΥ ΠΑΙΖΕΙ ΕΝΤΟΣ ΕΔΡΑΣ
        for (int i = 0; i < homead.getSelectedHomePlayers().size(); i++) {
            if(i==0) {
                player = (homebuilder.append(homead.getSelectedHomePlayers().get(i).getPlayer_name())).toString();
                hplayer1.setText(player);
            }else if(i==1) {
                player = (homebuilder.append(homead.getSelectedHomePlayers().get(i).getPlayer_name())).toString();
                hplayer2.setText(player);
            }else if(i==2) {
                player = (homebuilder.append(homead.getSelectedHomePlayers().get(i).getPlayer_name())).toString();
                hplayer3.setText(player);
            }else if(i==3) {
                player = (homebuilder.append(homead.getSelectedHomePlayers().get(i).getPlayer_name())).toString();
                hplayer4.setText(player);
            }else if(i==4) {
                player = (homebuilder.append(homead.getSelectedHomePlayers().get(i).getPlayer_name())).toString();
                hplayer5.setText(player);
            }
        }
        //ΠΕΡΝΑΩ ΣΤΑ TEXTVIEW ΤΩΝ ΠΑΙΧΤΩΝ ΜΟΥ ΤΑ ΟΝΟΜΑΤΑ ΤΟΥΣ ΚΑΙ ΓΙΑ ΤΗΝ ΟΜΑΔΑ ΠΟΥ ΠΑΙΖΕΙ ΕΚΤΟΣ ΕΔΡΑΣ
        for (int i = 0; i < awayad.getSelectedAwayPlayers().size(); i++) {
            if(i==0) {
                player = (awaybuilder.append(awayad.getSelectedAwayPlayers().get(i).getPlayer_name())).toString();
                aplayer1.setText(player);
            }else if(i==1) {
                player = (awaybuilder.append(awayad.getSelectedAwayPlayers().get(i).getPlayer_name())).toString();
                aplayer2.setText(player);
            }else if(i==2) {
                player = (awaybuilder.append(awayad.getSelectedAwayPlayers().get(i).getPlayer_name())).toString();
                aplayer3.setText(player);
            }else if(i==3) {
                player = (awaybuilder.append(awayad.getSelectedAwayPlayers().get(i).getPlayer_name())).toString();
                aplayer4.setText(player);
            }else if(i==4) {
                player = (awaybuilder.append(awayad.getSelectedAwayPlayers().get(i).getPlayer_name())).toString();
                aplayer5.setText(player);
            }
        }*/
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }
}
