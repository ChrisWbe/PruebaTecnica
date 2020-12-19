package com.example.pruebatecnica.ui.posts;

import android.content.Context;
import android.util.Log;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pruebatecnica.R;
import com.example.pruebatecnica.models.post.PostsModel;
import com.example.pruebatecnica.repositories.Repositories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PostsViewModel extends ViewModel {

    private MutableLiveData<List<PostsModel>> mPostModel;
    private Repositories mRepo;

    public void init(Context c){
        mRepo = new Repositories(c);
        if(mPostModel!=null){
            return;
        }
        mRepo = Repositories.getInstance(c);
        mPostModel = mRepo.getPostModels();
    }


    public void update() throws JSONException {
        mPostModel.getValue().clear();
        mPostModel = mRepo.updatePostsModels();
    }

    public LiveData<List<PostsModel>> getHomeModel(){
        return mPostModel;
    }

}