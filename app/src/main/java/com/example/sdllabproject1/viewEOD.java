package com.example.sdllabproject1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class viewEOD extends AppCompatActivity {


    private FirebaseFirestore db;
    DataAdapter mdataAdapter;
    RecyclerView rvlist;
    List<Record> user_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_eod);
        db=FirebaseFirestore.getInstance();
        DocumentReference documentReference= db.collection("EmployeeEOD").document();
        rvlist=findViewById(R.id.rvuserlist);

        rvlist.setHasFixedSize(true);
        rvlist.setLayoutManager(new LinearLayoutManager(this));
        rvlist.setAdapter(mdataAdapter);
        db.collection("EmployeeEOD")

                .addSnapshotListener( new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                        for(DocumentChange doc : documentSnapshots.getDocumentChanges()){
                            doc.getDocument().toObject(Record.class);

                            if(doc.getType() == DocumentChange.Type.ADDED)
                            {
                                Record record=doc.getDocument().toObject(Record.class);
                                user_list.add(record);
                                mdataAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });


    }
}
/*
 db.collection("EmployeeEOD")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    private QuerySnapshot documentSnapshots;

                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        for(DocumentChange dc : documentSnapshots.getDocumentChanges())
                        {
                            if(dc.getType() == DocumentChange.Type.ADDED)
                            {
                                Record record=dc.getDocument().toObject(Record.class);
                                userlist.add(record);
                                mdataAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
 */