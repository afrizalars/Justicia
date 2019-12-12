package com.example.muvi.adapter.justicia;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.muvi.ChatActivity;
import com.example.muvi.R;
import com.example.muvi.models.LawyerPopuler.LawyerPopulerModel;
import com.example.muvi.models.ProbonoModel.ProbonoModel;
import com.example.muvi.views.Activity.ProfileDetail;
import com.squareup.picasso.Picasso;

public class ProbonoAdapter extends RecyclerView.Adapter<ProbonoAdapter.MyViewHolder>{
    ProbonoModel listModel;

    public ProbonoAdapter(ProbonoModel ModelList) {
        listModel = ModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_probono, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder,final int position){
        holder.nama.setText(listModel.getData().get(position).getNama());
        holder.biodata.setText(listModel.getData().get(position).getAbout());
        Picasso.get().load(listModel.getData().get(position).getUrl()).into(holder.photo);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ChatActivity.class);
                i.putExtra("Id", listModel.getData().get(position).getUrl_chat());
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount () {
        return listModel.getData().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nama,biodata, pendidikan;
        public ImageView photo;
        public MyViewHolder(View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.tv_nama);
            biodata = itemView.findViewById(R.id.biodata);
            photo = itemView.findViewById(R.id.lawyer_photo_probono);

        }
    }
}