package com.ingrid.jsonclient.model;


public interface LoadAlbumCallback{

    void onResponse(AlbumItem album);

    void onFailure(Throwable throwable);
}
