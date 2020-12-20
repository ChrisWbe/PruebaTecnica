package com.example.pruebatecnica.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebatecnica.R;
import com.example.pruebatecnica.models.post.PostsModel;
import com.example.pruebatecnica.repositories.SharedPreferenceManager;

import java.util.List;
import java.util.Random;

public class RecyclerAdapterFav extends RecyclerView.Adapter<RecyclerAdapterFav.RecyclerHolderFav>{
    private List<PostsModel> items;
    private Context c;

    public RecyclerAdapterFav(List<PostsModel> items, Context c) {
        this.items = items;
        this.c=c;
    }

    @NonNull
    @Override
    public RecyclerHolderFav onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_list_fav_view, parent, false);
        return new RecyclerAdapterFav.RecyclerHolderFav(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolderFav holder, int position) {
        holder.cvFav.setAnimation(AnimationUtils.loadAnimation(c, R.anim.fade_transition));
        PostsModel item = items.get(position);
        holder.tvId.setText(String.valueOf(item.getId()));
        holder.tvTitle.setText(item.getTitle());
        holder.tvUserName.setText(item.getUsername());

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256),rnd.nextInt(256));
        holder.lLayoutBanner.setBackgroundColor(color);
        holder.lLayoutFooter.setBackgroundColor(color);
        holder.tvUserName.setTextColor(color);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class RecyclerHolderFav extends RecyclerView.ViewHolder{
        private TextView tvId;
        private TextView tvTitle;
        private TextView tvUserName;
        private LinearLayout lLayoutBanner;
        private LinearLayout lLayoutFooter;
        private CardView cvFav;

        public RecyclerHolderFav(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvIdFav);
            tvTitle = itemView.findViewById(R.id.tvTitleFAv);
            tvUserName = itemView.findViewById(R.id.tvUserNameFav);
            lLayoutBanner = itemView.findViewById(R.id.lLayoutBanner);
            lLayoutFooter = itemView.findViewById(R.id.lLayoutFooter);
            cvFav = itemView.findViewById(R.id.cvItemListFav);
        }
    }
}
