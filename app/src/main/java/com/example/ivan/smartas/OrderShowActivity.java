package com.example.ivan.smartas;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.R.attr.fraction;
import static android.R.attr.typeface;

public class OrderShowActivity extends AppCompatActivity {

    TextView orderDescription;
    TextView subjectName;
    TextView orderType;
    TextView orderCost;
    TextView orderDate;
    TextView orderLimit;
    Button buttonTake;
    static final String TAG = "myLogs";
    static final int PAGE_COUNT = 10;
    ViewPager pager;
    PagerAdapter pagerAdapter;

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
        buttonTake.setText("Взять заказ");
        buttonTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.take_order){
                    int task_id = intent.getIntExtra("task_id", 0);
                    YourOrderParamsDialog yourOrderParamsDialog = new YourOrderParamsDialog();
                    yourOrderParamsDialog.setTaskId(task_id);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    yourOrderParamsDialog.show(fragmentManager, "your_order_params");
                }
            }
        });

        //orderDescription.setText("Давно выяснено, что при оценке дизайна и композиции читаемый текст мешает сосредоточиться. Lorem Ipsum используют потому, что тот обеспечивает более или менее стандартное заполнение шаблона, а также реальное распределение букв и пробелов в абзацах, которое не получается при простой дубликации \"Здесь ваш текст.. Здесь ваш текст.. Здесь ваш текст..\" Многие программы электронной вёрстки и редакторы HTML используют Lorem Ipsum в качестве текста по умолчанию, так что поиск по ключевым словам \"lorem ipsum\" сразу показывает, как много веб-страниц всё ещё дожидаются своего настоящего рождения. За прошедшие годы текст Lorem Ipsum получил много версий. Некоторые версии появились по ошибке, некоторые - намеренно (например, юмористические варианты). + Давно выяснено, что при оценке дизайна и композиции читаемый текст мешает сосредоточиться. Lorem Ipsum используют потому, что тот обеспечивает более или менее стандартное заполнение шаблона, а также реальное распределение букв и пробелов в абзацах, которое не получается при простой дубликации \"Здесь ваш текст.. Здесь ваш текст.. Здесь ваш текст..\" Многие программы электронной вёрстки и редакторы HTML используют Lorem Ipsum в качестве текста по умолчанию, так что поиск по ключевым словам \"lorem ipsum\" сразу показывает, как много веб-страниц всё ещё дожидаются своего настоящего рождения. За прошедшие годы текст Lorem Ipsum получил много версий. Некоторые версии появились по ошибке, некоторые - намеренно (например, юмористические варианты).");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Все категории");

        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected, position = " + position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(R.anim.right_in,R.anim.left_out);
        }
        return true;
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

    }
}
