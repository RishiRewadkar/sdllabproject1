package com.example.sdllabproject1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class AssignTasksActivity extends AppCompatActivity {
    EditText name1, name2, name3;
    EditText task1, task2, task3;
    EditText date1, date2, date3;
    String title;
    Button save;
    FirebaseAuth firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_tasks);
        Intent i = getIntent();
        title = i.getExtras().getString("title");
        name1 = (EditText) findViewById(R.id.e1);
        name2 = (EditText) findViewById(R.id.e2);
        name3 = (EditText) findViewById(R.id.e3);
        task1 = (EditText) findViewById(R.id.task1);
        task2 = (EditText) findViewById(R.id.task2);
        task3 = (EditText) findViewById(R.id.task3);
        date1 = (EditText) findViewById(R.id.date1);
        date2 = (EditText) findViewById(R.id.date2);
        date3 = (EditText) findViewById(R.id.date3);
        save = (Button) findViewById(R.id.saveButton);
        save.setEnabled(false);
        firebaseUser = FirebaseAuth.getInstance();
        if (name1 != null && name2 != null && name3 != null) {
            save.setEnabled(true);
            onSaveClicked();
        }

    }

    private void onSaveClicked() {

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<>();
                map.put("name1", name1.getText().toString().trim());
                map.put("name2", name2.getText().toString().trim());
                map.put("name3", name3.getText().toString().trim());
                map.put("task1", task1.getText().toString().trim());
                map.put("task2", task2.getText().toString().trim());
                map.put("task3", task3.getText().toString().trim());
                map.put("date1", date1.getText().toString().trim());
                map.put("date2", date2.getText().toString().trim());
                map.put("date3", date3.getText().toString().trim());

                final Map<String,String> userMap = new HashMap<>();
                userMap.put("Status","Ongoing");
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                // add entry to firestore
                Log.d("titlelll", "onClick: "+title);
                FirebaseFirestore.getInstance().collection("Project").document(title).collection("tasks").document(title).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                       FirebaseFirestore.getInstance().collection("Project").document(title).set(userMap, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                           public void onSuccess(Void aVoid) {

                           }
                       });
                        Toast.makeText(AssignTasksActivity.this, "Project assigned", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AssignTasksActivity.this, ManagerActivity.class);
                        startActivity(intent);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AssignTasksActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });

    }
}
