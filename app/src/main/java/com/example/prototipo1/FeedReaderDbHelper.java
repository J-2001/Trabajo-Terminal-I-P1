package com.example.prototipo1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FeedReaderDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
            FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FeedReaderContract.FeedEntry.COLUMN_CHARGE_COUNTER + " INTEGER NOT NULL," +
            FeedReaderContract.FeedEntry.COLUMN_CURRENT_NOW + " INTEGER NOT NULL," +
            FeedReaderContract.FeedEntry.COLUMN_CAPACITY + " INTEGER NOT NULL," +
            FeedReaderContract.FeedEntry.COLUMN_STATUS + " INTEGER NOT NULL," +
            FeedReaderContract.FeedEntry.COLUMN_TIMESTAMP + " TEXT NOT NULL)";
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}
