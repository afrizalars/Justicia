package com.example.muvi.adapter.justicia;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.muvi.R;
import com.example.muvi.models.JadwalModel.JadwalModel;
import com.example.muvi.models.ReviewModel.ReviewModel;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.MyViewHolder>{
    JadwalModel listModel;

    public JadwalAdapter(JadwalModel ModelList) {
        listModel = ModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder,final int position){
        holder.hari.setText(listModel.getData().get(position).getHari());
        holder.jam.setText(listModel.getData().get(position).getJam());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount () {
        return listModel.getData().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView hari,jam;

        public MyViewHolder(View itemView) {
            super(itemView);

            hari = itemView.findViewById(R.id.tv_hari);
            jam = itemView.findViewById(R.id.tv_jam);

        }
    }
}