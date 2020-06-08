package com.example.todoapp.model.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.todoapp.R;
import com.example.todoapp.model.DBOpenHelper;

public class ReminderCursorAdapter extends CursorAdapter {
    public ReminderCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        //return LayoutInflater.from(context).inflate(R.layout.reminder_today_list_item, parent, false);;
        return LayoutInflater.from(context).inflate(R.layout.reminder_today_list_items, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        String noteText = cursor.getString(cursor.getColumnIndex(DBOpenHelper.NOTE_TEXT));
        TextView noteTextView = view.findViewById(R.id.reminderTextView);
        noteTextView.setText(noteText);

    }
}
