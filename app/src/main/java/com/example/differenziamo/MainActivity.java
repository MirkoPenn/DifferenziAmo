package com.example.differenziamo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Html;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.differenziamo.classes.CategoriaDifferenziata;
import com.example.differenziamo.classes.ElementoImageList;
import com.example.differenziamo.database.DBClass;
import com.example.differenziamo.fragments.*;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {


    private String mTitle = null;
    private ArrayList<CategoriaDifferenziata> queryResult;
    private FloatingActionButton fab = null;
    private int onItemClickMethod = 0;


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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
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
                Intent intent = new Intent(getApplicationContext(), CalendarioSettimanaActivity.class);
                startActivity(intent);
                }
            });
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

        } else if (id == R.id.nav_impostazioni) {

        } else if (id == R.id.nav_faq) {

        } else if (id == R.id.nav_credits) {

        }

        // change the content of the content_main Constraint Layout based on the chosen fragment class.
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();

        // change activity title
        setTitle(mTitle);

        // close the drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
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

                Intent intentDoveSiTrova = new Intent(this, DoveSiTrovaCategoriaActivity.class); 	//creo un oggetto di tipo intent
                Bundle bDoveSiTrova = new Bundle();
                bDoveSiTrova.putInt("idCategoria", position+1);    //metto nel bundle l'idCategoria
                intentDoveSiTrova.putExtras(bDoveSiTrova);
                startActivity(intentDoveSiTrova);


            break;

            case (R.string.a_servizi):


                break;
        }
    }


}
