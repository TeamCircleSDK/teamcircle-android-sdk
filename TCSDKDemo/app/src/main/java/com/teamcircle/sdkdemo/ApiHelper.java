package com.teamcircle.sdkdemo;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;

public class ApiHelper {
    private static String BASE_URL;
    private static final String BASE_URL_DEBUG = "http://xksocial-test.xkglow.com/";
    private static final String BASE_URL_RELEASE = "http://xksocial-test.xkglow.com/";
    private static AsyncHttpClient client;

    public static void setup() {
        client = new AsyncHttpClient();
        client.addHeader("system", "android");
        client.addHeader("systemVersion", Build.VERSION.RELEASE);
        client.addHeader("deviceModel", Build.MODEL);
        client.addHeader("user-agent", "android");
        client.setResponseTimeout(60 * 1000);
        BASE_URL = BASE_URL_RELEASE;
        if (BuildConfig.DEBUG) {
            BASE_URL = BASE_URL_DEBUG;
        }
    }

    public interface ApiCallback {
        void onSuccess(JSONObject response);

        void onFail(String errorMsg);
    }

    public static void get(final String url, final ApiCallback callback) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                client.get(getAbsoluteUrl(url), null, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if (callback != null) {
                            try {
                                JSONObject response = new JSONObject(new String(responseBody));
                                boolean result = response.optBoolean("resultStatus");
                                if (result) {
                                    callback.onSuccess(response);
                                } else {
                                    callback.onFail(response.optString("errorCode"));
                                }
                            } catch (JSONException e) {
                                callback.onFail(e.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        if (callback != null && error.getMessage() != null) {
                            callback.onFail(error.getMessage());
                        }
                    }
                });
            }
        });
    }

    public static void post(final String url, final String body, final ApiCallback callback) {
        Looper looper = Looper.getMainLooper();
        new Handler(looper).post(new Runnable() {
            @Override
            public void run() {
                try {
                    StringEntity entity = new StringEntity(body, Charset.defaultCharset());
                    client.post(null, getAbsoluteUrl(url), entity, "application/json", new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if (callback != null) {
                                try {
                                    JSONObject response = new JSONObject(new String(responseBody));
                                    boolean result = response.optBoolean("resultStatus");
                                    if (result) {
                                        callback.onSuccess(response);
                                    } else {
                                        callback.onFail(response.optString("errorCode"));
                                    }
                                } catch (JSONException e) {
                                    callback.onFail(e.getMessage());
                                }
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            if (callback != null && error.getMessage() != null) {
                                callback.onFail(error.getMessage());
                            }
                        }
                    });
                } catch (Exception e) {
                    if (callback != null && e.getMessage() != null) {
                        callback.onFail(e.getMessage());
                    }
                }
            }
        });
    }

    public static void post(final String url, final List<NameValuePair> nameValuePairs) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpclient = HttpClientBuilder.create().build();
                    HttpPost httppost = new HttpPost(url);
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs);
                    httppost.setEntity(entity);
                    HttpResponse response = httpclient.execute(httppost);
                    Log.d("POST SUCCESS", response.toString());
                } catch (Exception e) {
                    Log.e("POST FAIL", e.getMessage());
                }
            }
        });
        thread.start();
    }

    public static void put(final String url, final String body, final ApiCallback callback) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                try {
                    StringEntity entity = new StringEntity(body, Charset.defaultCharset());
                    client.put(null, getAbsoluteUrl(url), entity, "application/json", new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if (callback != null) {
                                try {
                                    JSONObject response = new JSONObject(new String(responseBody));
                                    boolean result = response.optBoolean("resultStatus");
                                    if (result) {
                                        callback.onSuccess(response);
                                    } else {
                                        callback.onFail(response.optString("errorCode"));
                                    }
                                } catch (JSONException e) {
                                    callback.onFail(e.getMessage());
                                }
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            if (callback != null && error.getMessage() != null) {
                                callback.onFail(error.getMessage());
                            }
                        }
                    });
                } catch (Exception e) {
                    if (callback != null && e.getMessage() != null) {
                        callback.onFail(e.getMessage());
                    }
                }
            }
        });
    }

    public static void delete(final String url, final ApiCallback callback) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                client.delete(null, getAbsoluteUrl(url), null, "application/json", new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if (callback != null) {
                            try {
                                JSONObject response = new JSONObject(new String(responseBody));
                                boolean result = response.optBoolean("resultStatus");
                                if (result) {
                                    callback.onSuccess(response);
                                } else {
                                    callback.onFail(response.optString("errorCode"));
                                }
                            } catch (JSONException e) {
                                callback.onFail(e.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        if (callback != null && error.getMessage() != null) {
                            callback.onFail(error.getMessage());
                        }
                    }
                });
            }
        });
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
