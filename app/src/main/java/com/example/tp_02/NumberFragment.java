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
    public static String INT_KEY = "intNumberKey";
    public static String IS_ODD_KEY = "isNumberOddKey";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_big_number, container, false);
        TextView mTextView = view.findViewById(R.id.big_number_tv);

        if (savedInstanceState != null
                && savedInstanceState.containsKey(INT_KEY)
                && savedInstanceState.containsKey(IS_ODD_KEY)) {
            mNumber = savedInstanceState.getInt(INT_KEY);
            isOdd = savedInstanceState.getBoolean(IS_ODD_KEY);
        }

        mTextView.setText(String.valueOf(mNumber));
        if (getContext() != null) {
            if (isOdd) {
                mTextView.setTextColor(getContext().getResources().getColor(R.color.myBlue));
                mTextView.setBackgroundColor(getContext().getResources().getColor(R.color.myLightBlue));
            } else {
                mTextView.setTextColor(getContext().getResources().getColor(R.color.myRed));
                mTextView.setBackgroundColor(getContext().getResources().getColor(R.color.myLightRed));
            }
        }
        return view;
    }

    // calls before onCreateView
    public void setNumber(int number, boolean isOdd) {
        this.mNumber = number;
        this.isOdd = isOdd;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(INT_KEY, mNumber);
        outState.putBoolean(IS_ODD_KEY, isOdd);
    }
}
