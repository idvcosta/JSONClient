package com.ingrid.jsonclient.todos;

import com.ingrid.jsonclient.model.TodoItem;
import com.ingrid.jsonclient.model.TodosRemoteRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodosPresenter implements TodosContract.Presenter {
    private TodosContract.View view;
    private List<TodoItem> todos;

    @Override
    public void loadData() {
        if (view != null) {
            view.showLoading();
        }
        TodosRemoteRepository repository = new TodosRemoteRepository();
        repository.allTodos().enqueue(new Callback<List<TodoItem>>() {
            @Override
            public void onResponse(Call<List<TodoItem>> call, Response<List<TodoItem>> response) {
                todos = response.body();
                if (view != null) {
                    view.showTodos(todos);
                    view.hideLoading();
                }
            }

            @Override
            public void onFailure(Call<List<TodoItem>> call, Throwable t) {
                if (view != null) {
                    view.showLoadTodosError();
                    view.hideLoading();
                }
            }
        });
    }

    @Override
    public void setView(TodosContract.View view) {
        this.view = view;
        if(todos != null){
            view.hideLoading();
            view.showTodos(todos);
        }
    }
}
