package com.example.sdllabproject1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class employee1 extends Fragment {

    private TabAdapter tabAdapter;
    private ViewPager viewPager;
    FloatingActionButton addProject;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_admin,container,false);

        tabAdapter = new TabAdapter(getFragmentManager());
        viewPager = view.findViewById(R.id.container);
        setupViewPager(viewPager);
        Log.d("empoe32", "onCreateView: ");

        TabLayout tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        addProject = view.findViewById(R.id.fab);
        addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAddProject();
            }
        });
        return view;
    }


    public void setAddProject(){
        Intent intent = new Intent(getContext(),addProjectForm.class);
        startActivity(intent);
    }
    private void setupViewPager(ViewPager viewPager){
        Log.d("empoe33", "onCreateView: ");

        TabAdapter adapter = new TabAdapter(getFragmentManager());
        adapter.addFragment(new emp_fragment(),"Ongoing");
        adapter.addFragment(new emp_fragment3(),"Done");
        viewPager.setAdapter(adapter);
    }


}
