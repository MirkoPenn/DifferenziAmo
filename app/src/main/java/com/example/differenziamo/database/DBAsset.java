package com.example.differenziamo.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBAsset extends SQLiteAssetHelper {

	public DBAsset(Context context, String name, int version) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
		
		setForcedUpgrade(version);
	}
	
	
	public Cursor get(String table, String columns[])
	{
		return this.get(table, columns, null, null, null, null, null, null);
	}
	
	public Cursor get(String table)
	{
		return this.get(table, null, null, null, null, null, null, null);
	}
	
	public Cursor get(String table, String columns[], String selection, String[] SelectionArgs, String groupBy, String having, String orderBy, String limit)
    {
        Cursor crs=null;
        try
        {
            SQLiteDatabase db=this.getReadableDatabase();
            crs=db.query(table, columns,  selection, SelectionArgs, groupBy, having, orderBy, limit);
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
	
	public Cursor query(String sql)
	{
		Cursor crs=null;
        try
        {
            SQLiteDatabase db=this.getReadableDatabase();
            crs=db.rawQuery(sql, null);
        }
        catch(SQLiteException sqle)
        {
        	Log.e("M5SVT","ERRORE DB: "+sqle.toString());
            return null;
        }
        catch(Exception e)
        {
        	Log.e("M5SVT","ERRORE DB: "+e.toString());
            return null;
        }
        return crs;
	}
	
	public Boolean execute(String sql)
	{
		try
		{
			SQLiteDatabase db = this.getWritableDatabase();
			//db.rawQuery(sql, null);
			//Utils.Log(sql);
			db.execSQL(sql);
			return true;
		}
		catch(SQLiteException sqle)
        {
        	Log.e("M5SVT","ERRORE DB: "+sqle.toString());
            return false;
        }
        catch(Exception e)
        {
        	Log.e("M5SVT","ERRORE DB: "+e.toString());
            return false;
        }
	}

}
