package com.lu.indexpagedemo.view.simpleviews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.view.activitys.MainActivity;

public class WellcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        startActivity(new Intent(this, MainActivity.class));
    }
}
