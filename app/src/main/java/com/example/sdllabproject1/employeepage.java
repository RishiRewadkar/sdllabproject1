package com.example.sdllabproject1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class employeepage extends AppCompatActivity {
    EditText email,password;
    Button myeod,neweod,task;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeepage);
        myeod = findViewById(R.id.myeod);
        neweod = findViewById(R.id.neweod);
        task = findViewById(R.id.task);

        myeod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(employeepage.this,smartui.class);
                startActivity(intent1);
            }
        });
        neweod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(employeepage.this,newnoteactivity.class);
                startActivity(intent1);
            }
        });
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(employeepage.this,adminMenu.class);
                startActivity(intent1);
            }
        });
    }
}