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
    private long currentAverage;
    private long energyCounter;

    public Prototype() {
        this.id = 0;
        this.chargeCounter = 0;
        this.currentNow = 0;
        this.capacity = 0;
        this.status = 0;
        this.timeStamp = "";
        this.currentAverage = 0;
        this.energyCounter = 0;
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

    public long getCurrentAverage() {
        return currentAverage;
    }

    public long getEnergyCounter() {
        return energyCounter;
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

    public void setCurrentAverage(long currentAverage) {
        this.currentAverage = currentAverage;
    }

    public void setEnergyCounter(long energyCounter) {
        this.energyCounter = energyCounter;
    }

    public String[] toStringArray() {
        String[] s = {this.id + "", this.chargeCounter + "", this.currentNow + "", this.capacity + "", this.status + "",
                this.timeStamp, this.currentAverage + "", this.energyCounter + ""};
        return s;
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
