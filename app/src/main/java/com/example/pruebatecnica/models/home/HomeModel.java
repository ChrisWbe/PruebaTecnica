package com.example.pruebatecnica.models.home;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class HomeModel {
    static RequestQueue requestQueueq;
    private static final String urlPosts = "https://jsonplaceholder.typicode.com/posts";
    static JSONArray respuesta;
    public static void jsonArrayRequest(Context c){

        requestQueueq = Volley.newRequestQueue(c);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlPosts,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        respuesta = response;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        requestQueueq.add(jsonArrayRequest);
    }
}
