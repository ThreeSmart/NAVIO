package com.example.navio.backend.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.navio.backend.service.JWTService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class API {
    private static final String IP_ADDRESS = "16.16.118.246";
    private static final String IP_PORT = "7801";
    private static final String PROTOCOL = "http";
    private static final String BACKEND_URL = PROTOCOL + "://" + IP_ADDRESS + ":" + IP_PORT;

    protected API() {
    }

    public static void makeGet(final Context context,
                               final String postfix,
                               final CallBack callBack,
                               final CallBack errorCallBack) {
        System.out.println(BACKEND_URL + postfix);
        final StringRequest stringRequest = new StringRequest(
                com.android.volley.Request.Method.GET,
                BACKEND_URL + postfix,
                s -> {
                    try {
                        callBack.execute(s);
                    } catch (JSONException e) {
                        System.out.println("Could not parse (method: GET)");
                        e.printStackTrace();
                    }
                },
                error -> {
                    error.printStackTrace();
                    try {
                        errorCallBack.execute(error.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headerMap = new HashMap<>();
                headerMap.put("Content-Type", "application/json; charset=utf-8");
                headerMap.put("Authorization", "Bearer " + JWTService.getInstance(context).getJwtToken());
                return headerMap;
            }
        };
        Volley.newRequestQueue(context).add(stringRequest);
    }

    protected static void makePost(final Context context,
                                   final String postfix,
                                   final JSONObject body,
                                   final CallBack callBack,
                                   final CallBack errorCallBack) {
        final RequestQueue requestQueue = Volley.newRequestQueue(context);
        try {
            final String requestBody = body.toString();
            final StringRequest stringRequest = new StringRequest(
                    com.android.volley.Request.Method.POST,
                    BACKEND_URL + postfix,
                    response -> Log.i("VOLLEY1", response),
                    error -> {
                        final NetworkResponse response = error.networkResponse;
                        try {
                            if (response == null) return;
                            final String parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                            errorCallBack.execute(parsed);
                        } catch (final UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            System.out.println("Could not parse the string: ");
                            e.printStackTrace();
                        }
                    }
            ) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> headerMap = new HashMap<>();
                    headerMap.put("Content-Type", "application/json");
                    headerMap.put("Authorization", "Bearer " + JWTService.getInstance(context).getJwtToken());
                    return headerMap;
                }

                @Override
                public byte[] getBody() {
                    return requestBody.getBytes(StandardCharsets.UTF_8);
                }

                @Override
                protected Response<String> parseNetworkResponse(final NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        try {
                            String parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                            callBack.execute(parsed);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            System.out.println("Could not parse (method: POST, error)");
                            e.printStackTrace();
                        }
                        responseString = String.valueOf(response.headers);
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(Objects.requireNonNull(response)));
                }
            };
            requestQueue.add(stringRequest);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

}
