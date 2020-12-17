package com.example.pruebatecnica.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebatecnica.R;
import com.example.pruebatecnica.adapter.RecyclerAdapter;
import com.example.pruebatecnica.models.home.HomeModel;
import com.example.pruebatecnica.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private RecyclerView rvLista;
    private RecyclerAdapter adapter;
    private List<HomeModel> items;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.init(getContext());
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel.getHomeModel().observe(getViewLifecycleOwner(), new Observer<List<HomeModel>>() {
            @Override
            public void onChanged(List<HomeModel> homeModels) {
                Log.d("MVVM", String.valueOf(homeModels.size()));

                adapter.notifyDataSetChanged();
            }
        });

        initViews(root);
        initValues();

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Seleccion: "+items.get(rvLista.getChildAdapterPosition(v)).getId(), Toast.LENGTH_LONG).show();
            }
        });



        return root;
    }

    private void initViews(View view){
        rvLista = (RecyclerView) view.findViewById(R.id.rvLista);
    }

    private void initValues(){
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvLista.setLayoutManager(manager);

        items = new ArrayList<>();
        items.add(new HomeModel(1, 1, "title", "body", R.drawable.user));
        items.add(new HomeModel(2, 2, "title", "body", R.drawable.user));
        items.add(new HomeModel(2, 3, "title", "body", R.drawable.user));
        items.add(new HomeModel(3, 4, "title", "body", R.drawable.user));
        adapter = new RecyclerAdapter(items);
        rvLista.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}