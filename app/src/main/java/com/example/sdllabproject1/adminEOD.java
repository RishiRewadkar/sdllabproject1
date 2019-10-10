package com.example.sdllabproject1;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class adminEOD extends AppCompatActivity {

    RecyclerView recyclerView;
    public ArrayList<projectTitles> taskList;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.admin_read_eod);
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Admin eod");

        recyclerView = findViewById(R.id.rveod);
        taskList = new ArrayList<>();
        final String emailuser = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
        Log.d("emailele", "onCreate: "+emailuser);
        FirebaseFirestore.getInstance().collection("Project").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                String eml = emailuser;
                for (final QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                   final ProjectDetails eod = documentSnapshot.toObject(ProjectDetails.class);
                   FirebaseFirestore.getInstance().collection("Project").document(eod.getTitle()).collection("tasks").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                       @Override
                       public void onSuccess(QuerySnapshot queryDocumentSnapshot) {
                            for(final QueryDocumentSnapshot ds : queryDocumentSnapshot){
                                task t = ds.toObject(task.class);
                                Log.d("emailll", "onSuccess: "+emailuser+t.getEmployee());
                                if(!t.getEmployee().equals(null) && t.getEmployee().equals(emailuser)){
                                    taskList.add(new projectTitles(eod.getTitle(),t.getTitlet()));
                                }
                            }
                           Log.d("in", "onSuccess: "+taskList);

                           Log.d("out", "onSuccess: "+taskList);
                           LinearLayoutManager layoutManager = new LinearLayoutManager(adminEOD.this);
                           final RecyclerView.LayoutManager rvLiLayoutManager = layoutManager;
                           recyclerView.setLayoutManager(rvLiLayoutManager);
                           eod_details dom = new eod_details(adminEOD.this, taskList);
                           recyclerView.setAdapter(dom);

                       }

                   });
                }
            }
        });
    }
}
