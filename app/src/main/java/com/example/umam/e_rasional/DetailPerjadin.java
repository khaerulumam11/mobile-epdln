


package com.example.umam.e_rasional;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.umam.e_rasional.util.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailPerjadin extends AppCompatActivity {

    TextView namakeg,satker,tglbrng,tglplng,nopen,jmldeleg,namadeleg,nik,nip,jabatan, status,status1;
    EditText ab, userInput,userInput1;
    private Button setujui,tolak,eval;

    final Context context = this;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    ArrayList<HashMap<String, String>> list_data;

    String urlupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_perjadin);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detailperjadin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detail Perjadin");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent a = getIntent();
        String id = a.getStringExtra("id");
        String idperjadin = a.getStringExtra("id_perjadin");

        urlupdate = "http://10.2.7.149/e-pdln/update.php?id=" + idperjadin;
        namakeg = findViewById(R.id.tvNamaKeg);
        satker = findViewById(R.id.tvSatker);
        tglbrng = findViewById(R.id.tvTglBrng);
        tglplng = findViewById(R.id.tvTglPlng);
        nopen = findViewById(R.id.tvNoPen);
        jmldeleg = findViewById(R.id.tvJmlDeleg);
        namadeleg = findViewById(R.id.tvNamaDeleg);
        nik = findViewById(R.id.tvNIK);
        nip = findViewById(R.id.tvNIP);
        jabatan = findViewById(R.id.tvJabatan);

        setujui = findViewById(R.id.setuju);
        eval = findViewById(R.id.evaluasi);
        tolak = findViewById(R.id.tolak);

        setujui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setuju();
            }
        });

        eval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluasi();
            }
        });

        tolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tolakin();
            }
        });

        requestQueue = Volley.newRequestQueue(DetailPerjadin.this);

        list_data = new ArrayList<HashMap<String, String>>();

        stringRequest = new StringRequest(Request.Method.GET, id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("perjadin");
                    for (int a = 0; a < jsonArray.length(); a ++){
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map  = new HashMap<String, String>();
                        map.put("id", json.getString("id"));
                        map.put("namakegiatan", json.getString("namakegiatan"));
                        map.put("satker", json.getString("satker"));
                       // map.put("id_delegasi", json.getString("id_delegasi"));
                        map.put("waktuawal", json.getString("waktuawal"));
                        map.put("waktuakhir", json.getString("waktuakhir"));
                        map.put("nama_pegawai", json.getString("nama_pegawai"));
                        map.put("nip", json.getString("nip"));
                        map.put("nik", json.getString("nik"));
                        map.put("jabatan", json.getString("jabatan"));
                        map.put("no", json.getString("no"));
                        map.put("id_delegasi", json.getString("id_delegasi"));
                        list_data.add(map);
                    }
                    namakeg.setText(list_data.get(0).get("namakegiatan"));
                    satker.setText(list_data.get(0).get("satker"));
                    tglbrng.setText(list_data.get(0).get("waktuawal"));
                    nopen.setText(list_data.get(0).get("no"));
                    tglplng.setText(list_data.get(0).get("waktuakhir"));
                    namadeleg.setText(list_data.get(0).get("nama_pegawai"));
                    nik.setText(list_data.get(0).get("nip"));
                    nip.setText(list_data.get(0).get("nip"));
                    jabatan.setText(list_data.get(0).get("jabatan"));
                    jmldeleg.setText(list_data.get(0).get("id_delegasi"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailPerjadin.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);

    }

    private void tolakin() {
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.dialoginputan, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);
        status = promptsView.findViewById(R.id.statusperjadin);



        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text

                                StringRequest updateReq = new StringRequest(Request.Method.POST, urlupdate,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                try {
                                                    JSONObject res = new JSONObject(response);
                                                    Toast.makeText(DetailPerjadin.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }

                                                startActivity( new Intent(DetailPerjadin.this,Home.class));
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {

                                                Toast.makeText(DetailPerjadin.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                                            }
                                        }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        Map<String,String> map = new HashMap<>();
                                        map.put("alasan",userInput.getText().toString());
                                        map.put("status_perjadin","Tolak");

                                        return map;
                                    }
                                };

                                AppController.getInstance().addToRequestQueue(updateReq);

//                                        result.setText(userInput.getText());
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }


    private void evaluasi() {
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.dialoginputan, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        userInput1 = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);
         status1 = promptsView.findViewById(R.id.statusperjadin);



        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text

                                StringRequest updateReq = new StringRequest(Request.Method.POST, urlupdate,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                try {
                                                    JSONObject res = new JSONObject(response);
                                                    Toast.makeText(DetailPerjadin.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }

                                                startActivity( new Intent(DetailPerjadin.this,Home.class));
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {

                                                Toast.makeText(DetailPerjadin.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                                            }
                                        }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        Map<String,String> map = new HashMap<>();
                                        map.put("alasan",userInput1.getText().toString());
                                        map.put("status_perjadin","Evaluasi");

                                        return map;
                                    }
                                };

                                AppController.getInstance().addToRequestQueue(updateReq);

//                                        result.setText(userInput.getText());
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }


    private void setuju() {
        StringRequest updateReq = new StringRequest(Request.Method.POST, urlupdate,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(DetailPerjadin.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(DetailPerjadin.this,Home.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(DetailPerjadin.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("status_perjadin","Acc");

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(updateReq);
    }
}
