package com.ingrid.jsonclient.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.ingrid.jsonclient.R;
import com.ingrid.jsonclient.model.services.TodoHolder;

import java.util.List;

public class TodosAdapter extends RecyclerView.Adapter<TodoHolder> {

    private List<TodoItem> todos;

    public TodosAdapter(List<TodoItem> todos) {
        this.todos = todos;
    }

    @Override
    public TodoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_todos, parent, false);
        TodoHolder holder = new TodoHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TodoHolder holder, int position) {
        TodoItem todoItem = todos.get(position);

        holder.titleText.setText(todoItem.getTitle());
    }


        @Override
        public int getItemCount () {
            return this.todos.size();
        }
    }
