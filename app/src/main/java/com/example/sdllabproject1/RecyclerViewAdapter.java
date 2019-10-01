package com.example.sdllabproject1;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    public ArrayList<projectTitles> pro_title;
    Context mcontxt;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    public RecyclerViewAdapter(Context mcontxt, ArrayList<projectTitles> title) {
        this.mcontxt = mcontxt;
        pro_title = title;
        for (int i = 0; i < title.size(); i++) {
            expandState.append(i, false);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(mcontxt).inflate(R.layout.items, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.setIsRecyclable(false);
        TextView name = holder.title;
        name.setText(pro_title.get(position).getDname());
        holder.desc.setText(pro_title.get(position).getDesc());
        holder.lead.setText(pro_title.get(position).getLead());
        holder.stat.setText(pro_title.get(position).getStat());

        final boolean isExpanded = expandState.get(position);
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

        holder.buttonLayout.setRotation(expandState.get(position) ? 180f : 0f);
        holder.buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickButton(holder.expandableLayout, holder.buttonLayout, position);
            }
        });
        holder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// WRITE CODE HEREEEEEEES
            }
        });
    }

    @Override
    public int getItemCount() {
        return pro_title.size();
    }

    private void onClickButton(final LinearLayout expandableLayout, final RelativeLayout buttonLayout, final int i) {

        //Simply set View to Gone if not expanded
        //Not necessary but I put simple rotation on button layout
        if (expandableLayout.getVisibility() == View.VISIBLE) {
            createRotateAnimator(buttonLayout, 180f, 0f).start();
            expandableLayout.setVisibility(View.GONE);
            expandState.put(i, false);
        } else {
            createRotateAnimator(buttonLayout, 0f, 180f).start();
            expandableLayout.setVisibility(View.VISIBLE);
            expandState.put(i, true);
        }
    }

    private ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(new LinearInterpolator());
        return animator;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout buttonLayout;
        public LinearLayout expandableLayout;
        TextView title, desc, lead, stat;
        private Button b;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv1);
            desc = itemView.findViewById(R.id.tv_desc1);
            lead = itemView.findViewById(R.id.tv_lead1);
            stat = itemView.findViewById(R.id.tv_status1);
            b = (Button) itemView.findViewById(R.id.selectButton);
            expandableLayout = (LinearLayout) itemView.findViewById(R.id.expandableLayout1);
            buttonLayout = (RelativeLayout) itemView.findViewById(R.id.buttonnew);
        }
    }
}
