package com.example.differenziamo.fragments;


import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.os.StrictMode;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.differenziamo.MainActivity;
import com.example.differenziamo.R;
import com.example.differenziamo.customobjects.CategoriaDifferenziata;
import com.example.differenziamo.database.DBClass;

import java.util.ArrayList;
import java.util.Calendar;

public class CalendarFragment extends Fragment {

    private ArrayList<CategoriaDifferenziata> queryResult;

    public CalendarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // inflates the fragment_calendario layout into the content_main constraint layout
        View rootView = inflater.inflate(R.layout.fragment_calendario, container, false);

        // calendar activity stuff (needs rootView)
        CalendarMain(rootView);

        return rootView;
    }

    private void CalendarMain(View rootView) {

        /* this is the same thing as CalendarActivity from the older project but in a fragment, so I had to replace some things:

            - this (the activity) -> getActivity() function     [because we're not in the activity object anymore but in the fragment, with getActivity() I can get the activity calling]
            - findViewById -> rootView.findViewById             [because the view with the layout of the fragment is not the one of the main activity, but the one inflated by the fragment]

         */

        // INFO CALENDARIO
        final Calendar c = Calendar.getInstance();
        int mMonth = c.get(Calendar.MONTH);			//ricavo il mese
        int mDay = c.get(Calendar.DAY_OF_MONTH);		//ricavo il giorno
        int mWeek = c.get(Calendar.DAY_OF_WEEK);		//ricavo il giorno della settimana

        // SET FONT
        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/San Francisco Regular.ttf"); //imposto il font San Francisco contenuto nella cartella assets/fonts
        TextView data = (TextView) rootView.findViewById(R.id.textView_date);
        data.setTypeface(myTypeface);

        //Assegno il nome in base al numero del giorno (viene utilizzato il calendario inglese)
        String sett="";
        if (mWeek == 2) {
            sett = "lunedì";
        } else if (mWeek == 3) {
            sett = "martedì";
        } else if (mWeek == 4) {
            sett = "mercoledì";
        } else if (mWeek == 5) {
            sett = "giovedì";
        } else if (mWeek == 6) {
            sett = "venerdì";
        } else if (mWeek == 7) {
            sett = "sabato";
        } else if (mWeek == 1) {
            sett = "domenica";
        }

        //assegno il nome in base al numero del mese
        String mese="";
        if (mMonth == 0) {
            mese = "gennaio";
        } else if (mMonth == 1) {
            mese = "febbraio";
        } else if (mMonth == 2) {
            mese = "marzo";
        } else if (mMonth == 3) {
            mese = "aprile";
        } else if (mMonth == 4) {
            mese = "maggio";
        } else if (mMonth == 5) {
            mese = "giugno";
        } else if (mMonth == 6) {
            mese = "luglio";
        } else if (mMonth == 7) {
            mese = "agosto";
        }else if  (mMonth == 8) {
            mese = "settembre";
        } else if (mMonth == 9) {
            mese = "ottobre";
        } else if (mMonth == 10) {
            mese = "novembre";
        } else if (mMonth == 11) {
            mese = "dicembre";
        }

        //stampo la data
        data.setText(""+sett+" "+mDay+" "+mese);

        //INSERIRE QUESTO IF STATEMENT se si vuole effettuare una connessione al database in questa activity
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //eseguo la query
        queryResult = DBClass.queryCalendario(getActivity());
        TextView categoria = (TextView) rootView.findViewById(R.id.textView_categoria);
        TextView text1 = (TextView) rootView.findViewById(R.id.textView_oggipuoibuttare);
        TextView text2 = (TextView) rootView.findViewById(R.id.textView_dovepuoibuttare);
        String colore1="";		//viene utilizzato colore 1 per la prima categoria(nel caso in cui quello stesso giorno vengano ritirate due categorie di rifiuti differenti)
        String colore2="";		//quindi il colore 2 viene utilizzato per la seconda categoria(se quel giorno viene ritirato una sola categoria di rifiuti allora viene utilizzato solo colore 1)
        String nome1="";			//viene utilizzato nome 1 per la prima categoria(nel caso in cui quello stesso giorno vengano ritirate due categorie di rifiuti differenti)
        String nome2="";			//quindi il nome 2 viene utilizzato per la seconda categoria(se quel giorno viene ritirato una sola categoria di rifiuti allora viene stampato solo il nome della prima categoria)
        String busta1="";			//viene utilizzato busta 1 per la prima categoria(nel caso in cui quello stesso giorno vengano ritirate due categorie di rifiuti differenti)
        String busta2="";			//quindi il nome 2 viene utilizzato per la seconda categoria(se quel giorno viene ritirato una sola categoria di rifiuti allora viene stampato solo l'immagine del sacchetto della prima categoria)

        int cat1=0;
        int cat2=0;

        //scelgo la categoria in base al giorno corrente
        for(CategoriaDifferenziata i : queryResult){  //for each per scorrere i risultati della query
            if(i.getIdGiorno()== mWeek){
                if(cat1 != 0){
                    cat2=i.getIdCategoria();
                    colore2=i.getColore();
                    nome2=","+i.getNome();
                    busta2=i.getBusta();
                }
                else{
                    cat1=i.getIdCategoria();
                    colore1=i.getColore();
                    nome1=i.getNome();
                    busta1=i.getBusta();
                }

            }


        }
        if(cat1!=0) {

            if(cat2!=0){
                //andiamo ad inserire gli url delle due immagini in imageview2 e imageview3 (quando si tratta di due categorie)
                ImageView immagine_categoria2 = (ImageView) rootView.findViewById(R.id.imageView_categoria2);
                immagine_categoria2.setImageResource(getResources().getIdentifier("immagine_cat_"+cat1, "drawable", getActivity().getPackageName()));
                ImageView immagine_categoria3 = (ImageView) rootView.findViewById(R.id.imageView_categoria3);
                immagine_categoria3.setImageResource(getResources().getIdentifier("immagine_cat_"+cat2, "drawable", getActivity().getPackageName()));
                ImageView immagine_categoria1 = (ImageView) rootView.findViewById(R.id.imageView_categoria1);
                immagine_categoria1.setImageResource(getResources().getIdentifier("", "drawable", getActivity().getPackageName()));

            } else {
                //andiamo ad inserire l'url della singola immagine in imageview1 (quando si tratta di una sola categoria)
                ImageView immagine_categoria1 = (ImageView) rootView.findViewById(R.id.imageView_categoria1);
                immagine_categoria1.setImageResource(getResources().getIdentifier("immagine_cat_"+cat1, "drawable", getActivity().getPackageName()));
                ImageView immagine_categoria2 = (ImageView) rootView.findViewById(R.id.imageView_categoria2);
                immagine_categoria2.setImageResource(getResources().getIdentifier("", "drawable", getActivity().getPackageName()));
                ImageView immagine_categoria3 = (ImageView) rootView.findViewById(R.id.imageView_categoria3);
                immagine_categoria3.setImageResource(getResources().getIdentifier("", "drawable", getActivity().getPackageName()));;
            }
        }

        else {
            //altrimenti (nel nostro caso il sabato) non stampare alcuna immagine perch� il sabato non vengono ritirati i rifiuti, ma stampa una stringa/messaggio
            ImageView immagine_categoria1 = (ImageView) rootView.findViewById(R.id.imageView_categoria1);
            immagine_categoria1.setImageResource(getResources().getIdentifier("", "drawable", getActivity().getPackageName()));
            ImageView immagine_categoria2 = (ImageView) rootView.findViewById(R.id.imageView_categoria2);
            immagine_categoria2.setImageResource(getResources().getIdentifier("", "drawable", getActivity().getPackageName()));
            ImageView immagine_categoria3 = (ImageView) rootView.findViewById(R.id.imageView_categoria3);
            immagine_categoria3.setImageResource(getResources().getIdentifier("", "drawable", getActivity().getPackageName()));
            nome1="Oggi non si butta alcun rifiuto!";   //viene stampata questa stringa
            colore1="#000000";
            text1.setText("");
            text2.setText("");
        }


        categoria.setTypeface(myTypeface); //imposto il font San Francisco
        text1.setTypeface(myTypeface);
        text2.setTypeface(myTypeface);
        // categoria.setText(stringa);  //stampo la categoria
        //importando il package android.text.Html, che permette di utilizzare funzioni html, si pu� gestire il colore del testo delle categorie anche nel caso in cui le categorie, quel giorno, siano due
        String text = "<font color="+colore1+">"+nome1+"</font> <font color="+colore2+">"+nome2+"</font>";
        categoria.setText(Html.fromHtml(text));
        if(busta2==""){
            if(busta1!=""){
                text2.setText("puoi gettare il rifiuto nella "+busta1+" e depositarlo fuori la porta dalle 20 alle 24.");
            } else{
                text2.setText("");
            }}
        else{
            text2.setText("puoi gettare i rifiuti nella "+busta1+" e nella "+busta2+" e depositarli fuori la porta dalle 20 alle 24.");
        }
    }

    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.a_calendario);
        ((MainActivity) getActivity()).simulateItemChecking(R.id.nav_calendario);
    }

}