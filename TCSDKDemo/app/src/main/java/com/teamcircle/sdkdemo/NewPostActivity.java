package com.teamcircle.sdkdemo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.xkglow.xkchrome.sdk.TeamCircleSDK;
import com.xkglow.xkchrome.sdk.listener.NewPostCallback;
import com.xkglow.xkchrome.sdk.model.bean.NewPostDetail;

public class NewPostActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        setContentView(R.layout.activity_new_post);
        findViewById(R.id.ivBack).setOnClickListener(this);
        findViewById(R.id.tvNewPost).setOnClickListener(this);
        findViewById(R.id.ivNewPost).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.ivNewPost:
            case R.id.tvNewPost:
                TeamCircleSDK.getInstance().openNewPostActivity(new NewPostCallback() {
                    @Override
                    public void onNewPost(NewPostDetail newPostDetail) {

                    }
                });
                break;
        }
    }
}