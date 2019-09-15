package com.example.artworkpool.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.artworkpool.R;
import com.example.artworkpool.data.Artwork;
import com.example.artworkpool.helper.SharedHelper;
import com.example.artworkpool.room.ArtDAO;
import com.example.artworkpool.room.ArtEntity;
import com.example.artworkpool.ui.ArtDetailActivity;


import java.util.List;
import java.util.concurrent.Executors;

public class ArtWorkAdapter extends RecyclerView.Adapter<ArtWorkAdapter.ArtVH> {

    public List<Artwork> myList;

    public ArtWorkAdapter(List<Artwork> list){
        this.myList = list;
    }

    @NonNull
    @Override
    public ArtVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_artwork, viewGroup, false);
        return new ArtVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtVH holder, int position) {
        SharedHelper helper = new SharedHelper(holder.itemView.getContext());

        Artwork work = myList.get(position);

        Glide.with(holder.avatar.getContext())
                .load(work.getUrl())
                .error(R.drawable.tiger)
                .placeholder(R.drawable.tiger)
                .into(holder.avatar);

        holder.tags.setText(work.getTags());

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            ArtEntity entity = new ArtEntity(work.getTags(), work.getId(), work.getUser(), work.getUrl());
            @Override
            public void onClick(View view) {
                helper.saveTag(work.getTags());
                addArt2DB(holder.itemView.getContext(), entity);
                openActivity(holder.itemView.getContext());
            }
        });
    }


    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }

   public class ArtVH extends RecyclerView.ViewHolder{

        private TextView tags;
        private ImageView avatar;

        public ArtVH(View view){
            super(view);

            tags = view.findViewById(R.id.artTag);
            avatar = view.findViewById(R.id.artAvatar);
        }
    }

    private void addArt2DB(Context context, ArtEntity art){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ArtDAO.Companion.getArtDao(context.getApplicationContext()).addArt2DB(art);
            }
        });
    }

    private void openActivity(Context context){
        Intent intent = new Intent(context, ArtDetailActivity.class);
        context.startActivity(intent);
    }
}
