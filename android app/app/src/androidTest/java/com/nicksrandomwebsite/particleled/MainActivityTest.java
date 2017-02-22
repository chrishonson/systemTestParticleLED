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
    BluetoothArduino bluetoothArduino;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        bluetoothArduino = BluetoothArduino.getInstance("HC-05");
        bluetoothArduino.Connect();
    }

    private boolean getLedStatus(BluetoothArduino bluetoothArduino) {
        bluetoothArduino.SendMessage("gls#");
        while(bluetoothArduino.countMessages() == 0 ){;}
        String message = bluetoothArduino.getLastMessage();
        Log.d("MainActivityTest", "setUp: message from arduino:" + message);
        if(message.equals("on")){
            return true;
        }else{
            return false;
        }
    }

    public MainActivityTest(Class activityClass) {
        super(activityClass);
    }
    public void test(){

        assertFalse(getLedStatus(bluetoothArduino));

    }

}