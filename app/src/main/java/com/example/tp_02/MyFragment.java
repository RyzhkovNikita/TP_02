package com.example.tp_02;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MyFragment extends Fragment implements View.OnClickListener {

    public static final String ARRAY_KEY = "arrayKey";
    public static final String TAG = "MyLogs";

    private RecyclerView mRecyclerView;
    private EditText mEditText;
    private ArrayList<Integer> mIntList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null)
            mIntList = ((MainActivity) getActivity()).getSavedData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mRecyclerView = view.findViewById(R.id.my_list);
        mEditText = view.findViewById(R.id.editText);

        if (mIntList == null) {
            if (savedInstanceState != null && savedInstanceState.containsKey(ARRAY_KEY)) {
                mIntList = savedInstanceState.getIntegerArrayList(ARRAY_KEY);
            } else {
                mIntList = hundredNumbersList();
            }
        }

        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_anim_fall_down);

        mRecyclerView.setLayoutAnimation(animation);
        mRecyclerView.setAdapter(new MyAdapter(getContext(), mIntList));

        if (mRecyclerView.getAdapter() != null) {
            MyAdapter.OnNumberClickListener activityListener = (MyAdapter.OnNumberClickListener) getActivity();
            ((MyAdapter) mRecyclerView.getAdapter()).setOnNumberClickListener(activityListener);
        }

        view.findViewById(R.id.add_button).setOnClickListener(this);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mRecyclerView.getAdapter() != null) {
            ArrayList<Integer> data = new ArrayList<>(((MyAdapter) mRecyclerView.getAdapter()).getDataList());
            outState.putIntegerArrayList(ARRAY_KEY, data);
        }
    }

    @Override
    public void onClick(View v) {
        String text = mEditText.getText().toString();
        if (text.isEmpty())
            Toast.makeText(getContext(), "Empty", Toast.LENGTH_SHORT).show();
        else {
            try {
                int number = Integer.parseInt(text);
                if (mRecyclerView.getAdapter() == null) {
                    Toast.makeText(getContext(), "Adapter is null", Toast.LENGTH_SHORT).show();
                    return;
                }
                ((MyAdapter) mRecyclerView.getAdapter()).getDataList().add(number);
                int lastPosition = ((MyAdapter) mRecyclerView.getAdapter()).getDataList().size();
                mRecyclerView.getAdapter().notifyItemInserted(lastPosition);
                mRecyclerView.scrollToPosition(lastPosition - 1);
                mEditText.setText("");
                Toast.makeText(getContext(), "Inserted", Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Wrong format", Toast.LENGTH_SHORT).show();
            }
        }
    }

    ArrayList<Integer> hundredNumbersList() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + 1);
        }
        return list;
    }

}
