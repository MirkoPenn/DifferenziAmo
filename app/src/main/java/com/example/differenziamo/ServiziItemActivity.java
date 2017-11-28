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
			titolo_servizio.setText(R.string.titolo_centri_raccolta);
			titolo_servizio.setTextColor(getResources().getColor(R.color.green));
			immagine_servizio.setImageResource(getResources().getIdentifier("immagine_sp_3", "drawable", getPackageName()));
			titolo_cosa_e.setTextColor(getResources().getColor(R.color.green));
			cosa_e.setText(R.string.cosa_centri_raccolta);
			titolo_rifiuto_conferire.setTextColor(getResources().getColor(R.color.green));
			rifiuto_conferire.setText(R.string.rifiuti_centri_raccolta);
			titolo_quando.setTextColor(getResources().getColor(R.color.green));
			quando.setText(R.string.quando_centri_raccolta);
			break;
		case 1:
			titolo_servizio.setText(R.string.titolo_isole_ecologiche);
			titolo_servizio.setTextColor(getResources().getColor(R.color.blue));
			immagine_servizio.setImageResource(getResources().getIdentifier("immagine_sp_2", "drawable", getPackageName()));
			titolo_cosa_e.setTextColor(getResources().getColor(R.color.blue));
			cosa_e.setText(R.string.cosa_isole_ecologiche);
			titolo_rifiuto_conferire.setTextColor(getResources().getColor(R.color.blue));
			rifiuto_conferire.setText(R.string.rifiuti_isole_ecologiche);
			titolo_quando.setTextColor(getResources().getColor(R.color.blue));
			quando.setText(R.string.quando_isole_ecologiche);
			break;
		case 2:
			titolo_servizio.setText(R.string.titolo_sfalci_potature);
			titolo_servizio.setTextColor(getResources().getColor(R.color.red));
			immagine_servizio.setImageResource(getResources().getIdentifier("immagine_sfalci_potature", "drawable", getPackageName()));
			titolo_cosa_e.setTextColor(getResources().getColor(R.color.red));
			cosa_e.setText(R.string.cosa_sfalci_potature);
			titolo_rifiuto_conferire.setTextColor(getResources().getColor(R.color.red));
			rifiuto_conferire.setText(R.string.rifiuti_sfalci_potature);
			titolo_quando.setTextColor(getResources().getColor(R.color.red));
			quando.setText(R.string.quando_sfalci_potature);
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
