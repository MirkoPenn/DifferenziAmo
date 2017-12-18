package com.example.differenziamo.fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.differenziamo.CalendarioSettimanaleActivity;
import com.example.differenziamo.MainActivity;
import com.example.differenziamo.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // inflates the fragment_calendario layout into the content_main constraint layout
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // set onClick listeners for buttons in home screen
        setListenerForButton(R.id.bt_calendario, R.id.nav_calendario, rootView);
        setListenerForButton(R.id.bt_dovelobutto, R.id.nav_dovelobutto, rootView);
        setListenerForButton(R.id.bt_dovesitrova, R.id.nav_dovesitrova, rootView);
        setListenerForButton(R.id.bt_servizi, R.id.nav_servizi, rootView);

        return rootView;
    }

    public void setListenerForButton(int buttonId, final int navId, View rootView){

        ImageView imageView = (ImageView) rootView.findViewById(buttonId);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ((MainActivity) getActivity()).simulateItemSelection(navId);

            }
        });
    }

    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.app_name);
        ((MainActivity) getActivity()).simulateItemChecking(R.id.nav_home);
    }

}