package com.example.ivan.smartas;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ivan.smartas.AddActivities.AddCategory;
import com.example.ivan.smartas.AddFragment.AddFragment;
import com.example.ivan.smartas.ChatFragment.ChatFragment;
import com.example.ivan.smartas.ClockFragment.ClockFragment;
import com.example.ivan.smartas.HomeFragment.HomeFragment;
import com.example.ivan.smartas.ProfileFragment.ProfileFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener{

    ListView listViewAllOrders;
    Typeface typeface;
    Toolbar toolbar;
    TabLayout tabLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewAllOrders = (ListView) findViewById(R.id.all_orders);
        //bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_bottom);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_cat2);
        getSupportActionBar().setTitle("Все категории");

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).disallowAddToBackStack().commit();

        Drawable[] icons = {
            getResources().getDrawable(R.drawable.ic_item_bottom_home),
                getResources().getDrawable(R.drawable.ic_item_bottom_clock),
                getResources().getDrawable(R.drawable.ic_item_bottom_add),
                getResources().getDrawable(R.drawable.ic_item_bottom_chat),
                getResources().getDrawable(R.drawable.ic_item_bottom_profile),
        };

        tabLayout = (TabLayout) findViewById(R.id.navigation_bottom2);
        for(int i = 0; i < 5; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            //tab.setText(titles[i]);
            tab.setIcon(icons[i]);
            tabLayout.setTabTextColors(Color.BLACK, Color.BLACK);
            tabLayout.addTab(tab);
        }

        //bottomNavigationView.inflateMenu(R.menu.bottom_nav_items);

        TabLayout.Tab tab = tabLayout.getTabAt(0);
        tab.select();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 1:
                        ClockFragment clockFragment = new ClockFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, clockFragment).disallowAddToBackStack().commit();
                        //listViewAllOrders.setEnabled(false);
                        break;
                    case 2:
                        //AddFragment addFragment = new AddFragment();
                        //getSupportFragmentManager().beginTransaction().replace(R.id.container, addFragment).disallowAddToBackStack().commit();
                        //listViewAllOrders.setEnabled(false);
                        startActivity(new Intent(getApplication(), AddCategory.class));
                        break;
                    case 3:
                        ChatFragment chatFragment = new ChatFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, chatFragment).disallowAddToBackStack().commit();
                        //listViewAllOrders.setEnabled(false);
                        break;
                    case 4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).disallowAddToBackStack().commit();
                        //listViewAllOrders.setEnabled(false);
                        break;

                    case 0:
                        HomeFragment homeFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).disallowAddToBackStack().commit();
                        //listViewAllOrders.setEnabled(false);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 1:
                        ClockFragment clockFragment = new ClockFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, clockFragment).disallowAddToBackStack().commit();
                        //listViewAllOrders.setEnabled(false);
                        break;
                    case 2:
                        //AddFragment addFragment = new AddFragment();
                        //getSupportFragmentManager().beginTransaction().replace(R.id.container, addFragment).disallowAddToBackStack().commit();
                        //listViewAllOrders.setEnabled(false);
                        startActivity(new Intent(getApplication(), AddCategory.class));
                        break;
                    case 3:
                        ChatFragment chatFragment = new ChatFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, chatFragment).disallowAddToBackStack().commit();
                        //listViewAllOrders.setEnabled(false);
                        break;
                    case 4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).disallowAddToBackStack().commit();
                        //listViewAllOrders.setEnabled(false);
                        break;

                    case 0:
                        HomeFragment homeFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).disallowAddToBackStack().commit();
                        //listViewAllOrders.setEnabled(false);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            startActivity(new Intent(MainActivity.this, CategoryActivity.class));
            overridePendingTransition(R.anim.right_in,R.anim.left_out);
        }
        if(item.getItemId() == R.id.action_filter){
            startActivity(new Intent(MainActivity.this, FilterActivity.class));
            overridePendingTransition(R.anim.left_in,R.anim.right_out);
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(this, OrderShowActivity.class));
    }
}
