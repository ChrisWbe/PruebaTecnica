package com.example.pruebatecnica.ui.favoritos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebatecnica.R;
import com.example.pruebatecnica.adapter.RecyclerAdapter;
import com.example.pruebatecnica.adapter.RecyclerAdapterFav;
import com.example.pruebatecnica.models.post.PostsModel;

import java.util.List;

public class FavoritosFragment extends Fragment {
    private RecyclerView rvListaFav;
    private RecyclerAdapterFav adapterFav;
    private FavoritosViewModel favoritosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        favoritosViewModel = new ViewModelProvider(this).get(FavoritosViewModel.class);
        favoritosViewModel.init(getContext());
        View root = inflater.inflate(R.layout.fragment_favoritos, container, false);
        rvListaFav = (RecyclerView) root.findViewById(R.id.rvListaFav);

        favoritosViewModel.getHomeModel().observe(getViewLifecycleOwner(), new Observer<List<PostsModel>>() {
            @Override
            public void onChanged(List<PostsModel> postsModels) {
                adapterFav.notifyDataSetChanged();
            }
        });
        initRecyclerView();
        return root;
    }

    public void initRecyclerView(){
        adapterFav = new RecyclerAdapterFav(favoritosViewModel.getHomeModel().getValue(), getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvListaFav.setLayoutManager(manager);
        rvListaFav.setAdapter(adapterFav);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(getContext(), "Favoritos Borrados", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }
}