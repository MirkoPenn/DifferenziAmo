package com.example.differenziamo;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class DoveLoButtoRifiutoActivity extends Activity {

	String nome_rifiuto;		//il nome del rifiuto scelto
	int id_categoria_rifiuto; 	//l'id della categoria del rifiuto scelto, conservata nel bundle
	String categoria_rifiuto;	//la categoria del rifiuto scelto
	String modalita_rifiuto; 	//la modalit� di smaltimento del rifiuto scelto
	String colore_rifiuto;

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dovelobutto_rifiuto);

		Intent i = getIntent(); 											//recupero l'intent con cui è stata chiamata questa activity
		nome_rifiuto = i.getStringExtra("nome_rifiuto");					//prendo dall'intent il nome del rifiuto
		id_categoria_rifiuto = i.getIntExtra("id_categoria_rifiuto", 0);	// prendo dall'intent l'id del rifiuto
		categoria_rifiuto = i.getStringExtra("categoria_rifiuto");			// prendo dall'intent la categoria
		modalita_rifiuto = i.getStringExtra("modalita_rifiuto");			// prendo dall'intent la modalità
		colore_rifiuto = i.getStringExtra("colore_rifiuto");				// prendo dall'intent la modalità

		//imposto l'ImageView usando getImmagine che, in base a id_categoria_rifiuto, sceglie la risorsa corretta
		ImageView immagine_rifiuto = (ImageView) findViewById(R.id.imageview_immagine_rifiuto);
		immagine_rifiuto.setImageResource(getResources().getIdentifier("immagine_cat_"+id_categoria_rifiuto, "drawable", getPackageName()));

		//imposto gli altri valori
		TextView textview_nome_rifiuto = (TextView) findViewById(R.id.textview_nome_rifiuto);
		TextView textview_categoria_rifiuto = (TextView) findViewById(R.id.textview_categoria_rifiuto);
		TextView textview_modalita_rifiuto = (TextView) findViewById(R.id.textview_modalita_rifiuto);
		textview_nome_rifiuto.setText(nome_rifiuto);
		textview_categoria_rifiuto.setText(categoria_rifiuto.toUpperCase());
		textview_categoria_rifiuto.setTextColor(Color.parseColor(colore_rifiuto));
		textview_modalita_rifiuto.setText(modalita_rifiuto);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_dovelobutto_rifiuto_drawer, menu);
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