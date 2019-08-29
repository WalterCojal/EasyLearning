package com.walter.cojal.easylearning.utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.walter.cojal.easylearning.data.entities.Assessor;
import com.walter.cojal.easylearning.data.entities.User;

public class SavePreferences {

    private Context context;
    private SharedPreferences sharedPreferences;

    public SavePreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public void saveInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value).apply();
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, -1);
    }

    public void saveString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value).apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void saveUser(String key, User value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String jsonString = gson.toJson(value);
        editor.putString(key, jsonString).apply();
    }

    public User getUser(String key) {
        String jsonString = sharedPreferences.getString(key, "");
        Gson gson = new Gson();
        if (jsonString.isEmpty()) return null;
        else return gson.fromJson(jsonString, User.class);
    }

    public void saveAsesor(String key, Assessor assessor) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String jsonString = gson.toJson(assessor);
        editor.putString(key, jsonString).apply();
    }

    public Assessor getAsesor(String key) {
        String jsonString = sharedPreferences.getString(key, "");
        Gson gson = new Gson();
        if (jsonString.isEmpty()) return null;
        else return gson.fromJson(jsonString, Assessor.class);
    }

    public Boolean existsPreference(String key) {
        return sharedPreferences.contains(key);
    }

    public void removePreference(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    public void clearPreferences() {
        sharedPreferences.edit().clear().apply();
    }

}
