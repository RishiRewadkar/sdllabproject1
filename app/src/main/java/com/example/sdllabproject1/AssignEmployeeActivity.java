package com.example.sdllabproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AssignEmployeeActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public ArrayList<ModelUsers> users;
    RecyclerViewAdapter2 adapter;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_employee);

        setUpRecyclerView();
        db = FirebaseFirestore.getInstance();
        db.collection("USERS").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot ds : task.getResult()) {
                    ModelUsers mu = new ModelUsers(ds.getString("UserName"), ds.getString("Role"));
                    users.add(mu);
                }
                adapter = new RecyclerViewAdapter2(AssignEmployeeActivity.this, users);
                recyclerView.setAdapter(adapter);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AssignEmployeeActivity.this, "Problem", Toast.LENGTH_SHORT).show();
                Log.v("heellooo", e.getMessage());
            }
        });

    }

    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.rvAE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
