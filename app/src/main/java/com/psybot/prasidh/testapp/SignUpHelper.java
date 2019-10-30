package com.psybot.prasidh.testapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

class SignUpHelper {
    private static SignUpHandler handler;
    public static void loginUserTask(String postBody, SignUpHandler handler) {
        SignUpHelper.handler = handler;
        LoginTaskk loginTaskk = new LoginTaskk();
        loginTaskk.execute(postBody);
    }

    private static class LoginTaskk extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... strings) {
            String url = "https://4l6qi5oh0h.execute-api.ap-southeast-1.amazonaws.com/prod/unauth/login";
            try {

                URL url1 = new URL(url);
                HttpsURLConnection conn= (HttpsURLConnection) url1.openConnection();
                conn.setDoOutput( true );
                conn.setInstanceFollowRedirects( false );
                conn.setRequestMethod( "POST" );
                conn.setRequestProperty("Content-Type","application/json");
                try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
                    wr.write( strings[0].getBytes("UTF-8"));
                }
                int http_result = conn.getResponseCode();
                Log.d("HTTPRESU", String.valueOf(http_result));
                if (http_result==HttpsURLConnection.HTTP_OK){
                    InputStream stream = conn.getInputStream();
//                    InputStreamReader isw = new InputStreamReader(stream);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    handler.LoginSuccessful(result.toString());
                    Log.d("TESTResult:",result.toString());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
