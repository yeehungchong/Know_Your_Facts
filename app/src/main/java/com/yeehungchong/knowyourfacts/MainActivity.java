package com.yeehungchong.knowyourfacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnRead;
    ViewPager vPager;
    ArrayList<Fragment> al;
    MyFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = findViewById(R.id.btnRead);
        vPager = findViewById(R.id.viewpager);

        FragmentManager fm = getSupportFragmentManager();
        al = new ArrayList<Fragment>();
        al.add(new Frag1());
        al.add(new Frag2());
        adapter = new MyFragmentPagerAdapter(fm, al);
        vPager.setAdapter(adapter);

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPause() {
        super.onPause();
        int currVPager = vPager.getCurrentItem();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putInt("currVPager", currVPager);
        prefEdit.commit();
        Log.d("SAVE", String.valueOf(currVPager));
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int currVPager = prefs.getInt("currVPager", 0);
        vPager.setCurrentItem(currVPager, true);

        Log.d("RESTORE", String.valueOf(currVPager));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_previous:
                previous();
                return true;
            case R.id.action_random:
                return true;
            case R.id.action_next:
                next();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void previous() {
        if (vPager.getCurrentItem() > 0){
            int previousPage = vPager.getCurrentItem() - 1;
            vPager.setCurrentItem(previousPage, true);
        }
    }

    public void next() {
        int max = vPager.getChildCount();
        if (vPager.getCurrentItem() < max-1){
            int nextPage = vPager.getCurrentItem() + 1;
            vPager.setCurrentItem(nextPage, true);
        }
    }





}