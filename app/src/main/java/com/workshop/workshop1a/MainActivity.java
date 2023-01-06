package com.workshop.workshop1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    EditText mUsernameTxt;
    EditText mPasswordTxt;
    Button mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsernameTxt = findViewById(R.id.txtUsername);
        mPasswordTxt = findViewById(R.id.txtPassword);
        mLoginBtn = findViewById(R.id.btnLogin);

        //STEP 2 Read from Shared Preferences
        //sharedPreferences pref1 = getPreferences(MODE_PRIVATE);
        SharedPreferences pref = getSharedPreferences("user_credentials", MODE_PRIVATE);

        if (pref.contains("username") && pref.contains("password"))
        {
            //STEP 2 Read from shared Preferences
            boolean loginOk = logIn(pref.getString("username", ""), pref.getString("password", ""));

            if (loginOk)
            {
                startProtectedActivity();
            }
        }

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsernameTxt.getText().toString();
                String password = mPasswordTxt.getText().toString();

                boolean loginOk = logIn(username, password);
                if (loginOk)
                {
                    //STEP 1 Write to Shared Preferences
                    //step1
                    SharedPreferences pref = getSharedPreferences("user_credentials", MODE_PRIVATE);
                    //step2
                    SharedPreferences.Editor editor = pref.edit();
                    //step3
                    editor.putString("username",username);
                    editor.putString("password",password);
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Login successful!",Toast.LENGTH_SHORT).show();
                    //SEND to protected activity
                    startProtectedActivity();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),
                            "Credentials are not valid",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //check with values which getting from getSharedpreferences
    private boolean logIn(String username, String password) {
        if (username.equals("DipSA") && password.equals("DipSA")) {
            return true;
        }
        return false;
    }

    //send to protected activity
    private void startProtectedActivity() {
        Intent intent = new Intent(this, ProtectedActivity.class);
        startActivity(intent);
    }
}