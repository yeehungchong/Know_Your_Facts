package com.yeehungchong.knowyourfacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnRead;
    ViewPager vPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = findViewById(R.id.btnRead);
        vPager = findViewById(R.id.viewpager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
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