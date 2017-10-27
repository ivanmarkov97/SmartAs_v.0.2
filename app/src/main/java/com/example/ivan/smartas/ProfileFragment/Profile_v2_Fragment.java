package com.example.ivan.smartas.ProfileFragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.ivan.smartas.ProfileFragment.profile_fragments.Profile_v2_FragmentDoing;
import com.example.ivan.smartas.ProfileFragment.profile_fragments.Profile_v2_FragmentHistory;
import com.example.ivan.smartas.ProfileFragment.profile_fragments.Profile_v2_FragmentRating;
import com.example.ivan.smartas.ProfileFragment.profile_fragments.Profile_v2_FragmentTook;
import com.example.ivan.smartas.R;

import static android.R.color.white;

/**
 * Created by Ivan on 26.10.2017.
 */

public class Profile_v2_Fragment extends Fragment {

    TabLayout tabLayout;
    int[] iconsWhite = {R.drawable.white_1, R.drawable.white_2, R.drawable.white_3, R.drawable.white_4 };
    int[] iconsBlack = {R.drawable.black_1, R.drawable.black_2, R.drawable.black_3, R.drawable.black_4 };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_v2_layout, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.profile_v2_tablayout);
        for(int i = 0; i < 4; i++){
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setIcon(iconsWhite[i]);
            tabLayout.addTab(tab);
        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        tab.setIcon(iconsBlack[tab.getPosition()]);
                        Profile_v2_FragmentDoing fragmentDoing = new Profile_v2_FragmentDoing();
                        getFragmentManager().beginTransaction().replace(R.id.profile_v2_info, fragmentDoing).disallowAddToBackStack().commit();
                        break;
                    case 1:
                        tab.setIcon(iconsBlack[tab.getPosition()]);
                        Profile_v2_FragmentTook fragmentTook = new Profile_v2_FragmentTook();
                        getFragmentManager().beginTransaction().replace(R.id.profile_v2_info, fragmentTook).disallowAddToBackStack().commit();
                        break;
                    case 2:
                        tab.setIcon(iconsBlack[tab.getPosition()]);
                        Profile_v2_FragmentHistory fragmentHistory = new Profile_v2_FragmentHistory();
                        getFragmentManager().beginTransaction().replace(R.id.profile_v2_info, fragmentHistory).disallowAddToBackStack().commit();
                        break;
                    case 3:
                        tab.setIcon(iconsBlack[tab.getPosition()]);
                        Profile_v2_FragmentRating fragmentRating = new Profile_v2_FragmentRating();
                        getFragmentManager().beginTransaction().replace(R.id.profile_v2_info, fragmentRating).disallowAddToBackStack().commit();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setIcon(iconsWhite[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        TabLayout.Tab tab = tabLayout.getTabAt(3);
        tab.select();
        return view;
    }
}
