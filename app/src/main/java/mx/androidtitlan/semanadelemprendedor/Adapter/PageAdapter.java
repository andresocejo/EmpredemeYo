package mx.androidtitlan.semanadelemprendedor.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Jhordan on 25/07/14.
 */
public class PageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;


    public PageAdapter(android.support.v4.app.FragmentManager fm, List<Fragment> fragments) {

        super(fm);
        // TODO Auto-generated constructor stub
        this.fragments = fragments;


    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  (10 + (position + 1)) + " DE AGO";
    }

    @Override
    public Fragment getItem(int position) {


        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


}
