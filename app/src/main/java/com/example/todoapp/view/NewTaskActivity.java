package com.example.todoapp.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.todoapp.R;

public class NewTaskActivity extends AppCompatActivity {

    TimePicker time;

    // Bottom bar button

    TextView setData;
    TextView setTime;
    RelativeLayout setCategory;

    // Bottom bar layout

    ConstraintLayout layoutData;
    ConstraintLayout layoutTime;
    ConstraintLayout layoutCategory;

    CalendarView calendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task_activity);

        time = findViewById(R.id.time);
        time.setIs24HourView(true);
        calendar = findViewById(R.id.calendarView);
        calendar.setMinDate(System.currentTimeMillis());



        bottomButtons();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent mainScreen = new Intent(NewTaskActivity.this, ToDoActivity.class);
        startActivity(mainScreen);
    }

    public void bottomButtons() {

        layoutData = findViewById(R.id.bottom_calendar);
        layoutTime = findViewById(R.id.bottom_time);
        layoutCategory = findViewById(R.id.layout_new_task_bottom_list_category);

        setData = findViewById(R.id.calendar);
        setTime = findViewById(R.id.alarm);
        setCategory = findViewById(R.id.check_box_layout);

        setData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setData.getVisibility() == View.GONE) {
                    setData.setBackgroundResource(R.drawable.ic_calendar_off);
                    layoutData.setVisibility(View.GONE);
                } else {
                    setData.setBackgroundResource(R.drawable.ic_calendar_on);
                    layoutData.setVisibility(View.VISIBLE);
                    layoutTime.setVisibility(View.GONE);
                    layoutCategory.setVisibility(View.GONE);
                }
            }
        });

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setTime.getVisibility() == View.GONE) {
                    setTime.setBackgroundResource(R.drawable.ic_alarm_off);
                    layoutTime.setVisibility(View.GONE);
                } else {
                    setTime.setBackgroundResource(R.drawable.ic_alarm_on);
                    layoutTime.setVisibility(View.VISIBLE);
                    layoutData.setVisibility(View.GONE);
                    layoutCategory.setVisibility(View.GONE);
                }
            }
        });

        setCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setCategory.getVisibility() == View.GONE) {
                    layoutCategory.setVisibility(View.GONE);
                } else {
                    layoutCategory.setVisibility(View.VISIBLE);
                    layoutData.setVisibility(View.GONE);
                    layoutTime.setVisibility(View.GONE);
                }
            }
        });

    }
}
