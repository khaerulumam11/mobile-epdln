package com.example.umam.e_rasional;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.umam.e_rasional.adapter.AdapterData;
import com.example.umam.e_rasional.model.ApprovalModel;
import com.example.umam.e_rasional.model.ResponsModel;
import com.example.umam.e_rasional.network.config.RetroServer;
import com.example.umam.e_rasional.network.config.RetrofitBuilder;
import com.example.umam.e_rasional.network.interfaces.ReadData;
import com.example.umam.e_rasional.util.AppController;
import com.example.umam.e_rasional.util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListApproval extends AppCompatActivity {
    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<ApprovalModel> mItems = new ArrayList<>();
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_approval);

        Toolbar toolbar = (Toolbar) findViewById(R.id.approval_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Approval Perjadin");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        loadJson();

        pd = new ProgressDialog(this);
        mRecycler = (RecyclerView) findViewById(R.id.recyclerView);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AdapterData(ListApproval.this,mItems);
        mRecycler.setAdapter(mAdapter);

        pd.setMessage("Loading ...");
        pd.setCancelable(false);
        pd.show();

//        ReadData api = RetroServer.getClient().create(ReadData.class);
//        Call<ResponsModel> getTampil = api.getTampil();
//        getTampil.enqueue(new Callback<ResponsModel>() {
//            @Override
//            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
//                pd.hide();
//                Log.d("RETRO", "RESPONSE : " + response.body().getKode());
//                mItems = response.body().getResult();
//
//                mAdapter = new AdapterData(ListApproval.this,mItems);
//                mRecycler.setAdapter(mAdapter);
//                mAdapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponsModel> call, Throwable t) {
//                pd.hide();
//                Log.d("RETRO", "FAILED : respon gagal");
//
//            }
//        });
    }

    private void loadJson() {
//        pd.setMessage("Mengambil Data");
//        pd.setCancelable(false);
//        pd.show();

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_DATA,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("volley","response : " + response.toString());
                        for(int i = 0 ; i < response.length(); i++)
                        {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                ApprovalModel md = new ApprovalModel();
                                md.setId(data.getString("id"));
                                md.setNamakegiatan(data.getString("namakegiatan"));
                                md.setId_delegasi(data.getString("id_delegasi"));
                                md.setStatus(data.getString("status"));
                                md.setNo(data.getString("no"));
                                md.setAlasan(data.getString("alasan"));
                                mItems.add(md);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "error : " + error.getMessage());
                    }
                });

        AppController.getInstance().addToRequestQueue(reqData);
    }

}

