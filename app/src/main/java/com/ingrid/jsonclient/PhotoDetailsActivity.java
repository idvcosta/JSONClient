package com.ingrid.jsonclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.ingrid.jsonclient.model.PhotoItem;

public class PhotoDetailsActivity extends AppCompatActivity {

    public static final String PARAM_PHOTO_ITEM = "paramPhoto";

    private ImageView imagePhotoDetail;
    private TextView textPhotoDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);

        PhotoItem item = (PhotoItem) getIntent().getSerializableExtra(PARAM_PHOTO_ITEM);

        init(item);
    }

    private void init(PhotoItem item) {
        imagePhotoDetail = findViewById(R.id.photoDetailItem);
        textPhotoDetail = findViewById(R.id.textDetailItem);

        textPhotoDetail.setText(item.getTitle());

        GlideUrl glideUrl = new GlideUrl(item.getUrl(),
                new LazyHeaders.Builder()
                        .addHeader("User-Agent", "JSONClient")
                        .build());


        Glide
                .with(this)
                .load(glideUrl)
                .into(imagePhotoDetail);

    }
}