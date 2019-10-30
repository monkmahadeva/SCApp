package com.psybot.prasidh.testapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements SignUpHandler {
    EditText uname_box,pass_box;
    Button login_btn;

    @Override
    protected void onResume() {
//        TextValidators();

        initalizeClickListeners();
        super.onResume();
    }

    private void initalizeClickListeners() {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextValidated()){
                    String postData = "{\"uname\":\""+uname_box.getText().toString().trim()+"\",\"passwd\":\""+pass_box.getText().toString().trim()+"\" }";
                   // String postData="{\"uname\":\"demo_account\",\"passwd\":\"Demo@123#\"}";
                    SignUpHelper.loginUserTask(postData,MainActivity.this);
                }
            }
        });
    }

    private boolean TextValidated() {
        if (uname_box.getText().toString().trim().length()>0&&pass_box.getText().toString().trim().length()>0){
            return  true;
        }else{
            return  false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname_box = (EditText)findViewById(R.id.uname_box);
        pass_box= (EditText)findViewById(R.id.pass_box);
        login_btn= (Button)findViewById(R.id.login_btn);
    }

    @Override
    public void LoginSuccessful(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject authObj = jsonObject.getJSONObject("AuthenticationResult");
            getSharedPreferences("login_prefs", Context.MODE_PRIVATE).edit().putString("access_token",authObj.getString("AccessToken")).putString("id_token",authObj.getString("IdToken")).apply();
            Intent nextActivity = new Intent(this,PostLoginActivity.class);
            startActivity(nextActivity);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
