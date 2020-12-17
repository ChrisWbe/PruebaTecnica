package com.example.pruebatecnica.repositories;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.pruebatecnica.models.home.HomeModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Repositories {
    static Context c;
    static RequestQueue requestQueueq;
    private static final String urlPosts = "https://jsonplaceholder.typicode.com/posts";
    private static Repositories instance;
    private static ArrayList<HomeModel> dataSet = new ArrayList<>();

    public static Context getC() {
        return c;
    }

    public static void setC(Context c) {
        Repositories.c = c;
    }

    public static Repositories getInstance(){
        if(instance==null){
            instance = new Repositories();
        }
        return instance;
    }

    public MutableLiveData<List<HomeModel>> getHomeModel(){
        //jsonArrayRequest();
        setHomeModel();
        MutableLiveData<List<HomeModel>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }
    private void setHomeModel(){
        dataSet.add(new HomeModel(1, 1, "title", "body"));
    }
    public static void jsonArrayRequest(){

        requestQueueq = Volley.newRequestQueue(c);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlPosts,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int size = response.length();
                        for(int i=0;i<size;i++){
                            try {
                                JSONObject jsonObject = new JSONObject(response.get(i).toString());
                                dataSet.add(new HomeModel(jsonObject.getInt("userId"), jsonObject.getInt("id"), jsonObject.getString("title"), jsonObject.getString("body")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

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
