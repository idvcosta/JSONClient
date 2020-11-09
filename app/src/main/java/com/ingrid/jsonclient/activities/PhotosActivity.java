package com.ingrid.jsonclient.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ingrid.jsonclient.R;
import com.ingrid.jsonclient.model.PhotoItem;
import com.ingrid.jsonclient.activities.adapters.PhotosAdapter;
import com.ingrid.jsonclient.photos.PhotosContract;
import com.ingrid.jsonclient.photos.PhotosPresenter;

import java.util.List;

public class PhotosActivity extends AppCompatActivity implements PhotosContract.View {

    private RecyclerView recyclePhotos;
    private ProgressBar progress;
    private PhotosContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        init();
    }

    private void init() {
        recyclePhotos = findViewById(R.id.recyclePhotos);
        progress = findViewById(R.id.progress);

        recyclePhotos.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        presenter = new PhotosPresenter();
        presenter.loadData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.setView(null);
    }

    @Override
    public void showLoading() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showPhotos(List<PhotoItem> photos) {
        PhotosAdapter adapter = new PhotosAdapter(photos);
        recyclePhotos.setAdapter(adapter);
    }

    @Override
    public void showLoadingPhotosError() {
        Toast.makeText(this, "Falha ao baixar dados.", Toast.LENGTH_SHORT).show();
    }
}