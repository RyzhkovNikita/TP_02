package com.example.tp_02;

import android.content.Context;
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

    RecyclerView recyclerView;
    EditText editText;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = view.findViewById(R.id.my_list);
        editText = view.findViewById(R.id.editText);

        ArrayList<Integer> integers;

        if (savedInstanceState != null && savedInstanceState.containsKey(ARRAY_KEY)) {
            integers = savedInstanceState.getIntegerArrayList(ARRAY_KEY);
        } else {
            integers = hundredNumbersList();
        }

        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_anim_fall_down);

        recyclerView.setLayoutAnimation(animation);
        recyclerView.setAdapter(new MyAdapter(getContext(), integers));

        if (recyclerView.getAdapter() != null) {
            MyAdapter.OnNumberClickListener activityListener = (MyAdapter.OnNumberClickListener) getActivity();
            ((MyAdapter) recyclerView.getAdapter()).setOnNumberClickListener(activityListener);
        }

        view.findViewById(R.id.add_button).setOnClickListener(this);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (recyclerView.getAdapter() != null) {
            ArrayList<Integer> data = new ArrayList<>(((MyAdapter) recyclerView.getAdapter()).getDataList());
            outState.putIntegerArrayList(ARRAY_KEY, data);
        }
    }

    @Override
    public void onClick(View v) {
        String text = editText.getText().toString();
        if (text.isEmpty())
            Toast.makeText(getContext(), "Empty", Toast.LENGTH_SHORT).show();
        else {
            try {
                int number = Integer.parseInt(text);
                if (recyclerView.getAdapter() == null) {
                    Toast.makeText(getContext(), "Adapter is null", Toast.LENGTH_SHORT).show();
                    return;
                }
                ((MyAdapter) recyclerView.getAdapter()).getDataList().add(number);
                int lastPosition = ((MyAdapter) recyclerView.getAdapter()).getDataList().size();
                recyclerView.getAdapter().notifyItemInserted(lastPosition);
                recyclerView.scrollToPosition(lastPosition - 1);
                editText.setText("");
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
