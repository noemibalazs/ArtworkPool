package com.example.artworkpool.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.artworkpool.R;
import com.example.artworkpool.adapter.ArtWorkAdapter;
import com.example.artworkpool.data.Artwork;
import com.example.artworkpool.data.ArtworkBlock;
import com.example.artworkpool.network.ArtApi;
import com.example.artworkpool.network.ArtClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.artworkpool.util.ConstatntsKt.IMAGE_TYPE;
import static com.example.artworkpool.util.ConstatntsKt.KEY;
import static com.example.artworkpool.util.ConstatntsKt.LATEST;

public class LatestFragment extends Fragment {

    private RecyclerView recyclerView;
    private EditText searchText;
    private ImageView done;
    private ArtWorkAdapter adapter;
    private static final String TAG = LatestFragment.class.getName();

    public LatestFragment(){}
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
        getImages();
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    String search = searchText.getText().toString().trim();
                    loadSearchedImages(search);
                    searchText.setText("");
                }else {
                   informUser();
                }
            }
        });
    }

    private void setUpRecycleView(){
        recyclerView.setHasFixedSize(true);
    }

    private void informUser(){
        Toast.makeText(getActivity(), getString(R.string.search_toast), Toast.LENGTH_LONG).show();
    }

    private Boolean isValid(){
        return searchText.getText().toString().length() > 3;
    }

    private void getImages(){
        ArtApi artApi = ArtClient.getRetrofit().create(ArtApi.class);
        Call<ArtworkBlock> latestArtWork = artApi.getLatestArtWorks(KEY, LATEST, 50, IMAGE_TYPE, "tiger");
        latestArtWork.enqueue(new Callback<ArtworkBlock>(){
            @Override
            public void onResponse(Call<ArtworkBlock> call, Response<ArtworkBlock> response) {
                if (!response.isSuccessful()){
                    Log.d(TAG, "onResponse error: " + response.message());
                }

                if (response.body() != null){
                    List<Artwork> artworks = response.body().getHits();
                    adapter = new ArtWorkAdapter(artworks);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArtworkBlock> call, Throwable t) {
                Toast.makeText(getActivity(), getString(R.string.error_toast), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadSearchedImages(String search){
        ArtApi artApi = ArtClient.getRetrofit().create(ArtApi.class);
        Call<ArtworkBlock> latestArtWork = artApi.getLatestArtWorks(KEY, LATEST, 50, IMAGE_TYPE, search);
        latestArtWork.enqueue(new Callback<ArtworkBlock>(){
            @Override
            public void onResponse(Call<ArtworkBlock> call, Response<ArtworkBlock> response) {
                if (!response.isSuccessful()){
                    Log.d(TAG, "onResponse error: " + response.message());
                }

                if (response.body() != null){
                    List<Artwork> artworks = response.body().getHits();
                    adapter = new ArtWorkAdapter(artworks);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArtworkBlock> call, Throwable t) {
                Toast.makeText(getActivity(), getString(R.string.error_toast), Toast.LENGTH_LONG).show();
            }
        });
    }
}
