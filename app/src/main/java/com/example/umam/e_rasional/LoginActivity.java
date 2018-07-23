package com.example.umam.e_rasional;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.example.umam.e_rasional.API.AppVar;
import com.example.umam.e_rasional.model.User;
import com.example.umam.e_rasional.network.LoginService;
import com.example.umam.e_rasional.util.PrefUtil;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout nama,sandi;
    Button masuk;
    private Context context;
    ProgressDialog pDialog;

    private LoginService loginService;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        context = LoginActivity.this;

        if(isSessionLogin()) {
            Home.start(this);
            LoginActivity.this.finish();
        }


        nama = findViewById(R.id.username);
        sandi = findViewById(R.id.password);
        masuk = findViewById(R.id.login);

        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAct();
            }
        });
    }

    private void loginAct() {
        String user = nama.getEditText().getText().toString();
        String pass = sandi.getEditText().getText().toString();

        if(TextUtils.isEmpty(user)) {
            nama.setError("Username Tidak Boleh Kosong!");
            return;
        }

        if(TextUtils.isEmpty(pass)) {
            sandi.setError("Password Tidak Boleh Kosong");
            return;
        }
        loginService = new LoginService(this);
        loginService.doLogin(user, pass, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                User user = (User) response.body();

                if(user != null) {
                    if(!user.isError()) {
                        PrefUtil.putUser(LoginActivity.this, PrefUtil.USER_SESSION, user);
                        Home.start(LoginActivity.this);
                        LoginActivity.this.finish();
                    }

                    Toast.makeText(LoginActivity.this, user.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(LoginActivity.this, "An error occurred!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    boolean isSessionLogin() {
        return PrefUtil.getUser(this, PrefUtil.USER_SESSION) != null;
    }

//    private void login() {
//        final String user = nama.getEditText().getText().toString();
//        final String pass = sandi.getEditText().getText().toString();
//
//       // pDialog.setMessage("Login Success..");
//      //  showDialog();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppVar.LOGIN_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        //If we are getting success from server
//                        if (response.contains(AppVar.LOGIN_SUCCESS)) {
//                            //hideDialog();
//                            gotoCourseActivity();
//
//                        } else {
//                         //   hideDialog();
//                            //Displaying an error message on toast
//                            Toast.makeText(context, "Invalid username or password", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //You can handle error here if you want
//                    //    hideDialog();
//                        Toast.makeText(context, "The server unreachable", Toast.LENGTH_LONG).show();
//
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                //Adding parameters to request
//                params.put(AppVar.KEY_EMAIL, user);
//                params.put(AppVar.KEY_PASSWORD, pass );
//
//                //returning parameter
//                return params;
//            }
//        };
//
//        //Adding the string request to the queue
//        Volley.newRequestQueue(this).add(stringRequest);
//
//    }
//
//    private void gotoCourseActivity() {
//        Intent intent = new Intent(context, Home.class);
//        startActivity(intent);
//        finish();
//    }

//    private void showDialog() {
//        if (!pDialog.isShowing())
//            pDialog.show();
//    }

//    private void hideDialog() {
//        if (pDialog.isShowing())
//            pDialog.dismiss();
//    }
}
