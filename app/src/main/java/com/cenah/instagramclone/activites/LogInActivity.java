package com.cenah.instagramclone.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.cenah.instagramclone.R;
import com.cenah.instagramclone.UI.PrograssBarDialog;
import com.cenah.instagramclone.helpers.ApplicationPreferenceManager;
import com.cenah.instagramclone.interfaces.AuthenticationListener;
import com.cenah.instagramclone.models.TokenResponse;
import com.cenah.instagramclone.rest.RestFullHelper;
import com.cenah.instagramclone.rest.services.TokenService;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cenah.instagramclone.helpers.Utils.AUTORISATION_CODE;
import static com.cenah.instagramclone.helpers.Utils.CLIENT_ID;
import static com.cenah.instagramclone.helpers.Utils.CLIENT_SECRET;
import static com.cenah.instagramclone.helpers.Utils.LOGIN_URL;
import static com.cenah.instagramclone.helpers.Utils.REDIRECT_URI;

public class LogInActivity extends AppCompatActivity implements AuthenticationListener{

    private static final String TAG = LogInActivity.class.getSimpleName();
    private AuthenticationListener mListener;
    private WebView mWebView;
    private PrograssBarDialog prograssBarDialog;
    private TokenService tokenService;
    private String mAuthToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tokenService = new RestFullHelper().getTokenServiceClient();
        prograssBarDialog = new PrograssBarDialog(this);
        mListener = this;
        setUpWebView();

        CookieSyncManager.createInstance(getApplicationContext());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setUpWebView() {
        mWebView = findViewById(R.id.wv_login);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setWebViewClient(new OAuthWebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(LOGIN_URL);
    }

    private class OAuthWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d(TAG, "Redirecting URL " + url);
            if (url.startsWith(REDIRECT_URI)) {
                String urls[] = url.split("=");
                mListener.onCodeReceived(urls[1]);
                return true;
            }
            return false;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Log.d(TAG, "Page error: " + description);

            super.onReceivedError(view, errorCode, description, failingUrl);
//            mListener.onError(description);
//            InstagramDialog.this.dismiss();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.d(TAG, "Loading URL: " + url);

            super.onPageStarted(view, url, favicon);
            prograssBarDialog.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            String title = mWebView.getTitle();
//            if (title != null && title.length() > 0) {
//                mTitle.setText(title);
//            }
            Log.d(TAG, "onPageFinished URL: " + url);
            prograssBarDialog.hide();
        }

    }

    @Override
    public void onCodeReceived(String code) {
        final Call<TokenResponse> accessToken =  tokenService.getAccessToken(CLIENT_ID,CLIENT_SECRET,REDIRECT_URI,AUTORISATION_CODE,code);
        String temp = accessToken.request().url().toString();
        accessToken.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(@NotNull Call<TokenResponse> call, @NotNull Response<TokenResponse> response) {

                if(response.isSuccessful()){
                    if (response.body() != null) {
                        mAuthToken = response.body().getAccess_token();
                    }else{
                        Toast.makeText(LogInActivity.this, "token is null", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    new ApplicationPreferenceManager(getApplicationContext()).setToken(mAuthToken);
                    finish();
                    startActivity(new Intent(LogInActivity.this, HomeActivity.class));

                }else{
                    try {
                        if (response.errorBody() != null) {
                            Toast.makeText(LogInActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }


            @Override
            public void onFailure(@NotNull Call<TokenResponse> call, @NotNull Throwable t) {
                Toast.makeText(LogInActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
