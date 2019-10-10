package com.example.sdllabproject1;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class eod_details extends RecyclerView.Adapter<eod_details.ViewHolder> {

    private Context mContext;
    private ArrayList<projectTitles> pro_title;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    public eod_details(Context context, ArrayList<projectTitles> title){
        mContext = context;
        pro_title = title;
        for(int i = 0;i<title.size();i++){
            expandState.append(i,false);
        }
    }


    @NonNull
    @Override
    public eod_details.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view =  layoutInflater.inflate(R.layout.info_eod,parent,false);
        eod_details.ViewHolder viewHolder = new eod_details.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final  eod_details.ViewHolder holder, final int position) {
        holder.setIsRecyclable(false);
        TextView name = holder.title;
        name.setText(pro_title.get(position).getDname());
       holder.desc.setText(pro_title.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return pro_title.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, desc;
        public RelativeLayout buttonLayout;
        public LinearLayout expandableLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv);
            desc = itemView.findViewById(R.id.tvdesc);



        }
    }


}
