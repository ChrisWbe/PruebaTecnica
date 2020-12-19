package com.example.pruebatecnica.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebatecnica.R;
import com.example.pruebatecnica.models.post.PostsModel;
import com.example.pruebatecnica.repositories.SharedPreferenceManager;

import java.util.List;

public class RecyclerAdapterFav extends RecyclerView.Adapter<RecyclerAdapterFav.RecyclerHolderFav>{
    private List<PostsModel> items;
    private SharedPreferenceManager sharedPreferenceManager;
    private Context c;

    public RecyclerAdapterFav(List<PostsModel> items, Context c) {
        this.items = items;
        this.c=c;
        //deleteNotFav();
    }

    public void deleteNotFav(){
        sharedPreferenceManager = new SharedPreferenceManager(c);
        for(int i=0; i<items.size();i++){
            String itemId = String.valueOf(items.get(i).getId());
            if(sharedPreferenceManager.obtenerFavShared(itemId)){
                items.remove(i);
            }
        }
    }
    @NonNull
    @Override
    public RecyclerHolderFav onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_list_fav_view, parent, false);
        return new RecyclerAdapterFav.RecyclerHolderFav(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolderFav holder, int position) {
        PostsModel item = items.get(position);
        holder.tvId.setText(String.valueOf(item.getId()));
        holder.tvTitle.setText(item.getTitle());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class RecyclerHolderFav extends RecyclerView.ViewHolder{
        private TextView tvId;
        private TextView tvTitle;

        public RecyclerHolderFav(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvIdFav);
            tvTitle = itemView.findViewById(R.id.tvTitleFAv);

        }
    }
}
