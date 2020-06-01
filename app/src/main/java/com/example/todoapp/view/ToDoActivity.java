package com.example.todoapp.view;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.example.todoapp.R;
import com.example.todoapp.databinding.TodoActivityBinding;
import com.example.todoapp.viewmodel.ToDoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ToDoActivity extends AppCompatActivity {

    ToDoViewModel viewModel = new ToDoViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TodoActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.todo_activity);
        binding.setViewModel(viewModel);
        viewModel.onCreate();

        openDialog();






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onCreate();
    }

    public void openDialog() {

        FloatingActionButton fab = findViewById(R.id.fab_main);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(ToDoActivity.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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

                dialog.show();

            }
        });

    }

}
