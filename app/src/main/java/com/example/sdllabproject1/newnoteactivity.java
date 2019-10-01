package com.example.sdllabproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class newnoteactivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerpriority;
    FirebaseAuth fa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newnoteactivity);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("MAKE EOD REPORT");
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerpriority = findViewById(R.id.number_picker_priorty);

        numberPickerpriority.setMinValue(1);
        numberPickerpriority.setMaxValue(9);

        fa = FirebaseAuth.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_menu_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                savenote();
            default:
                return super.onOptionsItemSelected(item);

        }

    }
    private void savenote()
    {
        String title=editTextTitle.getText().toString();
        String description=editTextDescription.getText().toString();
        int priority=numberPickerpriority.getValue();
        String date = "01-10-2019";
        if(title.trim().isEmpty()||description.trim().isEmpty())
        {
            Toast.makeText(newnoteactivity.this,"Please enter title and description",Toast.LENGTH_SHORT).show();
            return;
        }
        HashMap<String,String> map =  new HashMap<>();
        map.put("Date",date);
        map.put("Description",description);
        map.put("Title",title);
        CollectionReference notebookref= FirebaseFirestore.getInstance().collection("USERS");
        notebookref.document(fa.getCurrentUser().getEmail().toString()).collection("EOD").document(date).set(map);
     //   notebookref.add(new note(title,description,priority));
        Toast.makeText(newnoteactivity.this,"note added",Toast.LENGTH_SHORT).show();
        finish();
    }

}
