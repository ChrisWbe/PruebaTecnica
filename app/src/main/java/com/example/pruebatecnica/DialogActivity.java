package com.example.pruebatecnica;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pruebatecnica.models.home.HomeModel;

import org.json.JSONException;
import org.json.JSONObject;

public class DialogActivity extends AppCompatActivity {
    HomeModel item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Bundle parametros = this.getIntent().getExtras();

        try {
            item = new HomeModel(new JSONObject(parametros.getString("item")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView userId = findViewById(R.id.tvUserIdDialog);
        TextView id = findViewById(R.id.tvIdDialog);
        TextView name = findViewById(R.id.tvNameDialog);
        TextView username = findViewById(R.id.tvUseNameDialog);
        TextView email = findViewById(R.id.tvEmailDialog);
        TextView phone = findViewById(R.id.tvPhoneDialog);
        TextView website = findViewById(R.id.tvWebsiteDialog);


        userId.setText(String.valueOf(item.getUserId()));
        id.setText(String.valueOf(item.getId()));
        name.setText(item.getName());
        username.setText(item.getUsername());
        email.setText(item.getEmail());
        phone.setText(item.getPhone());
        website.setText(item.getWebsite());
    }
}