package com.teamcircle.sdkdemo;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.xkglow.xkchrome.sdk.TeamCircleSDK;
import com.xkglow.xkchrome.sdk.listener.NewPostCallback;
import com.xkglow.xkchrome.sdk.model.bean.NewPostDetail;
import com.xkglow.xkchrome.sdk.model.enumeration.TeamCircleTheme;
import com.xkglow.xkchrome.sdk.ui.StoreActivity;
import com.xkglow.xkchrome.sdk.xlog.XLog;

public class CustomActivity extends AppCompatActivity implements View.OnClickListener {

    private int currentId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        setContentView(R.layout.activity_custom);
        findViewById(R.id.ivBack).setOnClickListener(this);
        findViewById(R.id.rlTextDefualt).setOnClickListener(this);
        findViewById(R.id.rlTextFontRegular).setOnClickListener(this);
        findViewById(R.id.rlTextFontBold).setOnClickListener(this);
        findViewById(R.id.rlTextFontAction).setOnClickListener(this);
        findViewById(R.id.rlTextFontProductName).setOnClickListener(this);
        findViewById(R.id.rlTextFontProductPrice).setOnClickListener(this);
        findViewById(R.id.rlProductDescColor).setOnClickListener(this);
        findViewById(R.id.rlIconFavorite).setOnClickListener(this);
        findViewById(R.id.rlIconFavoriteSelected).setOnClickListener(this);
        findViewById(R.id.rlIconTag).setOnClickListener(this);
        findViewById(R.id.rlIconTagSelected).setOnClickListener(this);
        findViewById(R.id.rlIconLike).setOnClickListener(this);
        findViewById(R.id.rlIconLikeSelected).setOnClickListener(this);
        findViewById(R.id.rlIconShare).setOnClickListener(this);
        findViewById(R.id.rlIconComment).setOnClickListener(this);
        findViewById(R.id.rlIconNewPost).setOnClickListener(this);
        findViewById(R.id.rlIconBack).setOnClickListener(this);
        findViewById(R.id.rlSystemBackgroundColor).setOnClickListener(this);
        findViewById(R.id.rlBackgroundEffectColor).setOnClickListener(this);
        findViewById(R.id.rlLinkColor).setOnClickListener(this);
        findViewById(R.id.rlPrivacyPolicy).setOnClickListener(this);
        findViewById(R.id.rlTermsAndConditions).setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Restore the original style
        switch (currentId){
            case R.id.rlTextFontRegular:
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
                TeamCircleSDK.getInstance().getUiSetting().setTextFontRegular(typeface, getResources().getColor(R.color.dark_main_text_color));
                break;
            case R.id.rlTextFontBold:
                Typeface typefaceBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
                TeamCircleSDK.getInstance().getUiSetting().setTextFontBold(typefaceBold, getResources().getColor(R.color.dark_main_text_color));
                break;
            case R.id.rlTextFontAction:
                Typeface typefaceAction = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
                TeamCircleSDK.getInstance().getUiSetting().setTextFontAction(typefaceAction, getResources().getColor(R.color.dark_main_color));
                break;
            case R.id.rlTextFontProductName:
                Typeface typefaceProductName = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
                TeamCircleSDK.getInstance().getUiSetting().setTextFontProductName(typefaceProductName, getResources().getColor(R.color.dark_secondary_text_color));
                break;
            case R.id.rlTextFontProductPrice:
                Typeface typefaceProductPrice = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
                TeamCircleSDK.getInstance().getUiSetting().setTextFontProductPrice(typefaceProductPrice, getResources().getColor(R.color.dark_secondary_text_color));
                break;
            case R.id.rlProductDescColor:
                Typeface typefaceProductDesc = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
                TeamCircleSDK.getInstance().getUiSetting().setTextFontProductDesc(typefaceProductDesc, getResources().getColor(R.color.dark_secondary_text_color));
                break;
            case R.id.rlIconFavorite:
                TeamCircleSDK.getInstance().getUiSetting().setIconFavorite(R.drawable.dark_circle_favor);
                break;
            case R.id.rlIconFavoriteSelected:
                TeamCircleSDK.getInstance().getUiSetting().setIconFavoriteSelected(R.drawable.dark_circle_favor_sel);
                break;
            case R.id.rlIconTag:
                TeamCircleSDK.getInstance().getUiSetting().setIconTag(R.drawable.dark_ready_to_post_tag);
                break;
            case R.id.rlIconTagSelected:
                TeamCircleSDK.getInstance().getUiSetting().setIconTagSelected(R.drawable.light_ready_to_post_tag);
                break;
            case R.id.rlIconLike:
                TeamCircleSDK.getInstance().getUiSetting().setIconLike(R.drawable.dark_circle_like);
                break;
            case R.id.rlIconLikeSelected:
                TeamCircleSDK.getInstance().getUiSetting().setIconLikeSelected(R.drawable.dark_circle_like_sel);
                break;
            case R.id.rlIconShare:
                TeamCircleSDK.getInstance().getUiSetting().setIconShare(R.drawable.dark_share);
                break;
            case R.id.rlIconComment:
                TeamCircleSDK.getInstance().getUiSetting().setIconComment(R.drawable.dark_circle_comment);
                break;
            case R.id.rlIconNewPost:
                TeamCircleSDK.getInstance().getUiSetting().setIconNewPost(R.drawable.add_post);
                break;
            case R.id.rlIconBack:
                TeamCircleSDK.getInstance().getUiSetting().setIconBack(R.drawable.dark_back);
                break;
            case R.id.rlSystemBackgroundColor:
                TeamCircleSDK.getInstance().getUiSetting().setSystemBackgroundColor(getResources().getColor(R.color.dark_background_color));
                break;
            case R.id.rlBackgroundEffectColor:
                TeamCircleSDK.getInstance().getUiSetting().setBackgroundEffectColor(getResources().getColor(R.color.dark_background_effect_color));
                break;
            case R.id.rlLinkColor:
                TeamCircleSDK.getInstance().getUiSetting().setLinkColor(getResources().getColor(R.color.dark_main_color));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.rlTextDefualt:
                TeamCircleSDK.getInstance().getUiSetting().setTheme(TeamCircleTheme.DARK);
                startActivity(new Intent(this, CircleActivity.class));
                break;
            case R.id.rlTextFontRegular:
                currentId= R.id.rlTextFontRegular;
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
                TeamCircleSDK.getInstance().getUiSetting().setTextFontRegular(typeface, getResources().getColor(R.color.redColor));
                startActivity(new Intent(this, CircleActivity.class));
                break;
            case R.id.rlTextFontBold:
                currentId= R.id.rlTextFontBold;
                Typeface typefaceBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
                TeamCircleSDK.getInstance().getUiSetting().setTextFontBold(typefaceBold, getResources().getColor(R.color.greenColor));
                startActivity(new Intent(this, CircleActivity.class));
                break;
            case R.id.rlTextFontAction:
                currentId= R.id.rlTextFontAction;
                Typeface typefaceAction = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
                TeamCircleSDK.getInstance().getUiSetting().setTextFontAction(typefaceAction, getResources().getColor(R.color.redColor));
                startActivity(new Intent(this, CircleActivity.class));
                break;
            case R.id.rlTextFontProductName:
                currentId= R.id.rlTextFontProductName;
                Typeface typefaceProductName = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
                TeamCircleSDK.getInstance().getUiSetting().setTextFontProductName(typefaceProductName, getResources().getColor(R.color.redColor));
                startActivity(new Intent(this, StoreActivity.class));
                break;
            case R.id.rlTextFontProductPrice:
                currentId= R.id.rlTextFontProductPrice;
                Typeface typefaceProductPrice = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
                TeamCircleSDK.getInstance().getUiSetting().setTextFontProductPrice(typefaceProductPrice, getResources().getColor(R.color.blue));
                startActivity(new Intent(this, StoreActivity.class));
                break;
            case R.id.rlProductDescColor:
                currentId= R.id.rlProductDescColor;
                Typeface typefaceProductDesc = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
                TeamCircleSDK.getInstance().getUiSetting().setTextFontProductDesc(typefaceProductDesc, getResources().getColor(R.color.greenColor));
                startActivity(new Intent(this, StoreActivity.class));
                break;
            case R.id.rlIconFavorite:
                currentId= R.id.rlIconFavorite;
                TeamCircleSDK.getInstance().getUiSetting().setIconFavorite(R.drawable.favorite);
                startActivity(new Intent(this, CircleActivity.class));
                break;
            case R.id.rlIconFavoriteSelected:
                currentId= R.id.rlIconFavoriteSelected;
                TeamCircleSDK.getInstance().getUiSetting().setIconFavoriteSelected(R.drawable.favoriteselected);
                startActivity(new Intent(this, CircleActivity.class));
                break;
            case R.id.rlIconTag:
                currentId= R.id.rlIconTag;
                TeamCircleSDK.getInstance().getUiSetting().setIconTag(R.drawable.tag);
                startActivity(new Intent(this, CircleActivity.class));
                break;
            case R.id.rlIconTagSelected:
                currentId= R.id.rlIconTagSelected;
                TeamCircleSDK.getInstance().getUiSetting().setIconTagSelected(R.drawable.tagselected);
                TeamCircleSDK.getInstance().openNewPostActivity(new NewPostCallback() {
                    @Override
                    public void onNewPost(NewPostDetail newPostDetail) {

                    }
                });
                break;
            case R.id.rlIconLike:
                currentId= R.id.rlIconLike;
                TeamCircleSDK.getInstance().getUiSetting().setIconLike(R.drawable.like);
                startActivity(new Intent(this, CircleActivity.class));
                break;
            case R.id.rlIconLikeSelected:
                currentId= R.id.rlIconLikeSelected;
                TeamCircleSDK.getInstance().getUiSetting().setIconLikeSelected(R.drawable.likeselected);
                startActivity(new Intent(this, CircleActivity.class));
                break;
            case R.id.rlIconShare:
                currentId= R.id.rlIconShare;
                TeamCircleSDK.getInstance().getUiSetting().setIconShare(R.drawable.share);
                startActivity(new Intent(this, CircleActivity.class));
                break;
            case R.id.rlIconComment:
                currentId= R.id.rlIconComment;
                TeamCircleSDK.getInstance().getUiSetting().setIconComment(R.drawable.comment);
                startActivity(new Intent(this, CircleActivity.class));
                break;
            case R.id.rlIconNewPost:
                currentId= R.id.rlIconNewPost;
                TeamCircleSDK.getInstance().getUiSetting().setIconNewPost(R.drawable.newpost);
                startActivity(new Intent(this, CircleActivity.class));
                break;
            case R.id.rlIconBack:
                currentId= R.id.rlIconBack;
                TeamCircleSDK.getInstance().getUiSetting().setIconBack(R.drawable.back_black);
                startActivity(new Intent(this, CircleActivity.class));
                break;
            case R.id.rlSystemBackgroundColor:
                currentId= R.id.rlSystemBackgroundColor;
                TeamCircleSDK.getInstance().getUiSetting().setSystemBackgroundColor(getResources().getColor(R.color.redColor));
                startActivity(new Intent(this, CircleActivity.class));
                break;
            case R.id.rlBackgroundEffectColor:
                currentId= R.id.rlBackgroundEffectColor;
                TeamCircleSDK.getInstance().getUiSetting().setBackgroundEffectColor(getResources().getColor(R.color.greenColor));
                startActivity(new Intent(this, CircleActivity.class));
                break;
            case R.id.rlLinkColor:
                currentId= R.id.rlLinkColor;
                TeamCircleSDK.getInstance().getUiSetting().setLinkColor(getResources().getColor(R.color.blue));
                startActivity(new Intent(this, CircleActivity.class));
                break;
            case R.id.rlPrivacyPolicy:
                TeamCircleSDK.getInstance().setPrivacyPolicy("https://www.xkglow.com/privacy-policy");
                TeamCircleSDK.getInstance().openAccountActivity();
                break;
            case R.id.rlTermsAndConditions:
                TeamCircleSDK.getInstance().setTermsAndConditions("https://www.xkglow.com/terms-and-condition");
                TeamCircleSDK.getInstance().openAccountActivity();
                break;
        }
    }
}