package com.ingrid.jsonclient.model;

import com.ingrid.jsonclient.model.services.JSONPlaceholderService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class TodosRemoteRepository {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private final JSONPlaceholderService service;

    public TodosRemoteRepository(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        service = retrofit.create(JSONPlaceholderService.class);
    }

    public Call<List<TodoItem>> allTodos() {
        return service.allTodos();
    }
}
