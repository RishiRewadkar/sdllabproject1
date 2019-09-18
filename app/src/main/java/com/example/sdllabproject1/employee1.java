package com.example.sdllabproject1;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class employee1 extends AppCompatActivity {
    private static final String TAG="EOD";
    private TextView mdisplaydate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Button mbutton;
    private EditText mMaintext;
    private FirebaseFirestore mFirestore;
    String date;
    private DocumentReference noteref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee1);
        mFirestore =FirebaseFirestore.getInstance();
        mdisplaydate=(TextView) findViewById(R.id.datepick);
        mdisplaydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog =new DatePickerDialog(
                        employee1.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                mMaintext = (EditText)findViewById(R.id.maintext);
                mbutton = (Button)findViewById(R.id.button1);

                mbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String eodreport = mMaintext.getText().toString();
                        Record record=new Record(date,eodreport);
                        noteref.set(record);
                        noteref.set(record).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(employee1.this, "Done", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(employee1.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                Log.d(TAG,"OnDateSet: mm/dd/yyyy: "+ month +"/"+day+"/"+year);
                date = month + "-"+ day + "-"+ year;
                mdisplaydate.setText(date);

                noteref=mFirestore.collection("EmployeeEOD").document(date);
            }
        };
    }
}
