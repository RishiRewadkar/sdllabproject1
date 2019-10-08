package com.example.sdllabproject1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewHolder> {
    Context mcontxt;
    ArrayList<ModelUsers> userArrayList;
    FirebaseFirestore db;
    String myText;
    FirebaseAuth firebaseUser;

    public RecyclerViewAdapter2(Context mcontxt, ArrayList<ModelUsers> userArrayList) {
        this.mcontxt = mcontxt;
        this.userArrayList = userArrayList;
        db = FirebaseFirestore.getInstance();
        firebaseUser = FirebaseAuth.getInstance();
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
                /*
                CheckBox c = (CheckBox) view;
                if (c.isChecked()) {
                    final String s = holder.tvuser.getText().toString();
                    AlertDialog.Builder mydialog = new AlertDialog.Builder(mcontxt);
                    mydialog.setTitle("enter task");

                    final EditText weightInput = new EditText(mcontxt);
                    weightInput.setInputType(InputType.TYPE_CLASS_TEXT);
                    mydialog.setView(weightInput);

                    mydialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            myText=weightInput.getText().toString();
                            Map<String, Object> map = new HashMap<>();
                            map.put("task",myText);
                            map.put("employee",s);
                            FirebaseFirestore.getInstance().collection("Project").document(String.valueOf(firebaseUser)).collection("ProjectName").document().set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(mcontxt, "Project assigned", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(mcontxt, ManagerActivity.class);
                                    mcontxt.startActivity(intent);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(mcontxt, "Error!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                    mydialog.setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    mydialog.show(); */
                //}
            }
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }
}