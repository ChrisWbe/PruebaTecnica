package com.example.pruebatecnica.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.pruebatecnica.R;
import com.example.pruebatecnica.models.home.HomeModel;

import java.util.List;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.init(getActivity());
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        homeViewModel.getHomeModel().observe(getViewLifecycleOwner(), new Observer<List<HomeModel>>() {
            @Override
            public void onChanged(List<HomeModel> homeModels) {
                Log.d("MVVM", String.valueOf(homeModels.size()));
            }
        });



        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


}