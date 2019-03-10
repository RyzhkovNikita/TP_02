package com.example.tp_02;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Integer> mData;
    private Context mContext;

    MyAdapter(Context context, List<Integer> data) {
        super();
        this.mData = data;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.list_element, viewGroup, false);
        return new MyViewHolder(v);
    }

    List<Integer> getDataList() {
        if (mData != null)
            return mData;
        else
            return new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        int number = mData.get(position);
        myViewHolder.mTextView.setText(String.valueOf(number));

        if (isOdd(number)) {
            myViewHolder.mTextView.setTextColor(mContext.getResources().getColor(R.color.myBlue));
            myViewHolder.mTextView.setBackgroundColor(mContext.getResources().getColor(R.color.myLightBlue));
        } else {
            myViewHolder.mTextView.setTextColor(mContext.getResources().getColor(R.color.myRed));
            myViewHolder.mTextView.setBackgroundColor(mContext.getResources().getColor(R.color.myLightRed));
        }
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
