package com.nicksrandomwebsite.particleled;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import static org.junit.Assert.*;

/**
 * Created by nickc on 1/16/17.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2 <MainActivity>{
    public MainActivityTest() {
        super(MainActivity.class);
    }
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        BluetoothArduino bluetoothArduino = BluetoothArduino.getInstance("HC-05");
        bluetoothArduino.Connect();
        bluetoothArduino.SendMessage("*1371#");
        while(bluetoothArduino.countMessages() == 0 ){;}
        String message = bluetoothArduino.getLastMessage();
        Log.d("MainActivityTest", "setUp: message from arduino:" + message);
    }

    public MainActivityTest(Class activityClass) {
        super(activityClass);
    }
    public void test(){

    }
}