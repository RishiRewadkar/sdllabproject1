package com.example.sdllabproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class smartui extends AppCompatActivity {
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private FirebaseAuth FA=FirebaseAuth.getInstance();
    ArrayList<note> list;
    private noteadapter adapter;
    private CollectionReference NBR=db.collection("USERS");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smartui);
        FloatingActionButton buttonaddnote=findViewById(R.id.button_add_note);
        buttonaddnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(smartui.this,newnoteactivity.class));
            }
        });
        setuprecyclerview();
    }
    private void  setuprecyclerview()
    {
      String erole = FirebaseAuth.getInstance().getCurrentUser().getEmail();


        Query query=NBR.document(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString()).collection("EOD").orderBy("Date");
       FirestoreRecyclerOptions<note> options=new FirestoreRecyclerOptions.Builder<note>().setQuery(query,note.class).build();

                RecyclerView recyclerView=findViewById(R.id.recycler_view);
        adapter=new noteadapter(options);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.deleteitem(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
