package com.example.quizapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class ProfessorActivity1 extends AppCompatActivity {
    Toolbar toolbar;
    Button add;
    ImageView logout;
    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager recycler_manager;
    String [] Quizes = {"Quiz 1", "Quiz 2", "Quiz 3"};
    String [] subject = {"Data Structures And Algorithms","Discrete Mathematical Structures","Operating Systems"};
    int [] quiz_images = {R.drawable.quiz_icon,R.drawable.quiz_icon,R.drawable.quiz_icon,R.drawable.quiz_icon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor1);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        logout = (ImageView) findViewById(R.id.log_out);
        add = (Button) findViewById(R.id.add);
        //when logout icon is clicked...log out!
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfessorActivity1.this, "Logged Out Successfully.", Toast.LENGTH_SHORT).show();
                Intent intent_logout = new Intent(ProfessorActivity1.this,MainActivity.class);
                startActivity(intent_logout);
            }
        });
        //when add button is clicked add a quiz question.
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //THINK OF A CODE
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recylerView);
        recyclerView.setHasFixedSize(true);
        recycler_manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recycler_manager);
        recycler_adapter = new ProgramAdapter(this,Quizes,subject,quiz_images);
        recyclerView.setAdapter(recycler_adapter);
    }
}