package com.example.differenziamo.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.differenziamo.MainActivity;
import com.example.differenziamo.R;

public class FaqFragment extends Fragment {

    public FaqFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // inflates the fragment_calendario layout into the content_main constraint layout
        View rootView = inflater.inflate(R.layout.fragment_faq, container, false);

        return rootView;

    }

    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.a_faq);
        ((MainActivity) getActivity()).simulateItemChecking(R.id.nav_faq);
    }
    

}