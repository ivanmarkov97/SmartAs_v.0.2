package com.example.ivan.smartas.ProfileFragment.profile_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ivan.smartas.R;

/**
 * Created by Ivan on 26.10.2017.
 */

public class Profile_v2_FragmentRating extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_v2_rating_layout, container, false);
        return view;
    }
}
