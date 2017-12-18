package com.example.differenziamo;


import android.content.Intent;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MapActivity extends AppCompatActivity {

	String latitudine;
	String longitudine;
	String indirizzo;
	String descrizione;
	String id_categoria;
	String nome_img;
	static LatLng indMap = new LatLng(0, 0);
	private GoogleMap map;
	@SuppressWarnings("unused")
	private Button tipoMappa;
	private String nomeSpeciale = null;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true); // tasto indietro

		// ottengo l'intent
		Intent i = getIntent();

		// ricavo la stringa della categoria dal bundle e la setto come titolo
		if (i.hasExtra("nomeServizio")) {
			nomeSpeciale = i.getStringExtra("nomeServizio");
			setTitle(nomeSpeciale);
		} else {
			nomeSpeciale = "Default";
		}

		//ottengo le altre info dall'intent per la mappa
		id_categoria = i.getStringExtra("id_categoria");	//prendo l'id della categoria
		latitudine = i.getStringExtra("latitudine");		//prendo il valore della latitudine
		longitudine = i.getStringExtra("longitudine");		//prendo il valore della longitudine
		descrizione = i.getStringExtra("descrizione");		//prendo la descrizione
		indirizzo = i.getStringExtra("indirizzo");			//prendo anche l'indirizzo
		nome_img = "mappa_sp_"+id_categoria;				//per stampare il segnalino corretto sulla mappa

		double lat = 0;
		double lon = 0;

		if (latitudine.compareTo("0") == 0 && longitudine.compareTo("0") == 0) {

			// se nel DB non abbiamo settato le coordinate, faremo in modo da ricavarle automaticamente dall'indirizzo

			Geocoder geocoder = new Geocoder(this, Locale.ITALY);
			List<Address> addresses = null;

			try {
				addresses = geocoder.getFromLocationName(indirizzo + ", Napoli", 1);
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (addresses.size() > 0) {
				System.out.println(addresses.get(0).getUrl());
				lat = addresses.get(0).getLatitude();
				lon = addresses.get(0).getLongitude();
			} else {
				lat = 0;
				lon = 0;
			}

		} else {
				// poiche' latitudine e longitudine sono stati memorizzati nel db come stringhe, qui le convertiamo in float
				lat = Float.parseFloat(latitudine);
				lon = Float.parseFloat(longitudine);
		}


		System.out.println("indirizzo: '"+indirizzo+", Napoli'"+", latitudine: "+lat+", longitudine: "+lon);


		indMap = new LatLng(lat, lon);						//l'indirizzo e' dato dall'insieme di latitudine e longitudine
		Typeface myTypeface = Typeface.createFromAsset(this.getAssets(),"fonts/San Francisco Regular.ttf"); //imposto il font San Francisco contenuto nella cartella assets/fonts
		TextView address = (TextView) findViewById(R.id.textView_indirizzo_map);
		TextView description = (TextView) findViewById(R.id.textView_descrizione_map);
		address.setTypeface(myTypeface);
		address.setText(indirizzo);							//stampo l'indirizzo
		description.setTypeface(myTypeface);
		description.setText(descrizione);					//stampo la descrizione
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
		        .getMap();
		    
		    if (map!=null){
		      @SuppressWarnings("unused")
			Marker puntomappa = map.addMarker(new MarkerOptions()			//permette di aggiungere un segnalino sulla mappa
		    	  .position(indMap)											//il segnalino si posiziona all'indirizzo preso in considerazione
		          .title(indirizzo)											//cliccando sul segnalino compare l'indirizzo preciso 
		          .icon(BitmapDescriptorFactory.fromResource(getResources().getIdentifier(nome_img, "drawable", this.getPackageName())))		//scelgo, in base alla categoria, l'icona del segnalino
		          );
		      
		   //muove la camera instantaneamente all'indirizzo selezionato con uno zoom di 12
		      map.moveCamera(CameraUpdateFactory.newLatLngZoom(indMap, 12));
		      
		   // Zoom in, camera animata
		      map.animateCamera(CameraUpdateFactory.zoomTo(17), 2000, null);
		      
		      
		      tipoMappa = (Button) findViewById(R.id.button_tipomappa);		//bottone per cambiare il tipo di mappa
		      
		      
		    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_drawer, menu);
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
	
	//FUNZIONE onClick per cambiare il tipo di mappa
	public void changeType(View view){
		//se la mappa ? di tipo normale allora cliccando passer? alla mappa di tipo satellite
		if(map.getMapType() == GoogleMap.MAP_TYPE_NORMAL){
		map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		}
		//altrimenti cliccando si ritorna alla mappa di tipo normale
		else
			map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	}
}
