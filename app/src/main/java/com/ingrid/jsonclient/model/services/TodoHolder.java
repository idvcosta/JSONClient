package com.ingrid.jsonclient.model.services;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ingrid.jsonclient.R;

public class TodoHolder extends RecyclerView.ViewHolder {
    public TextView titleText;

    public TodoHolder(View itemView) {
        super(itemView);
        titleText = itemView.findViewById(R.id.titleText);
    }
}
