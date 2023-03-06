package com.example.tugasfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private boolean isFragmentDisplayed = false;

    private View mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = findViewById(R.id.main_fragment);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFragmentDisplayed) {
                    displayFragment();
                } else {
                    closeFragment();
                }
            }
        });
    }

    public void displayFragment() {
        DetailFragment detailFragment = DetailFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_detail, detailFragment).addToBackStack(null)
                .commit();
        button.setText("< Back");
        isFragmentDisplayed = true;

        mainFragment.setVisibility(View.INVISIBLE);
    }

    public void closeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DetailFragment detailFragment = (DetailFragment) fragmentManager
                .findFragmentById(R.id.fragment_detail);

        if (detailFragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(detailFragment).commit();
        }
        button.setText("Show more");
        isFragmentDisplayed = false;

        mainFragment.setVisibility(View.VISIBLE);
    }
}