package com.example.test89787.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test89787.R;
import com.example.test89787.helper.RecyclerInterface;
import com.example.test89787.model.ParentClass;

import java.util.List;


public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.ProductViewHolder> {



    private final List<ParentClass> titleList;

    RecyclerInterface recyclerInterface;

    public TitleAdapter(RecyclerInterface anRecyclerInterface, List<ParentClass> titleList) {
        this.recyclerInterface = anRecyclerInterface;
        this.titleList = titleList;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_title, null);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        ParentClass parentClass = titleList.get(position);
        holder.titleTv.setText(parentClass.getTitle());

        holder.imagesRecycler.setHasFixedSize(true);
        holder.imagesRecycler.setNestedScrollingEnabled(false);

        LinearLayoutManager latestLinearLayout = new GridLayoutManager(holder.imagesRecycler.getContext(), 3, GridLayoutManager.HORIZONTAL, false);


        holder.imagesRecycler.setLayoutManager(latestLinearLayout);


        holder.openGalleryButton.setOnClickListener(view -> recyclerInterface.onAddClicked(position));
        
        ImagesAdapter imagesAdapter = new ImagesAdapter(parentClass.getImageList());
        holder.imagesRecycler.setAdapter(imagesAdapter);


    }


    @Override
    public int getItemCount() {
        return titleList.size();
    }


    static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView titleTv;
        ImageButton openGalleryButton;
        RecyclerView imagesRecycler;

        public ProductViewHolder(View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.titleTv);
            openGalleryButton = itemView.findViewById(R.id.titleImageButton);
            imagesRecycler = itemView.findViewById(R.id.imagesTitleRecycler);
        }
    }
}
