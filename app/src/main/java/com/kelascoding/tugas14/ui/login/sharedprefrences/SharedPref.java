package com.kelascoding.tugas14.ui.login.sharedprefrences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.kelascoding.tugas14.ui.login.activity.Login;

public class SharedPref {

    //Storage File
    public static final String SHARED_PREF_NAME = "kelascoding";

    //Name
    public static final String USER_NAME= "username";
    //Email
    public static final String EMAIL = "email";

    public static SharedPref mInstance;

    public static Context mCtx;

    public SharedPref(Context context) {
            mCtx = context;
    }


    public static synchronized SharedPref getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPref(context);
        }
        return mInstance;
    }


    //method to store user data
    public void storeUser(String names) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_NAME, names);
        editor.commit();
    }

//    public void storeEmail(String email) {
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(EMAIL, email);
//        editor.commit();
//    }
    //check if user is logged in username
    public boolean isLoggedUsername() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_NAME , null) != null;
    }

//    //check if user is logged in email
//    public boolean isLoggedEmail() {
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return sharedPreferences.getString(EMAIL , null) != null;
//    }


    //find logged in user
    public String LoggedInUsername() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_NAME, null);
    }
//    public String LoggedEmail() {
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return sharedPreferences.getString(EMAIL, null);
//    }

    //Logout user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        mCtx.startActivity(new Intent(mCtx, Login.class));
    }


}
