package com.example.prototipo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv05;
    private TextView tv06;
    private TextView tv08;
    private TextView tv10;
    private TextView tv13;
    private TextView tv15;
    private TextView tv18;
    private TextView tv20;
    private TextView tv23;
    private TextView tv25;
    private TextView tv28;
    private TextView tv29;
    private TextView tv31;
    private TextView tv34;
    private Button btn01;
    private Button btn02;

    private boolean btn01_status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BatteryManager batteryManager = (BatteryManager)this.getSystemService(Context.BATTERY_SERVICE);

        tv05 = this.findViewById(R.id.tv05);
        tv06 = this.findViewById(R.id.tv06);
        tv08 = this.findViewById(R.id.tv08);
        tv10 = this.findViewById(R.id.tv10);
        tv13 = this.findViewById(R.id.tv13);
        tv15 = this.findViewById(R.id.tv15);
        tv18 = this.findViewById(R.id.tv18);
        tv20 = this.findViewById(R.id.tv20);
        tv23 = this.findViewById(R.id.tv23);
        tv25 = this.findViewById(R.id.tv25);
        tv28 = this.findViewById(R.id.tv28);
        tv29 = this.findViewById(R.id.tv29);
        tv31 = this.findViewById(R.id.tv31);
        tv34 = this.findViewById(R.id.tv34);

        btn01 = this.findViewById(R.id.btn01);

        Intent intent = new Intent(this, batteryService.class);

        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn01_status) {
                    btn01_status = false;

                    btn01.setText("Iniciar Escáneo");

                    stopService(intent);
                } else {
                    btn01_status = true;

                    btn01.setText("Detener Escáneo");

                    startService(intent);
                }
            }
        });

        btn02 = this.findViewById(R.id.btn02);

        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Información Copiada al Portapapeles", "prototype.getAll(dbHelper.getReadableDatabase())");
                clipboardManager.setPrimaryClip(clipData);
            }
        });

        BroadcastReceiver br = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter("initialValues");
        filter.addAction("finalValues");
        this.registerReceiver(br, filter);

    }

    public class MyBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case "initialValues":
                        String[] propiedad = intent.getStringArrayExtra("data");
                    break;
                case "finalValues":
                    break;
            }
        }

    }
}