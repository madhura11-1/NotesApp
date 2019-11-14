package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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
import com.google.firebase.firestore.FirebaseFirestore;

public class Main2Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText e1;
    EditText e2;
    EditText e3;
    private String emailrr,passrr;
    Button b3;
    private ProgressBar p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();
        e1 = findViewById(R.id.user_1);
        e2 = findViewById(R.id.emial_1);
        e3 = findViewById(R.id.pass_1);
        p= findViewById(R.id.progress);
        b3 = findViewById(R.id.button_ready);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                emailrr = e2.getText().toString().trim();
                passrr = e3.getText().toString().trim();

                if(emailrr.isEmpty())
                {
                    e2.setError("PLEASE ENTER AN EMAIL");
                    e2.requestFocus();
                    return ;
                }
                if(passrr.isEmpty())
                {
                    e3.setError("PLEASE ENTER A PASSWORD");
                    e3.requestFocus();
                    return ;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(emailrr).matches())
                {
                    e2.setError("PLEASE ENTER A VALID EMAIL");
                    e2.requestFocus();
                    return ;
                }
                p.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(emailrr,passrr)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {  Toast.makeText(Main2Activity.this, "SUCCESSFULLY COMPLTED", Toast.LENGTH_SHORT).show();
                                   p.setVisibility(View.GONE);
                                }
                                else {
                                    Toast.makeText(Main2Activity.this, "NOT DONE", Toast.LENGTH_SHORT).show();
                                    p.setVisibility(View.GONE);
                                }
                            }
                        });

            }





        });
    }



        /*Intent intent = new Intent(Main2Activity.this,Main4Activity.class);
        startActivity(intent);*/


}
