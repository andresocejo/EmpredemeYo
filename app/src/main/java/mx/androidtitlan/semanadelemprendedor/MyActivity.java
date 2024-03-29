package mx.androidtitlan.semanadelemprendedor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import mx.androidtitlan.semanadelemprendedor.Adapter.SpinnerAdapterBar;
import mx.androidtitlan.semanadelemprendedor.Model.SpinnerModelBar;
import mx.androidtitlan.semanadelemprendedor.fragments.Explorer;
import mx.androidtitlan.semanadelemprendedor.fragments.Map;
import mx.androidtitlan.semanadelemprendedor.fragments.Schedule;
import mx.androidtitlan.semanadelemprendedor.fragments.Streaming;
import mx.androidtitlan.semanadelemprendedor.main.Login_Screen;


public class MyActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    SharedPreferences prefs;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    public static RequestQueue requestQueue;

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    private ArrayList<SpinnerModelBar> navSpinner;

    // Navigation adapter
    private SpinnerAdapterBar adapter;
    private int fragSelect = -1;

    private static String FRAG_SELECT = "frag_selec";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        requestQueue = Volley.newRequestQueue(this);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        if (savedInstanceState != null) {
            fragSelect = savedInstanceState.getInt(FRAG_SELECT, -1);
        }

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        if (fragSelect == position) {
            return;
        } else {
            fragSelect = position;
        }

        switch (position) {
            case 0:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, Schedule.newInstance(position))
                        .commit();

                break;

            case 1:

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, Explorer.newInstance(position))
                        .commit();


                break;

            case 2:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, Streaming.newInstance(position))
                        .commit();


                break;

            case 3:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, Map.newInstance(position))
                        .commit();


                break;
            case 4:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, Schedule.newInstance(position))
                        .commit();


                break;
            default:

        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(FRAG_SELECT, -1);

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 0:
                mTitle = getString(R.string.schedule);
                break;
            case 1:
                mTitle = getString(R.string.explorer);
                break;
            case 2:
                mTitle = getString(R.string.streaming);
                break;
            case 3:
                mTitle = getString(R.string.map);
                break;
            case 4:
                mTitle = getString(R.string.settings);
                break;
            default:
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            restoreActionBar();
            getMenuInflater().inflate(R.menu.my, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){

            case R.id.action_session:
                Intent intento = new Intent(MyActivity.this,Login_Screen.class);
                startActivity(intento);
                SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.commit();
                break;


        }
        return super.onOptionsItemSelected(item);
    }



        // Inflate the menu; this adds items to the action bar if it is present.
        // le paso menu azul que es el menu que cree el activitymenu



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
        public static PlaceholderFragment newInstance(int sectionNumber) {
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
            View rootView = inflater.inflate(R.layout.fragment_my, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MyActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }


}
