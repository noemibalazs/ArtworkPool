package com.example.artworkpool.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.artworkpool.R;
import com.example.artworkpool.adapter.ArtWorkAdapter;
import com.example.artworkpool.data.Artwork;
import com.example.artworkpool.room.ArtDAO;
import com.example.artworkpool.room.ArtEntity;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    public FavoriteFragment(){}

    private RecyclerView recyclerView;
    private EditText searchText;
    private ImageView done;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_container, container, false);
        recyclerView = view.findViewById(R.id.artRecycler);
        searchText = view.findViewById(R.id.artSearch);
        done = view.findViewById(R.id.searchDone);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecycleView();
        loadImages();
    }

    private void setUpRecycleView(){
        recyclerView.setHasFixedSize(true);
    }

    private void loadImages(){
        ArtDAO.Companion.getArtDao(getActivity().getBaseContext()).getArtList().observe(this, new Observer<List<ArtEntity>>(){

            @Override
            public void onChanged(@Nullable List<ArtEntity> artEntities) {
                List<Artwork> artworks = entity2Work(artEntities);
                recyclerView.setAdapter(new ArtWorkAdapter(artworks));

            }
        });
    }

    private List<Artwork> entity2Work(List<ArtEntity> entities){
        List<Artwork> artworks = new ArrayList<>();
        for (ArtEntity entity : entities){
            artworks.add(new Artwork(entity.getId(), entity.getTag(), entity.getUser(), entity.getUrl()));
        }
        return artworks;
    }
}
