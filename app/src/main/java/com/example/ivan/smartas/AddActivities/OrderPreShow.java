package com.example.ivan.smartas.AddActivities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivan.smartas.PageFragment;
import com.example.ivan.smartas.R;
import java.io.IOException;

public class OrderPreShow extends AppCompatActivity {

    TextView orderDescription;
    TextView subjectName;
    TextView orderType;
    TextView orderCost;
    TextView orderDate;
    TextView orderLimit;
    Button buttonTake;
    ViewPager pager;
    PagerAdapter pagerAdapter;
    String[] imageString = null;
    Bitmap[] imageBitmap = null;
    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_show);

        final Intent intent = getIntent();

        subjectName = (TextView) findViewById(R.id.subject_name);
        orderType = (TextView) findViewById(R.id.order_type);
        orderCost = (TextView) findViewById(R.id.order_cost);
        orderDate = (TextView) findViewById(R.id.order_date);
        orderLimit = (TextView) findViewById(R.id.order_limit);
        orderDescription = (TextView) findViewById(R.id.order_description);

        subjectName.setText(intent.getStringExtra("task_name"));
        orderType.setText(intent.getStringExtra("task_type"));
        orderCost.setText(String.valueOf(intent.getIntExtra("task_cost", 0)));
        orderDate.setText(intent.getStringExtra("task_date"));
        orderLimit.setText(intent.getStringExtra("task_limit"));
        orderDescription.setText(intent.getStringExtra("task_description"));

        buttonTake = (Button) findViewById(R.id.take_order);
        buttonTake.setText("Выложить заказ");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Предпросмотр");

        Toast.makeText(getApplicationContext(), "SHOW", Toast.LENGTH_LONG).show();

        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = null;
        MyFragmentPagerAdapter myFragmentPagerAdapter = new OrderPreShow.MyFragmentPagerAdapter(getSupportFragmentManager());
        int photoCount = intent.getExtras().size() - 6;
        Log.d("MyTAG", "PhotoCount == " + String.valueOf(photoCount));

        if(photoCount > 0) {
            myFragmentPagerAdapter.setSize(photoCount);
            imageBitmap = new Bitmap[photoCount];
            imageString = new String[photoCount];
            for (int i = 0; i < photoCount; i++) {
                try {
                    imageString[i] = intent.getStringExtra("file_name" + i);
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(intent.getStringExtra("file_name" + i)));
                    imageBitmap[i] = bitmap;
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }
        pager.setAdapter(myFragmentPagerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return true;
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        private int size = 1;

        public void setSize(int size){
            this.size = size;
        }

        @Override
        public Fragment getItem(int position) {
            PageFragment pageFragment = PageFragment.newInstance(position);
            pageFragment.setPosition(position);
            pageFragment.setFragmentManager(getSupportFragmentManager());
            pageFragment.setImageString(imageString);
            if(imageBitmap != null) {
                Log.d("MyTAG", "Bitmap == " + imageBitmap[position].toString());
                pageFragment.setBitmap(imageBitmap[position]);
            }
            return pageFragment;
        }

        @Override
        public int getCount() {
            return size;
        }

    }
}
