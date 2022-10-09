# [TeamCircle](https://teamcircle.io) SDK for Android

![Platform](https://img.shields.io/badge/platform-ANDROID-orange.svg)
![Languages](https://img.shields.io/badge/language-JAVA-orange.svg)

# Introduction
This guide introduces a detailed integration guide of Team Circle Android SDK. 
If you want to learn more about the social SDK functions, please visit [Team Circle iOS & Android SDK Introduction](https://www.teamcircle.io/document/Team_Circle_iOS_Android_Social_SDK_Introduction)

# System environmental requirements

Android Studio, version 3.0 or above
Android, version 11 (Android SDK version 30) or above

# Main features

Team Circle offers a social SDK which is fully customizable and easy to integrate.

* **Circle** 
This is the core community function which Team Cricle SDK provides within your app. App users can make new posts, browse, comment, like, and collect other users’ posts. Please find more information about the introduction of Circle at [Circle in-app community](https://www.teamcircle.io/document/Team_Circle_iOS_Android_Social_SDK_Introduction?target=Circle_in-app_community).

<img src="https://user-images.githubusercontent.com/114135053/192727471-e96837fe-8fa5-43dd-a30f-9bf82c07d392.png" width="50%">

* **Store**
If you subscribed to the Premium Plan, the built-in store function is enabled in your SDK. The store banner appears at the top of customer’s social feed page. Your customer can also tag store products when making a new prost. Please find out more info about the UI & UX of the store section here [Store](https://www.teamcircle.io/document/Team_Circle_iOS_SDK_Integration_Guide?target=Store). You can [manage products and categories](https://www.teamcircle.io/document/Team_Circle_Admin_Dashboard_Feature_Introduction?target=Manage_store) in the store via Admin Dashboard. If you already have an existing eCommerce section in your app and still wish to utilize Team Circle Customer Posts Bar to funnel UGC to your product listings, we also offer [a module that can be integrated into your own app pages outside the Circle](https://www.teamcircle.io/document/Team_Circle_iOS_SDK_Integration_Guide?target=Store).

<img src="https://user-images.githubusercontent.com/114135053/192728188-dd3a80a1-7e8c-480f-a526-fb6f378ed664.png" width="50%">

* **Instant messaging** Customers can chat with others individually using our IM system. We currently support sending text messages, voice messages, photos, videos. An admin can also [link the admin account with a Circle community account](https://www.teamcircle.io/document/Team_Circle_Admin_Dashboard_Feature_Introduction?target=Link_unlink_accounts). After linking, an “official staff” badge will appear next to the Circle account. Please find more information about details of the IM system here [Instant Messaging](https://www.teamcircle.io/document/Team_Circle_iOS_Android_Social_SDK_Introduction?target=Instant_messaging).

<img src="https://user-images.githubusercontent.com/114135053/192732545-e686d10f-bd3c-4c70-a7eb-ab1b3f7475d0.png" width="50%">

* **Customizable UI** Circle SDK offers customizable UI components so that you can fine tune the Circle to match the style of the rest of your app. First of all, you may switch between two different themes: Light Theme or Dark Theme. Then, all buttons, text, icons, and click effects can be customized. Please find more information about details of the Customizable UI here [Customizable UI](https://www.teamcircle.io/document/Team_Circle_iOS_Android_Social_SDK_Introduction?target=Customizable_UI).

<img src="https://user-images.githubusercontent.com/114135053/192732951-bbefef0a-d617-46a9-81f5-24c171a3ef0d.png" width="50%">

# Integration
## Get started
* Sign up at https://www.TeamCircle.io.
* Login and find App ID and App Key in Admin Dashboard → SDK&DOC → App Info, you will need them in the following steps.

## Project configuration
### Step 1 Add Dependency in your project’s build.gradle
```bash
buildscript {
  //...
}
allprojects {
  repositories {
    //...
    jcenter()
    maven { url 'https://jitpack.io' }
  }
}
```
> **note:** Gradle version7.0.0 or higher;Add Dependency in your project’s setting.gradle
```bash
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    jcenter() // Warning: this repository is going to shut down soon
    maven { url 'https://jitpack.io' }
  }
}
```

### Step 2 Add tools:replace="android:theme" in <application> tag in AndroidManifest.xml
```bash
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android" xmlns:tools="http://schemas.android.com/tools" >
  // ...
  <application
    // ...
    tools:replace="android:theme">
    // ...
  </application>
</manifest>
```

### Step 3 Copy and paste Team Circle SDK.aar into app/libs folder
```bash
android {
  //...
  repositories {
    flatDir {
      dirs 'libs'
    }
  }
}
```
> **note:** Gradle version7.0.0 or higher;Add Dependency in your project’s setting.gradle
```bash
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    flatDir {
      dirs 'app/libs'
    }
  }
}
```

### Step 4 Xcode Add Privacy Permission Description
```bash
implementation(name: 'TeamCircleSDK_Release', ext:'aar')
```
Circle SDK has local data storage and UI interface, so you also need to include the following dependencies in your project:
```bash
com.tencent:mmkv-static
com.google.code.gson:gson
com.github.bumptech.glide:glide
com.squareup.okhttp3:okhttp
android.arch.persistence.room:runtime
com.giphy.sdk:ui
com.google.firebase:firebase-core
com.airbnb.android:lottie
com.amazonaws:aws-android-sdk-s3
org.greenrobot:eventbus
com.github.CarGuo.GSYVideoPlayer:GSYVideoPlayer
org.jsoup:jsoup
io.hyphenate:hyphenate-chat
```
If your project does not have the above dependent package files, you can also install the dependent packages in the following ways:
```bash
com.tencent:mmkv-static
implementation 'com.tencent:mmkv-static:1.2.10'
implementation 'com.google.code.gson:gson:2.8.7'

implementation 'com.github.bumptech.glide:glide:4.11.0'
annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'
implementation "com.github.bumptech.glide:okhttp3-integration:4.11.0"

implementation 'com.squareup.okhttp3:okhttp:4.7.2'
implementation 'com.squareup.okhttp3:logging-interceptor:4.7.2'

implementation 'android.arch.persistence.room:runtime:1.1.1'
annotationProcessor "android.arch.persistence.room:compiler:1.1.1"

implementation 'com.giphy.sdk:ui:1.2.8'

implementation 'com.google.firebase:firebase-core:17.2.2'

implementation "com.airbnb.android:lottie:3.2.2"

implementation 'com.amazonaws:aws-android-sdk-s3:2.16.0'
implementation('com.amazonaws:aws-android-sdk-mobile-client:2.13.+@aar') { transitive = true }
implementation('com.amazonaws:aws-android-sdk-auth-userpools:2.13.+@aar') { transitive = true }

implementation 'org.greenrobot:eventbus:3.0.0'

implementation 'com.github.CarGuo.GSYVideoPlayer:GSYVideoPlayer:v8.1.6-jitpack'

implementation 'org.jsoup:jsoup:1.14.2'

implementation 'io.hyphenate:hyphenate-chat:3.9.5'
```

## TeamCircleListener callbacks
TeamCircleListener gives the results (success or fail) of SDK initialization, login, log out, user data modification, download json file, and unread messages number.
```bash
TeamCircleSDK.getInstance().registerTeamCircleListener(new TeamCircleListener() {
  @Override
  public void loginSuccess(String accountId) {}
  @Override
  public void logoutSuccess() {}
  @Override
  public void onNotificationStateChanged(int notificationCount) {}
  @Override
  public void onEditProfile(String accountName, String avatarUrl, String bio, ProfileAccountListener profileAccountListener) {}
  @Override
  public void onShareJsonDownloaded(String shareJsonContent) {}
  @Override
  public void onDeleteAccount() {}
});
```
Error Codes:
20001: SDK initialization fail
20002: Obtain UUID fail
20003: User not login
20004: Feature not supported

## SDK initialization
There are two options to initialize the SDK:
First, initialize the SDK in ‘onCreate’ of the application with appId and appKey.
```bash
TeamCircleSDK.getInstance().init(application, "xxxx", "xxxxxxx1e472ba7c915d5ab1080dc");
```
Or, register a global listener in ‘onCreate’ of the application and unregister in 'onTerminate'
```bash
public final TeamCircleListener teamCircleListener = new TeamCircleListener () {
  //...
}

@Override
public void onCreate () {
  super.onCreate();
  TeamCircleSDK.getInstance().registerTeamCircleListener(teamCircleListener);
}

@Override
public void onTerminate () {
  super.onTerminate();
  TeamCircleSDK.getInstance().unRegisterTeamCircleListener(teamCircleListener);
}
```

## User account

**Method:** userLogin

**Description:** After user login to your app, call userLogin to update the user login state and sync data with Circle.

**Parameters:**

```bash
public void userLogin(String thirdPartyId, String accountName, String avatarUrl, String accountEmail, String bio);
```
**Sample Code:**

```bash
TeamCircleSDK.getInstance().userLogin(UserRepository.getInstance().getThirdPartyId(),UserRepository.getInstance().getMe().getAccountName(),UserRepository.getInstance().getMe().getAvatarUrl(),UserRepository.getInstance().getMe().getAccountEmail(),UserRepository.getInstance().getMe().getBio());
```

**Method:** userLogout

**Description:** Description: After user logout from your app, call userLogout to update the user login state and sync data with Circle.

**Parameters:**

```bash
public void userLogout();
```
**Sample Code:**

```bash
TeamCircleSDK.getInstance().userLogout();
```

## Actions and notifications
There are several types of actions including like, comment, reply, favorite, followed by other users, and posts get featured by admin. All these actions can be sent to customers as push notifications, and you only need to set ‘Push Notification Callback’ in the Admin Dashboard → SDK&DOC → APP Info. After receiving push notifications in the app, the following methods can be used to redirect to Circle Notification Module:

**Method:** jumpToNotificationCenter

**Parameters:**

```bash
public void jumpToNotificationCenter(Context context, String message);
```
**Sample Code:**

```bash
TeamCircleSDK.getInstance().jumpToNotificationCenter(context, "xxx started following you.");
```
There are two types of messages received by the server, Circle messages and IM offline messages. The specific parameters are as follows:
| **Param** | **Type** | **Description** |
| --- | --- | --- |
| callbackType | String | Message type:<br>NOTIFICATION: Circle messages;<br>MESSAGE: IM offline messages. |
| callbackData | List<Object> | Data list |
| sign | String | Encryption rules: appKey + callbackData encrypt with MD5 |

**Signature sample:**
When you receive notification data as follows: 
```bash
{
  "callbackType": "NOTIFICATION",
  "callbackData": [{
    "notificationType": "FOLLOW",
    "ownerId": "1",
    "accountId": "2",
    "accountName": "Maria",
    "notificationTime": "1661394887563",
    "unreadNum": 11
  }],
  "sign": "e850231501b44e95fea1c8058b11fe3e"
}
```
Suppose your appKey is apmhwgc3, then you need to concatenate the appKey and the received notification data with a semicolon as follows:
```bash
apmhwgc3;[{"notificationType":"FOLLOW","ownerId":"1","accountId":"2","accountName":"Maria","notificationTime":1661394887563,"unreadNum":11}]
```
Finally, encrypt the spliced string above by MD5, and you can get the final encrypted string: **e850231501b44e95fea1c8058b11fe3e**.


CallbackData params of NOTIFICATION:
| **Param** | **Type** | **Description** |
| --- | --- | --- |
| notificationType | String | Notification type:<br>FOLLOW<br>LIKE_POST<br>FEATURED_POST<br>COMMENT<br>LIKE_COMMENT<br>REPLY<br>POST_MENTION<br>COMMENT_MENTION<br>DOWNLOAD_JSON |
| ownerId | String | The user id who receiving the notification |
| accountId | String | The user id who sending the notification |
| accountName | String | The user name who sending the notification |
| notificationTime | Long | Notification sending time, Unix timestamp, unit: ms |

CallbackData params of MESSAGE:
| **Param** | **Type** | **Description** |
| --- | --- | --- |
| messageType | String | Notification type:<br>txt<br>img<br>audio<br>video |
| ownerId | String | The user id who receiving the notification |
| accountId | String | The user id who sending the notification |
| accountName | String | The user name who sending the notification |
| notificationTime | Long | Notification sending time, Unix timestamp, unit: ms |

## Circle modules
Circle module consists of Feed module, UserCenter module, Search Module, and NewPost Module. The circle SDK provides you with a whole Circle UI component and all small independent UI components that you can use separately.

**Method:** CircleFragment

**Description:** Add a whole Circle UI component into the navigation controller, so that Feed module, Search module, UserCenter module, and NewPost module are all included.

```bash
FragmentManager supportFragmentManager = getSupportFragmentManager();
FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
fragmentTransaction.replace(R.id.root_view, CircleFragment.newInstance());
fragmentTransaction.commit();
```
Don't forget to define the fragment in the layout file:

```bash
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  xmlns:tools="http://schemas.android.com/tools"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  tools:context="CircleFragmentActivity">
  <fragment
    android:name="com.xkglow.xkchrome.sdk.ui.fragment.CircleFragment"
    android:id="@+id/root_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
</androidx.constraintlayout.widget.ConstraintLayout>
```
Use the following activities or views if you want to separately use these UI components:

**Method:** FeedView

**Description:** In the Feed module, you can view posts, like, comment, favorite, and follow users.

```bash
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
  xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  xmlns:tools="http://schemas.android.com/tools"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  tools:context="CircleFragmentActivity">
  <com.xkglow.xkchrome.sdk.ui.FeedView
    android:id="@+id/root_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
</androidx.constraintlayout.widget.ConstraintLayout>
```
Or you can also initialize the feed module in code:

```bash
FeedView feedView = new FeedView(MainActivity.this);parentView.addView(feedView);
```

**Method:** UserCenterActivity

**Description:** UserCenter module shows profile image, username, followers and followings, user’s posts and favorite posts.

```bash
TeamCircleSDK.getInstance().openAccountActivity();
```

**Method:** SearchActivity

**Description:** Search module is used to search user accounts and hashtags.

```bash
TeamCircleSDK.getInstance().openSearchActivity();
```

**Method:** NewPostActivity

**Description:** NewPost modules is used to send new user post which can be photos or videos. Also you can include product tags if you choose Premium Plan.

```bash
TeamCircleSDK.getInstance().openNewPostActivity(new NewPostCallback() {
  @Override
  public void onNewPost(NewPostDetail newPostDetail) {
  }
});
```
onNewPost method returns the local post information to be published. After that, you can publish the post information through the sendPost method.

```bash
TeamCircleSDK.getInstance().sendPost(newPostDetail, new SendPostCallBack() {
  @Override
  public void onPostPrepare(RequestPostData requestPostData) {
    // prepare to send post
  }

  @Override
  public void onPostSuccess(RequestPostData requestPostData) {
    // send post success
  }

  @Override
  public void onPostError(RequestPostData requestPostData) {
    // send post error
  }
});
```

## Share Json
Sometimes an app might have its own unique content that it wishes users can share along with the post. These contents are usually related with the core function of the app. Here are a few examples

* An IoT cookware app can enable users to share their recipe program while posting gourmet photos.
* A fitness app can enable users to share their customized training program while posting stunning results.
* An IoT lighting app can enable users to share the customized holiday light animation programs while showing off the end results.

<img src="https://user-images.githubusercontent.com/114135053/192987251-42b59af8-1ddc-4540-867b-811b32fcb1a9.png" width="30%">

In the above example of XKchrome app (for an IoT lighting hardware), the customers can share a light theme built by themselves along with their posts for others to download and run.

Team Circle SDK allows users to attach a downloadable json file while creating new posts. This file can be downloaded by others and perform functions outside Circle as mentioned in the above examples. Maximum size of a json file is 4096 bytes. If the file size exceeds this size, or the file is in another format such as PDF, your app may upload this file to a server and include the link in the json file. Customized icon, text and image can be uploaded along with the json to represent the shared Json file.

By default, the json file contains the following keys:

1. appId and appLogo: In some cases, you may use the same Circle community among multiple apps, for example, smart cookware app1 and app2 for different models. The appId and appLogo could be used to differentiate the shared Json file in order for the app to properly interact with the Json (such as making the compatible Json downloadable).
2. thumbnail: a thumbnail image file that represents the shared item.
3. name: plain text.
4. content: a json format content

**Method:** setShareJsonActivity

**Description:** Set your view controller which shows a custom view for selecting and sharing json.

**Sample Code:**

```bash
TeamCircleSDK.getInstance().getShareJsonSetting().setShareJsonActivity("com.xxxx.xxxx.xxxxActivity", "android.intent.category.DEFAULT");
```

**Method:** setShareJsonIcons

**Description:** Set upload and download icons.

**Sample Code:**

```bash
TeamCircleSDK.getInstance().getShareJsonSetting().setShareJsonIcons(R.drawable.dark_download_share_json,R.drawable.dark_download_share_json_disable,R.drawable.dark_upload_share_json);
```

**Method:** setShareJsonTitle

**Description:** Set title for the json file.

**Sample Code:**

```bash
TeamCircleSDK.getInstance().getShareJsonSetting().setShareJsonTitle("Title"));
```

**Method:** setShareJsonDownloadedTips

**Description:** Set the prompt text while downloading.

**Sample Code:**

```bash
TeamCircleSDK.getInstance().getShareJsonSetting().setShareJsonDownloadedTips("Download success");
```

## Store
Your customers can add product tags while creating new posts if you choose our Premium Plan. There are two options of integrating the Store module:
1. Add a whole Store module. A store section will appear at the top of the Circle social feed.
2. Only integrate the Customer Post Bar into your own product detail page if you have existing ecommerce functions in your app.

Both options require entering complete product info in the [Store](https://www.teamcircle.io/document/Team_Circle_iOS_Android_Social_SDK_Introduction?target=Store) page in the Admin Dashboard.

**Method:** StoreActivity

**Description:** Add a whole Store Module.

**Sample Code:**

```bash
TeamCircleSDK.getInstance().openStoreActivity();
```

**Method:** initCustomerPostBar

**Description:** Add a Customer Post Bar into your own product detail page.

**Sample Code:**

```bash
<com.xkglow.xkchrome.sdk.view.CustomerPostBarView
  android:id="@+id/customer_photo_gallery"
  android:layout_width="match_parent"
  android:layout_height="wrap_content"/>
  CustomerPostBarView customerPostBarView=findViewById(R.id.customer_photo_gallery);
  customerPostBarView.initCustomerPostBar(mProductId);
  customerPostBarView.setDataLoadStateListener(new CustomerPostBarView.DataLoadStateListener() {
  @Override
  public void onDataLoadSuccess(List<PostBean> postBeans) {
  }
});
```

## Instant messaging

**Method:** IMMessageActivity

**Description:** Add a whole IM system.

**Sample Code:**

```bash
IMMessageActivity.start(getContext());
```

# Customizable UI components
Customization on all UI components is optional, it will use default values if any of the following methods are not called.

## Text fonts and colors
* **Method:** setTextFontRegular <br> **Description:** Set regular text font and color, including post descriptions, comments, replies, etc.

* **Method:** setTextFontBold <br> **Description:** Set bold text font and color, including username.

* **Method:** setTextFontAction <br> **Description:** Set action button text font and color, including follow button.

* **Method:** setTextFontProductName <br> **Description:** Set product name text font and color.

* **Method:** setTextFontProductPrice <br> **Description:** Set product price text font and color.

* **Method:** setProductDescColor <br> **Description:** Set product description text color.

Sample Code:

```bash
Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto.ttf");
TeamCircleSDK.getInstance().getUiSetting().setTextFontRegular(typeface,context.getResources().getColor(R.color.redColor));

Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto.ttf");
TeamCircleSDK.getInstance().getUiSetting().setTextFontBold(typeface);

Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto.ttf");
TeamCircleSDK.getInstance().getUiSetting().setTextFontAction(typeface,context.getResources().getColor(R.color.redColor));

Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto.ttf");
TeamCircleSDK.getInstance().getUiSetting().setTextFontProductName(typeface,context.getResources().getColor(R.color.redColor));

Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto.ttf");
TeamCircleSDK.getInstance().getUiSetting().setTextFontProductPrice(typeface,context.getResources().getColor(R.color.redColor));

Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto.ttf");
TeamCircleSDK.getInstance().getUiSetting().setProductDescColor(typeface,context.getResources().getColor(R.color.redColor));
```

## Icons
* **Method:** setIconFavorite <br> **Description:** Set icon for favorite-unselected.
<img src="https://user-images.githubusercontent.com/114135053/193207595-31fc189d-cfd0-4545-bf08-a921e70e5ec8.png" width="40%">

* **Method:** setIconFavoriteSelected <br> **Description:** Set icon for favorite-selected.
<img src="https://user-images.githubusercontent.com/114135053/193207820-e7bc98ee-f689-4b05-8b2e-16f145320ffe.png" width="40%">

* **Method:** setIconTag <br> **Description:** Set icon for tag-unselected.
<img src="https://user-images.githubusercontent.com/114135053/193208162-34169299-6d0b-4ce7-aa81-6f66e044eb0b.png" width="40%">

* **Method:** setIconTagSelected <br> **Description:** Set icon for tag-selected.
<img src="https://user-images.githubusercontent.com/114135053/193208063-acba5fd5-e175-419f-9c0a-2359b933eed4.png" width="40%">

* **Method:** setIconLike <br> **Description:** Set icon for like-unselected.
<img src="https://user-images.githubusercontent.com/114135053/193207743-8ce0c13a-fdd0-4a29-abab-40f8c0aa7294.png" width="40%">

* **Method:** setIconLikeSelected <br> **Description:** Set icon for like-selected.
<img src="https://user-images.githubusercontent.com/114135053/193207089-aa46975e-e9a5-4912-8a2e-0a74fa0b5df0.png" width="40%">

* **Method:** setIconShare <br> **Description:** Set icon for share post.
<img src="https://user-images.githubusercontent.com/114135053/193207304-96a4cb64-df41-457b-8cf2-fb6a70055c2a.png" width="40%">

* **Method:** setIconComment <br> **Description:** Set icon for making comments.
<img srchttps://user-images.githubusercontent.com/114135053/193208233-6ca58d96-f7a2-4be9-b314-1548975cce4f.png" width="40%">

* **Method:** setIconNewPost <br> **Description:** Set icon for creating a new post.
<img src="https://user-images.githubusercontent.com/114135053/193207459-4f27af98-21d5-434b-a06b-b6b55b953e69.png" width="40%">

* **Method:** setIconBack <br> **Description:** Set icon for all back buttons.
<img src="https://user-images.githubusercontent.com/114135053/193207889-fac83ce6-6499-4fd9-adf4-1b4e8c5fc1b4.png" width="40%">

Sample Code:
```bash
TeamCircleSDK.getInstance().getUiSetting().setIconFavorite(R.drawable.favorite);
TeamCircleSDK.getInstance().getUiSetting().setIconFavoriteSelected(R.drawable.favorite_selected);
TeamCircleSDK.getInstance().getUiSetting().setIconTag(R.drawable.tag);
TeamCircleSDK.getInstance().getUiSetting().setIconTagSelected(R.drawable.tag_select);
TeamCircleSDK.getInstance().getUiSetting().setIconLike(R.drawable.like);
TeamCircleSDK.getInstance().getUiSetting().setIconLikeSelected(R.drawable.like_selected);
TeamCircleSDK.getInstance().getUiSetting().setIconShare(R.drawable.share);
TeamCircleSDK.getInstance().getUiSetting().setIconComment(R.drawable.comment);
TeamCircleSDK.getInstance().getUiSetting().setIconNewPost(R.drawable.newpost);
TeamCircleSDK.getInstance().getUiSetting().setIconBack(R.drawable.back);
```
## Colors

* **Method:** setSystemBackgroundColor <br> **Description:** Set the background color of all pages.
<img src="https://user-images.githubusercontent.com/114135053/192998082-93f4eb97-3582-451a-86cf-3381340e22a9.png" width="50%">

* **Method:** setBackgroundEffectColor <br> **Description:** Set the background color of the highlighted section.
<img src="https://user-images.githubusercontent.com/114135053/192998234-79f3e83a-1d75-497f-aea8-8316884e9dcb.png" width="30%">

* **Method:** setLinkColor <br> **Description:** Set all web page link color.
<img src="https://user-images.githubusercontent.com/114135053/192998363-b97d4841-52a9-4a7c-976d-e2cf0828cd66.png" width="30%">

Sample Code:
```bash
TeamCircleSDK.getInstance().getUiSetting().setSystemBackgroundColor(context.getColor(R.color.blackColor));
TeamCircleSDK.getInstance().getUiSetting().setBackgroundEffectColor(context.getColor(R.color.blackColor));
TeamCircleSDK.getInstance().getUiSetting().setLinkColor(context.getColor(R.color.white));
```

## Redirect

* **Method:** toStoreVCtrl <br> **Description:** Redirects to the Store view controller.

* **Method:** toUserCenterVCtrl <br> **Description:** Redirects to the UserCenter view controller.

* **Method:** toNewPostVCtrl <br> **Description:** Redirects to the NewPost view controller.

* **Method:** toSearchVCtrl <br> **Description:** Redirects to the Search view controller.

* **Method:** toChatListAction <br> **Description:** Redirects to the Chat view controller.

* **Method:** toChatAction <br> **Description:** Redirects to the Chat view controller.

Sample Code:
```bash
TCManager.sharedInstance.toStoreVCtrl(nav: navigation) { vc, error in
  if let storeVC = vc {
  }
}
TCManager.sharedInstance.toUserCenterVCtrl(nav: navigation) { vc, error in
  if let userVC = vc {
  }
}
TCManager.sharedInstance.toNewPostVCtrl { error in }
TCManager.sharedInstance.toSearchVCtrl { error in }
TCManager.sharedInstance.toChatListAction(nav: navigation)
TCManager.sharedInstance.toChatAction(nav: navigation, conversationId: "conversationId")
```

## Terms and privacy policy

* **Method:** setPrivacyPolicy <br> **Description:** Set privacy policy website url.

* **Method:** setTermsAndConditions <br> **Description:** Set terms and conditions website url.

Sample Code:
```bash
TeamCircleSDK.getInstance().setPrivacyPolicy("https://xxxxxx.html");
TeamCircleSDK.getInstance().setTermsAndConditions("https://xxxxxx.html");
```
If the links are not entered,these two documents will remain hidden.
