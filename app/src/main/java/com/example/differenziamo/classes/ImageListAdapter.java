package com.example.differenziamo.classes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.differenziamo.R;

import java.util.ArrayList;
import java.util.Locale;

//CLASSE ADAPTER per la gestione della listview con immagini (imagelist)
//da usare con lista di rifiuti e di categorie speciali

public class ImageListAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<ElementoImageList> imageList;		//da modificare durante la ricerca
	private ArrayList<ElementoImageList> imageListFull; //copia da usare per i filtri

	public ImageListAdapter(Context context, ArrayList<ElementoImageList> imageList) {
		this.context = context; 
		this.imageList = new ArrayList<ElementoImageList>(imageList);
		this.imageListFull = new ArrayList<ElementoImageList>(imageList);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return imageList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		// se non è una view già usata creala con il suo layout
		if (convertView == null) {
		LayoutInflater layoutInflater =
				(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = layoutInflater.inflate(R.layout.imagelist_item, null);
	}
		
		//otteniamo	l'elemento alla posizione richiesta
		ElementoImageList elem = (ElementoImageList) getItem(position);

		// scegliamo il logo usando la funzione getResources.getIdentifier
		ImageView logo = (ImageView) convertView.findViewById(R.id.imagelist_logo);
		logo.setImageResource(this.context.getResources().getIdentifier(elem.getPathLogoElemento(), "drawable", this.context.getPackageName()));
		
		// setto il testo
		TextView testo = (TextView) convertView.findViewById(R.id.imagelist_text);
		testo.setText(elem.getNome());

		return convertView;
	}
	
	//FUNZIONE FILTRO per ricerca
	
	public void filter(String charText) {
		charText = charText.toLowerCase(Locale.getDefault());
		this.imageList.clear();
		if (charText.length() == 0) {
			this.imageList.addAll(imageListFull);
		} else {
			for (ElementoImageList elemento : imageListFull) {
				if (elemento.getNome().toLowerCase(Locale.getDefault())
						.contains(charText)) {
					imageList.add(elemento);
				}
			}
		}
		notifyDataSetChanged();
	}
	
	//FUNZIONE FILTRAGGIO per selezionare, nell'imagelist della sezione "dove si trova", solamente
	//categorie diverse tra di loro e non una categoria per ogni elemento speciale.
	//Fa un check sulla imagelistcompleta e, se la categoria (identificata da un id) è già stata aggiunta
	//non inserisce l'elemento.
	
	public void filterCategorieSp(){
		//POST-Condition: l'adapter trasforma imageList lasciando solo categorie diverse
		this.imageList.clear();
		int i=0, k=0;
		for (ElementoImageList elemento : imageListFull) {
			if (imageListFull.get(i).getIdCategoria() > k){
				System.out.println(imageListFull.get(i).getIdCategoria());
				imageList.add(elemento);
				k++;
			}
			i++;
		}
		notifyDataSetChanged();
	}
	
//	public int getLogo(ElementoLista elem){
//		//la funzione getLogo, in base al tipo (TIPO_RIFIUTI o TIPO_SPECIALI), sceglie la risorsa giusta per il logo
//		//della categoria (presa da elem con il metodo getCategoria)
//		if (tipo==TIPO_RIFIUTI)
//			switch (elem.getIdCategoria()){
//				case 1:
//					return R.drawable.logo_cat_1;
//				case 2:
//					return R.drawable.logo_cat_2;
//				case 3:
//					return R.drawable.logo_cat_3;
//				case 4:
//					return R.drawable.logo_cat_4;
//				case 5:
//					return R.drawable.logo_cat_5;
//				case 6:
//					return R.drawable.logo_cat_6;
//				case 7:
//					return R.drawable.logo_cat_7;
//				case 8:
//					return R.drawable.logo_cat_8;
//				case 9:
//					return R.drawable.logo_cat_9;
//				case 10:
//					return R.drawable.logo_cat_10;
//				case 11:
//					return R.drawable.logo_cat_11;
//				case 12:
//					return R.drawable.logo_cat_12;
//				case 13:
//					return R.drawable.logo_cat_13;
//				case 14:
//					return R.drawable.logo_cat_14;
//				case 15:
//					return R.drawable.logo_cat_15;
//				default:
//					return R.drawable.thumbnail;
//				}
//		else if (tipo==TIPO_SPECIALI)
//			switch (elem.getIdCategoria()){
//				case 1:
//					return R.drawable.logo_sp_1;
//				case 2: 
//					return R.drawable.logo_sp_2;
//				case 3:
//					return R.drawable.logo_sp_3;
//				default:
//					return R.drawable.thumbnail;
//				}
//		return R.drawable.thumbnail;
//	}
}