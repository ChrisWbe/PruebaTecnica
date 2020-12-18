package com.example.pruebatecnica;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.pruebatecnica.models.post.PostsModel;
import com.example.pruebatecnica.repositories.SharedPreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;


public class DialogActivity extends AppCompatActivity {
    PostsModel item;

    TextView userId;
    TextView id;
    TextView name;
    TextView username;
    TextView email ;
    TextView phone;
    TextView website;
    Button btnFav;

    SharedPreferenceManager sharedPreferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Bundle parametros = this.getIntent().getExtras();
        sharedPreferenceManager = new SharedPreferenceManager(this);

        try {
            item = new PostsModel(new JSONObject(parametros.getString("item")));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        initViews();

        btnFav.setEnabled(sharedPreferenceManager.obtenerFavShared(String.valueOf(item.getId())));

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemId = String.valueOf(item.getId());
                sharedPreferenceManager.saveCV(itemId);
                btnFav.setEnabled(false);
            }
        });


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