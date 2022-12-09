package com.teamcircle.sdkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CircleMainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        setContentView(R.layout.activity_circle_main);
        findViewById(R.id.rlController).setOnClickListener(this);
        findViewById(R.id.rlFeed).setOnClickListener(this);
        findViewById(R.id.rlUserCenter).setOnClickListener(this);
        findViewById(R.id.rlSearch).setOnClickListener(this);
        findViewById(R.id.rlNewPost).setOnClickListener(this);
        findViewById(R.id.ivBack).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.rlController:
                startActivity(new Intent(this, CircleIndexActivity.class));
                break;
            case R.id.rlFeed:
                startActivity(new Intent(this, FeedViewActivity.class));
                break;
            case R.id.rlUserCenter:
                startActivity(new Intent(this, UserCenterActivity.class));
                break;
            case R.id.rlSearch:
                startActivity(new Intent(this, SearchModuleActivity.class));
                break;
            case R.id.rlNewPost:
                startActivity(new Intent(this, NewPostActivity.class));
                break;
        }
    }
}