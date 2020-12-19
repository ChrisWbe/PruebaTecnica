package com.example.pruebatecnica.ui.favoritos;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pruebatecnica.models.post.PostsModel;
import com.example.pruebatecnica.repositories.Repositories;
import com.example.pruebatecnica.repositories.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class FavoritosViewModel extends ViewModel {
    private MutableLiveData<List<PostsModel>> mPostModel;
    private Repositories mRepo;

    public void init(Context c){
        mRepo = new Repositories(c);
        if(mPostModel!=null){
            return;
        }
        mRepo = Repositories.getInstance(c);
        mPostModel = mRepo.getPostFav();
    }
    public void deleteFav(Context c){
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(c);
        sharedPreferenceManager.deleteFilePre();
        mPostModel.getValue().clear();
    }



    public LiveData<List<PostsModel>> getHomeModelFav(){
        return mPostModel;
    }

}