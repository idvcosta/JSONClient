package com.ingrid.jsonclient.todos;

import com.ingrid.jsonclient.model.TodoItem;

import java.util.List;

public interface TodosContract {
    interface View {
        void showLoading();

        void hideLoading();

        void showTodos(List<TodoItem> todos);

        void showLoadTodosError();
    }

    interface Presenter {
        void loadData();
    }
}
