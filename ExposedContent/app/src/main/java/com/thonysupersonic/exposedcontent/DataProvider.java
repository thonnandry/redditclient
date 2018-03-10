package com.thonysupersonic.exposedcontent;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;

/**
 * Created by anthony on 2/24/18.
 */

public class DataProvider extends ContentProvider{

    public static final String AUTHORITY = "com.androidatc.exposedcontent.provider";
    private static final String DATABASE_NAME = "table.db";
    private static final String DATABASE_TABLE_NAME = "t1";
    private static final int DATABASE_VERSION = 1;
    private static final UriMatcher sUriMatcher;
    public static final int DATUM = 1;
    public static final int DATUM_ID = 2;
    private static HashMap<String, String> projMap;

    static {

        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, DATABASE_TABLE_NAME, DATUM);
        sUriMatcher.addURI(AUTHORITY, DATABASE_TABLE_NAME + "/#", DATUM_ID);
        projMap = new HashMap<String, String>();
        projMap.put(Constants.ID, Constants.ID);
        projMap.put(Constants.TEXT, Constants.TEXT);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());

        return  true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(DATABASE_TABLE_NAME);
        qb.setProjectionMap(projMap);
        if(sUriMatcher.match(uri) != DATUM){
            if(sUriMatcher.match(uri) == DATUM_ID){
                s = s + " _id=" + uri.getLastPathSegment();
            }else{
                throw  new IllegalArgumentException("Unknown URI " + uri);
            }
        }

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor c = qb.query(database, strings, s, strings1,null, null, s1);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        if(sUriMatcher.match(uri) == DATUM){
            return  Constants.CONTENT_TYPE;
        }else{
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        if(sUriMatcher.match(uri) != DATUM){
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        ContentValues v;
        if(contentValues != null){
            v = new ContentValues(contentValues);
        }else{
            v = new ContentValues();
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long rId = db.insert(DATABASE_TABLE_NAME, Constants.TEXT, v);

        if(rId > 0){
            Uri ur = ContentUris.withAppendedId(Constants.URL, rId);
            getContext().getContentResolver().notifyChange(ur, null);
            return ur;
        }

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 1;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    private static  class DBHelper extends SQLiteOpenHelper{

        DBHelper(Context c){
            super(c, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE " + DATABASE_TABLE_NAME + " (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Constants.TEXT + " VARCHAR(20)"
                    +")");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }

    private DBHelper dbHelper;





}
