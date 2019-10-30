package com.psybot.prasidh.testapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class BarChartFragment extends Fragment {
    BarChart chart ;
    ArrayList<BarEntry> BARENTRY ;
    //ArrayList<String> BarEntryLabels ;
    BarDataSet Bardataset ;
    BarData BARDATA ;
    private Context mCon;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bar_chart, container, false);
        mCon =  getContext();
        chart = (BarChart)rootView.findViewById(R.id.barchart);

        BARENTRY = new ArrayList<>();

//        BarEntryLabels = new ArrayList<String>();

        AddValuesToBARENTRY();

//        AddValuesToBarEntryLabels();
        Bardataset = new BarDataSet(BARENTRY, "");



        BARDATA = new BarData(Bardataset);
        BARDATA.setBarWidth(1.5f);
        Bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

        chart.setData(BARDATA);

        chart.animateY(3000);

        return rootView;

    }

    public void AddValuesToBARENTRY(){
        SharedPreferences prefs = mCon.getSharedPreferences("data_prefs", Context.MODE_PRIVATE);
        String savedString = prefs.getString("string", "");
        StringTokenizer st = new StringTokenizer(savedString, ",");
        ArrayList<Integer> savedList = new ArrayList<>();
        int count = 0;
        while(st.hasMoreElements()) {
//            savedList.add();
            BARENTRY.add(new BarEntry(count,Integer.parseInt(st.nextToken())));
            count+=5;
        }

//        BARENTRY.add(new BarEntry(4f, 1));
//        BARENTRY.add(new BarEntry(6f, 2));
//        BARENTRY.add(new BarEntry(8f, 3));
//        BARENTRY.add(new BarEntry(7f, 4));
//        BARENTRY.add(new BarEntry(3f, 5));

    }

    public void AddValuesToBarEntryLabels(){

//        BarEntryLabels.add("January");
//        BarEntryLabels.add("February");
//        BarEntryLabels.add("March");
//        BarEntryLabels.add("April");
//        BarEntryLabels.add("May");
//        BarEntryLabels.add("June");

    }
}
