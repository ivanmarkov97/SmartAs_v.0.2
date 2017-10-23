package com.example.ivan.smartas.AddActivities;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ivan.smartas.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

public class AddDate extends AppCompatActivity {

    Toolbar toolbar;
    MaterialCalendarView materialCalendarView;
    Button next;
    TextView time;
    java.util.Calendar calendar = java.util.Calendar.getInstance();
    String[] months = {"Янв", "Фев" , "Март" , "Апр" , "Май" , "Июнь" , "Июль" , "Авг" , "Сент" , "Окт" , "Ноя" , "Дек" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_date);

        toolbar = (Toolbar) findViewById(R.id.add_date_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Выберите день сдачи");

        next = (Button) findViewById(R.id.add_date_next_btn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddTime.class));
            }
        });

        materialCalendarView = (MaterialCalendarView) findViewById(R.id.add_date_calendarView);
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(2017, 0, 0))
                .setMaximumDate(CalendarDay.from(2020, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddDate.this, android.R.style.Theme_DeviceDefault_Light_Dialog ,onTimeSetListener, calendar.get(java.util.Calendar.HOUR_OF_DAY), calendar.get(java.util.Calendar.MINUTE), true);
                LayoutInflater layoutInflater = getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.time_picker_title, null);
                ((TextView) view.findViewById(R.id.time_picker_title_title)).
                        setText("" + date.getDay() + " " + months[date.getMonth()] + " " + date.getYear());
                timePickerDialog.setCustomTitle(view);
                timePickerDialog.show();
                Toast.makeText(getApplicationContext(), date.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            //time.setText(hourOfDay + ":" + minute);
            //Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), AddDescription.class));
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return true;
    }
}
