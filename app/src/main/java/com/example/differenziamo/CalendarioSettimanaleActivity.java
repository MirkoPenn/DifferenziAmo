package com.example.differenziamo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.differenziamo.customobjects.CategoriaDifferenziata;
import com.example.differenziamo.database.DBClass;

import java.util.ArrayList;

public class CalendarioSettimanaleActivity extends AppCompatActivity {


    private ArrayList<CategoriaDifferenziata> queryResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario_settimanale);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // tasto indietro

        //INSERIRE QUESTO IF STATEMENT se si vuole effettuare una connessione al database in questa activity
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //importo l'arraylist di Categoria differenziata dall'activity Calendario con tutti gli elementi restituiti dalla query
        //queryResult = (ArrayList<CategoriaDifferenziata>) getIntent().getSerializableExtra("calendarioResult");
        queryResult = DBClass.queryCalendario(this);

        for(CategoriaDifferenziata i : queryResult){ 	//for each per scorrere il risultato della query
            if(i.getIdGiorno()== 1){
                TableLayout stk = (TableLayout) findViewById(R.id.table_layout7);
                TableRow tbrow = new TableRow(this);																							//viene creata una nuova riga
                ImageView iv1 = new ImageView(this);																							//viene creata una nuova immagine
                iv1.setImageResource(getResources().getIdentifier("logo_cat_"+i.getIdCategoria(), "drawable", getPackageName()));				//viene scelto il logo_missing da stampare in base alla categoria del rifiuto
                TextView tv1 = new TextView(this);																							//viene creato un nuovo testo
                tv1.setText(i.getNome());
                tv1.setTextColor(Color.BLACK);
                tv1.setTextSize(20);
                //permette di formattare il testo(grandezza, quando andare a capo) in base alla risoluzione del dispositivo che utilizza l'app
                android.view.Display display = ((android.view.WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                tv1.setMaxWidth((int)(display.getWidth()*0.68));
                TextView spazio = new TextView(this);
                spazio.setText("  ");			//spazio tra immagine e testo nella riga del tableLayout
                tbrow.addView(iv1);			//viene aggiunta l'immagine alla riga
                tbrow.addView(spazio);		//viene aggiunto lo spazio alla riga per distanziare testo e immagine
                tbrow.addView(tv1);			//viene aggiunto il testo alla riga
                stk.addView(tbrow);			//viene aggiunta la riga al tableLayout
            }
            if(i.getIdGiorno()== 2){
                TableLayout stk = (TableLayout) findViewById(R.id.table_layout1);
                TableRow tbrow = new TableRow(this);
                ImageView iv1 = new ImageView(this);
                iv1.setImageResource(getResources().getIdentifier("logo_cat_"+i.getIdCategoria(), "drawable", getPackageName()));
                TextView tv1 = new TextView(this);
                tv1.setText(i.getNome());
                tv1.setTextColor(Color.BLACK);
                tv1.setTextSize(20);
                android.view.Display display = ((android.view.WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                tv1.setMaxWidth((int)(display.getWidth()*0.68));
                TextView spazio = new TextView(this);
                spazio.setText("  ");
                tbrow.addView(iv1);
                tbrow.addView(spazio);
                tbrow.addView(tv1);
                stk.addView(tbrow);
            }
            if(i.getIdGiorno()== 3){
                TableLayout stk = (TableLayout) findViewById(R.id.table_layout2);
                TableRow tbrow = new TableRow(this);
                TableLayout.LayoutParams tableRowParams=
                        new TableLayout.LayoutParams
                                (TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                tableRowParams.setMargins(0, 0, 0, 10);
                tbrow.setLayoutParams(tableRowParams);
                ImageView iv1 = new ImageView(this);
                iv1.setImageResource(getResources().getIdentifier("logo_cat_"+i.getIdCategoria(), "drawable", getPackageName()));
                TextView tv1 = new TextView(this);
                tv1.setText(i.getNome());
                tv1.setTextColor(Color.BLACK);
                tv1.setTextSize(20);
                android.view.Display display = ((android.view.WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                tv1.setMaxWidth((int)(display.getWidth()*0.68));
                TextView spazio = new TextView(this);
                spazio.setText("  ");
                tbrow.addView(iv1);
                tbrow.addView(spazio);
                tbrow.addView(tv1);
                stk.addView(tbrow);
            }
            if(i.getIdGiorno()== 4){
                TableLayout stk = (TableLayout) findViewById(R.id.table_layout3);
                TableRow tbrow = new TableRow(this);
                ImageView iv1 = new ImageView(this);
                iv1.setImageResource(getResources().getIdentifier("logo_cat_"+i.getIdCategoria(), "drawable", getPackageName()));

                TextView tv1 = new TextView(this);
                tv1.setText(i.getNome());
                tv1.setTextColor(Color.BLACK);
                tv1.setTextSize(20);
                android.view.Display display = ((android.view.WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                tv1.setMaxWidth((int)(display.getWidth()*0.68));
                TextView spazio = new TextView(this);
                spazio.setText("  ");
                tbrow.addView(iv1);
                tbrow.addView(spazio);
                tbrow.addView(tv1);
                stk.addView(tbrow);
            }
            if(i.getIdGiorno()== 5){
                TableLayout stk = (TableLayout) findViewById(R.id.table_layout4);
                TableRow tbrow = new TableRow(this);
                ImageView iv1 = new ImageView(this);
                iv1.setImageResource(getResources().getIdentifier("logo_cat_"+i.getIdCategoria(), "drawable", getPackageName()));
                TextView tv1 = new TextView(this);
                tv1.setText(i.getNome());
                tv1.setTextColor(Color.BLACK);
                tv1.setTextSize(20);
                android.view.Display display = ((android.view.WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                tv1.setMaxWidth((int)(display.getWidth()*0.68));
                TextView spazio = new TextView(this);
                spazio.setText("  ");
                tbrow.addView(iv1);
                tbrow.addView(spazio);
                tbrow.addView(tv1);
                stk.addView(tbrow);
            }
            if(i.getIdGiorno()== 6){
                TableLayout stk = (TableLayout) findViewById(R.id.table_layout5);
                TableRow tbrow = new TableRow(this);
                ImageView iv1 = new ImageView(this);
                iv1.setImageResource(getResources().getIdentifier("logo_cat_"+i.getIdCategoria(), "drawable", getPackageName()));
                TextView tv1 = new TextView(this);
                tv1.setText(i.getNome());
                tv1.setTextColor(Color.BLACK);
                tv1.setTextSize(20);
                android.view.Display display = ((android.view.WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                tv1.setMaxWidth((int)(display.getWidth()*0.68));
                TextView spazio = new TextView(this);
                spazio.setText("  ");
                tbrow.addView(iv1);
                tbrow.addView(spazio);
                tbrow.addView(tv1);
                stk.addView(tbrow);
            }
            if(i.getIdGiorno()== 7){
                TableLayout stk = (TableLayout) findViewById(R.id.table_layout6);
                TableRow tbrow = new TableRow(this);
                ImageView iv1 = new ImageView(this);
                iv1.setImageResource(getResources().getIdentifier("logo_cat_"+i.getIdCategoria(), "drawable", getPackageName()));
                TextView tv1 = new TextView(this);
                tv1.setText(i.getNome());
                tv1.setTextColor(Color.BLACK);
                tv1.setTextSize(20);
                android.view.Display display = ((android.view.WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                tv1.setMaxWidth((int)(display.getWidth()*0.68));
                TextView spazio = new TextView(this);
                spazio.setText("  ");
                tbrow.addView(iv1);
                tbrow.addView(spazio);
                tbrow.addView(tv1);
                stk.addView(tbrow);
            }
        }

        //se la table è vuota perché quel giorno non si butta alcun rifiuto, stampa "Oggi non si butta alcun rifiuto"
        for(int j=1; j<8; j++){
            String nome_table = "table_layout"+j;
            int id_table = getResources().getIdentifier(nome_table, "id", getPackageName());
            TableLayout stk = (TableLayout) findViewById(id_table);
            if(stk.getChildCount() == 0){
                TableRow tbrow = new TableRow(this);
                TextView tv1 = new TextView(this);
                tv1.setText("Oggi non si butta alcun rifiuto!");
                tv1.setTextColor(Color.BLACK);
                tv1.setTextSize(20);
                tbrow.addView(tv1);
                stk.addView(tbrow);
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calendario_settimanale_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                this.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
