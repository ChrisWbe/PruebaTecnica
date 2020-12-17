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
        sharedPreferences = c.getSharedPreferences(c.getString(R.string.preference_file_fav), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean obtenerFavShared(String id){
        String listString = sharedPreferences.getString(id,"null");
        Log.d("MVVM", listString);
        return listString=="null";
    }

    public void saveCV(String itemId){
        editor.putString(itemId, itemId);
        editor.apply();
    }
}
