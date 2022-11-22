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
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.internal.ScrimInsetsFrameLayout;

public class StudentActivity extends AppCompatActivity {
    Toolbar toolbar1;
    ImageView logout1;
    ScrollView sview;
    RecyclerView recyclerView1;
    RecyclerView.Adapter recycler_adapter1;
    RecyclerView.LayoutManager recycler_manager1;
    String [] Quizes = {"Quiz 1", "Quiz 2", "Quiz 3"};
    String [] subject = {"Data Structures And Algorithms","Discrete Mathematical Structures","Operating Systems"};
    int [] quiz_images = {R.drawable.quiz_icon,R.drawable.quiz_icon,R.drawable.quiz_icon};
    String [] start_the_quiz = {"Take Quiz1","Take Quiz2","Take Quiz3"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        toolbar1 = (Toolbar) findViewById(R.id.toolBar2);
        logout1 = (ImageView) findViewById(R.id.log_out1);
        sview = (ScrollView) findViewById(R.id.scrollView);
        //when logout icon is clicked...log out!
        logout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StudentActivity.this, "Logged Out Successfully.", Toast.LENGTH_SHORT).show();
                Intent intent_logout = new Intent(StudentActivity.this,MainActivity.class);
                startActivity(intent_logout);
            }
        });
        recyclerView1 = (RecyclerView) findViewById(R.id.recylerView2);
        recyclerView1.setHasFixedSize(true);
        recycler_manager1 = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(recycler_manager1);
        recycler_adapter1 = new MyListAdapter(this,Quizes,subject,quiz_images,start_the_quiz);
        recyclerView1.setAdapter(recycler_adapter1);

    }
}