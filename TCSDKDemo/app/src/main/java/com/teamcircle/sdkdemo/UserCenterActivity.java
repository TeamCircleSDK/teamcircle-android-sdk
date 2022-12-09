package com.teamcircle.sdkdemo;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.xkglow.xkchrome.sdk.TeamCircleSDK;

public class UserCenterActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        setContentView(R.layout.activity_user_center);
        findViewById(R.id.ivBack).setOnClickListener(this);
        findViewById(R.id.tvUserCenter).setOnClickListener(this);
        findViewById(R.id.ivUser).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.ivUser:
            case R.id.tvUserCenter:
                TeamCircleSDK.getInstance().openAccountActivity();
                break;
        }
    }
}