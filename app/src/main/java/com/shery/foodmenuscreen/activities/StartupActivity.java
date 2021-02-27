package com.shery.foodmenuscreen.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.shery.foodmenuscreen.R;

public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

    }

    public void startActivity(View view) {
        switch (view.getId()) {
            case R.id.btnDiscreete:
                startActivity(new Intent(StartupActivity.this, DiscreatePositionActivity.class));
                break;
            case R.id.btnCustom:
                startActivity(new Intent(StartupActivity.this, MainActivity.class));
                break;
        }
    }
}