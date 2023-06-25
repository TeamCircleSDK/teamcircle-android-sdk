package com.teamcircle.sdkdemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.amazonaws.util.StringUtils;
import com.xkglow.xkchrome.sdk.TeamCircleSDK;
import com.xkglow.xkchrome.sdk.api.enity.TeamCircleGeneralThrowable;
import com.xkglow.xkchrome.sdk.api.enity.TeamCircleThrowable;
import com.xkglow.xkchrome.sdk.listener.ProfileAccountListener;
import com.xkglow.xkchrome.sdk.listener.TeamCircleListener;
import com.xkglow.xkchrome.sdk.model.bean.ShareJson;
import com.xkglow.xkchrome.sdk.model.enumeration.TeamCircleTheme;
import com.xkglow.xkchrome.sdk.xlog.XLog;

import org.json.JSONException;
import org.json.JSONObject;

public class MyApplication extends Application {

    private static Context mContext;

    public final TeamCircleListener teamCircleListener = new TeamCircleListener() {

        @Override
        public void initSuccess() {

        }

        @Override
        public void circleError(TeamCircleThrowable throwable) {

        }

        @Override
        public void loginSuccess(String accountId) {

        }

        @Override
        public void logoutSuccess() {

        }

        @Override
        public void onEditProfile(@Nullable String accountName, @Nullable String avatarUrl, @Nullable String bio) {

        }

        @Override
        public void onNotificationStateChanged(int notificationCount) {

        }

        @Override
        public void onShareJsonDownloaded(ShareJson shareJsonContent) {

        }

        @Override
        public void onDeleteAccount() {

        }

        @Override
        public void onMessageChanged(int msgCount) {

        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        TeamCircleSDK.getInstance().init(this, "cojksawr", "d6526542a1c247c399aa41e66122118f");
        TeamCircleSDK.getInstance().registerTeamCircleListener(teamCircleListener);
        TeamCircleSDK.getInstance().init(this, "cojksawr", "d6526542a1c247c399aa41e66122118f");
        ApiHelper.setup();
    }

    public static Context getContext() {
        return mContext;
    }

    public void onTerminate() {
        super.onTerminate();
        TeamCircleSDK.getInstance().unRegisterTeamCircleListener(teamCircleListener);
    }

}
