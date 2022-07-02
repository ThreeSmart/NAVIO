package com.example.navio.backend.local_storage;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorage {
    private int mode = MODE_PRIVATE;
    private String key;
    private Context context;

    private LocalStorage() {
    }

    public static LocalStorage getInstance() {
        return new LocalStorage();
    }

    public LocalStorage setContext(final Context context) {
        this.context = context;
        return this;
    }

    public LocalStorage setMode(final int mode) {
        this.mode = mode;
        return this;
    }

    public LocalStorage setKey(final String key) {
        this.key = key;
        return this;
    }

    public boolean getBoolean(final String key) {
        return getPreferences().getBoolean(key, false);
    }

    public String getString(final String key) {
        return getPreferences().getString(key, null);
    }

    public void writeBoolean(final String key, final Boolean value) {
        @SuppressLint("CommitPrefEdits") final SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void writeString(final String key, final String value) {
        @SuppressLint("CommitPrefEdits") final SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void removeString(final String key) {
        @SuppressLint("CommitPrefEdits") final SharedPreferences.Editor editor = getPreferences().edit();
        editor.remove(key);
        editor.apply();
    }

    private SharedPreferences getPreferences() {
        return context.getSharedPreferences(this.key, this.mode);
    }

}
