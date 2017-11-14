package com.example.differenziamo;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


//questa classe permette di gestire la notifica in maniera tale che non venga inviata due volte lo stesso giorno
//ma 24 ore dopo al suo ultimo lancio, che nel nostro caso avviene alle 20
public class Utils {

	public static final String MY_PREFERENCES="com.example.differenziamo";
	public static final String tag = "differenziamo";
	
	public static final int _ResultIndirizzo = 10;
	
	public static void Log(String msg)
	{
		Log.i(tag,msg);
	}
	
	
	public static List<Date> getDaysBetweenDates(Date startdate, Date enddate)
	{
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startdate);

	    while (calendar.getTime().before(enddate))
	    {
	        Date result = calendar.getTime();
	        dates.add(result);
	        calendar.add(Calendar.DATE, 1);
	    }
	    return dates;
	}
	
	public static String capitalize(String line)
	{
	  return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}
	
	public static List<Date> getDaysFromNow(int numberOfDays)
	{
		if(numberOfDays<1) return null;
		
		Calendar calendar = Calendar.getInstance();
		
		List<Date> dates = new ArrayList<Date>();
		//dates.add(calendar.getTime());

	    for(int i=0; i<numberOfDays; i++)
	    {
	        Date result = calendar.getTime();
	        dates.add(result);
	        calendar.add(Calendar.DATE, 1);
	    }
	    return dates;
	}
	
	public static List<Date> getDaysFromTomorrow(int numberOfDays)
	{
		if(numberOfDays<1) return null;
		
		Calendar calendar = Calendar.getInstance();
		
		List<Date> dates = new ArrayList<Date>();
		//dates.add(calendar.getTime());

	    for(int i=0; i<numberOfDays; i++)
	    {
	    	calendar.add(Calendar.DATE, 1);
	    	Date result = calendar.getTime();
	        dates.add(result);
	    }
	    return dates;
	}

}
