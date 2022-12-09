package com.teamcircle.sdkdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xkglow.xkchrome.sdk.TeamCircleSDK;

public class SearchModuleActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        setContentView(R.layout.activity_search_module);
        findViewById(R.id.ivBack).setOnClickListener(this);
        findViewById(R.id.tvSearch).setOnClickListener(this);
        findViewById(R.id.ivSearch).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.ivSearch:
            case R.id.tvSearch:
                TeamCircleSDK.getInstance().openSearchActivity();
                break;
        }
    }
}