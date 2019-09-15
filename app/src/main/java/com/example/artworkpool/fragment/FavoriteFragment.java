package com.example.artworkpool.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.artworkpool.R;
import com.example.artworkpool.adapter.ArtWorkAdapter;
import com.example.artworkpool.data.Artwork;
import com.example.artworkpool.helper.Status;
import com.example.artworkpool.room.ArtDAO;
import com.example.artworkpool.room.ArtEntity;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements TextWatcher {

    public FavoriteFragment(){}

    private RecyclerView recyclerView;
    private EditText searchText;
    private ConstraintLayout layout;
    private ArtWorkAdapter adapter;
    private Boolean notEmpty = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = view.findViewById(R.id.artRecycler);
        searchText = view.findViewById(R.id.artSearch);
        layout = view.findViewById(R.id.fragmentContainer);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecycleView();
        loadImages();
        searchText.addTextChangedListener(this);
        observeStatus();
    }

    private void setUpRecycleView(){
        recyclerView.setHasFixedSize(true);
    }

    private List<Artwork> entity2Work(List<ArtEntity> entities){
        List<Artwork> artworks = new ArrayList<>();
        for (ArtEntity entity : entities){
            artworks.add(new Artwork(entity.getId(), entity.getTag(), entity.getUser(), entity.getUrl()));
        }
        return artworks;
    }

    private void loadImages(){
        ArtDAO.Companion.getArtDao(getActivity().getBaseContext()).getArtList().observe(this, new Observer<List<ArtEntity>>(){

            @Override
            public void onChanged(@Nullable List<ArtEntity> artEntities) {
                List<Artwork> artworks = entity2Work(artEntities);
                recyclerView.setVisibility(View.VISIBLE);
                adapter = new ArtWorkAdapter(artworks);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void showOptions2User(){
        Snackbar snackbar = Snackbar.make(layout, getString(R.string.empty_snack_toast), Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        TextView textView = view.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(getContext().getColor(R.color.colorPrimary));
        snackbar.setAction(getString(R.string.snack_undo), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchText.setText("");
                notEmpty = true;
                Status.INSTANCE.setStatus(notEmpty);
            }
        });
        snackbar.show();
    }


    private void searchingForArtWork(String searchedText){
        ArtDAO.Companion.getArtDao(getActivity().getBaseContext()).getSearchedArtList(searchedText).observe(this, new Observer<List<ArtEntity>>() {
            @Override
            public void onChanged(@Nullable List<ArtEntity> entities) {
                if (entities == null || entities.isEmpty()){
                    recyclerView.setVisibility(View.GONE);
                    showOptions2User();
                }else {
                    recyclerView.setVisibility(View.VISIBLE);
                    adapter.myList.clear();
                    adapter.myList = entity2Work(entities);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.toString().length() >= 3){
            String searchedText = editable.toString();
            searchingForArtWork(searchedText);
        }
    }

    private void observeStatus(){
        Status.INSTANCE.getStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean check) {
                if (check){
                   loadImages();
                }
            }
        });
    }

}
