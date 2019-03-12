package com.example.tp_02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public static final String ARRAY_KEY = "arrayKey";
    public static final String TAG = "MyLogs";
    RecyclerView recyclerView;
    Button addBtn;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.my_list);
        addBtn = findViewById(R.id.add_button);
        editText = findViewById(R.id.editText);

        ArrayList<Integer> integers;
        if (savedInstanceState != null && savedInstanceState.containsKey(ARRAY_KEY)) {
            integers = savedInstanceState.getIntegerArrayList(ARRAY_KEY);
        } else {
            integers = new ArrayList<>();
            fillList(integers);
        }

        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_anim_fall_down);

        recyclerView.setLayoutAnimation(animation);
        recyclerView.setAdapter(new MyAdapter(this, integers));
        addBtn.setOnClickListener(this);
    }

    void fillList(List<Integer> toFill) {
        for (int i = 0; i < 100; i++) {
            toFill.add(i + 1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_button:
                String text = editText.getText().toString();
                if (text.isEmpty())
                    Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
                else {
                    try {
                        int number = Integer.parseInt(text);
                        ((MyAdapter) recyclerView.getAdapter()).getDataList().add(number);
                        int lastPosition = ((MyAdapter) recyclerView.getAdapter()).getDataList().size();
                        recyclerView.getAdapter().notifyItemInserted(lastPosition);
                        recyclerView.scrollToPosition(lastPosition - 1);
                        editText.setText("");
                        Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, "Wrong format", Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(ARRAY_KEY, new ArrayList<>(((MyAdapter) recyclerView.getAdapter()).getDataList()));
    }
}
