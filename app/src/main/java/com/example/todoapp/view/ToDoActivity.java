package com.example.todoapp.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.example.todoapp.R;
import com.example.todoapp.databinding.TodoActivityBinding;
import com.example.todoapp.viewmodel.ToDoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ToDoActivity extends AppCompatActivity {

    ToDoViewModel viewModel = new ToDoViewModel();

    FloatingActionButton fab;

    // Category activity buttons
    ConstraintLayout inboxCategory;
    ConstraintLayout workCategory;
    ConstraintLayout shoppingCategory;
    ConstraintLayout familyCategory;
    ConstraintLayout personalCategory;

    // Object for holding data for category activity
    CategoryData putData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TodoActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.todo_activity);
        binding.setViewModel(viewModel);
        viewModel.onCreate();

        openDialog();

        openCategoryInboxDialogFragment();
        openCategoryWorkDialogFragment();
        openCategoryShoppingDialogFragment();
        openCategoryFamilyDialogFragment();
        openCategoryPersonalDialogFragment();

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

        fab = findViewById(R.id.fab_main);
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

    public void  openCategoryInboxDialogFragment() {
        inboxCategory = findViewById(R.id.inbox_category);

        inboxCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                putData = new CategoryData(getResources().getString(R.string.inbox_label), getResources().getColor(R.color.inbox_label_color));

                Intent goToInboxCategory = new Intent(ToDoActivity.this, ListOfCategoryActivity.class);

                goToInboxCategory.putExtra("Class Data", putData);

                startActivity(goToInboxCategory);



            }
        });


    }

    public void  openCategoryWorkDialogFragment() {

        workCategory = (ConstraintLayout) findViewById(R.id.work_category);

        workCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                putData = new CategoryData(getResources().getString(R.string.work_label), getResources().getColor(R.color.work_label_color));

                Intent goToInboxCategory = new Intent(ToDoActivity.this, ListOfCategoryActivity.class);

                goToInboxCategory.putExtra("Class Data", putData);

                startActivity(goToInboxCategory);


            }
        });

    }

    public void  openCategoryShoppingDialogFragment() {

        shoppingCategory = (ConstraintLayout) findViewById(R.id.shopping_category);

        shoppingCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                putData = new CategoryData(getResources().getString(R.string.shopping_label), getResources().getColor(R.color.shopping_label_color));

                Intent goToInboxCategory = new Intent(ToDoActivity.this, ListOfCategoryActivity.class);

                goToInboxCategory.putExtra("Class Data", putData);

                startActivity(goToInboxCategory);


            }
        });

    }

    public void  openCategoryFamilyDialogFragment() {

        familyCategory = (ConstraintLayout) findViewById(R.id.family_category);

        familyCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                putData = new CategoryData(getResources().getString(R.string.family_label), getResources().getColor(R.color.family_label_color));

                Intent goToInboxCategory = new Intent(ToDoActivity.this, ListOfCategoryActivity.class);

                goToInboxCategory.putExtra("Class Data", putData);

                startActivity(goToInboxCategory);


            }
        });

    }

    public void  openCategoryPersonalDialogFragment() {

        personalCategory = (ConstraintLayout) findViewById(R.id.personal_category);

        personalCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                putData = new CategoryData(getResources().getString(R.string.personal_label), getResources().getColor(R.color.personal_label_color));

                Intent goToInboxCategory = new Intent(ToDoActivity.this, ListOfCategoryActivity.class);

                goToInboxCategory.putExtra("Class Data", putData);

                startActivity(goToInboxCategory);


            }
        });

    }


    @Override
    public void onBackPressed() {


    }




}
