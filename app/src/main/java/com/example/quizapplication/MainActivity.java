package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView app_name;
    TextView login,forgot_pass;
    EditText username, password;
    Button sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app_name = (ImageView) findViewById(R.id.imageView);
        login = (TextView) findViewById(R.id.textView);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        forgot_pass = (TextView) findViewById(R.id.textView2);
        sign_in = (Button) findViewById(R.id.button);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String pass = password.getText().toString();
                //hello kushank

                String profArray [] = {"Prof1", "Prof2", "Prof3", "Prof4"};
                String prof_passArray [] = {"1000", "1001", "1002", "1003"};
                String studentArray [] = {"Kushank", "Vanshika", "Nishita", "Shaunak", "Nayan", "Shreyansh"};
                String student_passArray [] = {"1203", "1211", "1605", "0411", "1410", "1812"};
                if(name.length() == 0){
                    Toast.makeText(getApplicationContext(), "please enter correct username", Toast.LENGTH_SHORT).show();
                }
                else if (pass.length() == 0){
                    Toast.makeText(getApplicationContext(), "please enter correct password", Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean bl = false;
                    //checking whether professor logged in!
                    for(int i = 0; i<profArray.length; i++){
                        if(name.equals(profArray[i])){
                            if(pass.equals(prof_passArray[i])){
                                //professor activity opens...
                                Toast.makeText(getApplicationContext(), "Welcome "+profArray[i], Toast.LENGTH_SHORT).show();
                                Intent intent_prof = new Intent(MainActivity.this,ProfessorActivity1.class);
                                startActivity(intent_prof);
                                bl = true;
                                break;
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                                bl = true;
                                break;
                            }
                        }
                    }
                    //checking whether student logged in!
                    for(int i = 0; i<studentArray.length; i++){
                        if(name.equals(studentArray[i])){
                            if(pass.equals(student_passArray[i])){
                                //student activity opens...
                                Toast.makeText(getApplicationContext(), "Welcome "+studentArray[i], Toast.LENGTH_SHORT).show();
                                Intent intent_student = new Intent(MainActivity.this,StudentActivity.class);
                                startActivity(intent_student);
                                bl = true;
                                break;
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                                bl = true;
                                break;
                            }
                        }
                    }
                    //if username not in list...display not registered
                    if(bl == false){
                        Toast.makeText(getApplicationContext(), "Not Registered", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}