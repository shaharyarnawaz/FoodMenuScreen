package com.shery.foodmenuscreen.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shery.foodmenuscreen.R;
import com.shery.foodmenuscreen.adapter.MenusAdapter;
import com.shery.foodmenuscreen.constants.Constant;

public class SearchActivity extends AppCompatActivity {
    RecyclerView rc_RecyclerView;
    LinearLayoutManager linearLayoutManager;
    LinearLayout lay_search;
    EditText edt_search;
    TextView txt_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        rc_RecyclerView = findViewById(R.id.rc_RecyclerView);

        lay_search = findViewById(R.id.lay_search);
        edt_search = findViewById(R.id.edt_search);
        txt_cancel = findViewById(R.id.txt_cancel);


        lay_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_search.requestFocus();
            }
        });
        txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rc_RecyclerView.setLayoutManager(linearLayoutManager);
        rc_RecyclerView.setAdapter(new MenusAdapter(this, Constant.generateData() /*,new TabClick() {
            @Override
            public void onTabClicked(int position) {

            }
        }*/));

    }
}