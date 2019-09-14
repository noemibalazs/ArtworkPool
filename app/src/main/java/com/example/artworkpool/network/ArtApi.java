package com.example.artworkpool.network;

import com.example.artworkpool.data.ArtworkBlock;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArtApi {

    @GET("?")
    Call<ArtworkBlock> getMostPopularArtWorks(@Query("key") String key, @Query("order") String order, @Query("per_page") int number, @Query("image_type") String photo, @Query("q") String query);

    @GET("?")
    Call<ArtworkBlock> getLatestArtWorks(@Query("key") String key, @Query("order") String order, @Query("per_page") int number, @Query("image_type") String photo, @Query("q") String query);
}
