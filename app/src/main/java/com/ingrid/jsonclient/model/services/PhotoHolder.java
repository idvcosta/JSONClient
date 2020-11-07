package com.ingrid.jsonclient.model.services;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ingrid.jsonclient.R;

public class PhotoHolder extends RecyclerView.ViewHolder {

    public TextView titleTextPhotos;
    public ImageView photoIv;

    public PhotoHolder(View itemView) {
        super(itemView);
        titleTextPhotos = itemView.findViewById(R.id.titleTextPhoto);
        photoIv = itemView.findViewById(R.id.photoIv);
    }
}
