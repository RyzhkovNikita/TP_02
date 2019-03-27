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

    public static final String INT_KEY = "intNumberKey";
    public static final String INT_TEXT_COLOR_ID = "intTextColorId";
    public static final String INT_BG_COLOR_ID = "intBgColorId";

    private int mNumber;
    private int mTextColorId;
    private int mBgColorId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_big_number, container, false);
        TextView mTextView = view.findViewById(R.id.big_number_tv);

        if (savedInstanceState != null
                && savedInstanceState.containsKey(INT_KEY)
                && savedInstanceState.containsKey(INT_TEXT_COLOR_ID)
                && savedInstanceState.containsKey(INT_BG_COLOR_ID)) {
            mNumber = savedInstanceState.getInt(INT_KEY);
            mTextColorId = savedInstanceState.getInt(INT_TEXT_COLOR_ID);
            mBgColorId = savedInstanceState.getInt(INT_BG_COLOR_ID);
        }

        mTextView.setText(String.valueOf(mNumber));

        mTextView.setTextColor(mTextColorId);
        mTextView.setBackgroundColor(mBgColorId);
        return view;
    }

    // calls before onCreateView
    public void setNumberConfig(int number, int textColorId, int bgColorId) {
        mNumber = number;
        mTextColorId = textColorId;
        mBgColorId = bgColorId;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(INT_KEY, mNumber);
        outState.putInt(INT_TEXT_COLOR_ID, mTextColorId);
        outState.putInt(INT_BG_COLOR_ID, mBgColorId);
    }
}
