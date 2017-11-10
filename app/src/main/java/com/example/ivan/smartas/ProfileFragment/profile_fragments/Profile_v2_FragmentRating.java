package com.example.ivan.smartas.ProfileFragment.profile_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.ivan.smartas.R;

/**
 * Created by Ivan on 26.10.2017.
 */

public class Profile_v2_FragmentRating extends Fragment {

    LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_v2_rating_layout, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.profile_v2_comments);
        for(int i = 0; i < 5; i++){
            View comment = LayoutInflater.from(getContext()).inflate(R.layout.comment_layout, linearLayout, false);
            linearLayout.addView(comment);
        }
        return view;
    }
}
