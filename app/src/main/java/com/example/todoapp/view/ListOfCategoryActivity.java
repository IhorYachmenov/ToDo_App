package com.example.todoapp.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.todoapp.R;
import com.example.todoapp.model.CategoryData;
import com.example.todoapp.model.VisibilityOfListNewTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListOfCategoryActivity extends AppCompatActivity {

    CategoryData data;

    FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_category_data);

        openDialog();

        // Set data from intent
        Intent intent = getIntent();
        TextView setLabel = (TextView) findViewById(R.id.label_name);
        ConstraintLayout setColor = (ConstraintLayout) findViewById(R.id.task_list_background);
        data = (CategoryData)intent.getSerializableExtra("Class Data");
        setColor.setBackgroundColor(data.getBackgroundColor());
        setLabel.setText(data.getLabelName());


    }

    public void openDialog() {

        fab = findViewById(R.id.fab_category_activity);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(ListOfCategoryActivity.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.fragment_floating_button);
                ColorDrawable dialogColor = new ColorDrawable(Color.WHITE);
                dialogColor.setAlpha(200);
                dialog.getWindow().setBackgroundDrawable(dialogColor);


                FloatingActionButton dialogButton1 = (FloatingActionButton) dialog.findViewById(R.id.fab_fragment);
                dialogButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                ConstraintLayout task = (ConstraintLayout) dialog.findViewById(R.id.task_newtask);
                ConstraintLayout list = (ConstraintLayout) dialog.findViewById(R.id.list_newtask);
                task.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent task = new Intent(ListOfCategoryActivity.this, NewTaskActivity.class);
                        VisibilityOfListNewTask visibility = new VisibilityOfListNewTask();
                        visibility.setVisibility(0);
                        task.putExtra("V", visibility);
                        startActivity(task);
                    }
                });

                list.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent task = new Intent(ListOfCategoryActivity.this, NewTaskActivity.class);
                        VisibilityOfListNewTask visibility = new VisibilityOfListNewTask();
                        visibility.setVisibility(1);
                        task.putExtra("V", visibility);
                        startActivity(task);
                    }
                });

                dialog.show();

            }
        });

    }

}
