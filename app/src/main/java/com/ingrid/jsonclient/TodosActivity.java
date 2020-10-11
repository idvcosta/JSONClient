package com.ingrid.jsonclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ingrid.jsonclient.model.TodoItem;
import com.ingrid.jsonclient.model.TodosAdapter;
import com.ingrid.jsonclient.todos.TodosContract;
import com.ingrid.jsonclient.todos.TodosPresenter;

import java.util.List;

public class TodosActivity extends AppCompatActivity implements TodosContract.View {

    private ProgressBar progress;
    private RecyclerView recycle;

    private TodosContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);

        init();
    }

    private void init() {
        progress = findViewById(R.id.progress);
        recycle = findViewById(R.id.recycle);

        recycle.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        presenter = new TodosPresenter();
        presenter.loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.setView(null);
    }

    @Override
    public void showLoading() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progress.setVisibility(View.GONE);
    }

    public void showTodos(List<TodoItem> todos) {
        TodosAdapter adapter = new TodosAdapter(todos);
        recycle.setAdapter(adapter);
    }

    public void showLoadTodosError() {
        Toast.makeText(this,R.string.loadFailure,Toast.LENGTH_SHORT).show();
    }
}