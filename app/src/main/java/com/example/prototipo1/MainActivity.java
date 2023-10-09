package com.example.prototipo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private Long currentAverage0 = 0L, currentAverage1 = 0L;
    private Long energyCounter0 = 0L, energyCounter1 = 0L;
    private boolean btn01_status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BatteryManager batteryManager = (BatteryManager)this.getSystemService(Context.BATTERY_SERVICE);

        TextView tv05 = this.findViewById(R.id.tv05);
        TextView tv07 = this.findViewById(R.id.tv07);
        TextView tv10 = this.findViewById(R.id.tv10);
        TextView tv12 = this.findViewById(R.id.tv12);
        TextView tv15 = this.findViewById(R.id.tv15);
        TextView tv17 = this.findViewById(R.id.tv17);
        TextView tv20 = this.findViewById(R.id.tv20);
        TextView tv22 = this.findViewById(R.id.tv22);
        TextView tv25 = this.findViewById(R.id.tv25);
        TextView tv27 = this.findViewById(R.id.tv27);
        TextView tv30 = this.findViewById(R.id.tv30);
        TextView tv32 = this.findViewById(R.id.tv32);
        TextView tv35 = this.findViewById(R.id.tv35);
        TextView tv37 = this.findViewById(R.id.tv37);

        Button btn01 = this.findViewById(R.id.btn01);

        Prototype prototype = new Prototype(0, 0, 0, 0, "");
        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(getBaseContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn01_status) {
                    prototype.setChargeCounter(batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER));
                    prototype.setCurrentNow(batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW));
                    currentAverage1 = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE);
                    energyCounter1 = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER);
                    prototype.setCapacity(batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY));
                    prototype.setStatus(batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_STATUS));

                    Date date = new Date();
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(date);
                    DateFormat dateFormat = new SimpleDateFormat("HH:mm");
                    prototype.setTimeStamp(dateFormat.format(calendar.getTime()));

                    tv07.setText("" + prototype.getChargeCounter());
                    tv12.setText("" + prototype.getCurrentNow());
                    tv17.setText("" + currentAverage1);
                    tv22.setText("" + energyCounter1);
                    tv27.setText("" + prototype.getCapacity());
                    tv32.setText("" + prototype.getStatus());
                    tv37.setText(prototype.getTimeStamp());

                    btn01_status = false;

                    btn01.setText("Iniciar Escáneo");
                } else {
                    prototype.setChargeCounter(batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER));
                    prototype.setCurrentNow(batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW));
                    currentAverage0 = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE);
                    energyCounter0 = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER);
                    prototype.setCapacity(batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY));
                    prototype.setStatus(batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_STATUS));

                    Date date = new Date();
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(date);
                    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                    prototype.setTimeStamp(dateFormat.format(calendar.getTime()));

                    tv05.setText("" + prototype.getChargeCounter());
                    tv10.setText("" + prototype.getCurrentNow());
                    tv15.setText("" + currentAverage0);
                    tv20.setText("" + energyCounter0);
                    tv25.setText("" + prototype.getCapacity());
                    tv30.setText("" + prototype.getStatus());
                    tv35.setText(prototype.getTimeStamp());

                    tv07.setText("00");
                    tv12.setText("00");
                    tv17.setText("00");
                    tv22.setText("00");
                    tv27.setText("0");
                    tv32.setText("0");
                    tv37.setText("--:--");

                    btn01_status = true;

                    btn01.setText("Detener Escáneo");
                }

                prototype.setId(db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, prototype.toContentValues()));
            }
        });

        Button btn02 = this.findViewById(R.id.btn02);
        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Información Copiada al Portapapeles", prototype.getAll(dbHelper.getReadableDatabase()));
                clipboardManager.setPrimaryClip(clipData);
            }
        });

    }
}