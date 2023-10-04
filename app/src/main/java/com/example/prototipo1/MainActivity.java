package com.example.prototipo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv01 = this.findViewById(R.id.tv01);
        BatteryManager batteryManager = (BatteryManager)this.getSystemService(Context.BATTERY_SERVICE);
        //Long energy = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER);
        String info1 = "Remaining battery capacity in\nmicroampere-hours:   " + batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER) + " uAh\n\n" +
                "Instantaneous battery current in\nmicroamperes:   " + batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW) + " uA\n\n" +
                "Average battery current in\nmicroamperes:   " + batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE) + " uA\n\n" +
                "Remaining battery capacity as an\ninteger percentage:   " + batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY) + " %\n\n" +
                "Remaining energy in\nnanowatt-hours:   " + batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER) + " nWh\n\n" +
                "Status: " + batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_STATUS);
        tv01.setText(info1);
    }
}