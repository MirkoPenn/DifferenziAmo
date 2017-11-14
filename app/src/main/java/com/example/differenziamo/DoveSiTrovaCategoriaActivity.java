package com.example.differenziamo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.differenziamo.customobjects.ElementoImageList;
import com.example.differenziamo.database.DBClass;

import java.util.ArrayList;

public class DoveSiTrovaCategoriaActivity extends Activity
implements OnItemClickListener {

	private int idCategoria = 0;
	private ArrayList<String[]> listaIndirizziDescrizioni = new ArrayList<String[]>();
	private ArrayList<ElementoImageList> maps = new ArrayList<ElementoImageList>();
	private ArrayList<ElementoImageList> queryResult = new ArrayList<ElementoImageList>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dovesitrova_categoria);

		// ricavo l'idCategoria dal bundle
		idCategoria = getIntent().getIntExtra("idCategoria", 0);

		// rieffetto la query
		queryResult = DBClass.querySpeciali(this);

		// filtro i risultati
		ArrayList<ElementoImageList> categoryResult = getCategoryResult(queryResult,idCategoria);
		
		//utilizzo un arraylist di String[] per importare tutti gli indirizzi, le descrizioni, le coordinate e gli id di ogni elemento della lista
		for (ElementoImageList elemento : categoryResult){
			listaIndirizziDescrizioni.add(new String[]{elemento.getIndirizzo(),elemento.getDescrizione(),elemento.getLatitudine(),elemento.getLongitudine(), String.valueOf(elemento.getIdCategoria())});
		}
		
		maps.addAll( categoryResult );
		
		//imposto l'adapter
		ArrayAdapter<String[]> adapter = new ArrayAdapter<String[]>(this, android.R.layout.simple_list_item_2, android.R.id.text1, listaIndirizziDescrizioni){
		
	    //effettuo l'override della funzione getView per modificare il layout degli item del ListView 
		//in modo che ognuno di essi contenga sia l'indirizzo che la relativa descrizione 
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = super.getView(position, convertView, parent);
			String[] entry = listaIndirizziDescrizioni.get(position);
			TextView text1 = (TextView) view.findViewById(android.R.id.text1);
			TextView text2 = (TextView) view.findViewById(android.R.id.text2);
			text1.setText(entry[0]);
			text1.setTypeface(null, Typeface.BOLD);
			text2.setText(entry[1]);
			
			return view; 
			}
		};
	
		//recupero il ListView tramite il suo id e lo collego all'adapter
		ListView indirizziLV = (ListView) findViewById(R.id.listview_dovesitrova_categoria);
		indirizziLV.setAdapter(adapter);
		//imposto il listener
		indirizziLV.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_dovesitrova_categoria_drawer, menu);
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
	
	@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			//trova l'ElementoLista alla posizione position e usa le funzioni get per ricavarne le informazioni
			String[] entry = listaIndirizziDescrizioni.get(position);
			String indirizzo  = entry[0];
			String descrizione = entry[1];
			String latitudine = entry[2];
			String longitudine = entry[3];
			String id_categoria = entry[4];
			Intent intent = new Intent(this, MapActivity.class); 				//creo un oggetto di tipo intent
			Bundle b = new Bundle(); 									//creo il bundle
			b.putString("indirizzo", indirizzo);						//metto nel bundle l'indirizzo
			b.putString("descrizione", descrizione);
			b.putString("latitudine", latitudine); 						//metto nel bundle la latitudine
			b.putString("longitudine", longitudine); 					//metto nel bundle la longitudine
			b.putString("id_categoria", id_categoria);					//metto nel bundle l'id della categoria
			intent.putExtras(b);										//metto il bundle nell'intent
			startActivity(intent);										//faccio partire l'intent
			}


	private ArrayList<ElementoImageList> getCategoryResult(ArrayList<ElementoImageList> queryResult, int idCategoria){
		//PRECondition: queryResult è un ArrayList di ElementoLista contenente tutti gli elementi "speciali" ricavati dalla query
		//PostCondition: viene restituito un ArrayList di ElementoLista contenente solamente gli elementi "speciali" il cui id categoria è idCategoria

		ArrayList<ElementoImageList> categoryResult = new ArrayList<ElementoImageList>();
		for(ElementoImageList elemento : queryResult){
			if(elemento.getIdCategoria()==idCategoria){
				categoryResult.add(elemento);
			}
		}

		return categoryResult;
	}


}
