package com.example.sdllabproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AssignTask extends AppCompatActivity {
    Button assignemployee;
    Button assigntask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_task);
        assignemployee = (Button) findViewById(R.id.AssignEmployees);
        assigntask = (Button) findViewById(R.id.AssignTasks);
        assignemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(AssignTask.this, AssignEmployeeActivity.class);
                startActivity(i1);
            }
        });
        assignemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(AssignTask.this, AssigntTaskSchedule.class);
                startActivity(i2);
            }
        });
    }

}
