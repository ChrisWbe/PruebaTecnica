package com.example.pruebatecnica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pruebatecnica.models.home.HomeModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DialogActivity extends AppCompatActivity {
    HomeModel item;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    TextView userId;
    TextView id;
    TextView name;
    TextView username;
    TextView email ;
    TextView phone;
    TextView website;
    Button btnFav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        sharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_fav), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Bundle parametros = this.getIntent().getExtras();

        try {
            item = new HomeModel(new JSONObject(parametros.getString("item")));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        initViews();

        btnFav.setEnabled(obtenerFavShared());

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemId = String.valueOf(item.getId());
                editor.putString(itemId, itemId);
                editor.apply();
                btnFav.setEnabled(false);
            }
        });


    }

    public boolean obtenerFavShared(){
        String listString = sharedPreferences.getString(String.valueOf(item.getId()),"null");
        Log.d("MVVM", listString);
        return listString=="null";
    }

    public void initViews(){

        userId = findViewById(R.id.tvUserIdDialog);
        id = findViewById(R.id.tvIdDialog);
        name = findViewById(R.id.tvNameDialog);
        username = findViewById(R.id.tvUseNameDialog);
        email = findViewById(R.id.tvEmailDialog);
        phone = findViewById(R.id.tvPhoneDialog);
        website = findViewById(R.id.tvWebsiteDialog);
        btnFav = findViewById(R.id.btnFavoritos);

        userId.setText(String.valueOf(item.getUserId()));
        id.setText(String.valueOf(item.getId()));
        name.setText(item.getName());
        username.setText(item.getUsername());
        email.setText(item.getEmail());
        phone.setText(item.getPhone());
        website.setText(item.getWebsite());
    }
}