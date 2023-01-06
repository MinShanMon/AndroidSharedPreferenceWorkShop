package com.workshop.workshop1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProtectedActivity extends AppCompatActivity {
    TextView mInfoTxt;
    Button mLogoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protected);

        mInfoTxt = findViewById(R.id.txtInfo);
        mLogoutBtn = findViewById(R.id.btnLogout);

        //Read from shared preferences
        final SharedPreferences pref = getSharedPreferences("user_credentials", MODE_PRIVATE);
        String username = pref.getString("username", "");


        mInfoTxt.setText("Hi, " + username + "! All the best in the next exam!");


        //Delete and Clear Shared
        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();

                //editor.remove("user_credentials")
                //editor.commit();

                finish();
            }
        });
    }
}
