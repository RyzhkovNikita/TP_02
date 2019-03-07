package com.example.tp_02;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> strings = new ArrayList<>();
        fillList(strings);

        recyclerView = findViewById(R.id.my_list);
        recyclerView.setAdapter(new MyAdapter(strings));
    }

    void fillList(List<String> toFill) {
        for (int i = 0; i < 200; i++) {
            toFill.add(Integer.toString(i + 1));
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView mImageView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text);
            mImageView = itemView.findViewById(R.id.avatar);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private List<String> mData;

        MyAdapter(List<String> data) {
            super();
            this.mData = data;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View v = inflater.inflate(R.layout.list_element, viewGroup, false);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            String str = mData.get(i);
            myViewHolder.mTextView.setText(str);
            myViewHolder.mImageView.setBackground(getDrawable(R.drawable.ic_launcher_foreground));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
}
