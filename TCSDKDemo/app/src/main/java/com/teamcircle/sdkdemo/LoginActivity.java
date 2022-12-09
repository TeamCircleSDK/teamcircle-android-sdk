package com.teamcircle.sdkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.xkglow.xkchrome.sdk.TeamCircleSDK;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        setContentView(R.layout.activity_login);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etName.setText(SpUtil.getInstance().getString(SpUtil.USERNAME, ""));
        etEmail.setText(SpUtil.getInstance().getString(SpUtil.EMAIL, ""));


        findViewById(R.id.tvLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etName.getText().toString();
                String email = etEmail.getText().toString();
                if (userName == null || userName.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please input Username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (email == null || email.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please input Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!SpUtil.isEmail(email)) {
                    Toast.makeText(LoginActivity.this, "Please input valid Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                login(userName, email);
            }
        });
    }

    private void login(String username, String email) {

        try {
            JSONObject object =new JSONObject();
            object.put("username", username);
            object.put("email", email);
            ApiHelper.post("demo/v1/user/login", object.toString(), new ApiHelper.ApiCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    boolean resultStatus = response.optBoolean("resultStatus");
                    if (resultStatus) {
                        JSONObject object = response.optJSONObject("resultData");
                        int userId = object.optInt("userId");
                        String username = object.optString("username");
                        String email = object.optString("email");
                        String avatar = object.optString("avatar");
                        String bio = object.optString("bio").equals("null")?"":object.optString("bio");
                        TeamCircleSDK.getInstance().userLogin(userId + "", username, avatar, email, bio);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        SpUtil.getInstance().save(SpUtil.USERID, userId);
                        SpUtil.getInstance().save(SpUtil.USERNAME, username);
                        SpUtil.getInstance().save(SpUtil.BIO, bio);
                        SpUtil.getInstance().save(SpUtil.EMAIL, email);
                        finish();
                    }
                }

                @Override
                public void onFail(String errorMsg) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
                        }
                    });
                    Log.d("onFail", "" + "errorMsg" + errorMsg);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("object.toString()", "------------"+e.toString());
        }
    }
}