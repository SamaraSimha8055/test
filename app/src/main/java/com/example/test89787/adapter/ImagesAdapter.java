package com.example.test89787.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.test89787.R;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder> {

    private final ArrayList<Uri> uri;

    public ImagesAdapter(ArrayList<Uri> uri) {
        this.uri = uri;
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_images, parent, false);
        return new ImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {


        //ImagesViewHolder.mImageRecyclerView.setImageURI(uri.get(position));

        Glide.with(holder.images.getContext())
                .load(uri.get(position))
                .into(holder.images);
    }

    @Override
    public int getItemCount() {
        return uri.size();
    }

    public static class ImagesViewHolder extends RecyclerView.ViewHolder {
        ImageView images;

        public ImagesViewHolder(View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.imageViewRecycler);
        }
    }
}