package mx.androidtitlan.semanadelemprendedor;

import android.app.Activity;
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

import mx.androidtitlan.semanadelemprendedor.Model.SpinnerModelBar;
import mx.androidtitlan.semanadelemprendedor.fragments.Explorer;
import mx.androidtitlan.semanadelemprendedor.fragments.Map;
import mx.androidtitlan.semanadelemprendedor.fragments.Streaming;
import mx.androidtitlan.semanadelemprendedor.Adapter.SpinnerAdapterBar;
import mx.androidtitlan.semanadelemprendedor.fragments.Schedule;

import java.util.ArrayList;


public class MyActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private ArrayList<SpinnerModelBar> navSpinner;

    // Navigation adapter
    private SpinnerAdapterBar adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

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
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
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