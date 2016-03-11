package com.example.mastek.blue.deep.swasthtesting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin;
    Intent intent;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button)findViewById(R.id.btnStartLogin);
        btnRegister = (Button)findViewById(R.id.btnStartRegister);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
       // Button beginFeedbackButton = (Button) findViewById(R.id.beginFeedbackButton);
       // beginFeedbackButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int x = v.getId();
        switch(x)
        {
            case R.id.btnStartLogin:
               intent = new Intent(this, Login.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btnStartRegister:
                intent = new Intent(this, Register.class);
                startActivity(intent);
                finish();
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