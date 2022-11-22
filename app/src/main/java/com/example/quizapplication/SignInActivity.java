package com.example.quizapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseDatabase firebaseDatabase;
    TextView register_title;
    EditText name, email, password, mobile;
    Button register;
    RadioGroup radioGroup;
    RadioButton radioButton;
    ProgressBar progressBar;
    String mailCheck = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    int userType;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mUser = mAuth.getCurrentUser();
        progressBar = (ProgressBar) findViewById(R.id.progressBar3);
        register_title = (TextView) findViewById(R.id.sign_in);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        mobile = (EditText) findViewById(R.id.editTextNumber);
        register = (Button) findViewById(R.id.Register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }
    private void registerUser(){
        progressBar.setVisibility(View.VISIBLE);
        String text_name = name.getText().toString().trim();
        String text_email = email.getText().toString().trim();
        String text_password = password.getText().toString().trim();
        String text_mobile = mobile.getText().toString().trim();
        int val = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(val);
        if(radioButton.getText().equals("Professor")){
            userType = 0;
        }
        if(radioButton.getText().equals("Student")){
            userType = 1;
        }
        if(!text_email.matches(mailCheck)){
            email.setError("Not a valid one!");
            email.requestFocus();
            return;
        }
        //validating inputs!!!!
        if(text_name.isEmpty()){
            name.setError("Name is required!");
            name.requestFocus();
            return;
        }
        if(text_mobile.isEmpty()){
            mobile.setError("Mobile No. is required!");
            mobile.requestFocus();
            return;
        }
        if(text_email.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }
//        if(!Patterns.EMAIL_ADDRESS.matcher(text_email).matches()){
//            email.setError("Please provide email address!");
//            email.requestFocus();
//            return;
//        }
        if(text_password.isEmpty()){
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }
        if(text_password.length() < 6){
            password.setError("The password length should be 6 characters!");
            password.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(text_email,text_password).addOnCompleteListener(SignInActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user = new User(text_name,text_email,text_mobile,text_password,userType);
                    firebaseDatabase.getReference().child("Users").child(mAuth.getInstance().getCurrentUser().getUid()).setValue(user);
                    Toast.makeText(SignInActivity.this, "You have Registered Sucessfully!", Toast.LENGTH_SHORT).show();
                    if(userType == 0){
                        startActivity(new Intent(SignInActivity.this,ProfessorActivity1.class));
                    }
                    if(userType == 1){
                        startActivity(new Intent(SignInActivity.this,StudentActivity.class));
                    }
                    progressBar.setVisibility(View.GONE);
//                    FirebaseDatabase.getInstance().getReference(""+ref)
//                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()){
//                                        Toast.makeText(SignInActivity.this, "Registered The User Successfully", Toast.LENGTH_SHORT).show();
//                                        progressBar.setVisibility(View.GONE);
//                                        if (ref == "Professor"){
//                                            startActivity(new Intent(SignInActivity.this,ProfessorActivity1.class));
//                                        }
//                                        if (ref == "Student"){
//                                            startActivity(new Intent(SignInActivity.this,StudentActivity.class));
//                                        }
//                                    }
//                                    else {
//                                        Toast.makeText(SignInActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
//                                        progressBar.setVisibility(View.GONE);
//                                    }
//
//                                }
//                            });
                }
                else{
                    Toast.makeText(SignInActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }

            }
        });
    }
}