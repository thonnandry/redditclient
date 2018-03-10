package com.thonysupersonic.readsmsapp;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by anthony on 2/23/18.
 */

public class SmsCursorAdapter extends CursorAdapter {
    public SmsCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context,c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return View.inflate(context, R.layout.row_sms, null);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView) view.findViewById(R.id.sms_origin)).setText(cursor.getString(cursor.getColumnIndexOrThrow(MainActivity.SmsColumns.ADDRESS)));
        ((TextView) view.findViewById(R.id.sms_body)).setText(cursor.getString(cursor.getColumnIndexOrThrow(MainActivity.SmsColumns.BODY)));
        ((TextView) view.findViewById(R.id.sms_date)).setText(cursor.getString(cursor.getColumnIndexOrThrow(MainActivity.SmsColumns.DATE)));
    }
}
