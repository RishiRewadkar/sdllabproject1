package com.example.sdllabproject1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class adminpage extends AppCompatActivity {
    EditText email,password;
    Button login;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage);
        getSupportActionBar().setTitle("Admin login");
        email=(EditText) findViewById(R.id.emailadmin);
        password=(EditText) findViewById(R.id.passwordadmin);
        login=(Button)findViewById(R.id.loginadmin);
        firebaseAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String username=email.getText().toString().trim();
                String epassword=password.getText().toString().trim();

                if(TextUtils.isEmpty(username))
                {
                    Toast.makeText(adminpage.this,"Please Enter Email ID",Toast.LENGTH_LONG);
                    return;
                }
                if(TextUtils.isEmpty(epassword))
                {
                    Toast.makeText(adminpage.this,"Please Enter Password",Toast.LENGTH_LONG);
                    return;
                }


                //firebaseAuth.signInWithEmailAndPassword(username, epassword)

                        //.addOnCompleteListener(adminpage.this, new OnCompleteListener<AuthResult>() {
                            /*@Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {*/

                                    Intent intent=new Intent(adminpage.this,adminMenu.class);
                                    startActivity(intent);

                               /* } else {
                                    Toast.makeText(adminpage.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        });*/



            }
        });
    }
}
