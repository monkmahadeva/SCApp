package com.psybot.prasidh.testapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class TableFragment extends Fragment {
    private Context mCon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_table, container, false);
        mCon = getContext();
        init(rootView);
        return rootView;
        //        return super.onCreateView(inflater, container, savedInstanceState);
    }


        public void init(View parent){
            TableLayout ll = (TableLayout)parent.findViewById(R.id.tabla_cuerpo);
            TableRow tbrow0 = (TableRow)parent.findViewById(R.id.tabla_cabecera);
            SharedPreferences prefs = getContext().getSharedPreferences("data_prefs", Context.MODE_PRIVATE);
            String savedString = prefs.getString("string", "");
            StringTokenizer st = new StringTokenizer(savedString, ",");
            ArrayList<Integer> savedList = new ArrayList<>();
            int count = 0;
            TextView tv = new TextView(getContext());
            tv.setText("Values");
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(40.0f);

            tbrow0.addView(tv);
            TextView tv_label = new TextView(getContext());
            tv_label.setText("Labels");
            tv_label.setTextSize(40.0f);
            tv_label.setTextColor(Color.BLACK);
            tbrow0.addView(tv_label);
            while (st.hasMoreElements()) {

                TableRow row= new TableRow(getContext());
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);
                row.setMinimumHeight(30);
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    row.setBackgroundDrawable(ContextCompat.getDrawable(mCon, R.drawable.cell_shape) );
                } else {
                    row.setBackground(ContextCompat.getDrawable(mCon, R.drawable.cell_shape));
                }
                TextView qty = new TextView(getContext());
                qty.setTextColor(Color.parseColor("#000000"));
                qty.setText(Integer.parseInt(st.nextToken())+"");
                qty.setTextSize(25.0f);
                row.addView(qty);
                ll.addView(row,count);
                count++;
            }
        }
}
