package com.example.sdllabproject1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class admin1 extends AppCompatActivity {

    private TabAdapter tabAdapter;
    private ViewPager viewPager;
    FloatingActionButton addProject;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        addProject = findViewById(R.id.fab);
        addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAddProject();
            }
        });
    }
    public void setAddProject(){
        Intent intent = new Intent(this,addProjectForm.class);
        startActivity(intent);
    }
    private void setupViewPager(ViewPager viewPager){
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new admin_fragment(),"Ongoing");
        adapter.addFragment(new admin_fragment2(),"Upcoming");
        adapter.addFragment(new admin_fragment3(),"Done");
        viewPager.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add_post: {
                finish();
                startActivity(new Intent(this, addProjectForm.class));
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
