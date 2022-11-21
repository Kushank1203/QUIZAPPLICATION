package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Quiz1_Questions extends AppCompatActivity {
    TextView heading_of_quiz;
    ListView listView;
    Button submit_button;
    String [] questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1_questions);
        listView = (ListView) findViewById(R.id.listView);
        heading_of_quiz = (TextView) findViewById(R.id.heading);
        submit_button = (Button) findViewById(R.id.submit);
    }
}