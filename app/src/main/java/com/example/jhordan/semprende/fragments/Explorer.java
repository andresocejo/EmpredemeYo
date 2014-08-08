package com.example.jhordan.semprende.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.jhordan.semprende.Adapter.PageAdapter;
import com.example.jhordan.semprende.MyActivity;
import com.example.jhordan.semprende.NavigationDrawerFragment;
import com.example.jhordan.semprende.R;
import com.example.jhordan.semprende.util.Event;
import com.viewpagerindicator.TabPageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jhordan on 04/08/14.
 */
public class Explorer extends Fragment {

    private String [] DAYS;

    private PagerAdapter mPagerAdapter;
    private ViewPager mviewPager;
    private List<Fragment> listaFragments;
    private TabPageIndicator mIndicator;
    // private PageIndicator mIndicatores;
    private TitlePageIndicator mIndicatores;



    public static Explorer newInstance(int position) {
        Explorer myexplore = new Explorer();
        Bundle extraArguments = new Bundle();
        extraArguments.putInt(NavigationDrawerFragment.ARG_SECTION_NUMBER, position);
        myexplore.setArguments(extraArguments);
        return myexplore;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.explorer_fragment,container,false);

        listaFragments = new ArrayList<Fragment>();

        listaFragments.add(ListEvents.newInstance("Explorer",DAYS[0]));
        listaFragments.add(ListEvents.newInstance("Explorer",DAYS[1]));
        listaFragments.add(ListEvents.newInstance("Explorer",DAYS[2]));
        listaFragments.add(ListEvents.newInstance("Explorer",DAYS[3]));
        listaFragments.add(ListEvents.newInstance("Explorer",DAYS[4]));


        // Creamos nuestro Adapter
        mPagerAdapter = new PageAdapter(getActivity().getSupportFragmentManager(),
                listaFragments);


        // Instanciamos nuestro ViewPager
        mviewPager = (ViewPager) v.findViewById(R.id.pager);


        // Establecemos el Adapter
        mviewPager.setAdapter(mPagerAdapter);

        //mIndicator = (TabPageIndicator) v.findViewById(R.id.indicator);
        //mIndicator.setViewPager(mviewPager);

        mIndicatores = (TitlePageIndicator) v.findViewById(R.id.indicators);
        mIndicatores.setViewPager(mviewPager);

        mIndicatores.setFooterColor(Color.parseColor("#00e575"));


        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MyActivity) activity).onSectionAttached(getArguments()
                .getInt(NavigationDrawerFragment.ARG_SECTION_NUMBER));

        DAYS = getResources().getStringArray(R.array.days_events);
    }


}
