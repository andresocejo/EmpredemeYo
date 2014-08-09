package mx.androidtitlan.semanadelemprendedor.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import mx.androidtitlan.semanadelemprendedor.Adapter.PageAdapter;
import mx.androidtitlan.semanadelemprendedor.Model.SpinnerModelBar;
import mx.androidtitlan.semanadelemprendedor.MyActivity;
import mx.androidtitlan.semanadelemprendedor.Adapter.SpinnerAdapterBar;
import mx.androidtitlan.semanadelemprendedor.NavigationDrawerFragment;
import mx.androidtitlan.semanadelemprendedor.R;
import com.viewpagerindicator.TitlePageIndicator;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jhordan on 04/08/14.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Schedule extends android.support.v4.app.Fragment implements android.app.ActionBar.OnNavigationListener {

    private ArrayList<SpinnerModelBar> navSpinner;
    private String [] DAYS;

    // Navigation adapter
    private SpinnerAdapterBar adapter;

    private PagerAdapter mPagerAdapter;
    private ViewPager mviewPager;
    private List<Fragment> listaFragments;
    private TitlePageIndicator mIndicatores;


    public static Schedule newInstance(int position) {
        Schedule myschedule = new Schedule();
        Bundle extraArguments = new Bundle();
        extraArguments.putInt(NavigationDrawerFragment.ARG_SECTION_NUMBER, position);
        myschedule.setArguments(extraArguments);
        return myschedule;
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.schedule_fragment, container, false);

        listaFragments = new ArrayList<Fragment>();
        listaFragments.add(ListEvents.newInstance("Schedule",DAYS[0]));
        listaFragments.add(ListEvents.newInstance("Schedule",DAYS[1]));
        listaFragments.add(ListEvents.newInstance("Schedule",DAYS[2]));
        listaFragments.add(ListEvents.newInstance("Schedule",DAYS[3]));
        listaFragments.add(ListEvents.newInstance("Schedule",DAYS[4]));


        // Creamos nuestro Adapter
        mPagerAdapter = new PageAdapter(getActivity().getSupportFragmentManager(),
                listaFragments);


        // Instanciamos nuestro ViewPager
        mviewPager = (ViewPager) v.findViewById(R.id.pager);


        // Establecemos el Adapter
        mviewPager.setAdapter(mPagerAdapter);
        mviewPager.setOffscreenPageLimit(5);

        //mIndicator = (TabPageIndicator) v.findViewById(R.id.indicator);
        //mIndicator.setViewPager(mviewPager);

        mIndicatores = (TitlePageIndicator) v.findViewById(R.id.indicators);
        mIndicatores.setViewPager(mviewPager);

        mIndicatores.setFooterColor(Color.parseColor("#00e575"));


        return v;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void spinnerBar() {

        android.app.ActionBar bar;

        bar = getActivity().getActionBar();


        bar.setDisplayShowTitleEnabled(false);

        bar.setTitle("");


        bar.setNavigationMode(android.app.ActionBar.NAVIGATION_MODE_LIST);

        navSpinner = new ArrayList<SpinnerModelBar>();
        navSpinner.add(new SpinnerModelBar("Conference", 0));
        navSpinner.add(new SpinnerModelBar("Conference", R.drawable.ic_launcher));
        navSpinner.add(new SpinnerModelBar("Conference", R.drawable.ic_launcher));
        navSpinner.add(new SpinnerModelBar("Conference", R.drawable.ic_launcher));


        // title drop down adapter
        adapter = new SpinnerAdapterBar(getActivity().getApplicationContext(), navSpinner);


        // assigning the spinner navigation
        bar.setListNavigationCallbacks(adapter, new android.app.ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {

                String hola = Integer.toString(itemPosition);

                Toast.makeText(getActivity(), hola, Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MyActivity) activity).onSectionAttached(getArguments()
                .getInt(NavigationDrawerFragment.ARG_SECTION_NUMBER));

        DAYS = getResources().getStringArray(R.array.days_events);

    }




    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        return false;
    }
}
