package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main3Activity extends AppCompatActivity {

    private FirebaseAuth auths;
    EditText user;
    EditText password;
    Button b4;
    String email,pass;
    private ProgressBar p1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        auths = FirebaseAuth.getInstance();
        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        b4 = findViewById(R.id.Button_ready_2);
        p1= findViewById(R.id.progress2);



        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = user.getText().toString();
                pass = password.getText().toString();
                p1.setVisibility(View.VISIBLE);
                auths.signInWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(Main3Activity.this, "HERES YOUR ACCOUNT", Toast.LENGTH_SHORT).show();
                                    p1.setVisibility(View.GONE);
                                    Intent intent = new Intent(Main3Activity.this,Main4Activity.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(Main3Activity.this, "SORRY CANNOT PROCEED YOUR REQUEST", Toast.LENGTH_SHORT).show();
                                    p1.setVisibility(View.GONE);
                                }
                            }
                        });


            }

        });
    }
}
