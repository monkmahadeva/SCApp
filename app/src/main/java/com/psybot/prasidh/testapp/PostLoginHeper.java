package com.psybot.prasidh.testapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

class PostLoginHeper {
    private static PostLoginHandler handler;
    public static void performDataRecordsTask(String postData, PostLoginHandler handler, Context context, String data) {
        PostLoginHeper.handler = handler;
        DataTask task = new DataTask();
        String access_token = "6ST0QMVzUSbdZyvIDZ5PCh1uTURog070cc51uI90";
        String _IdToken = context.getSharedPreferences("login_prefs",Context.MODE_PRIVATE).getString("id_token","");
        task.execute(postData,access_token,_IdToken,data);
    }

    private static class DataTask extends AsyncTask<String,Void,Void> {
        String url;
        @Override
        protected Void doInBackground(String... strings) {
            url="https://k38crffisi.execute-api.ap-southeast-1.amazonaws.com/beta/_q/JTC_F1/_q/execute";
            URL url1 = null;
            try {
                url1 = new URL(url);
                HttpsURLConnection conn= (HttpsURLConnection) url1.openConnection();
                conn.setDoOutput( true );
                conn.setInstanceFollowRedirects( false );
                conn.setRequestMethod( "POST" );
                conn.setRequestProperty("Content-Type","application/json");
                conn.setRequestProperty("x-api-key",strings[1]);
                conn.setRequestProperty("SCAuth",strings[2]);
                try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
                    wr.write( strings[0].getBytes("UTF-8"));
                }
                int http_result = conn.getResponseCode();
                Log.d("POSTLOGINHTTPRESU", String.valueOf(http_result));
                if (http_result==HttpsURLConnection.HTTP_OK){
                    InputStream stream = conn.getInputStream();
//                    InputStreamReader isw = new InputStreamReader(stream);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    handler.postLoginDataRetreived(result.toString(),strings[3]);
                    Log.d("PostLoginResult:",result.toString());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
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
