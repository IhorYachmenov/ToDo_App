package com.example.todoapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.todoapp.R;

public class ListOfCategoryActivity extends AppCompatActivity {

    CategoryData data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list_category);

        Intent intent = getIntent();

        TextView setLabel = (TextView) findViewById(R.id.label_name);
        ConstraintLayout setColor = (ConstraintLayout) findViewById(R.id.task_list_background);

        data = (CategoryData)intent.getSerializableExtra("Class Data");

        setColor.setBackgroundColor(data.getBackgroundColor());
        setLabel.setText(data.getLabelName());




    }


}
