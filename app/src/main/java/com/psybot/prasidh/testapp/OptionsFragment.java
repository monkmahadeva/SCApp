package com.psybot.prasidh.testapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OptionsFragment extends Fragment implements PostLoginHandler {
    TextView dataviewoption,chartviewoption;
    private Context mCon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_options, container, false);
        mCon =  getContext();
        dataviewoption=(TextView)rootView.findViewById(R.id.dataviewoption);
        chartviewoption=(TextView)rootView.findViewById(R.id.chartviewoption);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        InitializeListeners();
    }

    private void InitializeListeners() {
        dataviewoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postData = "{\"id\": \"pplctr_v2_x_query_sum_by_locn\", \n" +
                        " \"qtype\": \"esql\", \n" +
                        " \"qcontext\": \"scglobal\", \n" +
                        " \"params\": [ \n" +
                        "  { \n" +
                        "   \"key\": \"$3\", \"value\": \"05\" \n" +
                        "  }, \n" +
                        "  { \n" +
                        "   \"key\": \"$4\", \"value\": \"10\" \n" +
                        "  } \n" +
                        " ] }";
                PostLoginHeper.performDataRecordsTask(postData,OptionsFragment.this,mCon,"data");
            }
        });
        chartviewoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postData = "{\"id\": \"pplctr_v2_x_query_sum_by_locn\", \n" +
                        " \"qtype\": \"esql\", \n" +
                        " \"qcontext\": \"scglobal\", \n" +
                        " \"params\": [ \n" +
                        "  { \n" +
                        "   \"key\": \"$3\", \"value\": \"05\" \n" +
                        "  }, \n" +
                        "  { \n" +
                        "   \"key\": \"$4\", \"value\": \"10\" \n" +
                        "  } \n" +
                        " ] }";
                PostLoginHeper.performDataRecordsTask(postData,OptionsFragment.this,mCon,"chart");
            }
        });
    }

    ArrayList<Integer> dataVals = new ArrayList<>();
    @Override
    public void postLoginDataRetreived(String response, String responseType) {
        try {
            dataVals = new ArrayList<>();
            JSONObject obj = new JSONObject(response);
            JSONArray dataArr = obj.getJSONArray("data");
            for (int i=0;i<dataArr.length()-1;i++){
                JSONObject dataObj = dataArr.getJSONObject(i);
                JSONArray data = dataObj.getJSONArray("data");
                for (int j=0;j<data.length()-1;j++){
                     dataVals.add(data.getInt(j));
                }
            }
            if (responseType.trim().toLowerCase().equals("chart")){
                ((PostLoginActivity)getActivity()).showBarPage(dataVals);
            }else{
                ((PostLoginActivity)getActivity()).showTabularPage(dataVals);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
