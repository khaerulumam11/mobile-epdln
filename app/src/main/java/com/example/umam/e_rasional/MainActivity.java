package com.example.umam.e_rasional;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.example.umam.e_rasional.model.User;
import com.example.umam.e_rasional.util.PrefUtil;

public class MainActivity extends AppCompatActivity {

    private CardView btnLogout;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = PrefUtil.getUser(this, PrefUtil.USER_SESSION);

        btnLogout = findViewById(R.id.logout);

//        greeting.setText(getResources().getString(R.string.greeting, user.getData().getFirstname()));
//        email.setText(user.getData().getEmail());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutAct();

                LoginActivity.start(MainActivity.this);
                MainActivity.this.finish();
            }
        });

    }

    void logoutAct() {
        PrefUtil.clear(this);
    }
}
