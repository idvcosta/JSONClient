package com.ingrid.jsonclient.activities.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.ingrid.jsonclient.activities.PhotoDetailsActivity;
import com.ingrid.jsonclient.R;
import com.ingrid.jsonclient.model.PhotoItem;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotoHolder> implements View.OnClickListener {

    private List<PhotoItem> photos;

    public PhotosAdapter(List<PhotoItem> photos) {
        this.photos = photos;
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_photos, parent, false);

        view.setOnClickListener(this);


        PhotoHolder holder = new PhotoHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {
        PhotoItem photoItem = photos.get(position);
        holder.itemView.setTag(photoItem);

        holder.titleTextPhotos.setText(photoItem.getTitle());

        GlideUrl glideUrl = new GlideUrl(photoItem.getThumbnailUrl(),
                new LazyHeaders.Builder()
                        .addHeader("User-Agent", "JSONClient")
                        .build());


        Glide
                .with(holder.photoIv)
                .load(glideUrl)
                .into(holder.photoIv);

    }


    @Override
    public int getItemCount() {
        return this.photos.size();
    }

    @Override
    public void onClick(View source) {
        PhotoItem item = (PhotoItem) source.getTag();

        Context context = source.getContext();
        Intent intent = new Intent(context, PhotoDetailsActivity.class);
        intent.putExtra(PhotoDetailsActivity.PARAM_PHOTO_ITEM,item);

        context.startActivity(intent);
    }
}
