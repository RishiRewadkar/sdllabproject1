package com.example.sdllabproject1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class addProjectForm extends AppCompatActivity {


    TextView submit;
    EditText name, desc,lead;
    Spinner status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.add_project_form);
        super.onCreate(savedInstanceState);

        submit = (TextView)findViewById(R.id.Submit);
        name = (EditText) findViewById(R.id.title);
        desc = (EditText) findViewById(R.id.desc);
        lead = (EditText) findViewById(R.id.lead);
        status = (Spinner) findViewById(R.id.status);

        ArrayAdapter<String> deptAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.status_list));
        deptAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(deptAdapter);

        submit.setEnabled(true);

        if (name != null && desc != null && lead != null && status!=null) {
            submit.setEnabled(true);
            onNextClicked();
        }

    }


    private void onNextClicked() {

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<>();
                map.put("Title", name.getText().toString().trim());
                map.put("Description", desc.getText().toString().trim());
                map.put("TeamLead", lead.getText().toString().trim());
                map.put("Status", status.getSelectedItem().toString().trim());

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                // add entry to firestore
                FirebaseFirestore.getInstance().collection("Project").document(name.getText().toString()).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(addProjectForm.this, "Project added",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(addProjectForm.this, admin1.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addProjectForm.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });

    }
}
