package com.nicksrandomwebsite.particleled;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import java.io.IOException;
import java.util.List;

import io.particle.android.sdk.cloud.ParticleCloud;
import io.particle.android.sdk.cloud.ParticleCloudException;
import io.particle.android.sdk.cloud.ParticleCloudSDK;
import io.particle.android.sdk.cloud.ParticleDevice;
import io.particle.android.sdk.utils.Async;
import io.particle.android.sdk.utils.Toaster;

import static io.particle.android.sdk.utils.Py.list;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LedFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mDeviceID;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param deviceID Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LedFragment newInstance(String deviceID, String param2) {
        LedFragment fragment = new LedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, deviceID);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    Switch ledSwitch;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.fragment_led);
        if (getArguments() != null) {
            mDeviceID = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        ledSwitch = (Switch) getActivity().findViewById(R.id.switch1);
        ledSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ledSwitch.isChecked()){
                    digitalWrite(list("D7", "1"));
                }else{
                    digitalWrite(list("D7", "0"));
                }
            }
        });
    }

    private void digitalWrite(final List<String> args) {
        Async.executeAsync(ParticleCloud.get(getActivity()), new Async.ApiWork<ParticleCloud, Object>() {
            @Override
            public Object callApi(ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                int resultCode =
                        0;
                try {
            ParticleDevice particleDevice = particleCloud.getDevice(mDeviceID);
            resultCode = particleDevice.
                    callFunction("digitalwrite", args);
                } catch (ParticleCloudException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParticleDevice.FunctionDoesNotExistException e) {
                    e.printStackTrace();
                }
                return resultCode;
            }

            @Override
            public void onSuccess(Object o) {
                Toaster.s(getActivity(), "Result of calling digitalwrite: " + o.toString());

            }

            @Override
            public void onFailure(ParticleCloudException exception) {
                Toaster.s(getActivity(), "Result of calling digitalwrite: " + exception.toString());

            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_led, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        void onFragmentInteraction(Uri uri);
    }
}
