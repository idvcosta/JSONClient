package com.ingrid.jsonclient.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.ingrid.jsonclient.R;
import com.ingrid.jsonclient.model.AlbumItem;
import com.ingrid.jsonclient.model.AlbumsRepository;
import com.ingrid.jsonclient.model.LoadAlbumCallback;
import com.ingrid.jsonclient.model.PhotoItem;

import java.util.List;

public class PhotoDetailsActivity extends AppCompatActivity {

    public static final String PARAM_PHOTO_ITEM = "paramPhoto";

    private ImageView ivPhoto;
    private TextView tvTitle;
    private TextView tvTitleAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);

        PhotoItem item = (PhotoItem) getIntent().getSerializableExtra(PARAM_PHOTO_ITEM);

        init(item);
    }

    private void init(PhotoItem item) {
        ivPhoto = findViewById(R.id.ivPhoto);
        tvTitle = findViewById(R.id.tvTitle);
        tvTitleAlbum = findViewById(R.id.tvTitleAlbum);

        tvTitle.setText(item.getTitle());

        loadImage(item);
        loadAlbum(item.getAlbumId());
    }

    private void loadImage(PhotoItem item) {
        GlideUrl glideUrl = new GlideUrl(item.getUrl(),
                new LazyHeaders.Builder()
                        .addHeader("User-Agent", "JSONClient")
                        .build());
        Glide
                .with(this)
                .load(glideUrl)
                .into(ivPhoto);
    }

    private void loadAlbum(Long albumId) {
        AlbumsRepository repository = AlbumsRepository.getInstance();
        repository.loadAlbum(albumId, new LoadAlbumCallback() {
            @Override
            public void onResponse(AlbumItem album) {
                tvTitleAlbum.setText(album.getTitle());
            }

            @Override
            public void onFailure(Throwable throwable) {
                //nothing to do
            }
        });
    }
}