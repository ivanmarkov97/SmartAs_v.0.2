package com.example.ivan.smartas.AddActivities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ivan.smartas.R;

public class ShowPhotosActivity extends AppCompatActivity {

    ViewPager viewPager;
    ShowPhotoAdapter showPhotoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photos);

        String[] imageString = null;
        int pos = 0;

        Intent intent = getIntent();
        if(intent.getStringArrayExtra("file_names") != null){
            imageString = intent.getStringArrayExtra("file_names");
        }

        pos = intent.getIntExtra("image_pos", 0);

        Toast.makeText(getApplicationContext(), "len == " + imageString.length, Toast.LENGTH_LONG).show();
        viewPager = (ViewPager) findViewById(R.id.order_pre_show_view_pager);
        showPhotoAdapter = new ShowPhotoAdapter(ShowPhotosActivity.this, imageString);
        viewPager.setAdapter(showPhotoAdapter);
        viewPager.setCurrentItem(pos);
    }
}
