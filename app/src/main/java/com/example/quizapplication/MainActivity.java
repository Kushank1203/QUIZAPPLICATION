package com.example.quizapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    ImageView app_name;
    TextView login, create_acc;
    EditText email_user, password_user;
    Button sign_in;
    String mailCheck = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    FirebaseUser mUser;
    int userType;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        app_name = (ImageView) findViewById(R.id.imageView);
        login = (TextView) findViewById(R.id.textView);
        email_user = (EditText) findViewById(R.id.username);
        password_user = (EditText) findViewById(R.id.password);
        sign_in = (Button) findViewById(R.id.button);
        create_acc = (TextView) findViewById(R.id.createAccount);
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        userID = mUser.getUid();
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
        //Register Account
        create_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registration = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(registration);
            }
        });
    }
    //userLogin Method
    private void userLogin() {
        progressBar.setVisibility(View.VISIBLE);
        String email = email_user.getText().toString().trim();
        String password = password_user.getText().toString().trim();

        if (email.isEmpty()) {
            email_user.setError("Email is required!");
            email_user.requestFocus();
            return;
        }
        if (!email.matches(mailCheck)) {
            email_user.setError("Not a valid one!");
            email_user.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            password_user.setError("Password is required!");
            password_user.requestFocus();
            return;
        }
        if (password.length() < 6) {
            password_user.setError("The password length should be 6 characters!");
            password_user.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    userID = task.getResult().getUser().getUid();
                    FirebaseDatabase data = FirebaseDatabase.getInstance();
                    data.getReference().child("Users").child(userID).child("userType").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            userType = snapshot.getValue(Integer.class);
                            Intent intent;
                            if(userType==0)
                            {
                                intent = new Intent(MainActivity.this, ProfessorActivity1.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                            }
                            if(userType==1)
                            {
                                intent = new Intent(MainActivity.this, StudentActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                    progressBar.setVisibility(View.GONE);
                }
                else
                {
                    Toast.makeText(MainActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}
