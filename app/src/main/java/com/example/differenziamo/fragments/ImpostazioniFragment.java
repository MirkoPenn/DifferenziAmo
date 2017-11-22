package com.example.differenziamo.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.differenziamo.R;
import com.example.differenziamo.customobjects.ElementoImageList;
import com.example.differenziamo.customobjects.ImageListAdapter;
import com.example.differenziamo.database.DBClass;

import java.util.ArrayList;
import java.util.Locale;

public class ImpostazioniFragment extends Fragment {

    public ImpostazioniFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // inflates the fragment_calendario layout into the content_main constraint layout
        View rootView = inflater.inflate(R.layout.fragment_impostazioni, container, false);

        return rootView;

    }


}