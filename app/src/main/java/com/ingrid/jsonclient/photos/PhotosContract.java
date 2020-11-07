package com.ingrid.jsonclient.photos;

import com.ingrid.jsonclient.model.PhotoItem;

import java.util.List;

public interface PhotosContract {
    interface View {
        void showLoading();

        void hideLoading();

        void showPhotos(List<PhotoItem> photos);

        void showLoadingPhotosError();
    }

    interface Presenter {
        void loadData();

        void setView(PhotosContract.View view);
    }
}
