package com.example.sdllabproject1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>
{
    public List<Record> recordList;
    public DataAdapter(List<Record>trecord){
        this.recordList=trecord;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mview;
        TextView date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mview=itemView;
            date=mview.findViewById(R.id.Date);

        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.download_data,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.date.setText(recordList.get(position).getDate());
    }



    @Override
    public int getItemCount() {
        return recordList.size();
    }
}
