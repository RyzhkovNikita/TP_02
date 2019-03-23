package com.example.tp_02;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Integer> mData;
    private Context mContext;
    private OnNumberClickListener mOnNumberClickListener;

    MyAdapter(Context context, ArrayList<Integer> data, OnNumberClickListener listener) {
        super();
        this.mData = data;
        this.mContext = context;
        this.mOnNumberClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.list_element, viewGroup, false);
        return new MyViewHolder(v);
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


    List<Integer> getDataList() {
        if (mData != null)
            return mData;
        else
            return new ArrayList<>();
    }


    /*Check if mNumber is odd*/
    private boolean isOdd(int number) {
        return number % 2 == 1;
    }

    void addNumber(int number) {
        this.mData.add(number);
        int lastPosition = mData.size() - 1;
        this.notifyItemInserted(lastPosition);
    }

    interface OnNumberClickListener {
        void onNumberClick(List<Integer> data, int number, boolean isOdd);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTextView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text);
            mTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnNumberClickListener == null)             //if there is no listener, finish method, else call onNumberClick
                return;
            int number = mData.get(getAdapterPosition());
            mOnNumberClickListener.onNumberClick(mData, number, isOdd(number));
        }
    }


}
