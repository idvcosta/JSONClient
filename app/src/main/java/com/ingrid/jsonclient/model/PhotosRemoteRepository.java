package com.ingrid.jsonclient.model;

import com.ingrid.jsonclient.model.services.JSONPlaceholderService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class PhotosRemoteRepository {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private final JSONPlaceholderService service;

    public PhotosRemoteRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        service = retrofit.create(JSONPlaceholderService.class);
    }

    public void allPhotos(Callback<List<PhotoItem>> callback) {
        service.allPhotos().enqueue(callback);
    }
}
