package com.example.navio.backend.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class API {
    private static final String IP_ADDRESS = "16.170.209.14";
    private static final String IP_PORT = "7801";
    private static final String PROTOCOL = "http";
    private static final String BACKEND_URL = PROTOCOL + "://" + IP_ADDRESS + ":" + IP_PORT;

    public static void makeGet(final Context context,
                               final String postfix,
                               final CallBack callBack) {
        System.out.println(BACKEND_URL + postfix);
        final StringRequest stringRequest = new StringRequest(
                com.android.volley.Request.Method.GET,
                BACKEND_URL + postfix,
                s -> {
                    try {
                        final JSONObject jsonObject = new JSONObject(s);
                        callBack.execute(jsonObject);
                    } catch (JSONException e) {
                        System.out.println("Could not parse (method: GET)");
                        e.printStackTrace();
                    }
                },
                System.out::println
        );
        Volley.newRequestQueue(context).add(stringRequest);
    }

    public synchronized static String makeSynchronizedPost(final Context context,
                                                           final String postfix,
                                                           final JSONObject body) throws InterruptedException {
        String result = makePost(context,
                postfix,
                body,
                s -> {
                },
                s -> {
                }
        );

        System.out.println("helanq");

        return result;
    }

    public synchronized static String makePost(final Context context,
                                               final String postfix,
                                               final JSONObject body,
                                               final CallBack callBack,
                                               final CallBack errorCallBack) {
        final RequestQueue requestQueue = Volley.newRequestQueue(context);
        final String[] result = {"initt"};
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
                            final JSONObject jsonObject = new JSONObject(parsed);
                            errorCallBack.execute(jsonObject);
                            result[0] = parsed;
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
                public byte[] getBody() {
                    return requestBody.getBytes(StandardCharsets.UTF_8);
                }

                @Override
                protected Response<String> parseNetworkResponse(final NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        try {
                            String parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                            final JSONObject jsonObject = new JSONObject(parsed);
                            callBack.execute(jsonObject);
                            result[0] = parsed;
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
            return result[0];
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
