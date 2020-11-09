package com.ingrid.jsonclient.model.services;

import com.ingrid.jsonclient.model.AlbumItem;
import com.ingrid.jsonclient.model.PhotoItem;
import com.ingrid.jsonclient.model.TodoItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceholderService {
    @GET("todos")
    Call<List<TodoItem>> allTodos();

    @GET("photos")
    Call<List<PhotoItem>> allPhotos();

    @GET("albums")
    Call<List<AlbumItem>> allAlbums();
}
