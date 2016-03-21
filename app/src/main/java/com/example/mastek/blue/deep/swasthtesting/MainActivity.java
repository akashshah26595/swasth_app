package com.example.mastek.blue.deep.swasthtesting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin;
    Intent intent;
    public UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button)findViewById(R.id.btnStartLogin);
        btnLogin.setOnClickListener(this);
       // Button beginFeedbackButton = (Button) findViewById(R.id.beginFeedbackButton);
       // beginFeedbackButton.setOnClickListener(this);
        userLocalStore = new UserLocalStore(getApplicationContext());
        boolean status = userLocalStore.getLoginStatus();
        Intent intent = new Intent(MainActivity.this,Dashboard.class);
        Log.d("TEST",userLocalStore.getLoginStatus() + "");
        if(status){
            Log.d("TEST","SharedPref status" + status);
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View v) {
        int x = v.getId();
        switch(x)
        {
            case R.id.btnStartLogin:
               intent = new Intent(this, Login.class);
                startActivity(intent);
//                finish();
                break;

//            case R.id.beginFeedbackButton:
//                intent = new Intent(this, FeedbackActivity.class);
//                startActivity(intent);
//                finish();
//                break;
        }

    }
}

/*
fix mistake in form
shared preferences
language
pass hashmap to next activity ??
questions with as as edittext

 */