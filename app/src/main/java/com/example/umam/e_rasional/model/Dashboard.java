package com.example.umam.e_rasional.model;

public class Dashboard extends BaseResponse {
    private DashboardData data;

    public Dashboard() {
    }

    public DashboardData getData() {
        return data;
    }

    public void setData(DashboardData data) {
        this.data = data;
    }
}
