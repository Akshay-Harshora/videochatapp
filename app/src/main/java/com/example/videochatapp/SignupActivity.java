package com.example.videochatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

public class SignupActivity extends AppCompatActivity {
    EditText email,password,name;
    Button login,signup;
    FirebaseAuth auth;
    FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        email = (EditText)findViewById(R.id.editTextTextEmailAddress);
        password = (EditText)findViewById(R.id.editTextTextPassword);
        signup = (Button) findViewById(R.id.button);
        login = (Button)findViewById(R.id.button2);
        name = (EditText)findViewById(R.id.editTextTextPersonName);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1, pass, name1;
                email1 = email.getText().toString();
                pass = password.getText().toString();
                name1 = name.getText().toString();
                if (email1.isEmpty() || pass.isEmpty() || name1.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Enter email or password or name", Toast.LENGTH_SHORT).show();
                } else {
                    user1 user = new user1();
                    user.setEmail(email1);
                    user.setName(name1);
                    user.setPass(pass);

                    auth.createUserWithEmailAndPassword(email1, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                database.collection("Users").document("userdata").set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                    }
                                });
                                Toast.makeText(SignupActivity.this, "Account is created", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignupActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            }
        });
    }
}