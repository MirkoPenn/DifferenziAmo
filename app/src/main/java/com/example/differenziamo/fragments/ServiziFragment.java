package com.example.differenziamo.fragments;


import android.app.Fragment;
import android.os.Bundle;
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

import java.util.ArrayList;

public class ServiziFragment extends Fragment {

    private ImageListAdapter adapter;
    private ArrayList<ElementoImageList> queryResult;
    private EditText cercaRifiuto;

    public ServiziFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // inflates the fragment_calendario layout into the content_main constraint layout
        View rootView = inflater.inflate(R.layout.fragment_servizi, container, false);

        // dovesitrova activity stuff (needs rootView)
        serviziMain(rootView);

        return rootView;

    }

    private void serviziMain(View rootView) {

        /* this is the same thing as ServiziMain from the older project but in a fragment, so I had to replace some things:

            - this (the activity) -> getActivity() function     [because we're not in the activity object anymore but in the fragment, with getActivity() I can get the activity calling]
            - findViewById -> rootView.findViewById             [because the view with the layout of the fragment is not the one of the main activity, but the one inflated by the fragment]
            - this (the activity for setOnItemClickListener) -> (AdapterView.OnItemClickListener) getActivity()

         */

        //dal momento che questa sezione non interagisce con il DB, lavoro da stringhe per settare la lista
        //definisco un vettore di stringhe che conterrà i nomi degli item della list view
        String[] nomiServizi = new String[] {"Centri di raccolta itinerante",
                "Isole ecologiche","Servizio di conferimento di sfalci e potature"};
        //definisco un vettore di stringhe che conterrà i path dei loghi degli item della list view
        String[] pathServizi = new String[] {"logo_sp_3",
                "logo_sp_2","logo_sfalci_potature"};

        //definisco un ArrayList di ElementoLista
        ArrayList<ElementoImageList> sorgenteListaServizi = new ArrayList<ElementoImageList>();
        for(int i=0;i<nomiServizi.length;++i){
            ElementoImageList elem = new ElementoImageList(nomiServizi[i], pathServizi[i]);
            sorgenteListaServizi.add(elem);
        }

        //creo l'ArrayAdapter e lo collego all'ArrayList
        ImageListAdapter adapter = new ImageListAdapter(getActivity(),sorgenteListaServizi);

        //collego l'ArrayAdapter al ListView
        ListView serviziLV = (ListView) rootView.findViewById(R.id.listView_servizi);
        serviziLV.setAdapter(adapter);

        //assegno all'activity stessa la gestione dell'evento OnItemClickListner
        serviziLV.setOnItemClickListener((AdapterView.OnItemClickListener) getActivity());
    }


    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.a_servizi);
        ((MainActivity) getActivity()).simulateItemChecking(R.id.nav_servizi);
    }

}