package com.example.jhordan.semprende.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jhordan.semprende.MyActivity;
import com.example.jhordan.semprende.NavigationDrawerFragment;
import com.example.jhordan.semprende.R;

/**
 * Created by Jhordan on 04/08/14.
 */
public class Schedule extends Fragment {


    public static Schedule newInstance(int position) {
        Schedule myschedule = new Schedule();
        Bundle extraArguments = new Bundle();
        extraArguments.putInt(NavigationDrawerFragment.ARG_SECTION_NUMBER, position);
        myschedule.setArguments(extraArguments);
        return myschedule;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.schedule_fragment,container,false);

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MyActivity) activity).onSectionAttached(getArguments()
                .getInt(NavigationDrawerFragment.ARG_SECTION_NUMBER));
    }
}
