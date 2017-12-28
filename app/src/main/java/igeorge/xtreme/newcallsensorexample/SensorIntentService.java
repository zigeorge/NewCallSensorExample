package igeorge.xtreme.newcallsensorexample;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

public class SensorIntentService extends Service {
    SensorManager sensorManager;
    Sensor accelerometerSensor;
    boolean accelerometerPresent;

    public SensorIntentService() {

    }

//    @Override
//    protected void onHandleIntent(Intent intent) {
//        Log.e("Service", "Started");
//        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
//        if (sensorList.size() > 0) {
//            accelerometerPresent = true;
//            accelerometerSensor = sensorList.get(0);
//        } else {
//            accelerometerPresent = false;
//        }
//
//        if (accelerometerPresent) {
//            sensorManager.registerListener(accelerometerListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
//        }
//    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Service", "Started");
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (sensorList.size() > 0) {
            accelerometerPresent = true;
            accelerometerSensor = sensorList.get(0);
        } else {
            accelerometerPresent = false;
        }

        if (accelerometerPresent) {
            sensorManager.registerListener(accelerometerListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (accelerometerPresent) {
            sensorManager.unregisterListener(accelerometerListener);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private SensorEventListener accelerometerListener = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor arg0, int arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent arg0) {
            // TODO Auto-generated method stub
            float z_value = arg0.values[2];
            if(z_value < -8){
                Log.e("Face Down","Face down worked");
            }
        }
    };
}
