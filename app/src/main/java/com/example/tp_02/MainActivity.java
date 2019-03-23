package com.example.tp_02;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnNumberClickListener, MyFragment.DataGetter {

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
     * -data that we need to save into Activity for restoring fragment with RecyclerView
     * -number to show and boolean if it is odd
     * i carry this boolean through the whole program because i don't want make isOdd method of Adapter public
     * and want hide logic in Adapter
     */
    /*
        method save adapters list and replace list fragment with number fragment
     */
    @Override
    public void onNumberClick(List<Integer> data, int number, boolean isOdd) {
        list = new ArrayList<>(data);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        NumberFragment fragment = new NumberFragment();
        fragment.setNumber(number, isOdd);
        transaction.setTransition(4);
        transaction.replace(R.id.my_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(MyFragment.ARRAY_KEY, list);
    }

    @Override
    public ArrayList<Integer> getSavedData() {
        return list;
    }

    /*
        return saved list in MyFragment.java in OnCreate
     */
}
