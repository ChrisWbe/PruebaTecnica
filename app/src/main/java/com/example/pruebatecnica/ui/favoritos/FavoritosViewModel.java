package com.example.pruebatecnica.ui.favoritos;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pruebatecnica.models.post.PostsModel;
import com.example.pruebatecnica.repositories.Repositories;

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
        mPostModel = mRepo.getPostModels();
    }

    public LiveData<List<PostsModel>> getHomeModel(){
        return mPostModel;
    }

}