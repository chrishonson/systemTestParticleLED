package com.nicksrandomwebsite.particleled;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest2 {
    BluetoothArduino bluetoothArduino;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        bluetoothArduino = BluetoothArduino.getInstance("HC-05");
        bluetoothArduino.Connect();
    }
    @Test
    public void mainActivityTest2() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.login_button), withText("Login"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction switch_ = onView(
                allOf(withId(R.id.switch1), withText("LED"), isDisplayed()));
        switch_.perform(click());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(getLedStatus(bluetoothArduino));

        switch_.perform(click());
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        while(getLedStatus(bluetoothArduino));
        assertFalse(getLedStatus(bluetoothArduino));

    }
    private boolean getLedStatus(BluetoothArduino bluetoothArduino) {
        bluetoothArduino.SendMessage("gls#");
        while(bluetoothArduino.countMessages() == 0 ){;}
        String message = bluetoothArduino.getLastMessage();
        Log.d("MainActivityTest", "getLedStatus: message from arduino:" + message);
        if(message.equals("on")){
            return true;
        }else{
            return false;
        }
    }
}
