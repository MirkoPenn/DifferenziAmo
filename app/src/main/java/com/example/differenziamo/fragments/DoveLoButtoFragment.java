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
import com.example.differenziamo.classes.ElementoImageList;
import com.example.differenziamo.classes.ImageListAdapter;
import com.example.differenziamo.database.DBClass;

import java.util.ArrayList;
import java.util.Locale;

public class DoveLoButtoFragment extends Fragment {

    private ImageListAdapter adapter;
    private ArrayList<ElementoImageList> queryResult;
    private EditText cercaRifiuto;

    public DoveLoButtoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // inflates the fragment_calendario layout into the content_main constraint layout
        View rootView = inflater.inflate(R.layout.fragment_dovelobutto, container, false);

        // dovelobutto activity stuff (needs rootView)
        doveLoButtoMain(rootView);

        return rootView;

    }

    private void doveLoButtoMain(View rootView) {

        /* this is the same thing as DoveLoButtoActivity from the older project but in a fragment, so I had to replace some things:

            - this (the activity) -> getActivity() function     [because we're not in the activity object anymore but in the fragment, with getActivity() I can get the activity calling]
            - findViewById -> rootView.findViewById             [because the view with the layout of the fragment is not the one of the main activity, but the one inflated by the fragment]
            - this (the activity for setOnItemClickListener) -> (AdapterView.OnItemClickListener) getActivity()

         */

        //INSERIRE QUESTO IF STATEMENT se si vuole effettuare una connessione al database in questa activity
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //eseguo la query
        queryResult = DBClass.queryRifiuti(getActivity());

        //imposto l'adapterrootView.f
        adapter = new ImageListAdapter(getActivity(), queryResult);

        //imposto il ListView
        ListView listaRifiuti = (ListView) rootView.findViewById(R.id.listView_dovelobutto);
        listaRifiuti.setAdapter(adapter);

        //imposto il listener
        listaRifiuti.setOnItemClickListener((AdapterView.OnItemClickListener) getActivity());

        //imposto il campo di ricerca
        cercaRifiuto = (EditText) rootView.findViewById(R.id.editText_search_dovelobutto);
        cercaRifiuto.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                String text = cercaRifiuto.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

        });

    }

}