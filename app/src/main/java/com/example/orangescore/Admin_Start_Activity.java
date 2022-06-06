package com.example.orangescore;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Admin_Start_Activity extends AppCompatActivity {
    FloatingActionButton add_teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        add_teams = findViewById(R.id.add_teams_btn);

    }
}