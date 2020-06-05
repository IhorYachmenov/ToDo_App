package com.example.todoapp.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.todoapp.R;

public class NewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent mainScreen = new Intent(NewTaskActivity.this, ToDoActivity.class);
        startActivity(mainScreen);
    }
}
