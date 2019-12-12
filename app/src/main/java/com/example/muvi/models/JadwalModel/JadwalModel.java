package com.example.muvi.models.JadwalModel;

import java.util.ArrayList;

public class JadwalModel {
    String status;
    String success;
    ArrayList<ListData> data;

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
}
