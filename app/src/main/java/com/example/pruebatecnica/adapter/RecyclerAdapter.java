package com.example.pruebatecnica.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
        holder.cvItemList.setAnimation(AnimationUtils.loadAnimation(c,R.anim.fade_transition));

        PostsModel item = items.get(position);
        String id = String.valueOf(item.getId());
        holder.tvId.setText(String.valueOf(item.getId()));
        holder.tvTitle.setText(item.getTitle());
        holder.tvBody.setText(item.getBody());
        holder.imgItem.setImageResource(item.getImgResource());

        sharedPreferenceManager = new SharedPreferenceManager(c);
        holder.imgStar.setImageResource(
                sharedPreferenceManager.obtenerFavShared(id) ? android.R.drawable.star_big_off : android.R.drawable.star_big_on
        );

        holder.imgIndicator.setImageResource(
                !sharedPreferenceManager.obtenerFavShared(id) ?
                        android.R.drawable.checkbox_on_background : !sharedPreferenceManager.obtenerReadShared(id) ?
                            android.R.drawable.checkbox_on_background: item.getId()<=nFirts ?
                                R.drawable.ic_circle_indicator:R.drawable.ic_circle_not_indicator
        );


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnClickListener(View.OnClickListener listener){this.listener=listener;}

    @Override
    public void onClick(View v) {
        //Toast.makeText(c, String.valueOf(v.getId()), Toast.LENGTH_LONG).show();
        //v.setAnimation(AnimationUtils.loadAnimation(c, R.anim.traslate));
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
        private CardView cvItemList;



        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.imgItem);
            tvId = itemView.findViewById(R.id.tvId);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);
            imgStar = itemView.findViewById(R.id.imgStar);
            imgIndicator = itemView.findViewById(R.id.imgIndicator);
            cvItemList = itemView.findViewById(R.id.cvItemList);

        }
    }
}
