package com.example.tp_02;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text);
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

        //TODO: check string-to-int parsing

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
            String str = mData.get(position);
            int number = Integer.parseInt(mData.get(position));
            myViewHolder.mTextView.setText(str);
            if (isOdd(number))
                myViewHolder.mTextView.setBackgroundColor(getResources().getColor(R.color.myLightBlue));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        /*Check if number is odd*/
        boolean isOdd(int number) {
            return number % 2 == 1;
        }
    }
}
