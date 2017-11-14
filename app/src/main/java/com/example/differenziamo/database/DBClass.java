package com.example.differenziamo.database;

import android.content.Context;
import android.database.Cursor;

import com.example.differenziamo.customobjects.CategoriaDifferenziata;
import com.example.differenziamo.customobjects.ElementoImageList;

import java.sql.Statement;
import java.util.ArrayList;

public class DBClass {
	
	//CLASSE PER LA GESTIONE DEL DATABASE
	
	//Informazioni del Database
	//static String url = "jdbc:sqldroid/data/data/com.example.differenziata/differenziata.db";

	/*
	static String url = "jdbc:mysql://143.225.229.12:3306";
	static String driver = "com.mysql.jdbc.Driver";
	static String DBName = "M5SPomigliano_Differenziata";
	static String userName = "root";
	static String password = "P5bsGzwN85VykwbqtswYKBmS";
	*/

//	static String url = "jdbc:mysql://us-cdbr-iron-east-03.cleardb.net:3306";
//	static String driver = "com.mysql.jdbc.Driver";
//	static String DBName = "ad_6e0a33b02f2e788";
//	static String userName = "b0141293db2d9e"; 
//	static String password = "708677f7";
	
	//static Connection conn = null;
	
//	//FUNZIONE per la connessione al DB
//	public static void getDBConnection(Context context){
//		try {
//			//carico i driver
//			//Class.forName(driver).newInstance();
//
//			//Class.forName("SQLite.JDBCDriver").newInstance();
//			//creo la connessione
//			//conn = DriverManager.getConnection(url+"/"+DBName,userName,password);
//			//DriverManager.registerDriver((Driver) Class.forName("org.sqldroid.SQLDroidDriver").newInstance());
//
//			//System.out.println("Registrati driver");
//			//conn = DriverManager.getConnection("jdbc:sqldroid:/data/data/com.example.differenziata/differenziata.db");
//
//			//System.out.println("Connesso");
//
////			//creo lo statement in stat
////			Statement stat = conn.createStatement();
////			//eseguo lo statement e inserisco il risultato della query nel resultset
////			ResultSet res = stat.executeQuery("SELECT * from rifiuti");
////			//ResultSet res = stat.executeQuery("SELECT name FROM sqlite_master WHERE type = 'table'");
////			ArrayList<ElementoImageList> dati = new ArrayList<ElementoImageList>();
////			while (res.next()){
////				System.out.println(res.getString(0));
////			}
////			//chiudo res e stat
////			res.close();
////			stat.close();
//
////			PROVA DB
//
////			DBAsset dbHelper = new DBAsset(context, "differenziata.db", 1);
////			Cursor crs = dbHelper.query("SELECT name FROM sqlite_master WHERE type = 'table'");
////			if(crs != null)
////			{
////				while(crs.moveToNext())
////				{
////					System.out.println(crs.getString(0));
////				}
////				crs.close();
////			}
//
//	}
//		catch (Exception e) {
//			System.out.println("Errore connessione database.");
//			e.printStackTrace();
//		}
//	}
	
