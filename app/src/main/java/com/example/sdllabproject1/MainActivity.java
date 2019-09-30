package com.example.sdllabproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button buttonregister;
    private EditText email,password,role;
    private TextView signup;
    private ProgressDialog PD;
    private FirebaseAuth FA;
    private FirebaseFirestore FS;
    private DocumentReference noteref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PD=new ProgressDialog(this);
        FA=FirebaseAuth.getInstance();
        FS=FirebaseFirestore.getInstance();
        getSupportActionBar().setTitle("Home");
        buttonregister=(Button) findViewById(R.id.buttonregister);
        email=(EditText)findViewById(R.id.loginemail);
        password=(EditText)findViewById(R.id.loginpassword);
        role=(EditText)findViewById(R.id.role);
        signup=(TextView)findViewById(R.id.textView2);
        buttonregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity4();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity5();

            }
        });
    }
    public void openactivity1()
    {
        Intent intent=new Intent(this,adminpage.class);
        startActivity(intent);

    }

    public void openactivity4()
    {

        String username=email.getText().toString().trim();
        String pass=password.getText().toString().trim();
        String task=role.getText().toString().trim();

        if(TextUtils.isEmpty(username))
        {
            Toast.makeText(this,"Please ENter Email Address",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass))
        {
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }
        PD.setMessage("Registering user......");
        PD.show();
        FA.createUserWithEmailAndPassword(username,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Registered SUccessfully", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Could not register please try again", Toast.LENGTH_SHORT).show();

                }
            }

        });
        Map<String ,Object> usermap=new HashMap<>();
        usermap.put("UserName",username);
        usermap.put("Role",task);
       FS.collection("USERS").document(FA.getCurrentUser().getEmail().toString()).set(usermap);

    }

    public void openactivity5()
    {
        Intent intent=new Intent(this,login.class);
        startActivity(intent);
    }
}
