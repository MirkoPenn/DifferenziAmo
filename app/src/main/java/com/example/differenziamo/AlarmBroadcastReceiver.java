package com.example.differenziamo;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import java.util.Calendar;

//per la notifica viene utilizzato un timer che ogni giorno risveglia l'app. Quando viene svegliata
// si utilizza questa classe(Broadcast Receiver) che verifica se generare la notifica o meno.
//Quindi il timer viene resettato in modo tale da risvegliarsi 24 ore dopo
public class AlarmBroadcastReceiver extends BroadcastReceiver {

	@TargetApi(Build.VERSION_CODES.KITKAT)
	@Override
	public void onReceive(Context context, Intent arg1) {
		// TODO Auto-generated method stub

		
		Utils.Log("Allarme");
		@SuppressWarnings("unused")
		final SharedPreferences prefs = context.getSharedPreferences(Utils.MY_PREFERENCES, Context.MODE_PRIVATE);
		
				
			
					
	      NotificationManager mNotificationManager = (NotificationManager)
		  context.getSystemService(Context.NOTIFICATION_SERVICE);
		  //tramite questo intent, quando si clicca sulla notifica che si riceve
	      //si viene indirizzati all'activity "Calendario" dove viene indicata
	      //la categoria di rifiuti da buttare quel giorno
		  Intent notificationIntent = new Intent(context, MainActivity.class);
		  notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
	      notificationIntent.putExtra("notificationType", "comunicazione");
			        
		  PendingIntent contentIntent = PendingIntent.getActivity(context, (int) (Math.random() * 100),notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			        
		  int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		  if (currentapiVersion>=21)
			 {
		      Utils.Log("Current Version: "+currentapiVersion);
			  //viene modificato l'aspetto della notifica (testo, logo, suono)
			  NotificationCompat.Builder mBuilder =
			  new NotificationCompat.Builder(context)
			  .setSmallIcon(R.mipmap.ic_launcher)																	//logo piccolo della notifica
			  .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))			//logo grande della notifica
		      .setContentTitle("DifferenziAmo")															//titolo della notifica
			  .setContentText("Ricorda di mettere il contenitore fuori dalla tua proprietà!")						//testo della notifica
			  .setAutoCancel(true)																					//la notifica si cancella automaticamente dopo il click su di essa
			  .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))							//suono della notifica
			  .setVibrate(new long[] {500, 1500});															//vibrazione della notifica

			  mBuilder.setContentIntent(contentIntent);
			  mNotificationManager.notify(1, mBuilder.build());
			        }

		  else
			 {
			  NotificationCompat.Builder mBuilder =
			  new NotificationCompat.Builder(context)
			  .setSmallIcon(R.mipmap.ic_launcher)																	//logo piccolo della notifica
			  .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))			//logo grande della notifica
			  .setContentTitle("DifferenziAmo")															//titolo della notifica
		      .setContentText("Ricorda di mettere il contenitore fuori dalla tua proprietà!")						//testo della notifica
			  .setAutoCancel(true)																					//la notifica si cancella automaticamente dopo il click su di essa
			  .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))							//suono della notifica
			  .setVibrate(new long[] {500, 1500});															//vibrazione della notifica
			  mBuilder.setContentIntent(contentIntent);
			  mNotificationManager.notify(1, mBuilder.build());
			  }


		AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent dialogIntent = new Intent(context, AlarmBroadcastReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 123, dialogIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		//la notifica viene ricevuta dall'utente ogni giorno alle 20
		Calendar calendar = Calendar.getInstance();
		//if(calendar.get(Calendar.HOUR_OF_DAY)>18)
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR, 8);
		calendar.set(Calendar.AM_PM, Calendar.PM);

		    currentapiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentapiVersion >= android.os.Build.VERSION_CODES.KITKAT){
		    //viene settato l'allarme per versioni superiori al kitkat
			alarmMgr.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
		} else{
		    //viene settato l'allarme per versioni inferiori al kitkat
			alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
		}
		
		//alarmMgr.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
		
	}
	
	
	

}
