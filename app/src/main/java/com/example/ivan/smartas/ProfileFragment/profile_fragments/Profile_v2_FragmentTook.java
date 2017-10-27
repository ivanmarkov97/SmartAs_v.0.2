package com.example.ivan.smartas.ProfileFragment.profile_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivan.smartas.OrderShowActivity;
import com.example.ivan.smartas.R;
import com.example.ivan.smartas.RecyclerViewListOrder.Order;
import com.example.ivan.smartas.RecyclerViewListOrder.OrderAdapter;

import java.util.ArrayList;

/**
 * Created by Ivan on 27.10.2017.
 */

public class Profile_v2_FragmentTook extends Fragment {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private ArrayList<Order> orders = new ArrayList<>();
    LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_v2_took, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.profile_v2_took_list);
        /*recyclerView = (RecyclerView) view.findViewById(R.id.profile_v2_took_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        for(int i = 0; i < 3; i++){
            orders.add(new Order(
                    i,
                    "subject",
                    "type",
                    "create_date",
                    "deadlineDate",
                    100,
                    "description"
            ));
        }
        orderAdapter = new OrderAdapter(getContext());
        orderAdapter.setOrders(orders);
        recyclerView.setAdapter(orderAdapter);*/
        orderAdapter = new OrderAdapter(getContext());
        orderAdapter.setOrders(orders);
        for(int i = 0; i < 2; i++){
            View v = LayoutInflater.from(getContext()).inflate(R.layout.order_item, linearLayout, false);
            orders.add(new Order(
                    i,
                    "subject",
                    "type",
                    "1" + i + "-2-2017",
                    "",
                    100*(i + 1),
                    "description"
            ));
            ((TextView)v.findViewById(R.id.subject_name)).setText(orders.get(i).getSubject());
            ((TextView)v.findViewById(R.id.order_type)).setText(orders.get(i).getType());
            ((TextView)v.findViewById(R.id.order_cost)).setText(String.valueOf(orders.get(i).getCost()) + " P");
            ((TextView)v.findViewById(R.id.order_date)).setText(orders.get(i).getCreate_date());
            ((TextView)v.findViewById(R.id.order_limit)).setText(orders.get(i).getEnd_date());
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int j = 0; j < linearLayout.getChildCount(); j++){
                        if(v.equals(linearLayout.getChildAt(j))){
                            Toast.makeText(getContext(), "cliked " + j, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(v.getContext(), OrderShowActivity.class);
                            intent.putExtra("task_id", orders.get(j).getId());
                            intent.putExtra("task_name", orders.get(j).getSubject());
                            intent.putExtra("task_type", orders.get(j).getType());
                            intent.putExtra("task_description", orders.get(j).getDescription());
                            intent.putExtra("task_cost", orders.get(j).getCost());
                            intent.putExtra("task_date", orders.get(j).getCreate_date());
                            intent.putExtra("task_limit", orders.get(j).getEnd_date());
                            v.getContext().startActivity(intent);
                        }
                    }
                }
            });
            linearLayout.addView(v);
        }
        Toast.makeText(getContext(), "SIZE == " + orderAdapter.getItemCount(), Toast.LENGTH_SHORT).show();
        return view;
    }

}
