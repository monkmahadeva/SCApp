package com.psybot.prasidh.testapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class PostLoginActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);
        frameLayout = (FrameLayout)findViewById(R.id.fragment_container);
        Fragment fragment = new OptionsFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.fragment_container, fragment).commit();
    }

    public void showBarPage(ArrayList<Integer> vals) {
        Fragment fragment = new BarChartFragment();
        SharedPreferences prefs = getSharedPreferences("data_prefs", Context.MODE_PRIVATE);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < vals.size(); i++) {
            str.append(vals.get(i)).append(",");
        }
        prefs.edit().putString("string", str.toString()).apply();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment).commit();
    }

    public void showTabularPage(ArrayList<Integer> dataVals) {
        Fragment fragment = new TableFragment();
        SharedPreferences prefs = getSharedPreferences("data_prefs", Context.MODE_PRIVATE);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < dataVals.size(); i++) {
            str.append(dataVals.get(i)).append(",");
        }
        prefs.edit().putString("string", str.toString()).apply();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment).commit();
    }
}
