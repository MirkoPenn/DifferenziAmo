package com.example.differenziamo;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;

import com.example.differenziamo.customobjects.CategoriaDifferenziata;
import com.example.differenziamo.customobjects.ElementoImageList;
import com.example.differenziamo.fragments.*;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {


    private String mTitle = null;
    private ArrayList<CategoriaDifferenziata> queryResult;
    private FloatingActionButton fab = null;
    private int onItemClickMethod = 0;
    private int currentItemId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ACTION BAR
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // FLOATING BUTTON
        fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        fab.setVisibility(View.INVISIBLE);

        // DRAWER
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // INFLATE HOME FRAGMENT CONTENT
        Fragment initialFragment = new HomeFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, initialFragment).commit();

        // SET HOME AS THE CURRENTITEM FOR NAVIGATION
        simulateItemSelection(R.id.nav_home);

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Utils.Log("Resume");
        this.setAlarm();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void simulateItemSelection (int id) {
        // simulates call onNavigationItemSelected item selection with an ID

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        MenuItem menuItem = (MenuItem) navigationView.getMenu().findItem(id);
        menuItem.setChecked(true);
        onNavigationItemSelected(menuItem);

    }

    public void simulateItemChecking (int id){
        // simulates checking/unchecking of item in case it was selected with simulateItemSelection

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        MenuItem currentMenuItem = (MenuItem)navigationView.getMenu().findItem(currentItemId);
        currentMenuItem.setChecked(false);
        MenuItem newMenuItem = (MenuItem)navigationView.getMenu().findItem(id);
        newMenuItem.setChecked(true);

        currentItemId = id;     // sets the current checked item Id to the new one, required to manually handle the checking

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        selectedItemId(id);         // sets the correct fragment

        simulateItemChecking(id);   // forces checking manually to correctly handle back button / home buttons presses

        return true;
    }



    public void selectedItemId (int id) {

        // Handle navigation view item clicks here.

        Fragment fragment = null;

        // choose fragment based on selection
        if (id == R.id.nav_home) {

            fragment = new HomeFragment();
            mTitle = getString(R.string.app_name);

            // remove button
            fab.setVisibility(View.INVISIBLE);

        } else if (id == R.id.nav_calendario) {

            fragment = new CalendarFragment();
            mTitle = getString(R.string.a_calendario);

            // set button
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), CalendarioSettimanaleActivity.class);
                    startActivity(intent);
                }
            });
            fab.setImageResource(R.drawable.ic_calendario_settimanale);
            fab.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_dovelobutto) {

            fragment = new DoveLoButtoFragment();
            mTitle = getString(R.string.a_dovelobutto);

            // remove button
            fab.setVisibility(View.INVISIBLE);

            // set the right onItemClick function
            onItemClickMethod = R.string.a_dovelobutto;

        } else if (id == R.id.nav_dovesitrova) {

            fragment = new DoveSiTrovaFragment();
            mTitle = getString(R.string.a_dovesitrova);

            // remove button
            fab.setVisibility(View.INVISIBLE);

            // set the right onItemClick function
            onItemClickMethod = R.string.a_dovesitrova;

        } else if (id == R.id.nav_servizi) {

            fragment = new ServiziFragment();
            mTitle = getString(R.string.a_servizi);

            // remove button
            fab.setVisibility(View.INVISIBLE);

            // set the right onItemClick function
            onItemClickMethod = R.string.a_servizi;

        } else if (id == R.id.nav_impostazioni) {

            fragment = new ImpostazioniFragment();
            mTitle = getString(R.string.a_impostazioni);

            // remove button
            fab.setVisibility(View.INVISIBLE);

            // set the right onItemClick function
            onItemClickMethod = R.string.a_impostazioni;

        } else if (id == R.id.nav_faq) {

            fragment = new FaqFragment();
            mTitle = getString(R.string.a_faq);

            // remove button
            fab.setVisibility(View.INVISIBLE);

            // set the right onItemClick function
            onItemClickMethod = R.string.a_faq;

        }

        // change the content of the content_main Constraint Layout based on the chosen fragment class.
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, fragment).addToBackStack(mTitle).commit();

        // change activity title
        setTitle(mTitle);

        // close the drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        /* the onItemClick function defines what happens when you press a button in any ListView in the main activity.
           since we can have different ListViews (one for DoveLoButto fragment, one for DoveSiTrova fragment and one for Servizi fragment),
           there's a switch that's based on the onItemClickMethod that changes when you select the fragment */

        switch(onItemClickMethod) {

            case (R.string.a_dovelobutto):

                String nome_rifiuto = ((ElementoImageList) parent.getItemAtPosition(position)).getNome();
                int id_categoria_rifiuto = ((ElementoImageList) parent.getItemAtPosition(position)).getIdCategoria();
                String categoria_rifiuto = ((ElementoImageList) parent.getItemAtPosition(position)).getCategoria();
                String modalita_rifiuto = ((ElementoImageList) parent.getItemAtPosition(position)).getModalita();
                String colore_rifiuto = ((ElementoImageList) parent.getItemAtPosition(position)).getColore();

                Intent intentDoveLoButto = new Intent(this, DoveLoButtoRifiutoActivity.class); //creo un oggetto di tipo intent
                Bundle bDoveLoButto = new Bundle();                                    //creo il bundle
                bDoveLoButto.putString("nome_rifiuto", nome_rifiuto);                    //metto nel bundle il nome del rifiuto
                bDoveLoButto.putInt("id_categoria_rifiuto", id_categoria_rifiuto);    //metto nel bundle l'id della categoria del rifiuto
                bDoveLoButto.putString("categoria_rifiuto", categoria_rifiuto);        //metto nel bundle la categoria del rifiuto
                bDoveLoButto.putString("modalita_rifiuto", modalita_rifiuto);            //metto nel bundle la modalità di smaltimento
                bDoveLoButto.putString("colore_rifiuto", colore_rifiuto);                //metto nel bundle il colore
                intentDoveLoButto.putExtras(bDoveLoButto);                                        //metto il bundle nell'intent
                startActivity(intentDoveLoButto);              //faccio partire l'intent

            break;

            case (R.string.a_dovesitrova):

                String nome_speciale = ((ElementoImageList) parent.getItemAtPosition(position)).getNome();
                Intent intentDoveSiTrova = new Intent(this, DoveSiTrovaCategoriaActivity.class); 	//creo un oggetto di tipo intent
                Bundle bDoveSiTrova = new Bundle();
                bDoveSiTrova.putInt("idCategoria", position+1);    //metto nel bundle l'idCategoria
                bDoveSiTrova.putString("nomeServizio", nome_speciale);
                intentDoveSiTrova.putExtras(bDoveSiTrova);
                startActivity(intentDoveSiTrova);


            break;

            case (R.string.a_servizi):

                String nome_servizio = ((ElementoImageList) parent.getItemAtPosition(position)).getNome();
                Intent intent = new Intent(this, ServiziItemActivity.class); 					//creo un oggetto intent
                Bundle b = new Bundle();												//creo il bundle
                b.putInt("position", position);											//metto la posizione degli item del ListView nel bundle
                b.putString("nomeServizio", nome_servizio);                             // metto nel bundle il nome del servizio
                intent.putExtras(b);													//metto il bundle nell'intent
                startActivity(intent);

                break;
        }
    }

    // *******************  GESTIONE NOTIFICA    ************

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Utils._ResultIndirizzo) {
            if(resultCode == RESULT_OK){
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new CalendarFragment())
                        .commit();
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setAlarm()
    {
        final SharedPreferences prefs = this.getSharedPreferences(Utils.MY_PREFERENCES, Context.MODE_PRIVATE);
        Intent dialogIntent = new Intent(this, AlarmBroadcastReceiver.class);

        if(prefs.getInt("alarmSetted", 1)==1)
        {

            AlarmManager alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 123, dialogIntent,PendingIntent.FLAG_UPDATE_CURRENT);

            Calendar calendar = Calendar.getInstance();



            Utils.Log("Configuro alarm");

            //alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), 1000*60*60*24, pendingIntent);




            //Utils.Log("Orario: "+calendar.get(Calendar.HOUR_OF_DAY));
            if(calendar.get(Calendar.HOUR_OF_DAY)>19) calendar.add(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.HOUR, 8);
            calendar.set(Calendar.AM_PM, Calendar.PM);



            int currentapiVersion = android.os.Build.VERSION.SDK_INT;
            if (currentapiVersion >= android.os.Build.VERSION_CODES.KITKAT){
                // Do something for froyo and above versions
                alarmMgr.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            } else{
                // do something for phones running an SDK before kitkat
                alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }








            prefs.edit().putInt("alarmSetted", 1).commit();
            //Utils.Log(calendar.toString());

            //alarmMgr.cancel(PendingIntent.getBroadcast(this, 24, dialogIntent,PendingIntent.FLAG_CANCEL_CURRENT));
            //alarmMgr.cancel(PendingIntent.getBroadcast(this, 24, dialogIntent,PendingIntent.FLAG_NO_CREATE));
            //alarmMgr.cancel(PendingIntent.getBroadcast(this, 24, dialogIntent,PendingIntent.FLAG_UPDATE_CURRENT));

			/*
			boolean isWorking = (PendingIntent.getBroadcast(this, 0, dialogIntent, PendingIntent.FLAG_NO_CREATE) != null);//just changed the flag
			if (isWorking)
			{
				alarmMgr.cancel(PendingIntent.getBroadcast(this, 0, dialogIntent,PendingIntent.FLAG_UPDATE_CURRENT));
			}
			Utils.Log("alarm is " + (isWorking ? "" : "not") + " working...");

			if(!prefs.getBoolean("alarmSetted", false))
			{

			}


			if(!isWorking)
			{
				PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, dialogIntent,PendingIntent.FLAG_CANCEL_CURRENT);

				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.HOUR, 7);
				calendar.set(Calendar.AM_PM, Calendar.PM);

				Utils.Log("Configuro alarm");
		        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), 1000*60*60*24, pendingIntent);
			}
			*/

        }
    }


}
