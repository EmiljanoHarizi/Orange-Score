package com.example.orangescore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class R2_Activity extends AppCompatActivity {

    private Button foul ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r2);

       foul = (Button) findViewById(R.id.foulB) ;
        final int[] counter = {0};
       foul.setOnClickListener(new View.OnClickListener() {@Override
                                   public void onClick(View view) {

                                       counter[0] = counter[0] + 1;
                                     TextView text = (TextView) findViewById(R.id.foulcounter1) ;
                                     String number1 = Integer.toString(counter[0]) ;
                                     text.setText(number1);
                                   }
                               }
       );






    }
}