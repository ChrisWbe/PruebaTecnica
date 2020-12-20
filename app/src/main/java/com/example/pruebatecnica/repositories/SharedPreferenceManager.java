package com.example.pruebatecnica.repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.pruebatecnica.R;

public class SharedPreferenceManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context c;


    public SharedPreferenceManager(Context c){
        this.c = c;
    }

    public boolean obtenerFavShared(String id){
        initFavoritos();
        return sharedPreferences.getString(id,"null")=="null";
    }

    public void saveCV(String itemId){
        initFavoritos();
        editor.putString(itemId, itemId);
        editor.apply();
    }

    public void deleteFileFavPre() {
        initFavoritos();
        editor.clear();
        editor.apply();
    }

    public void initFavoritos(){
        sharedPreferences = c.getSharedPreferences(c.getString(R.string.preference_file_fav), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean obtenerReadShared(String id){
        initRead();
        return sharedPreferences.getString(id,"null")=="null";
    }

    public void saveRead(String itemId){
        initRead();
        editor.putString(itemId, itemId);
        editor.apply();
    }

    public void deleteFileReadPre() {
        initRead();
        editor.clear();
        editor.apply();
    }

    public void initRead(){
        sharedPreferences = c.getSharedPreferences(c.getString(R.string.preference_file_read), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
}
