package com.e.xyzsensordemo;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  TextView tv;
  Sensor s;
  SensorManager sm;
  SensorEventListener sel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
        sm= (SensorManager) getSystemService(SENSOR_SERVICE);
        s=sm.getDefaultSensor(Sensor.TYPE_PROXIMITY); // X Y Z , Light sensor
        if(s==null)
        {
            Toast.makeText(this, "Sorry no sensor", Toast.LENGTH_SHORT).show();
        }
        sel=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent se) {
//                tv.setText("X = "+se.values[0]
//                +"\nY = "+se.values[1]
//                +"\nZ = "+se.values[2]);
                if(se.values[0]==0)
                {
                    tv.setText("Dont Touch me");
                }
                else
                {
                    tv.setText("Please Touch me");
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sm.registerListener(sel,s,SensorManager.SENSOR_DELAY_NORMAL);

    }
}