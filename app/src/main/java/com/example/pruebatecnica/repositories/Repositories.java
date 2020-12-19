package com.example.pruebatecnica.repositories;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.example.pruebatecnica.R;
import com.example.pruebatecnica.models.post.PostsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Repositories {
    static Context c;
    static RequestQueue requestQueueq;
    private static Repositories instance;
    private static final String urlPosts = "https://jsonplaceholder.typicode.com/posts";
    private static final String urlUsers = "https://jsonplaceholder.typicode.com/users";
    private static JSONArray jsonArrayPosts = new JSONArray();
    private static JSONArray jsonArrayUsers = new JSONArray();
    private MutableLiveData<List<PostsModel>> data = new MutableLiveData<>();
    private ArrayList<PostsModel> dataSet = new ArrayList<>();


    public Repositories(Context c){
        this.c=c;
    }


    public static Repositories getInstance(Context c){
        if(instance==null){
            instance= new Repositories(c);
        }
        return instance;
    }

    public MutableLiveData<List<PostsModel>> getPostModels(){
        //dataSet.clear();
        requestQueueq = Volley.newRequestQueue(c);
        jsonArrayRequestPosts();
        data.setValue(dataSet);
        return data;
    }

    public MutableLiveData<List<PostsModel>> updatePostsModels() throws JSONException {
        createPostsModels(jsonArrayPosts, jsonArrayUsers);
        data.setValue(dataSet);
        return data;
    }


    public void jsonArrayRequestUsers(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlUsers,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("ResponseUsers", response.toString());
                        jsonArrayUsers=response;
                        try {
                            createPostsModels(jsonArrayPosts,jsonArrayUsers);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //dataSet.add(new PostsModel(1, 2, "title", "body", "Christian","chriswbe","chriswbe1993@gmail.com","123456789","google.com",R.drawable.user));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                    }
                }
        );

        requestQueueq.add(jsonArrayRequest);
    }

    public void jsonArrayRequestPosts(){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlPosts,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("ResponsePosts", response.toString());
                        jsonArrayPosts=response;
                        jsonArrayRequestUsers();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                    }
                }
        );

        requestQueueq.add(jsonArrayRequest);
    }


    public void createPostsModels(JSONArray p, JSONArray u) throws JSONException {
        List<PostsModel> pm = new ArrayList<>();
        for(int i=0; i<p.length();i++){
            JSONObject pJO = new JSONObject(p.get(i).toString());
            for(int j=0;j<u.length();j++){
                JSONObject uJO = new JSONObject(u.get(j).toString());

                if(uJO.getInt("id") == pJO.getInt("userId")){
                    pm.add(new PostsModel(
                            pJO.getInt("userId"),
                            pJO.getInt("id"),
                            pJO.getString("title"),
                            pJO.getString("body"),
                            uJO.getString("name"),
                            uJO.getString("username"),
                            uJO.getString("email"),
                            uJO.getString("phone"),
                            uJO.getString("website"),
                            R.drawable.user
                            ));
                }

            }
        }

        dataSet.addAll(pm);

    }
}
