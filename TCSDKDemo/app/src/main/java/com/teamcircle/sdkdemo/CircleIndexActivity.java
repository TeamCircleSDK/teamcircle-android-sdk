package com.teamcircle.sdkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class CircleIndexActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        setContentView(R.layout.activity_circle_index);

        findViewById(R.id.rlActivity).setOnClickListener(this);
        findViewById(R.id.rlFragment).setOnClickListener(this);
        findViewById(R.id.ivBack).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.rlActivity:
                startActivity(new Intent(this, CircleActivity.class));
                break;
            case R.id.rlFragment:
                startActivity(new Intent(this, CircleFragmentActivity.class));
                break;
        }
    }
}