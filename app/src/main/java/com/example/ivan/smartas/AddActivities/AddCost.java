package com.example.ivan.smartas.AddActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ivan.smartas.R;

public class AddCost extends AppCompatActivity {

    Toolbar toolbar;
    EditText input;
    Button add;
    ImageButton next;
    TextView balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cost);

        toolbar = (Toolbar) findViewById(R.id.add_cost_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Установите цену");

        add = (Button) findViewById(R.id.add_cost_add_btn);
        next = (ImageButton) findViewById(R.id.add_cost_next_btn);
        balance = (TextView) findViewById(R.id.add_cost_balance);
        input = (EditText) findViewById(R.id.add_cost_input_cost);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddDate.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return true;
    }
}
