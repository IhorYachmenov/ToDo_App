package com.example.todoapp.view;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.todoapp.R;
import com.example.todoapp.model.DBOpenHelper;
import com.example.todoapp.model.NewTaskCategory;

import com.example.todoapp.model.NotesProvider;
import com.example.todoapp.model.VisibilityOfListNewTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    // Checked states
    TextView inboxChecker, workChecker, shoppingChecker, personalChecker, familyChecker;
    ConstraintLayout inboxCategory, workCategory, shoppingCategory, personalCategory, familyCategory;
    NewTaskCategory isChecked;
    TextView labelCheckedCategory, colorCheckedCategory;

    // Cancel Button

    // TextChanger
    EditText editor;

    private String action;
    private String noteFilter;
    private String oldText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task_activity);

        time = findViewById(R.id.time);
        time.setIs24HourView(true);
        calendar = findViewById(R.id.calendarView);
        calendar.setMinDate(System.currentTimeMillis());

        editor = findViewById(R.id.reminderEditText);




        bottomButtons();


        checkCategory();

        getIntentDataFromFAB();

        cancelButton();
        doneButton();

        Intent intent = getIntent();

        Uri uri = intent.getParcelableExtra(NotesProvider.CONTENT_ITEM_TYPE);
        if (uri == null) {  // opened for new note

            action = Intent.ACTION_INSERT;
            //setTitle(getString(R.string.editor_title_insert_new_note));

        } else {   // opened for edit note

            action = Intent.ACTION_EDIT;
           // setTitle(getString(R.string.editor_title_edit_note));

            noteFilter = DBOpenHelper.NOTE_ID + "=" + uri.getLastPathSegment();
            Cursor cursor = getContentResolver().query(uri, DBOpenHelper.ALL_COLUMNS, noteFilter, null, null);
            if (cursor.moveToFirst()) {
                // cursor is not empty
                oldText = cursor.getString(cursor.getColumnIndex(DBOpenHelper.NOTE_TEXT));
                String lastChanged = cursor.getString(cursor.getColumnIndex(DBOpenHelper.NOTE_LAST_CHANGED));

                // display the note text
                editor.setText(oldText);





            }
            cursor.close();
        }









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

    public void checkCategory() {
        isChecked = new NewTaskCategory();

        inboxChecker = findViewById(R.id.new_task_inbox_checked);
        workChecker = findViewById(R.id.new_task_work_checked);
        shoppingChecker = findViewById(R.id.new_task_shopping_checked);
        familyChecker = findViewById(R.id.new_task_family_checked);
        personalChecker = findViewById(R.id.new_task_personal_checked);

        inboxCategory = findViewById(R.id.new_task_inbox_category);
        workCategory = findViewById(R.id.new_task_work_category);
        shoppingCategory = findViewById(R.id.new_task_shopping_category);
        familyCategory = findViewById(R.id.new_task_family_category);
        personalCategory = findViewById(R.id.new_task_personal_category);

        labelCheckedCategory = findViewById(R.id.bottom_bar_label_category);
        colorCheckedCategory = findViewById(R.id.bottom_bar_circle_category);


        inboxCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inboxCategory.getVisibility() == View.VISIBLE) {
                    if (isChecked.getCheckedStatus() != true) {
                        isChecked.setCheckedStatus(true);

                        isChecked.setInboxStatus(true);
                        isChecked.setWorkStatus(false);
                        isChecked.setShoppingStatus(false);
                        isChecked.setFamilyStatus(false);
                        isChecked.setPersonalStatus(false);

                        inboxChecker.setVisibility(View.VISIBLE);
                        workChecker.setVisibility(View.GONE);
                        shoppingChecker.setVisibility(View.GONE);
                        familyChecker.setVisibility(View.GONE);
                        personalChecker.setVisibility(View.GONE);
                        isChecked.setCheckedStatus(false);

                        labelCheckedCategory.setText(R.string.inbox_label);
                        labelCheckedCategory.setTextColor(getResources().getColor(R.color.blue));
                        colorCheckedCategory.setBackground(getResources().getDrawable(R.drawable.shape_circle_inbox_category));

                    }

                }
            }
        });

        workCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(workCategory.getVisibility() == View.VISIBLE) {
                    if (isChecked.getCheckedStatus() != true) {
                        isChecked.setCheckedStatus(true);

                        isChecked.setInboxStatus(false);
                        isChecked.setWorkStatus(true);
                        isChecked.setShoppingStatus(false);
                        isChecked.setFamilyStatus(false);
                        isChecked.setPersonalStatus(false);

                        inboxChecker.setVisibility(View.GONE);
                        workChecker.setVisibility(View.VISIBLE);
                        shoppingChecker.setVisibility(View.GONE);
                        familyChecker.setVisibility(View.GONE);
                        personalChecker.setVisibility(View.GONE);
                        isChecked.setCheckedStatus(false);

                        labelCheckedCategory.setText(R.string.work_label);
                        labelCheckedCategory.setTextColor(getResources().getColor(R.color.blue));
                        colorCheckedCategory.setBackground(getResources().getDrawable(R.drawable.shape_circle_work_category));
                    }

                }
            }
        });

        shoppingCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shoppingCategory.getVisibility() == View.VISIBLE) {
                    if (isChecked.getCheckedStatus() != true) {
                        isChecked.setCheckedStatus(true);

                        isChecked.setInboxStatus(false);
                        isChecked.setWorkStatus(false);
                        isChecked.setShoppingStatus(true);
                        isChecked.setFamilyStatus(false);
                        isChecked.setPersonalStatus(false);

                        inboxChecker.setVisibility(View.GONE);
                        workChecker.setVisibility(View.GONE);
                        shoppingChecker.setVisibility(View.VISIBLE);
                        familyChecker.setVisibility(View.GONE);
                        personalChecker.setVisibility(View.GONE);
                        isChecked.setCheckedStatus(false);

                        labelCheckedCategory.setText(R.string.shopping_label);
                        labelCheckedCategory.setTextColor(getResources().getColor(R.color.blue));
                        colorCheckedCategory.setBackground(getResources().getDrawable(R.drawable.shape_circle_shopping_category));
                    }

                }
            }
        });

        familyCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(familyCategory.getVisibility() == View.VISIBLE) {
                    if (isChecked.getCheckedStatus() != true) {
                        isChecked.setCheckedStatus(true);

                        isChecked.setInboxStatus(false);
                        isChecked.setWorkStatus(false);
                        isChecked.setShoppingStatus(false);
                        isChecked.setFamilyStatus(true);
                        isChecked.setPersonalStatus(false);

                        inboxChecker.setVisibility(View.GONE);
                        workChecker.setVisibility(View.GONE);
                        shoppingChecker.setVisibility(View.GONE);
                        familyChecker.setVisibility(View.VISIBLE);
                        personalChecker.setVisibility(View.GONE);
                        isChecked.setCheckedStatus(false);

                        labelCheckedCategory.setText(R.string.family_label);
                        labelCheckedCategory.setTextColor(getResources().getColor(R.color.blue));
                        colorCheckedCategory.setBackground(getResources().getDrawable(R.drawable.shape_circle_family_category));
                    }

                }
            }
        });

        personalCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(personalCategory.getVisibility() == View.VISIBLE) {
                    if (isChecked.getCheckedStatus() != true) {
                        isChecked.setCheckedStatus(true);

                        isChecked.setInboxStatus(false);
                        isChecked.setWorkStatus(false);
                        isChecked.setShoppingStatus(false);
                        isChecked.setFamilyStatus(false);
                        isChecked.setPersonalStatus(true);

                        inboxChecker.setVisibility(View.GONE);
                        workChecker.setVisibility(View.GONE);
                        shoppingChecker.setVisibility(View.GONE);
                        familyChecker.setVisibility(View.GONE);
                        personalChecker.setVisibility(View.VISIBLE);
                        isChecked.setCheckedStatus(false);

                        labelCheckedCategory.setText(R.string.personal_label);
                        labelCheckedCategory.setTextColor(getResources().getColor(R.color.blue));
                        colorCheckedCategory.setBackground(getResources().getDrawable(R.drawable.shape_circle_personal_category));
                    }

                }
            }
        });




    }

    public void getIntentDataFromFAB() {
        Intent intent = getIntent();
        VisibilityOfListNewTask data = (VisibilityOfListNewTask)intent.getSerializableExtra("V");
        layoutCategory = findViewById(R.id.layout_new_task_bottom_list_category);
        if (data.isVisibility() == 1) {
            layoutCategory.setVisibility(View.VISIBLE);
        } else {
            layoutCategory.setVisibility(View.GONE);
        }
    }

    public void cancelButton() {
        TextView cancel = findViewById(R.id.new_task_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(NewTaskActivity.this, ToDoActivity.class);
                startActivity(cancel);
            }
        });
    }

    public void doneButton() {
        TextView cancel = findViewById(R.id.new_task_done);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(NewTaskActivity.this, ToDoActivity.class);
                finishEditing();
                startActivity(cancel);
            }
        });
    }

    private void finishEditing() {
        String noteText = editor.getText().toString().trim(); // trim here removes any leading or following white spaces.

        switch (action) {
            case Intent.ACTION_INSERT:
                if (noteText.length() == 0) {
                    setResult(RESULT_CANCELED);
                } else {
                    insertNote(noteText);
                    setResult(RESULT_OK);
                }
                break;

        }
        finish();
    }

    private void insertNote(String noteText) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.NOTE_TEXT, noteText);
        getContentResolver().insert(NotesProvider.CONTENT_URI, values);
    }

    private void deleteNote() {
        getContentResolver().delete(NotesProvider.CONTENT_URI, noteFilter, null);
        Toast.makeText(this, "Notes was deleted", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    private void updateNote(String newNoteText) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.NOTE_TEXT, newNoteText);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("en"));
        Date date = new Date();
        values.put(DBOpenHelper.NOTE_LAST_CHANGED, dateFormat.format(date));
        getContentResolver().update(NotesProvider.CONTENT_URI, values, noteFilter, null);
        Toast.makeText(this, "Notes Was updated", Toast.LENGTH_SHORT).show();
    }


}
