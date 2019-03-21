package com.example.tp_02;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void onNumberClick(List<Integer> data, int number, boolean isOdd) {
        list = new ArrayList<>(data);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        NumberFragment fragment = new NumberFragment();
        fragment.setNumber(number, isOdd);
        transaction.replace(R.id.my_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(MyFragment.ARRAY_KEY, list);
    }

    public ArrayList<Integer> getSavedData(){
        return list;
    }
}
