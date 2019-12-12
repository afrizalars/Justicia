package com.example.muvi.adapter.justicia;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.muvi.R;
import com.example.muvi.views.Fragment.SearchLawyerFragment;
import com.example.muvi.models.JusticiaModel.Masalah.ListMasalahModel;

public class MasalahAdapter extends RecyclerView.Adapter<MasalahAdapter.MyViewHolder>{
    ListMasalahModel listModel;

    public MasalahAdapter(ListMasalahModel ModelList) {
        listModel = ModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_masalah, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (final MyViewHolder holder, final int position){
        holder.jenisMasalah.setText(listModel.getData().get(position).getJenis());
        holder.ketMasalah.setText(listModel.getData().get(position).getKet());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), SearchLawyerFragment.class);
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount () {
        return listModel.getData().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView jenisMasalah,ketMasalah;

        public MyViewHolder(View itemView) {
            super(itemView);
            jenisMasalah = itemView.findViewById(R.id.jenisMasalah);
            ketMasalah = itemView.findViewById(R.id.ketMasalah);

        }
    }
}