package com.example.muvi.network;

import com.example.muvi.models.JadwalModel.JadwalModel;
import com.example.muvi.models.JusticiaModel.Masalah.ListMasalahModel;
import com.example.muvi.models.LawyerPopuler.LawyerPopulerModel;
import com.example.muvi.models.LawyerSearch.LawyerSearchModel;
import com.example.muvi.models.ListJenis.ListJenisModel;
import com.example.muvi.models.ProbonoModel.ProbonoModel;
import com.example.muvi.models.ProfileModel.ProfileModel;
import com.example.muvi.models.ReviewModel.ReviewModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Interface {

    @GET("api/justicia/listmasalah")
    Call<ListMasalahModel> getListMasalah();

    @GET("api/justicia/listlawyerpopuler")
    Call<LawyerPopulerModel> getLawyerPopuler();

    @GET("api/justicia/listlawyer")
    Call<LawyerSearchModel> getLawyer();

    @GET("api/justicia/listjenis")
    Call<ListJenisModel> getListJenis();

    @GET("api/justicia/CariLawyer/{jenis}")
    Call<LawyerSearchModel> cariLawyer(@Path("jenis") String Jenis);

    @GET("api/justicia/DetailLawyer/{id}")
    Call<ProfileModel> getBiodata(@Path("id") String id);

    @GET("api/justicia/ReviewLawyer/{id}")
    Call<ReviewModel> getReview(@Path("id") String Id);

    @GET("api/justicia/JadwalLawyer/{id}")
    Call<JadwalModel> getJadwal(@Path("id") String Id);

    @GET("api/justicia/ListProbono")
    Call<ProbonoModel> getProbono();

}

