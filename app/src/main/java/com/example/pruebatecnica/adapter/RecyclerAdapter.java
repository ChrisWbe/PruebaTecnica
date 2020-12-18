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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> implements View.OnClickListener {
    private List<PostsModel> items;
    private View.OnClickListener listener;
    private SharedPreferenceManager sharedPreferenceManager;
    private Context c;
    private final int nFirts = 20;

    public RecyclerAdapter(List<PostsModel> items, Context c) {
        this.items = items;
        this.c=c;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_list_view, parent, false);
        view.setOnClickListener(this);
        return new RecyclerHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        PostsModel item = items.get(position);
        holder.tvId.setText(String.valueOf(item.getId()));
        holder.tvTitle.setText(item.getTitle());
        holder.tvBody.setText(item.getBody());
        holder.imgItem.setImageResource(item.getImgResource());

        sharedPreferenceManager = new SharedPreferenceManager(c);
        holder.imgStar.setImageResource(
                sharedPreferenceManager.obtenerFavShared(String.valueOf(item.getId())) ? android.R.drawable.star_big_off : android.R.drawable.star_big_on
        );

        if(item.getId()<=nFirts){
            holder.imgIndicator.setImageResource(R.drawable.ic_circle_indicator);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnClickListener(View.OnClickListener listener){this.listener=listener;}

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder{
        private ImageView imgItem;
        private TextView tvId;
        private TextView tvTitle;
        private TextView tvBody;
        private ImageView imgStar;
        private ImageView imgIndicator;



        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.imgItem);
            tvId = itemView.findViewById(R.id.tvId);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);
            imgStar = itemView.findViewById(R.id.imgStar);
            imgIndicator = itemView.findViewById(R.id.imgIndicator);

        }
    }
}
