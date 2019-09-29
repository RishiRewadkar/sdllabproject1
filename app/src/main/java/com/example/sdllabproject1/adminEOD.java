package com.example.sdllabproject1;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class adminEOD extends AppCompatActivity {

    RecyclerView recyclerView;
    public ArrayList<projectTitles> eodList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.admin_read_eod);
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Admin eod");

        recyclerView = findViewById(R.id.rveod);
        eodList = new ArrayList<>();


        FirebaseFirestore.getInstance().collection("EmployeeEOD").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    EOD eod = documentSnapshot.toObject(EOD.class);
                    Log.d("eood", "onSuccess: "+eod.getDate()+eod.getEod());
                    eodList.add(new projectTitles(eod.getDate(), eod.getEod()));
                }

                LinearLayoutManager layoutManager = new LinearLayoutManager(adminEOD.this);
                final RecyclerView.LayoutManager rvLiLayoutManager = layoutManager;
                recyclerView.setLayoutManager(rvLiLayoutManager);
                eod_details dom = new eod_details(adminEOD.this, eodList);
                recyclerView.setAdapter(dom);
            }
        });
    }
}
