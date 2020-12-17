package com.example.pruebatecnica.ui.home;

import android.content.Context;
import android.util.Log;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pruebatecnica.models.home.HomeModel;
import com.example.pruebatecnica.repositories.Repositories;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<HomeModel>> mHomeModel;
    private Repositories mRepo;

    public void init(Context c){
        if(mHomeModel != null){
            return;
        }
        mRepo = Repositories.getInstance();
        mRepo.setC(c);
        mHomeModel = mRepo.getHomeModel();
    }

    public LiveData<List<HomeModel>> getHomeModel(){
        return mHomeModel;
    }

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }


}