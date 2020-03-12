package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Main4Activity extends AppCompatActivity {

    private EditText noty;
    private Button bn;
    private static final String S="NOTES";
    int flag=0;
    FirebaseAuth authr;
    FirebaseFirestore fb = FirebaseFirestore.getInstance();
    CollectionReference cd = fb.collection("NOTES");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        authr = FirebaseAuth.getInstance();
        noty = findViewById(R.id.edit_text_notes);
        bn = findViewById(R.id.notebutton);

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = noty.getText().toString().trim();

                if(s.isEmpty())
                {
                    noty.setError("PLEASE ENTER A NOTE");
                    noty.requestFocus();
                    return ;
                }
                FirebaseUser user = authr.getCurrentUser();
                 if(user!=null) {
                     cd.document(user.getUid()).update("notes", s).addOnSuccessListener(new OnSuccessListener<Void>() {
                         @Override
                         public void onSuccess(Void aVoid) {
                             Toast.makeText(Main4Activity.this, "DONE", Toast.LENGTH_SHORT).show();
                         }
                     })
                             .addOnFailureListener(new OnFailureListener() {
                                 @Override
                                 public void onFailure(@NonNull Exception e) {
                                     Toast.makeText(Main4Activity.this, "NOT DONE", Toast.LENGTH_SHORT).show();
                                 }
                             });

                 }
}
    });
}

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser use = authr.getCurrentUser();
        if(use!=null)
        {

           cd.addSnapshotListener(new EventListener<QuerySnapshot>() {
               @Override
               public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                   if(e!=null)
                       return ;
                   FirebaseUser user =authr.getCurrentUser();
                   String data="";
                   for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots)
                   {
                       info ins = documentSnapshot.toObject(info.class);
                       if(user.getUid().equals(documentSnapshot.getId()))
                       {
                           data = ins.getNotes();
                           break;
                       }
                   }
                   noty.setText(data);
               }
           });


        }

    }
}