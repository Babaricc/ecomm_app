package com.example.ecomm_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Map;

public class UserSession {

   static SharedPreferences sharedPreferences;

    public UserSession(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean setUserPreferences(String userId, String userEmail, String userPassword, String token, String contact) {
        sharedPreferences.edit().putString("userId", userId).apply();
        sharedPreferences.edit().putString("userEmail", userEmail).apply();
        sharedPreferences.edit().putString("userPassword", userPassword).apply();
        sharedPreferences.edit().putString("token", token).apply();
        sharedPreferences.edit().putString("contact", contact).apply();
        return true;
    }

    public boolean clearSession() {
        sharedPreferences.edit().clear().apply();
        return true;
    }


    public boolean getUserStatus() {
        if (sharedPreferences != null) {
            Map<String, ?> keys = sharedPreferences.getAll();

            for (Map.Entry<String, ?> entry : keys.entrySet()) {
                Log.d("session: ", "" + entry.getKey() + ": " + entry.getValue());
                if (entry.getValue() == null || entry.getValue().toString().isEmpty()) return false;
            }

        } else return false;

        return true;
    }

}
