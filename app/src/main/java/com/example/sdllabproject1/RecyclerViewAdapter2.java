package com.example.sdllabproject1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewHolder> {
    Context mcontxt;
    ArrayList<ModelUsers> userArrayList;
    FirebaseFirestore db;

    public RecyclerViewAdapter2(Context mcontxt, ArrayList<ModelUsers> userArrayList) {
        this.mcontxt = mcontxt;
        this.userArrayList = userArrayList;
        db = FirebaseFirestore.getInstance();
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mcontxt);
        View view = layoutInflater.inflate(R.layout.singlerow, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, int position) {
        holder.tvuser.setText(userArrayList.get(position).getUserName());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void itemclick(View view, int pos) {
                CheckBox c = (CheckBox) view;
                if (c.isChecked()) {
                    String s = holder.tvuser.getText().toString();
                    // db.collection("Project").document()
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }
}