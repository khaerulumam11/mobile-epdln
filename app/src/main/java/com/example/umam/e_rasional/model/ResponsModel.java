package com.example.umam.e_rasional.model;

import java.util.List;

public class ResponsModel {
    String  kode, pesan;
    List<ApprovalModel> result;

    public List<ApprovalModel> getResult() {
        return result;
    }

    public void setResult(List<ApprovalModel> result) {
        this.result = result;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}
