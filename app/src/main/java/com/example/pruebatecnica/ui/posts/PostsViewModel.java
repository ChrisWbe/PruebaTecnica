package com.example.pruebatecnica.ui.posts;

import android.content.Context;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pruebatecnica.models.post.PostsModel;
import com.example.pruebatecnica.repositories.Repositories;

import java.util.List;

public class PostsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<PostsModel>> mHomeModel;
    private Repositories mRepo;

    public void init(Context c){
        if(mHomeModel != null){
            return;
        }
        mRepo = Repositories.getInstance();
        mRepo.setC(c);
        mHomeModel = mRepo.getHomeModel();
    }

    public LiveData<List<PostsModel>> getHomeModel(){
        return mHomeModel;
    }

    public PostsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }


}