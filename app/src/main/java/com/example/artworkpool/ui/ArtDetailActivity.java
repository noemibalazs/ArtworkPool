package com.example.artworkpool.ui;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.artworkpool.R;
import com.example.artworkpool.helper.SharedHelper;
import com.example.artworkpool.room.ArtDAO;
import com.example.artworkpool.room.ArtEntity;

public class ArtDetailActivity extends AppCompatActivity {

    private ImageView avatar;
    private ArtEntity entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_detail);

        avatar = findViewById(R.id.artDetailAvatar);

        Observer<ArtEntity> entityObserver = new Observer<ArtEntity>() {
            @Override
            public void onChanged(@Nullable ArtEntity artEntity) {
                    if (artEntity != null){
                        entity = artEntity;
                        populateUI(entity);
                    }
            }
        };

        String tag = new SharedHelper(getApplicationContext()).getTag();
        ArtDAO.Companion.getArtDao(this).getArtByTag(tag).observe(this, entityObserver);
    }

    private void populateUI(ArtEntity artEntity){
        Glide.with(this)
                .load(artEntity.getUrl())
                .placeholder(R.drawable.tiger)
                .error(R.drawable.tiger)
                .into(avatar);
    }
}
