package com.ingrid.jsonclient.model;

import com.ingrid.jsonclient.model.services.JSONPlaceholderService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class AlbumsRepository {

    private static AlbumsRepository INSTANCE = null;

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private final JSONPlaceholderService service;
    private List<AlbumItem> albums;

    private AlbumsRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        service = retrofit.create(JSONPlaceholderService.class);
    }

    public static AlbumsRepository getInstance() {
        if(INSTANCE == null){
            INSTANCE = new AlbumsRepository();
        }

        return INSTANCE;
    }

    public void loadAlbum(Long albumId, LoadAlbumCallback callback) {
        boolean isLoaded = albums != null;

        if (isLoaded) {
            AlbumItem album = findAlbum(albumId, albums);

            callback.onResponse(album);
        } else {
            fetchAlbums(albumId, callback);
        }
    }

    private void fetchAlbums(Long albumId, LoadAlbumCallback callback) {
        service.allAlbums().enqueue(new Callback<List<AlbumItem>>() {
            @Override
            public void onResponse(Call<List<AlbumItem>> call, Response<List<AlbumItem>> response) {
                albums = response.body();
                AlbumItem album = findAlbum(albumId, albums);

                callback.onResponse(album);
            }

            @Override
            public void onFailure(Call<List<AlbumItem>> call, Throwable throwable) {
                callback.onFailure(throwable);
            }
        });
    }

    private AlbumItem findAlbum(Long albumId, List<AlbumItem> albums) {
        for (int i = 0; i < albums.size(); i++) {
            AlbumItem item = albums.get(i);

            if (item.getId().equals(albumId)) {
                return item;
            }
        }
        return null;
    }
}
