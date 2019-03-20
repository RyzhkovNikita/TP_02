package com.example.tp_02;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class NumberFragment extends Fragment {

    private int mNumber;
    private boolean isOdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_big_number, container, false);
        TextView mTextView = view.findViewById(R.id.big_number_tv);
        mTextView.setText(String.valueOf(mNumber));
        if (isOdd) {
            mTextView.setTextColor(getContext().getResources().getColor(R.color.myBlue));
            mTextView.setBackgroundColor(getContext().getResources().getColor(R.color.myLightBlue));
        } else {
            mTextView.setTextColor(getContext().getResources().getColor(R.color.myRed));
            mTextView.setBackgroundColor(getContext().getResources().getColor(R.color.myLightRed));
        }
        return view;
    }

    public void setNumber(int number, boolean isOdd) {
        this.mNumber = number;
        this.isOdd = isOdd;
    }
}
