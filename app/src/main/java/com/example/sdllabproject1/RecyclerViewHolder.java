package com.example.sdllabproject1;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView tvuser;
    CheckBox cb;
    ItemClickListener ic;

    public RecyclerViewHolder(View view) {
        super(view);
        tvuser = view.findViewById(R.id.userTV);
        cb = view.findViewById(R.id.userCB);
        cb.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener ic) {
        this.ic = ic;
    }


    @Override
    public void onClick(View v) {
        this.ic.itemclick(v, getLayoutPosition());
    }
}
