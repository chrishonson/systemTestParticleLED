package com.nicksrandomwebsite.particleled;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LedFragment.OnFragmentInteractionListener,
LoginFragment.OnFragmentInteractionListener{
    private static final String TAG = MainActivity.class.getName();

    @Override
    public void onLoginSuccess(String id) {
        goToLedFragment(id);
    }

    enum FRAGMENT_TAG {
        LED,
        PASSWORD
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            goToLoginFragment();
        }else{
//            goToLedFragment(id);
        }
    }

    private void goToLedFragment(String id) {
        LedFragment ledFragment = LedFragment.newInstance(id, null);
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(FRAGMENT_TAG.LED.ordinal(), ledFragment).commit();
    }
    private void goToLoginFragment() {
        LoginFragment loginFragment = LoginFragment.newInstance(null, null);
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(FRAGMENT_TAG.LED.ordinal(), loginFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
