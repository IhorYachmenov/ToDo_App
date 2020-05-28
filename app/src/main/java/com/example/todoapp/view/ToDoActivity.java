package com.example.todoapp.view;

import android.os.Bundle;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import androidx.databinding.DataBindingUtil;

import com.example.todoapp.R;
import com.example.todoapp.databinding.TodoActivityBinding;
import com.example.todoapp.viewmodel.ToDoViewModel;


public class ToDoActivity extends AppCompatActivity {

    ToDoViewModel viewModel = new ToDoViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TodoActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.todo_activity);
        binding.setViewModel(viewModel);
        viewModel.onCreate();

        getSupportActionBar().hide();

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


}
