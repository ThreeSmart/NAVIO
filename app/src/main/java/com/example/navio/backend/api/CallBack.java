package com.example.navio.backend.api;

import org.json.JSONException;
import org.json.JSONObject;

public interface CallBack {

    void execute(final JSONObject s) throws JSONException;

}
