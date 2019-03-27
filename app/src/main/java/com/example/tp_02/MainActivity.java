package com.example.tp_02;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnNumberClickListener {

    ArrayList<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null && savedInstanceState.containsKey(MyFragment.ARRAY_KEY)) {
            list = savedInstanceState.getIntegerArrayList(MyFragment.ARRAY_KEY);
        }

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.my_fragment_container);

        if (fragment == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.my_fragment_container, new MyFragment());
            transaction.commit();
        }
    }

    /**
     * this method works when you click on textView in recyclerView
     * it gets 3 parameters:
     * -number to show
     * -TextColorId
     * -BackGround color id
     */
    /*
        method replace list fragment with number fragment
     */
    @Override
    public void onNumberClick(int number, int textColorId, int bgColorId) {
        NumberFragment fragment = new NumberFragment();
        fragment.setNumberConfig(number, textColorId, bgColorId);

        getSupportFragmentManager().beginTransaction().
                replace(R.id.my_fragment_container, fragment).
                addToBackStack(null).
                commit();
    }

}
