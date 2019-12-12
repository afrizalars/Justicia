package com.example.muvi.views.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.muvi.R;
import com.example.muvi.adapter.justicia.JadwalAdapter;
import com.example.muvi.adapter.justicia.LawyerAdapter;
import com.example.muvi.adapter.justicia.LawyerSearchAdapter;
import com.example.muvi.adapter.justicia.ReviewAdapter;
import com.example.muvi.models.JadwalModel.JadwalModel;
import com.example.muvi.models.LawyerPopuler.LawyerPopulerModel;
import com.example.muvi.models.LawyerSearch.LawyerSearchModel;
import com.example.muvi.models.ProfileModel.ProfileModel;
import com.example.muvi.models.ReviewModel.ReviewModel;
import com.example.muvi.network.Client;
import com.example.muvi.network.Interface;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileDetail extends AppCompatActivity {

    Interface anInterface;
    TextView tv_nama, tv_jenis_bidang, tv_pendidikan, tv_pengalaman, tv_biaya;
    ImageView photo;
    RecyclerView rv_review,rv_jadwal;

    private final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
    private final LinearLayoutManager jadwalLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

    private void getBiodata ( String id){
        Call<ProfileModel> call = anInterface.getBiodata(id);

        call.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                tv_nama.setText(response.body().getData().get(0).getNama());
                tv_jenis_bidang.setText(response.body().getData().get(0).getTop_skill());
                tv_pendidikan.setText(response.body().getData().get(0).getPendidikan());
                tv_pengalaman.setText(response.body().getData().get(0).getExp().toString());
                tv_biaya.setText(response.body().getData().get(0).getPrice().toString());
                Picasso.get().load(response.body().getData().get(0).getUrl()).into(photo);
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                Log.d("API", t.getMessage());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);

        tv_nama = findViewById(R.id.tv_namaLawyer);
        tv_jenis_bidang = findViewById(R.id.tv_bidang);
        tv_pengalaman = findViewById(R.id.tv_lama_pengalaman);
        tv_pendidikan = findViewById(R.id.tv_lulusan);
        tv_biaya = findViewById(R.id.biaya_lawyer);
        photo = findViewById(R.id.lawyer_photo_detail);


        rv_review = findViewById(R.id.rv_review);
        rv_jadwal = findViewById(R.id.rv_jadwal);

        rv_review.setLayoutManager(linearLayoutManager);
        rv_jadwal.setLayoutManager(jadwalLayoutManager);


        anInterface = Client.getClient().create(Interface.class);

        Intent mIntent = getIntent();
        getBiodata(mIntent.getStringExtra("Id"));
        getReview(mIntent.getStringExtra("Id"));
        getJadwal(mIntent.getStringExtra("Id"));
    }

    private void getReview (String id){
        Call<ReviewModel> call = anInterface.getReview(id);

        call.enqueue(new Callback<ReviewModel>() {
            @Override
            public void onResponse(Call<ReviewModel> call, Response<ReviewModel> response) {
                rv_review.setAdapter(new ReviewAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<ReviewModel> call, Throwable t) {
                Log.d("API", t.getMessage());
            }
        });
    }

    private void getJadwal (String id){
        Call<JadwalModel> call = anInterface.getJadwal(id);

        call.enqueue(new Callback<JadwalModel>() {
            @Override
            public void onResponse(Call<JadwalModel> call, Response<JadwalModel> response) {
                rv_jadwal.setAdapter(new JadwalAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<JadwalModel> call, Throwable t) {
                Log.d("API", t.getMessage());
            }
        });
    }
}
