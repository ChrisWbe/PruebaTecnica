package com.example.pruebatecnica.ui.posts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.example.pruebatecnica.models.post.PostsModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class PostsFragment extends Fragment {
    private RecyclerView rvLista;
    private RecyclerAdapter adapter;
    private PostsViewModel postsViewModel;
    private FloatingActionButton fab;
    private static final int LAUCH_CODE_ACTIVITY_DIALOG = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        postsViewModel = new ViewModelProvider(this).get(PostsViewModel.class);
        postsViewModel.init(getContext());
        View root = inflater.inflate(R.layout.fragment_posts, container, false);
        rvLista = (RecyclerView) root.findViewById(R.id.rvLista);
        fab = (FloatingActionButton) root.findViewById(R.id.fab);

        postsViewModel.getHomeModel().observe(getViewLifecycleOwner(), new Observer<List<PostsModel>>() {
            @Override
            public void onChanged(List<PostsModel> postsModels) {
                Log.d("MVVM", String.valueOf(postsModels.size()));
                adapter.notifyDataSetChanged();
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Actualización de los post
                try {
                    postsViewModel.update(getContext());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        initRecyclerView();
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postsViewModel.setReadPost(
                        getContext(),
                        postsViewModel.getHomeModel().getValue().get(rvLista.getChildAdapterPosition(v))
                );
                PostsModel item = postsViewModel.getHomeModel().getValue().get(rvLista.getChildAdapterPosition(v));
                Intent intent = new Intent(getContext(), DialogActivity.class);
                Bundle parametros = new Bundle();
                try {
                    parametros.putString("item", item.getObject().toString());
                    intent.putExtras(parametros);
                    startActivityForResult(intent,LAUCH_CODE_ACTIVITY_DIALOG);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Se obtiene la vista y se cambia el indicador
                ImageView imgIndicator = (ImageView) v.findViewById(R.id.imgIndicator);
                imgIndicator.setImageResource(android.R.drawable.checkbox_on_background);

            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case LAUCH_CODE_ACTIVITY_DIALOG:
                if(resultCode== Activity.RESULT_OK){
                    adapter.notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    public void initRecyclerView(){
        adapter = new RecyclerAdapter(postsViewModel.getHomeModel().getValue(), getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvLista.setLayoutManager(manager);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rvLista);
        rvLista.setAdapter(adapter);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_delete, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete:
                Toast.makeText(getContext(), "Borrado", Toast.LENGTH_LONG).show();
                postsViewModel.deletePre(getContext());
                postsViewModel.getHomeModel().getValue().clear();
                adapter.notifyDataSetChanged();
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

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback=new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Toast.makeText(getContext(), String.valueOf(viewHolder.getAdapterPosition()), Toast.LENGTH_LONG).show();
            postsViewModel.getHomeModel().getValue().remove(viewHolder.getAdapterPosition());
            adapter.notifyDataSetChanged();
        }
    };


}