package com.example.differenziamo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ServiziItemActivity extends AppCompatActivity {
	
	int position; // è la posizione di ogni item nel ListView
	String nomeServizio = null;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_servizi_item);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true); // tasto indietro

		// ottengo l'intent
		Intent i = getIntent();

		// ricavo la stringa della categoria dal bundle e la setto come titolo
		if (i.hasExtra("nomeServizio")) {
			nomeServizio = i.getStringExtra("nomeServizio");
			setTitle(nomeServizio);
		} else {
			nomeServizio = "Default";
		}

		position = i.getIntExtra("position", 0);  //prendo dall'intent la posizione degli item
		
		//recupero i TextView e l'ImageView dell'activity attraverso il loro id
		TextView titolo_servizio = (TextView) findViewById(R.id.textView_titoloServizio);
		ImageView immagine_servizio = (ImageView) findViewById(R.id.imageView_servizio);
		TextView titolo_cosa_e = (TextView) findViewById(R.id.textView_titolo_cosa_e);
		TextView cosa_e = (TextView) findViewById(R.id.textView_cosa_e);
		TextView titolo_rifiuto_conferire = (TextView) findViewById(R.id.textView_titolo_rifiuto_conferire);
		TextView rifiuto_conferire = (TextView) findViewById(R.id.textView_rifiuto_conferire);
		TextView titolo_quando = (TextView) findViewById(R.id.textView_titolo_quando);
		TextView quando = (TextView) findViewById(R.id.textView_quando);
		
		//gestisco attraverso uno switch come si deve comportare l'activity a seconda dell'item selezionato,
		//usando delle funzioni che mi permettono di settare i textView e l'ImageView dell'activity
		switch (position) { 
		case 0:
			titolo_servizio.setText(R.string.titolo_ritiro_ingombranti);
			titolo_servizio.setTextColor(getResources().getColor(R.color.colorPrimary));
			immagine_servizio.setImageResource(getResources().getIdentifier("immagine_ingombranti", "drawable", getPackageName()));
			titolo_cosa_e.setTextColor(getResources().getColor(R.color.colorPrimary));
			cosa_e.setText(R.string.cosa_ritiro_ingombranti);
			titolo_rifiuto_conferire.setTextColor(getResources().getColor(R.color.colorPrimary));
			rifiuto_conferire.setText(R.string.rifiuti_ritiro_ingombranti);
			titolo_quando.setTextColor(getResources().getColor(R.color.colorPrimary));
			quando.setText(R.string.quandodove_ritiro_ingombranti);
			break;
		case 1:
			titolo_servizio.setText(R.string.titolo_isole_ecologiche);
			titolo_servizio.setTextColor(getResources().getColor(R.color.colorPrimary));
			immagine_servizio.setImageResource(getResources().getIdentifier("immagine_isole_ecologiche", "drawable", getPackageName()));
			titolo_cosa_e.setTextColor(getResources().getColor(R.color.colorPrimary));
			cosa_e.setText(R.string.cosa_isole_ecologiche);
			titolo_rifiuto_conferire.setTextColor(getResources().getColor(R.color.colorPrimary));
			rifiuto_conferire.setText(R.string.rifiuti_isole_ecologiche);
			titolo_quando.setTextColor(getResources().getColor(R.color.colorPrimary));
			quando.setText(R.string.quandodove_isole_ecologiche);
			break;
		case 2:
			titolo_servizio.setText(R.string.titolo_ecopunti_mobili);
			titolo_servizio.setTextColor(getResources().getColor(R.color.colorPrimary));
			immagine_servizio.setImageResource(getResources().getIdentifier("immagine_ecopunti", "drawable", getPackageName()));
			titolo_cosa_e.setTextColor(getResources().getColor(R.color.colorPrimary));
			cosa_e.setText(R.string.cosa_ecopunti_mobili);
			titolo_rifiuto_conferire.setTextColor(getResources().getColor(R.color.colorPrimary));
			rifiuto_conferire.setText(R.string.rifiuti_ecopunti_mobili);
			titolo_quando.setTextColor(getResources().getColor(R.color.colorPrimary));
			quando.setText(R.string.quandodove_ecopunti_mobili);
			break;
			
			case 3:
				titolo_servizio.setText(R.string.titolo_raccolta_abiti);
				titolo_servizio.setTextColor(getResources().getColor(R.color.colorPrimary));
				immagine_servizio.setImageResource(getResources().getIdentifier("immagine_raccolta_abiti", "drawable", getPackageName()));
				titolo_cosa_e.setTextColor(getResources().getColor(R.color.colorPrimary));
				cosa_e.setText(R.string.cosa_raccolta_abiti);
				titolo_rifiuto_conferire.setTextColor(getResources().getColor(R.color.colorPrimary));
				rifiuto_conferire.setText(R.string.rifiuti_raccolta_abiti);
				titolo_quando.setTextColor(getResources().getColor(R.color.colorPrimary));
				quando.setText(R.string.quandodove_raccolta_abiti);
				break;

			case 4:
				titolo_servizio.setText(R.string.titolo_oli_esausti);
				titolo_servizio.setTextColor(getResources().getColor(R.color.colorPrimary));
				immagine_servizio.setImageResource(getResources().getIdentifier("immagine_oli", "drawable", getPackageName()));
				titolo_cosa_e.setTextColor(getResources().getColor(R.color.colorPrimary));
				cosa_e.setText(R.string.cosa_oli_esausti);
				titolo_rifiuto_conferire.setTextColor(getResources().getColor(R.color.colorPrimary));
				rifiuto_conferire.setText(R.string.rifiuti_oli_esausti);
				titolo_quando.setTextColor(getResources().getColor(R.color.colorPrimary));
				quando.setText(R.string.quandodove_oli_esausti);
				break;

			case 5:
				titolo_servizio.setText(R.string.titolo_autocompostaggio);
				titolo_servizio.setTextColor(getResources().getColor(R.color.colorPrimary));
				immagine_servizio.setImageResource(getResources().getIdentifier("immagine_autocompostaggio", "drawable", getPackageName()));
				titolo_cosa_e.setTextColor(getResources().getColor(R.color.colorPrimary));
				cosa_e.setText(R.string.cosa_autocompostaggio);
				titolo_rifiuto_conferire.setTextColor(getResources().getColor(R.color.colorPrimary));
				rifiuto_conferire.setText(R.string.rifiuti_autocompostaggio);
				titolo_quando.setTextColor(getResources().getColor(R.color.colorPrimary));
				quando.setText(R.string.quandodove_autocompostaggio);
				break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.servizi_item_drawer, menu);
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
