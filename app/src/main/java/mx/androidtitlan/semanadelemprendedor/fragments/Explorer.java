package mx.androidtitlan.semanadelemprendedor.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.TitlePageIndicator;

import java.util.ArrayList;
import java.util.List;

import mx.androidtitlan.semanadelemprendedor.Adapter.PageAdapter;
import mx.androidtitlan.semanadelemprendedor.MyActivity;
import mx.androidtitlan.semanadelemprendedor.NavigationDrawerFragment;
import mx.androidtitlan.semanadelemprendedor.R;

/**
 * Created by Jhordan on 04/08/14.
 */
public class Explorer extends Fragment implements DialogFilterEvents.UpdateList{

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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.explorer_fragment,container,false);
        setHasOptionsMenu(true);

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_mapa, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.filter_map:
                DialogFilterEvents dialogFilterEvents = new DialogFilterEvents();
                dialogFilterEvents.show(getChildFragmentManager(), null);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void recreateList(String eco, int id) {
        for (int i = 0; i<5; i++){
            ((ListEvents)listaFragments.get(i)).sortByEco(eco,id);
        }
    }

    /*public void requestEvents() {

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        final String url = getResources().getString(R.string.url_conferencias);
        String dataFromFile = readFileAsString("events.txt");
        if (!dataFromFile.isEmpty()) {
            JSONObject json = null;
            try {
                json = new JSONObject(dataFromFile);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.wtf("***", "Error: " + e);
            }
            //TODO: Populate everything here
            Log.e("***", "getting data from file: " + json.toString());
        } else {
            JsonObjectRequest getEvents = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {

                        //TODO: REFACTOR THIS IN ORDER TO EXECUTE INSIDE THEIR PARENT FRAGMENT.
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                writeToFile(response);


                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("Error", error.toString());
                        }
                    }
            );

            queue.add(getEvents);
        }
    }*/

    /*private void writeToFile(JSONObject agenda) throws IOException {
        String d = agenda.toString();
        File folder = new File(Environment.getExternalStorageDirectory() + "/sde2014");
        folder.mkdir();
        File file = new File(folder, "events.txt");
        //BufferedWriter buffer= null;
        if (folder.exists()) {
            if (file.exists()) {
                Log.e("WriteToFile", "File exists");
            } else {
                file.createNewFile();
                BufferedWriter writter = new BufferedWriter(new FileWriter(file));
                writter.write(d);
                writter.flush();
                writter.close();
                Log.e("writeToFile", "Saved data: " + d);
            }
        } else {
            folder.mkdir();
            file.createNewFile();
            BufferedWriter writter = new BufferedWriter(new FileWriter(file));
            writter.write(d);
            writter.flush();
            writter.close();
            Log.e("writeToFile", "Saved data: " + d);
        }
    }*/

   /* public String readFileAsString(String fileName) {
        Context context = getActivity().getApplicationContext();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(new File(Environment.getExternalStorageDirectory() + "/sde2014/", fileName)));
            while ((line = in.readLine()) != null) stringBuilder.append(line);

        } catch (FileNotFoundException e) {
            Log.e("readFileAsString", e.toString());
        } catch (IOException e) {
            Log.e("readFileAsString", e.toString());
        }

        return stringBuilder.toString();
    }*/
}
