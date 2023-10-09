package com.example.prototipo1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Prototype {

    private long id;
    private long chargeCounter;
    private long currentNow;
    private long capacity;
    private long status;
    private String timeStamp;

    public Prototype(long chargeCounter, long currentNow, long capacity, long status, String timeStamp) {
        this.id = 0;
        this.chargeCounter = chargeCounter;
        this.currentNow = currentNow;
        this.capacity = capacity;
        this.status = status;
        this.timeStamp = timeStamp;
    }

    public long getId() {
        return id;
    }

    public long getChargeCounter() {
        return chargeCounter;
    }

    public long getCurrentNow() {
        return currentNow;
    }

    public long getCapacity() {
        return capacity;
    }

    public long getStatus() {
        return status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setChargeCounter(long chargeCounter) {
        this.chargeCounter = chargeCounter;
    }

    public void setCurrentNow(long currentNow) {
        this.currentNow = currentNow;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_CHARGE_COUNTER, this.chargeCounter);
        values.put(FeedReaderContract.FeedEntry.COLUMN_CURRENT_NOW, this.currentNow);
        values.put(FeedReaderContract.FeedEntry.COLUMN_CAPACITY, this.capacity);
        values.put(FeedReaderContract.FeedEntry.COLUMN_STATUS, this.status);
        values.put(FeedReaderContract.FeedEntry.COLUMN_TIMESTAMP, this.timeStamp);
        return values;
    }

    public String getAll(SQLiteDatabase db) {
        String table = "";
        Cursor cursor = db.query(FeedReaderContract.FeedEntry.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            table += "ID: " + cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID)) +
                    ", Capacidad restante: " + cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_CHARGE_COUNTER)) +
                    " µAh, Corriente Instantánea: " + cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_CURRENT_NOW)) +
                    " µA, Capacidad: " + cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_CAPACITY)) +
                    " %, Estatus: " + cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_STATUS)) +
                    ", TimeStamp: " + cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_TIMESTAMP)) + "\n\n";
        }
        cursor.close();
        return table;
    }
}
