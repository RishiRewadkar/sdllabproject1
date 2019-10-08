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
        users = new ArrayList<>();
        setUpRecyclerView();

        db = FirebaseFirestore.getInstance();
        /*FirebaseFirestore.getInstance().collection("Project").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                   ModelUsers mu = documentSnapshot.toObject(ModelUsers.class);
                    if(mu.getRole().equals("Employee")) {
                        users.add(mu.getUserName());
                    }
                }

            }
        });
       */
        db.collection("USERS").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot ds : task.getResult()) {

                    ModelUsers mu = new ModelUsers(ds.getString("UserName"), ds.getString("role"));
                    if (mu.getRole().equalsIgnoreCase("Employee")) {
                        users.add(mu);
                    }

                }



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
        LinearLayoutManager manager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter2(AssignEmployeeActivity.this, users);
        recyclerView.setAdapter(adapter);
    }
}