	//QUERY per ricavare i dati della categoria "rifiuti" per l'activity "dove lo butto"
	public static ArrayList<ElementoImageList> queryRifiuti(Context context) {

		Statement stat = null;
		ArrayList<ElementoImageList> dati = new ArrayList<ElementoImageList>();
		String query = "SELECT rifiuto_nome,rifiuto_categoria,categoria_nome,categoria_modalita,categoria_colore FROM (rifiuti JOIN categorie ON rifiuti.rifiuto_categoria=categorie.categoria_id) ORDER BY rifiuto_nome";

		try {

			//controlla se la connessione è chiusa: in tal caso, si riconnette
			//if (conn.isClosed()){
				//DBClass.getDBConnection(this);
			//}

			//creo lo statement in stat
			//stat = conn.createStatement();

			//eseguo lo statement e inserisco il risultato della query nel resultset
			//ResultSet res = stat.executeQuery(query);
			DBAsset dbHelper = new DBAsset(context, "differenziata.db", 1);

			Cursor crs = dbHelper.query(query);

			//tutti i dati puntati da res li metto nell'array list
			if(crs != null)
			{
				while(crs.moveToNext())
				{
					ElementoImageList elem = new ElementoImageList
							(crs.getString(crs.getColumnIndex("rifiuto_nome")),
									crs.getInt(crs.getColumnIndex("rifiuto_categoria")),
									crs.getString(crs.getColumnIndex("categoria_nome")),
									crs.getString(crs.getColumnIndex("categoria_modalita")),
									crs.getString(crs.getColumnIndex("categoria_colore")));

					dati.add(elem);
				}
				crs.close();
			}
			//chiudo res e stat
			//res.close();
			//stat.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
			return dati;
	}
//
//	//QUERY per ricavare i dati della categoria "speciali" per l'activity "dove lo butto"
	public static ArrayList<ElementoImageList> querySpeciali(Context context) {

		Statement stat = null;
		ArrayList<ElementoImageList> dati = new ArrayList<ElementoImageList>();
		String query = "SELECT speciale_categoria,categoria_sp_nome,speciale_indirizzo,speciale_latitudine,speciale_longitudine,speciale_descrizione,speciale_id FROM (categorie_speciali JOIN speciali ON categorie_speciali.categoria_sp_id = speciali.speciale_categoria)";

		try {
			//controlla se la connessione è chiusa: in tal caso, si riconnette
			//if (conn.isClosed()){
			//	DBClass.getDBConnection();
			//}
			//creo lo statement in stat
			//stat = conn.createStatement();
			//eseguo lo statement e inserisco il risultato della query nel resultset
			//ResultSet res = stat.executeQuery(query);

			DBAsset dbHelper = new DBAsset(context, "differenziata.db", 1);

			Cursor crs = dbHelper.query(query);

			//tutti i dati puntati da res li metto nell'array list
			if(crs != null) {
				while (crs.moveToNext()) {
					ElementoImageList elem = new ElementoImageList
							(crs.getString(crs.getColumnIndex("categoria_sp_nome")),
									crs.getInt(crs.getColumnIndex("speciale_categoria")),
									crs.getString(crs.getColumnIndex("speciale_indirizzo")),
									crs.getString(crs.getColumnIndex("speciale_latitudine")),
									crs.getString(crs.getColumnIndex("speciale_longitudine")),
									crs.getString(crs.getColumnIndex("speciale_descrizione")),
									crs.getInt(crs.getColumnIndex("speciale_id")));
					dati.add(elem);
				}
			}
			//chiudo res e stat
			//res.close();
			//stat.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

			return dati;
	}
	//QUERY per ricavare i dati della categoria "rifiuti" per l'activity "Calendario"
	public static ArrayList<CategoriaDifferenziata> queryCalendario(Context context) {
		
		Statement stat = null;
		ArrayList<CategoriaDifferenziata> dati = new ArrayList<CategoriaDifferenziata>();
		String query = "SELECT categoria_nome, categoria_id, giorno_id, categoria_busta, categoria_colore FROM categorie JOIN giorni_differenziata ON categorie.categoria_id=giorni_differenziata.giorno_categoria";
		try {
			//controlla se la connessione è chiusa: in tal caso, si riconnette
			//if (conn.isClosed()){
			//	DBClass.getDBConnection();
			//}
			//creo lo statement in stat
			//stat = conn.createStatement();
			//eseguo lo statement e inserisco il risultato della query nel resultset
			//ResultSet res = stat.executeQuery(query);

			DBAsset dbHelper = new DBAsset(context, "differenziata.db", 1);

			Cursor crs = dbHelper.query(query);

			//tutti i dati puntati da res li metto nell'array list
			if(crs != null) {
				while (crs.moveToNext()) {
					CategoriaDifferenziata catDif = new CategoriaDifferenziata(
							crs.getString(crs.getColumnIndex("categoria_nome")),
							crs.getInt(crs.getColumnIndex("categoria_id")),
							crs.getInt(crs.getColumnIndex("giorno_id")),
							crs.getString(crs.getColumnIndex("categoria_busta")),
							crs.getString(crs.getColumnIndex("categoria_colore"))
							);
					dati.add(catDif);
				}
			}
			//chiudo res e stat
			//res.close();
			//stat.close();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}

			return dati;
	}
}