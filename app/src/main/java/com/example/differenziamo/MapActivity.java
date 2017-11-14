package com.example.differenziamo;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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


public class MapActivity extends Activity {

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
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		Intent i = getIntent();								//catturo l'intent proveniente dall'activity "DoveSiTrovaCategoria"
		latitudine = i.getStringExtra("latitudine");		//prendo il valore della latitudine
		descrizione = i.getStringExtra("descrizione");		//prendo la descrizione
		longitudine = i.getStringExtra("longitudine");		//prendo il valore della longitudine
		id_categoria = i.getStringExtra("id_categoria");	//prendo l'id della categoria
		nome_img = "mappa_sp_"+id_categoria;				//per stampare il segnalino corretto sulla mappa
		Float lat= Float.parseFloat(latitudine);			//poich? latitudine e longitudine sono stati memorizzati nel db come stringhe, qui le convertiamo in float
		Float lon= Float.parseFloat(longitudine);
		indMap = new LatLng(lat, lon);						//l'indirizzo ? dato dall'insieme di latitudine e longitudine
		indirizzo = i.getStringExtra("indirizzo");			//prendo anche l'indirizzo
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
		getMenuInflater().inflate(R.menu.activity_map_drawer, menu);
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
