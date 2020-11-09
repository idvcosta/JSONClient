package com.ingrid.jsonclient.photos;

import com.ingrid.jsonclient.model.PhotoItem;
import com.ingrid.jsonclient.model.PhotosRemoteRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosPresenter implements PhotosContract.Presenter {
    private PhotosContract.View view;
    private List<PhotoItem> photos;

    @Override
    public void loadData() {
        if (view != null) {
            view.showLoading();
        }

        PhotosRemoteRepository repository = new PhotosRemoteRepository();
        repository.allPhotos(new Callback<List<PhotoItem>>() {

            @Override
            public void onResponse(Call<List<PhotoItem>> call, Response<List<PhotoItem>> response) {
                photos = response.body();
                if (view != null) {
                    view.showPhotos(photos);
                    view.hideLoading();
                }
            }

            @Override
            public void onFailure(Call<List<PhotoItem>> call, Throwable t) {
                if (view != null) {
                    view.showLoadingPhotosError();
                    view.hideLoading();
                }

            }
        });


    }

    @Override
    public void setView(PhotosContract.View view) {
        this.view = view;
        if (photos != null && view != null) {
            view.hideLoading();
            view.showPhotos(photos);
        }
    }
}
