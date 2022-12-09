package com.teamcircle.sdkdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xkglow.xkchrome.sdk.TeamCircleSDK;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.grey));
        setContentView(R.layout.activity_main);
        findViewById(R.id.ivExit).setOnClickListener(this);
        findViewById(R.id.rlCircle).setOnClickListener(this);
        findViewById(R.id.rlShare).setOnClickListener(this);
        findViewById(R.id.rlStore).setOnClickListener(this);
        findViewById(R.id.rlMsg).setOnClickListener(this);
        findViewById(R.id.tvCustom).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivExit:
                ApiHelper.get("demo/v1/user/logout?userId=" + SpUtil.getInstance().getInt(SpUtil.USERID, 0), new ApiHelper.ApiCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        boolean resultStatus = response.optBoolean("resultStatus");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (resultStatus) {
                                    SpUtil.getInstance().clear();
                                    TeamCircleSDK.getInstance().userLogout();
                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                    }

                    @Override
                    public void onFail(String errorMsg) {
                        Log.d("onFail", "" + "errorMsg" + errorMsg);
                    }
                });
                break;
            case R.id.rlCircle:
                startActivity(new Intent(this, CircleMainActivity.class));
                break;
            case R.id.rlShare:
//                startActivity(new Intent(this, CircleMainActivity.class));
                break;
            case R.id.rlStore:
                startActivity(new Intent(this, StoreIndexActivity.class));
                break;
            case R.id.rlMsg:
                startActivity(new Intent(this, MsgActivity.class));
                break;
            case R.id.tvCustom:
                startActivity(new Intent(this, CustomActivity.class));
                break;
        }
    }
}