package com.example.pruebatecnica.adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebatecnica.R;
import com.example.pruebatecnica.models.home.HomeModel;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> implements View.OnClickListener {
    private List<HomeModel> items;
    private View.OnClickListener listener;

    public RecyclerAdapter(List<HomeModel> items) {
        this.items = items;
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
        HomeModel item = items.get(position);
        holder.tvId.setText(String.valueOf(item.getId()));
        holder.tvTitle.setText(item.getTitle());
        holder.tvBody.setText(item.getBody());
        holder.imgItem.setImageResource(item.getImgResource());
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


        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.imgItem);
            tvId = itemView.findViewById(R.id.tvId);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);
        }
    }
}
