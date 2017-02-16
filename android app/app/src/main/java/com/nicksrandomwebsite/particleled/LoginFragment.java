package com.nicksrandomwebsite.particleled;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.io.IOException;

import io.particle.android.sdk.cloud.ParticleCloud;
import io.particle.android.sdk.cloud.ParticleCloudException;
import io.particle.android.sdk.cloud.ParticleDevice;
import io.particle.android.sdk.utils.Async;
import io.particle.android.sdk.utils.Toaster;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        getActivity().setContentView(R.layout.fragment_login);

        ((EditText) getActivity().findViewById(R.id.email)).setText("chrishonson@gmail.com");
        ((EditText) getActivity().findViewById(R.id.password)).setText("gearbox9");

        getActivity().findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = ((EditText) getActivity().findViewById(R.id.email)).getText().toString();
                final String password = ((EditText) getActivity().findViewById(R.id.password)).getText().toString();

                Async.executeAsync(ParticleCloud.get(v.getContext()), new Async.ApiWork<ParticleCloud, Object>() {

                    private ParticleDevice mDevice;

                    @Override
                    public Object callApi(ParticleCloud sparkCloud) throws ParticleCloudException, IOException {
                        sparkCloud.logIn(email, password);
                        sparkCloud.getDevices();
                        mDevice = sparkCloud.getDevice("280020000247343337373739");
                        Object obj;

//                        try {
//                            obj = mDevice.getVariable("analogvalue");
//                            Log.d("BANANA", "analogvalue: " + obj);
//                        } catch (ParticleDevice.VariableDoesNotExistException e) {
//                            Toaster.s(getActivity(), "Error reading variable");
//                            obj = -1;
//                        }
//
//                        try {
//                            String strVariable = mDevice.getStringVariable("stringvalue");
//                            Log.d("BANANA", "stringvalue: " + strVariable);
//                        } catch (ParticleDevice.VariableDoesNotExistException e) {
//                            Toaster.s(getActivity(), "Error reading variable");
//                        }
//
//                        try {
//                            double dVariable = mDevice.getDoubleVariable("doublevalue");
//                            Log.d("BANANA", "doublevalue: " + dVariable);
//                        } catch (ParticleDevice.VariableDoesNotExistException e) {
//                            Toaster.s(getActivity(), "Error reading variable");
//                        }
//
//                        try {
//                            int intVariable = mDevice.getIntVariable("analogvalue");
//                            Log.d("BANANA", "int analogvalue: " + intVariable);
//                        } catch (ParticleDevice.VariableDoesNotExistException e) {
//                            Toaster.s(getActivity(), "Error reading variable");
//                        }

                        return -1;

                    }

                    @Override
                    public void onSuccess(Object value) {
                        if (mListener != null) {
                            mListener.onLoginSuccess(mDevice.getID());
                        }
                    }

                    @Override
                    public void onFailure(ParticleCloudException e) {
                        Toaster.l(getActivity(), e.getBestMessage());
                        e.printStackTrace();
                        Log.d("info", e.getBestMessage());
                    }
                });

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onLoginSuccess(String id);
    }
}
