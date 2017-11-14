package com.example.differenziamo.customobjects;

import java.io.Serializable;

//CLASSE per la gestione degli elementi del Calendario

@SuppressWarnings("serial")
public class CategoriaDifferenziata implements Serializable {
	private String nomeCategoria;
	private int idCategoria;
	private int idGiorno;
	private String categoriaBusta;
	private String coloreCategoria;


	//usare questo costruttore se si ha a che fare con liste di rifiuti, in cui voglio salvare
	//nome della categoria, id della categoria, id del giorno, busta della categoria e colore della categoria
	public CategoriaDifferenziata(String nome, int id_categoria, int id_giorno, String categoria_busta, String colore_categoria ){
		nomeCategoria = nome;
		idCategoria = id_categoria;
		idGiorno = id_giorno;
		categoriaBusta = categoria_busta;
		coloreCategoria = colore_categoria;
		
	}

	//restituisce il nome della categoria
	public String getNome() {
		return nomeCategoria;
	}
	//restituisce l'id della categoria
	public int getIdCategoria() {
		return idCategoria;
	}
	//restituisce l'id del giorno
	public int getIdGiorno(){
		return idGiorno;
	}
	
	//restituisce il nome della busta
	public String getBusta(){
		return categoriaBusta;
	}
	
	//restituisce il colore della categoria
	public String getColore(){
		return coloreCategoria;
	}
}