package com.example.prototipo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

    private Long chargeCounter0 = 0L, chargeCounter1 = 0L;
    private Long currentNow0 = 0L, currentNow1 = 0L;
    private Long currentAverage0 = 0L, currentAverage1 = 0L;
    private Long energyCounter0 = 0L, energyCounter1 = 0L;
    private Long capacity0 = 0L, capacity1 = 0L;
    private Long status0 = 0L, status1 = 0L;
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
        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn01_status) {
                    chargeCounter1 = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
                    currentNow1 = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW);
                    currentAverage1 = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE);
                    energyCounter1 = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER);
                    capacity1 = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
                    status1 = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_STATUS);

                    Date date = new Date();
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(date);
                    DateFormat dateFormat = new SimpleDateFormat("HH:mm");

                    tv07.setText("" + chargeCounter1);
                    tv12.setText("" + currentNow1);
                    tv17.setText("" + currentAverage1);
                    tv22.setText("" + energyCounter1);
                    tv27.setText("" + capacity1);
                    tv32.setText("" + status1);
                    tv37.setText(dateFormat.format(calendar.getTime()));

                    btn01_status = false;

                    btn01.setText("Iniciar Escáneo");
                } else {
                    chargeCounter0 = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
                    currentNow0 = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW);
                    currentAverage0 = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE);
                    energyCounter0 = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER);
                    capacity0 = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
                    status0 = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_STATUS);

                    Date date = new Date();
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(date);
                    DateFormat dateFormat = new SimpleDateFormat("HH:mm");

                    tv05.setText("" + chargeCounter0);
                    tv10.setText("" + currentNow0);
                    tv15.setText("" + currentAverage0);
                    tv20.setText("" + energyCounter0);
                    tv25.setText("" + capacity0);
                    tv30.setText("" + status0);
                    tv35.setText(dateFormat.format(calendar.getTime()));

                    tv07.setText("0.00");
                    tv12.setText("0.00");
                    tv17.setText("0.00");
                    tv22.setText("0.00");
                    tv27.setText("0");
                    tv32.setText("0");
                    tv37.setText("--:--");

                    btn01_status = true;

                    btn01.setText("Detener Escáneo");
                }
            }
        });

    }
}