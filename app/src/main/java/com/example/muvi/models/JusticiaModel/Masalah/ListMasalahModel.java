package com.example.muvi.models.JusticiaModel.Masalah;

import java.util.ArrayList;

public class ListMasalahModel {
    String status;
    String success;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<ListData> getData() {
        return data;
    }

    public void setData(ArrayList<ListData> data) {
        this.data = data;
    }

    ArrayList<ListData> data;
}
