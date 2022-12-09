package com.teamcircle.sdkdemo;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpUtil {

    private static final String SP = "sharedPreference";
    public static final String USERID = "userId";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String BIO = "bio";

    private SpUtil() {
    }

    private static SpUtil instance = new SpUtil();
    private static SharedPreferences mSp = null;

    public static SpUtil getInstance() {

        if (mSp == null) {
            mSp = MyApplication.getContext().getSharedPreferences(SP, Context.MODE_PRIVATE);
        }
        return instance;
    }


    public void save(String key, Object value) {

        if (value instanceof String) {
            mSp.edit().putString(key, (String) value).commit();
        } else if (value instanceof Boolean) {
            mSp.edit().putBoolean(key, (Boolean) value).commit();
        } else if (value instanceof Integer) {
            mSp.edit().putInt(key, (Integer) value).commit();
        }
    }

    public String getString(String key, String defValue) {
        return mSp.getString(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mSp.getBoolean(key, defValue);
    }

    public void clear() {
        mSp.edit().clear().commit();
    }

    public int getInt(String key, int defValue) {
        return mSp.getInt(key, defValue);
    }

    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
