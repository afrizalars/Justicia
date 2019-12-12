package com.example.muvi.adapter.justicia;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.muvi.views.Activity.ProfileDetail;
import com.example.muvi.R;
import com.example.muvi.models.LawyerPopuler.LawyerPopulerModel;
import com.squareup.picasso.Picasso;

public class LawyerAdapter extends RecyclerView.Adapter<LawyerAdapter.MyViewHolder>{
    LawyerPopulerModel listModel;

    public LawyerAdapter(LawyerPopulerModel ModelList) {
        listModel = ModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lawyer, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder,final int position){
        holder.nama.setText(listModel.getData().get(position).getNama());
        holder.top_skill.setText(listModel.getData().get(position).getTop_skill());

        Picasso.get().load(listModel.getData().get(position).getUrl()).into(holder.photo);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ProfileDetail.class);
                i.putExtra("Id", listModel.getData().get(position).getId());
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount () {
        return listModel.getData().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nama,top_skill;
        public ImageView photo;
        public MyViewHolder(View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.tv_nama);
            top_skill = itemView.findViewById(R.id.tv_topskill);
            photo = itemView.findViewById(R.id.lawyer_photo);

        }
    }
}