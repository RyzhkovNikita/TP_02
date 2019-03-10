package com.example.tp_02;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView mTextView;

    MyViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.text);
        mTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        Log.d(MainActivity.TAG, "onClick: " + v.getClass().getName() + " " + position);
        //TODO: normal listener for view
    }
}