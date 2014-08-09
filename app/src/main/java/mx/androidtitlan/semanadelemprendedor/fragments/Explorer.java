package mx.androidtitlan.semanadelemprendedor.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.androidtitlan.semanadelemprendedor.Adapter.PageAdapter;
import mx.androidtitlan.semanadelemprendedor.MyActivity;
import mx.androidtitlan.semanadelemprendedor.NavigationDrawerFragment;
import mx.androidtitlan.semanadelemprendedor.R;
import com.viewpagerindicator.TitlePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jhordan on 04/08/14.
 */
public class Explorer extends Fragment {

    private String [] DAYS;

    private PagerAdapter mPagerAdapter;
    private ViewPager mviewPager;
    private List<Fragment> listaFragments;
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

        DAYS = new String[]{"Lunes, 11 de Agosto","Martes, 12 de Agosto","Jueves, 14 de Agosto","Miércoles, 13 de Agosto","Viernes, 15 de Agosto"};


        listaFragments = new ArrayList<Fragment>();

        for(int i = 0; i<5; i++){

            listaFragments.add(ListEvents.newInstance("Explorer",DAYS[i]));

        }

        // Creamos nuestro Adapter
        mPagerAdapter = new PageAdapter(getActivity().getSupportFragmentManager(),
                listaFragments);


        // Instanciamos nuestro ViewPager
        mviewPager = (ViewPager) v.findViewById(R.id.pager);
        mviewPager.setOffscreenPageLimit(5);


        // Establecemos el Adapter
        mviewPager.setAdapter(mPagerAdapter);

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

    }


}
