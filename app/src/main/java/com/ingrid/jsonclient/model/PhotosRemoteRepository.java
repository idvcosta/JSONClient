package com.ingrid.jsonclient.model;

import com.ingrid.jsonclient.model.services.PlaceholderService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class PhotosRemoteRepository {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private final PlaceholderService service;

    public PhotosRemoteRepository() {

        Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(BASE_URL)
                                        .addConverterFactory(JacksonConverterFactory.create())
                                        .build();
        service = retrofit.create(PlaceholderService.class);
    }

    public Call<List<PhotoItem>> allPhotos(){ return service.allPhotos();}
}
