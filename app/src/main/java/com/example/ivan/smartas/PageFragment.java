package com.example.ivan.smartas;

import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Ivan on 26.04.2017.
 */

public class PageFragment extends Fragment {
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    ImageView imageView;
    int pageNumber;
    File file = null;
    String string = null;
    Bitmap bitmap = null;

    static public PageFragment newInstance(int page) {
        PageFragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    public void setFile(File file){
        this.file = file;
    }

    public void setString(String s){this.string = s;}

    public void setBitmap(Bitmap bitmap){this.bitmap = bitmap;}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, null);
        if(bitmap != null){
            imageView = (ImageView) view.findViewById(R.id.order_image);
            //imageView.setImageURI(Uri.fromFile(new File(string)));
            imageView.setImageBitmap(bitmap);
        }
        return view;
    }
}
