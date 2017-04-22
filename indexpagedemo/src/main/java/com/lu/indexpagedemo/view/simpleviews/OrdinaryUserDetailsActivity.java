package com.lu.indexpagedemo.view.simpleviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.Utils;

public class OrdinaryUserDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.TRANSPARENT(getWindow());

        setContentView(R.layout.activity_ordinary_user_detials);
    }
}
