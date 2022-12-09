package com.teamcircle.sdkdemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.amazonaws.util.StringUtils;
import com.xkglow.xkchrome.sdk.TeamCircleSDK;
import com.xkglow.xkchrome.sdk.api.enity.TeamCircleGeneralThrowable;
import com.xkglow.xkchrome.sdk.api.enity.TeamCircleThrowable;
import com.xkglow.xkchrome.sdk.listener.ProfileAccountListener;
import com.xkglow.xkchrome.sdk.listener.TeamCircleListener;
import com.xkglow.xkchrome.sdk.model.bean.ShareJson;
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
        public void onEditProfile(String accountName, String avatarUrl, String bio, ProfileAccountListener profileAccountListener) {
            try {
                JSONObject object = new JSONObject();
                object.put("username", accountName == null ? SpUtil.getInstance().getString(SpUtil.USERNAME, "") : accountName);
                if (avatarUrl != null) {
                    object.put("avatar", avatarUrl);
                }
                object.put("bio", bio == null ? SpUtil.getInstance().getString(SpUtil.BIO, "") : bio);
                ApiHelper.post("demo/v1/user/users/" + SpUtil.getInstance().getInt(SpUtil.USERID, 0), object.toString(), new ApiHelper.ApiCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        boolean resultStatus = response.optBoolean("resultStatus");
                        SpUtil.getInstance().save(SpUtil.USERNAME, accountName);
                        if (resultStatus) {
                            if (profileAccountListener != null)
                                profileAccountListener.onSuccess();
                        }
                    }

                    @Override
                    public void onFail(String errorMsg) {
                        if (profileAccountListener != null) {
                            profileAccountListener.onError(new TeamCircleGeneralThrowable(new Throwable(errorMsg)));
                        }
                        Log.d("onFail", "" + "errorMsg" + errorMsg);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
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
        TeamCircleSDK.getInstance().init(this, "ty9muwb1", "7c44217bea2c46beaeb87aa0b01a0fbc");
        TeamCircleSDK.getInstance().registerTeamCircleListener(teamCircleListener);
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
