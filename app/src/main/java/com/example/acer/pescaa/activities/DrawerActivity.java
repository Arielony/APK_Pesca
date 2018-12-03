package com.example.acer.pescaa.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.acer.pescaa.R;
import com.example.acer.pescaa.fragments.CrudUsuarios;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        String userLogeado = getIntent().getExtras().getString("USER");
        String emailLogeado = getIntent().getExtras().getString("EMAIL");

        if(userLogeado != "" && userLogeado != null) {
            toolbar.setTitle(userLogeado);

        }

        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);



        // SETUP DRAWER INFO
        TextView userDrawer =  headerView.findViewById(R.id.labelDrawer);
        TextView emailDrawer = headerView.findViewById(R.id.emailDrawer);

        userDrawer.setText(userLogeado);
        emailDrawer.setText(emailLogeado);

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
        getMenuInflater().inflate(R.menu.drawer, menu);
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
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        FragmentManager fragmentManager = getSupportFragmentManager();



        if (id == R.id.crud_usuarios) {

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, PlaceholderFragment.newInstance(1))
                    .commit();

        } else if (id == R.id.crud_productos) {

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, PlaceholderFragment.newInstance(2))
                    .commit();

        } else if (id == R.id.ver_usuarios) {

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, PlaceholderFragment.newInstance(3))
                    .commit();

        } else if (id == R.id.ver_productos) {

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, PlaceholderFragment.newInstance(4))
                    .commit();

        } else if (id == R.id.sync) {

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, PlaceholderFragment.newInstance(5))
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:

                break;
            case 2:
                // your code
                break;
        }
    }




    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
        */

        public static android.support.v4.app.Fragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            if(getArguments() == null) {
                return inflater.inflate(R.layout.fragment_home, container, false);
            }


            View rootView = null;

            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    rootView = inflater.inflate(R.layout.fragment_crud_usuarios, container, false);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    rootView = inflater.inflate(R.layout.fragment_item_list, container, false);
                    break;
                case 5:
                    break;
                default:
                    rootView = inflater.inflate(R.layout.fragment_home, container, false);
            }


            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);

            if(activity != null) {
                if(getArguments() != null) {
                    ((DrawerActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
                }

            }


        }

    }

}
