package com.example.umam.e_rasional;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.umam.e_rasional.util.PrefUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static void start(Context context) {
        Intent intent = new Intent(context, Home.class);
        context.startActivity(intent);
    }

    TextView kegiatan1,perjadin1,delegasi1;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private ArrayList<HashMap<String, String>> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        kegiatan1 = findViewById(R.id.kegiatan);
        perjadin1= findViewById(R.id.perjadin);
        delegasi1 = findViewById(R.id.delegasi);

        String url = "http://10.2.7.149/e-pdln/daftar.php";

        requestQueue = Volley.newRequestQueue(Home.this);

        list_data = new ArrayList<HashMap<String, String>>();

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("daftar");
                    for (int a = 0; a < jsonArray.length(); a ++){
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map  = new HashMap<String, String>();
                        map.put("id_perjadin", json.getString("id_perjadin"));
                        map.put("id_kegiatan", json.getString("id_kegiatan"));
                        map.put("id_delegasi", json.getString("id_delegasi"));
                        list_data.add(map);
                    }

                    kegiatan1.setText(list_data.get(0).get("id_kegiatan"));
                    perjadin1.setText(list_data.get(0).get("id_perjadin"));
                    delegasi1.setText(list_data.get(0).get("id_delegasi"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Home.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            // Handle the camera action
        } else if (id == R.id.nav_approval) {

            Intent ab = new Intent(Home.this,ListApproval.class);
            startActivity(ab);

        } else if (id == R.id.nav_logout) {

            logoutAct();

            LoginActivity.start(Home.this);
            Home.this.finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logoutAct() {
        PrefUtil.clear(this);
    }
}
