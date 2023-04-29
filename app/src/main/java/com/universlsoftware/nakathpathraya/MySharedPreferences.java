package com.universlsoftware.nakathpathraya;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    private static final String PREF_NAME = "MyPref";
    private static final String IS_LOGGED_IN = "isLoggedIn";
    private SharedPreferences pref;


    private static final String KEY_NAME = "name";
    private static final String KEY_ID = "id";



    private SharedPreferences.Editor editor;
    private Context context;

    public MySharedPreferences(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void setLoggedIn(boolean isLoggedIn) {
        editor.putBoolean(IS_LOGGED_IN, isLoggedIn);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGGED_IN, false);
    }


    public void setUser(String user,String id) {
        editor.putString(KEY_NAME, user);
        editor.putString(KEY_ID, id);
        editor.apply();
    }

    public String getUser() {
        String name = pref.getString(KEY_NAME, "");
        return name;
    }


    public String getid() {
        String id = pref.getString(KEY_ID, "");
        return id;
    }

    public void clear() {
        editor.clear();
        editor.apply();
    }
}