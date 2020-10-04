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
import com.ingrid.jsonclient.model.TodosRemoteRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progress;
    private RecyclerView recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        progress = findViewById(R.id.progress);
        recycle = findViewById(R.id.recycle);

        recycle.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        loadData();
    }

    private void loadData() {
        TodosRemoteRepository repository = new TodosRemoteRepository();
        repository.allTodos().enqueue(new Callback<List<TodoItem>>() {
            @Override
            public void onResponse(Call<List<TodoItem>> call, Response<List<TodoItem>> response) {
                MainActivity.this.showTodos(response.body());
            }

            @Override
            public void onFailure(Call<List<TodoItem>> call, Throwable t) {
                MainActivity.this.showLoadTodosError();

            }
        });
    }

    private void showTodos(List<TodoItem> todos) {
        TodosAdapter adapter = new TodosAdapter(todos);
        recycle.setAdapter(adapter);
        progress.setVisibility(View.GONE);
    }

    private void showLoadTodosError() {
        progress.setVisibility(View.GONE);
        Toast.makeText(this,R.string.loadFailure,Toast.LENGTH_SHORT).show();
    }
}