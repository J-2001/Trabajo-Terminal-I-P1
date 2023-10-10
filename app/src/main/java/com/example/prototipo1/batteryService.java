package com.example.prototipo1;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.BatteryManager;
import android.os.IBinder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class batteryService extends Service {

    private Prototype prototype;
    private FeedReaderDbHelper dbHelper;
    private BatteryManager batteryManager;
    private TimerTask timerTask;
    private boolean initial;

    @Override
    public void onCreate() {
        prototype = new Prototype();
        dbHelper = new FeedReaderDbHelper(getApplicationContext());
        batteryManager = (BatteryManager) getApplicationContext().getSystemService(Context.BATTERY_SERVICE);
        initial = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Timer timer = new Timer();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        timerTask = new TimerTask() {
            @Override
            public void run() {
                prototype.setChargeCounter(batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER));
                prototype.setCurrentNow(batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW));
                prototype.setCapacity(batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY));
                prototype.setStatus(batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_STATUS));

                Date date = new Date();
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(date);
                prototype.setTimeStamp(dateFormat.format(calendar.getTime()));

                prototype.setId(db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, prototype.toContentValues()));

                if (initial) {
                    initial = false;
                    prototype.setCurrentAverage(batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE));
                    prototype.setCurrentAverage(batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER));

                    Intent intent = new Intent();
                    intent.setAction("initialValues");
                    intent.putExtra("data", prototype.toStringArray());
                    sendBroadcast(intent);
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 30000);

        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        timerTask.cancel();

        Intent intent = new Intent();
        intent.setAction("finalValues");
        intent.putExtra("data", prototype.toStringArray());
        sendBroadcast(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
