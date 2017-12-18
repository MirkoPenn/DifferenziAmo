package com.example.differenziamo.customobjects;

import java.io.Serializable;

//CLASSE per la gestione degli elementi della ImageList

@SuppressWarnings("serial")
public class ElementoImageList implements Serializable{
	
	private String nomeElemento;
	private String pathLogoElemento;
	private int id_categoriaElemento;
	private String categoriaElemento;
	private String modalitaElemento;
	private String coloreElemento;
	private String indirizzoElemento;
	private String latitudineElemento;
	private String longitudineElemento;
	private String descrizioneElemento;
	private int speciale_id;

	//usare questo costruttore se si ha a che fare con liste di rifiuti, in cui voglio salvare
	//nome, id della categoria, nome della categoria, modalità di smaltimento e colore della categoria
	public ElementoImageList(String nome, int id_categoria, String categoria, String modalita, String colore){
		nomeElemento = nome;
		id_categoriaElemento = id_categoria;
		pathLogoElemento = "logo_cat_"+id_categoria;
		categoriaElemento = categoria;
		modalitaElemento = modalita;
		coloreElemento = colore;
	}

	//usare questo costruttore se si ha a che fare con speciali, in cui voglio salvare nome, id, indirizzo, latitudine, longitudine, descrizione e id speciale
	public ElementoImageList(String nome, int id_categoria, String indirizzo, String latitudine, String longitudine, String descrizione, int id_speciale){
		nomeElemento = nome;
		id_categoriaElemento = id_categoria;
		pathLogoElemento = "ic_sp_"+id_categoria;
		indirizzoElemento = indirizzo;
		latitudineElemento = latitudine;
		longitudineElemento = longitudine;
 		descrizioneElemento = descrizione;
 		speciale_id = id_speciale;
	}
	
	//usare questo costruttore se si ha a che fare con liste di servizi, dove i dati sono passati manualmente attraverso array di stringhe
	public ElementoImageList(String nome, String path){
		nomeElemento = nome;
		pathLogoElemento = path;
	}

	public String getNome() {
		return nomeElemento;
	}
	
	public int getIdCategoria() {
		return id_categoriaElemento;
	}
	
	public String getPathLogoElemento() {
		return pathLogoElemento;
	}
	
	public String getCategoria() {
		return categoriaElemento;
	}
	
	public String getModalita(){
		return modalitaElemento;
	}
	
	public String getColore(){
		return coloreElemento;
	}
	
	public String getIndirizzo(){
		return indirizzoElemento;
	}
	
	public String getLatitudine(){
		return latitudineElemento;
	}
	
	public String getLongitudine(){
		return longitudineElemento;
	}
	
	public String getDescrizione(){
		return descrizioneElemento;
	}
	
	public int getIdSpeciale() {
		return speciale_id;
	}
}