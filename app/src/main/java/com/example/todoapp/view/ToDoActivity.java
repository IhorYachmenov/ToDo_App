package com.example.todoapp.view;

import android.app.Dialog;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.PopupMenu;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.example.todoapp.R;

import com.example.todoapp.model.CategoryData;


import com.example.todoapp.model.NotesProvider;
import com.example.todoapp.model.VisibilityOfListNewTask;
import com.example.todoapp.model.adapters.ReminderCursorAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;


public class ToDoActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ReminderCursorAdapter cursorAdapter;
    private static final int RC_EDITOR = 111;

    FloatingActionButton fab;

    // Category activity buttons
    ConstraintLayout inboxCategory, workCategory, shoppingCategory, familyCategory, personalCategory;

    // Object for holding data for category activity
    CategoryData putData;

    // Main screen menu
    TextView dotButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_activity);

        dotButton = findViewById(R.id.app_bar_action);

        openDialog();

        openCategoryInboxDialogFragment();
        openCategoryWorkDialogFragment();
        openCategoryShoppingDialogFragment();
        openCategoryFamilyDialogFragment();
        openCategoryPersonalDialogFragment();

        dotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });

        initLoader();




    }


    public void openDialog() {

        fab = findViewById(R.id.fab_main);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

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


                ConstraintLayout task = (ConstraintLayout) dialog.findViewById(R.id.task_newtask);
                ConstraintLayout list = (ConstraintLayout) dialog.findViewById(R.id.list_newtask);
                task.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent task = new Intent(ToDoActivity.this, NewTaskActivity.class);
                        VisibilityOfListNewTask visibility = new VisibilityOfListNewTask();
                        visibility.setVisibility(0);
                        task.putExtra("V", visibility);
                        startActivityForResult(task, RC_EDITOR);
                    }
                });

                list.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent task = new Intent(ToDoActivity.this, NewTaskActivity.class);


                        VisibilityOfListNewTask visibility = new VisibilityOfListNewTask();
                        visibility.setVisibility(1);
                        task.putExtra("V", visibility);
                        startActivityForResult(task, RC_EDITOR);
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

    private void showPopupMenu(final View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.menu_todo_activity);

        popupMenu
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.log_out:
                                Toast.makeText(getApplicationContext(),
                                        getResources().getString(R.string.label_log_out_button_main_screen),
                                        Toast.LENGTH_SHORT).show();
                                logOut(v);
                                return true;
                            case R.id.delete:

                                deleteAllNotes();


                                return true;
                            default:
                                return false;
                        }
                    }
                });


        popupMenu.show();
    }

    public void logOut(View view) {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();
    }

    private void deleteAllNotes() {
        DialogInterface.OnClickListener dialogClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int button) {
                        if (button == DialogInterface.BUTTON_POSITIVE) {

                            // clear the database
                            getContentResolver().delete(NotesProvider.CONTENT_URI, null, null);
                            restartLoader();


                        }
                    }
                };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(getString(R.string.are_you_sure))
                .setPositiveButton(getString(android.R.string.yes), dialogClickListener)
                .setNegativeButton(getString(android.R.string.no), dialogClickListener)
                .show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_EDITOR) {
            if (resultCode == RESULT_OK) {
                restartLoader();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void restartLoader() {
        getLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, NotesProvider.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor data) {
        cursorAdapter.swapCursor(data);

        // View or hide the no notes msg
        TextView noNotesLinearLayout = findViewById(R.id.noNotesMsgTextView);
        if (cursorAdapter.isEmpty()) {  // there's no notes in the database to view
            noNotesLinearLayout.setVisibility(View.VISIBLE);
        }
        else {   // there's indeed notes in the database to view
            noNotesLinearLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }

    public void initLoader() {
        cursorAdapter = new ReminderCursorAdapter(this, null, 0);  // used to add more customization the list items
        ListView notesListView = findViewById(R.id.reminderListView);
        notesListView.setAdapter(cursorAdapter);

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TextView marker = findViewById(R.id.today_marker);
//                marker.setBackground(getResources().getDrawable(R.drawable.ic_today_marked));
            }
        });

        getLoaderManager().initLoader(0, null,  this);
    }
}
