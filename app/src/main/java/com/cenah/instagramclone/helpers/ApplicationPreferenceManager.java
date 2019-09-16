package com.cenah.instagramclone.helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class ApplicationPreferenceManager {


    private static final String APP_SHARED_PREFS = "Arge-preferences";
    private static final String AUTH_TOKEN = "AUTH_TOKEN";


    private Gson gson;
    private Context context;
    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    @SuppressLint("CommitPrefEdits")
    public ApplicationPreferenceManager(Context context) {
        this.appSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
        this.context = context;
        gson = new Gson();
    }

    public String getToken() {
        return appSharedPrefs.getString(AUTH_TOKEN, null);
    }

    public void setToken(String s) {
        prefsEditor.putString(AUTH_TOKEN, s).commit();
    }
}
