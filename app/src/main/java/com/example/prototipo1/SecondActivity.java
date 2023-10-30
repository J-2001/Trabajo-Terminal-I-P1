package com.example.prototipo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class SecondActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
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
    private Button btn03;
    private DateHandler dateHandler = new DateHandler();
    private String initialTimeStamp;
    private boolean btn01_status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        coordinatorLayout = this.findViewById(R.id.secondActivity);
        tv05 = this.findViewById(R.id.tvs05);
        tv06 = this.findViewById(R.id.tvs06);
        tv08 = this.findViewById(R.id.tvs08);
        tv10 = this.findViewById(R.id.tvs10);
        tv13 = this.findViewById(R.id.tvs13);
        tv15 = this.findViewById(R.id.tvs15);
        tv18 = this.findViewById(R.id.tvs18);
        tv20 = this.findViewById(R.id.tvs20);
        tv23 = this.findViewById(R.id.tvs23);
        tv25 = this.findViewById(R.id.tvs25);
        tv28 = this.findViewById(R.id.tvs28);
        tv29 = this.findViewById(R.id.tvs29);
        tv31 = this.findViewById(R.id.tvs31);
        tv34 = this.findViewById(R.id.tvs34);

        btn01 = this.findViewById(R.id.btns01);

        Intent intent = new Intent(getApplicationContext(), batteryService.class);

        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn01_status) {
                    btn01_status = false;

                    btn01.setText("Iniciar Escáneo");

                    stopService(intent);
                } else {
                    btn01_status = true;

                    tv06.setText("0");
                    tv10.setText("0");
                    tv15.setText("0");
                    tv20.setText("0");
                    tv25.setText("0");
                    tv29.setText("0");

                    btn01.setText("Detener Escáneo");

                    initialTimeStamp = dateHandler.getTimeStampDB();

                    startService(intent);
                }
            }
        });

        btn02 = this.findViewById(R.id.btns02);

        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(getApplicationContext());
        Prototype prototype = new Prototype();
        ClipboardManager clipboardManager = (ClipboardManager)getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);

        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                ClipData clipData = ClipData.newPlainText("Información Copiada al Portapapeles", prototype.getLastScan(db, initialTimeStamp));
                clipboardManager.setPrimaryClip(clipData);
            }
        });

        btn03 = this.findViewById(R.id.btns03);
        btn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db= dbHelper.getReadableDatabase();
                ClipData clipData = ClipData.newPlainText("Información Copiada al Portapapeles", prototype.getAll(db));
                clipboardManager.setPrimaryClip(clipData);
            }
        });

        BroadcastReceiver br = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter("initialValues");
        filter.addAction("finalValues");
        this.registerReceiver(br, filter);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case "initialValues":
                    String[] propiedad = intent.getStringArrayExtra("data");
                    tv05.setText(propiedad[0]);
                    tv08.setText(propiedad[1]);
                    tv13.setText(propiedad[2]);
                    tv18.setText(propiedad[3]);
                    tv23.setText(propiedad[4]);
                    tv28.setText(propiedad[5]);
                    tv31.setText(propiedad[6]);
                    tv34.setText(propiedad[7]);

                    Snackbar snackbar = Snackbar.make(coordinatorLayout, "Escáneo comenzado!", Snackbar.LENGTH_SHORT);
                    snackbar.show();

                    break;
                case "finalValues":
                    String[] propiedad1 = intent.getStringArrayExtra("data");
                    tv06.setText(propiedad1[0]);
                    tv10.setText(propiedad1[1]);
                    tv15.setText(propiedad1[2]);
                    tv20.setText(propiedad1[3]);
                    tv25.setText(propiedad1[4]);
                    tv29.setText(propiedad1[5]);

                    Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Escáneo Finalizado...", Snackbar.LENGTH_SHORT);
                    snackbar1.show();

                    break;
            }
        }

    }
}