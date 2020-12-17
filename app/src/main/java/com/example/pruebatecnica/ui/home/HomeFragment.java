package com.example.pruebatecnica.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebatecnica.DialogActivity;
import com.example.pruebatecnica.R;
import com.example.pruebatecnica.adapter.RecyclerAdapter;
import com.example.pruebatecnica.models.home.HomeModel;
import com.example.pruebatecnica.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private RecyclerView rvLista;
    private RecyclerAdapter adapter;
    private List<HomeModel> items;
    private HomeViewModel homeViewModel;
    private FloatingActionButton fab;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.init(getContext());
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(root);
        initValues();

        homeViewModel.getHomeModel().observe(getViewLifecycleOwner(), new Observer<List<HomeModel>>() {
            @Override
            public void onChanged(List<HomeModel> homeModels) {
                Log.d("MVVM", String.valueOf(homeModels.size()));
                adapter.notifyDataSetChanged();
            }
        });

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeModel item = items.get(rvLista.getChildAdapterPosition(v));
                Intent intent = new Intent(getContext(), DialogActivity.class);
                Bundle parametros = new Bundle();
                try {
                    Log.d("MVVM", item.getObject().toString());
                    parametros.putString("item", item.getObject().toString());
                    intent.putExtras(parametros);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Actualización de los post
                adapter.notifyDataSetChanged();
                Snackbar.make(view, "Desde Fragment", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        return root;
    }


    private void initViews(View view){
        rvLista = (RecyclerView) view.findViewById(R.id.rvLista);
        fab = getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.refresh);
    }

    private void initValues(){
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvLista.setLayoutManager(manager);

        items = new ArrayList<>();
        items.add(new HomeModel(1, 1, "title", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "Christian","chriswbe","chriswbe1993@gmail.com","123456789","google.com",R.drawable.user));
        items.add(new HomeModel(1, 2, "title", "body", "Christian","chriswbe","chriswbe1993@gmail.com","123456789","google.com",R.drawable.user));
        items.add(new HomeModel(1, 3, "title", "body", "Christian","chriswbe","chriswbe1993@gmail.com","123456789","google.com",R.drawable.user));
        items.add(new HomeModel(1, 4, "title", "body", "Christian","chriswbe","chriswbe1993@gmail.com","123456789","google.com",R.drawable.user));
        adapter = new RecyclerAdapter(items, getContext());
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rvLista);
        rvLista.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback=new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Toast.makeText(getContext(), String.valueOf(viewHolder.getAdapterPosition()), Toast.LENGTH_LONG).show();
            items.remove(viewHolder.getAdapterPosition());
            adapter.notifyDataSetChanged();
        }
    };


}