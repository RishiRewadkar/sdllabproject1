package com.example.sdllabproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class hi extends AppCompatActivity {

    EditText ename;
    EditText tname;

    Button sub;


    String news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hi);
        //extras = new Bundle();
        Intent i = getIntent();
        news = i.getStringExtra("str");
        ename = (EditText) findViewById(R.id.assignedEmployee);
        tname = (EditText) findViewById(R.id.tasktitle);
        sub = (Button) findViewById(R.id.submit);

        if (ename != null && tname != null) {
            sub.setEnabled(true);
            onSubClicked();
        }
    }

    private void onSubClicked() {
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<>();
                map.put("TaskTitle", tname.getText().toString().trim());
                map.put("EmployeeName", ename.getText().toString().trim());
                // map.put("Date",Date);
                FirebaseFirestore.getInstance().collection("Project").document(news).collection("tasks").document(tname.getText().toString()).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(hi.this, "Assigned", Toast.LENGTH_LONG).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(hi.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
