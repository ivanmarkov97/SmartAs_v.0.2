package com.example.ivan.smartas.AddActivities;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ivan.smartas.R;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Ivan on 24.10.2017.
 */

public class ShowPhotoAdapter extends PagerAdapter {

    String[] images = null;
    LayoutInflater inflater = null;
    Activity activity;

    public ShowPhotoAdapter(Activity activity, String[] images) {
        this.activity = activity;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.image_preshow_slide_item, container, false);
        ImageView imageView;
        imageView = (ImageView) itemView.findViewById(R.id.pre_show_order_view_pager_item);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        imageView.setMinimumHeight(height);
        imageView.setMinimumWidth(width);
        imageView.setImageURI(Uri.parse(images[position]));
        PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(imageView);
        photoViewAttacher.update();
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}
