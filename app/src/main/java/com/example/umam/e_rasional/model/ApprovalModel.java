package com.example.umam.e_rasional.model;

public class ApprovalModel {

    String namakegiatan,alasan,no,status,id_delegasi,id;


//    public ApprovalModel(){}

    public ApprovalModel() {

    }

    public ApprovalModel(String namakegiatan,String alasan,String no,String status, String id_delegasi, String id){
        this.namakegiatan = namakegiatan;
        this.alasan = alasan;
        this.no = no;
        this.status = status;
        this.id_delegasi = id_delegasi;
        this.id = id;

    }



    public String getNamakegiatan() {
        return namakegiatan;
    }

    public void setNamakegiatan(String namakegiatan) {
        this.namakegiatan = namakegiatan;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId_delegasi() {
        return id_delegasi;
    }

    public void setId_delegasi(String id_delegasi) {
        this.id_delegasi = id_delegasi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
