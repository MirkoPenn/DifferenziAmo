package com.example.differenziamo.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.differenziamo.MainActivity;
import com.example.differenziamo.R;
import com.example.differenziamo.customobjects.ElementoImageList;
import com.example.differenziamo.customobjects.ImageListAdapter;
import com.example.differenziamo.database.DBClass;

import java.util.ArrayList;

public class DoveSiTrovaFragment extends Fragment {

    private ImageListAdapter adapter;
    private ArrayList<ElementoImageList> queryResult;
    private EditText cercaRifiuto;

    public DoveSiTrovaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // inflates the fragment_calendario layout into the content_main constraint layout
        View rootView = inflater.inflate(R.layout.fragment_dovesitrova, container, false);

        // dovesitrova activity stuff (needs rootView)
        doveSiTrovaMain(rootView);

        return rootView;

    }

    private void doveSiTrovaMain(View rootView) {

        /* this is the same thing as DoveSiTrovaActivity from the older project but in a fragment, so I had to replace some things:

            - this (the activity) -> getActivity() function     [because we're not in the activity object anymore but in the fragment, with getActivity() I can get the activity calling]
            - findViewById -> rootView.findViewById             [because the view with the layout of the fragment is not the one of the main activity, but the one inflated by the fragment]
            - this (the activity for setOnItemClickListener) -> (AdapterView.OnItemClickListener) getActivity()

         */

        //INSERIRE QUESTO IF STATEMENT se si vuole effettuare una connessione al database in questa activity
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //effettuo la query
        queryResult = DBClass.querySpeciali(getActivity());

        //imposto l'adapter
        adapter = new ImageListAdapter(getActivity(),queryResult);

        //dal momento che la query mi seleziona le categorie per tutti gli oggetti della tabella speciali, uso questa
        //funzione per selezionare, nell'adapter, solo le categorie diverse da loro (in modo che nella lista escano solo categorie differenti)
        adapter.filterCategorieSp();

        //imposto il ListView
        ListView listaRifiuti = (ListView) rootView.findViewById(R.id.listView_dovesitrova);
        listaRifiuti.setAdapter(adapter);

        //imposto il listener
        listaRifiuti.setOnItemClickListener((AdapterView.OnItemClickListener) getActivity());


    }

    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.a_dovesitrova);
        ((MainActivity) getActivity()).simulateItemChecking(R.id.nav_dovesitrova);
    }

}