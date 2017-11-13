package com.example.differenziamo.database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context, String name, int version) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
		
		//setForcedUpgrade(version);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	
	public Cursor get()
    {
        Cursor crs=null;
        try
        {
            SQLiteDatabase db=this.getReadableDatabase();
            crs=db.query("va__categorie", null,  null, null, null, null, null, null);
        }
        catch(SQLiteException sqle)
        {
        	Log.d("GMB","ERRORE DB: "+sqle.toString());
            return null;
        }
        catch(Exception e)
        {
        	Log.d("GMB","ERRORE DB: "+e.toString());
            return null;
        }
        return crs;
    }

}
