package com.example.sdllabproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button1,button2,button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Home");
        button1=(Button) findViewById(R.id.button1);
        button2=(Button) findViewById(R.id.button2);
        button3=(Button) findViewById(R.id.button3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity1();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity2();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivity3();
            }
        });
    }
    public void openactivity1()
    {
       // Intent intent=new Intent(this,adminpage.class);
       // startActivity(intent);
    }
    public void openactivity2()
    {
       // Intent intent=new Intent(this,managerpage.class);
       // startActivity(intent);
    }
    public void openactivity3()
    {
        Intent intent=new Intent(this,employeepage.class);
        startActivity(intent);
    }
}
