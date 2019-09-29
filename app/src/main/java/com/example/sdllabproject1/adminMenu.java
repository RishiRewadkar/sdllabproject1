package com.example.sdllabproject1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class adminMenu extends AppCompatActivity {

    private Button proj, eod;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.admin_menu);
        super.onCreate(savedInstanceState);
        proj = findViewById(R.id.project);
        eod = findViewById(R.id.eod);
        proj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1();
            }
        });

        eod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button2();
            }
        });
    }

    public void button2() {
        Intent intent = new Intent(this,adminEOD.class);
        startActivity(intent);
    }

    public void button1(){
        Intent intent = new Intent(this, admin1.class);
        startActivity(intent);
    }

}
