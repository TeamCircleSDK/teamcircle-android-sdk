package com.teamcircle.sdkdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xkglow.xkchrome.sdk.TeamCircleSDK;
import com.xkglow.xkchrome.sdk.im.ui.IMMessageActivity;
import com.xkglow.xkchrome.sdk.listener.TeamCircleListener;

public class MsgActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvMsgUnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        setContentView(R.layout.activity_msg);
        findViewById(R.id.ivBack).setOnClickListener(this);
        findViewById(R.id.tvMsg).setOnClickListener(this);
        findViewById(R.id.ivMsg).setOnClickListener(this);
        tvMsgUnRead = findViewById(R.id.tvMsgUnRead);
        TeamCircleSDK.getInstance().registerTeamCircleListener(new TeamCircleListener() {
            @Override
            public void onMessageChanged(int msgCount) {
                if (msgCount > 0) {
                    tvMsgUnRead.setVisibility(View.VISIBLE);
                    tvMsgUnRead.setText("" + msgCount);
                } else {
                    tvMsgUnRead.setVisibility(View.GONE);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.tvMsg:
            case R.id.ivMsg:
                IMMessageActivity.start(this);
                break;
        }
    }
}