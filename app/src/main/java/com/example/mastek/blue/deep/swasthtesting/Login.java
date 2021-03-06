package com.example.mastek.blue.deep.swasthtesting;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText etusername;
    EditText pass;
    Button button;
    private User user;
//  public static final String SERVER_ADDRESS = "http://swasth-india.esy.es/volley/login.php";
    public static final String SERVER_ADDRESS = "http://swasth-india.esy.es/swasth/user_login.php";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    private UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        etusername = (EditText)findViewById(R.id.etLUsername);
        pass = (EditText)findViewById(R.id.etLPassword);
        button = (Button)findViewById(R.id.btnLogin);
        button.setOnClickListener(this);

            }

    @Override
    public void onClick(View v) {
        login();
    }
    private void login(){
        final String username = etusername.getText().toString();
        final String password = pass.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER_ADDRESS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int card_number=0,credits=0;
                String name="";
//                Toast.makeText(getApplicationContext(), "Login Response 1......." + response, Toast.LENGTH_LONG).show();
                Log.i("TEST", "Response..." + response);
                if(response.contains("Failure")){
                        Toast.makeText(getApplicationContext(), "Invalid Credentials......." + response, Toast.LENGTH_LONG).show();
                }
                else if(response!=null){
                    Log.i("TEST", "Inside success...");

                    try {
                        Log.i("TEST", "Inside try...");
                        JSONObject obj = new JSONObject(response);
                        name = obj.getString("fname");
                        card_number = obj.getInt("user_card_number");
                         credits = obj.getInt("u_credits");

                        Log.d("TEST","Value of fname:" + name);
                        Log.d("TEST","Value of card number:" + card_number);
                        Log.d("TEST","Value of credits:" + credits);
                    }
                    catch (Exception ex){

                    }
//                    Toast.makeText(getApplicationContext(), "Login Response 2......." + response, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Login.this,Dashboard.class);
                    user = new User(getApplicationContext());
                    user.addUserDetails(credits,card_number, name);
                    userLocalStore = new UserLocalStore(getApplicationContext());
                    userLocalStore.setLoggedInUser(true);
                    userLocalStore.storeUserData(username,password);
                    startActivity(intent);
                    finish();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Errror......." + error , Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put(KEY_USERNAME,username);
                params.put(KEY_PASSWORD,password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

