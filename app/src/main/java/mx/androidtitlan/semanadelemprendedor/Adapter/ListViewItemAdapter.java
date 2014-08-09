package mx.androidtitlan.semanadelemprendedor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import mx.androidtitlan.semanadelemprendedor.util.ListViewItemInterface;
import mx.androidtitlan.semanadelemprendedor.Model.ItemContentSection;
import mx.androidtitlan.semanadelemprendedor.Model.ItemSection;
import mx.androidtitlan.semanadelemprendedor.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jhordan on 20/07/14.
 */
public class ListViewItemAdapter extends BaseAdapter {

    private List<ListViewItemInterface> listViewItems;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public ListViewItemAdapter(List<ListViewItemInterface> listViewItems, Context mContext) {
        this.listViewItems = listViewItems;
        layoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
    }

    public ListViewItemAdapter(Context mContext){
        layoutInflater = LayoutInflater.from(mContext);
        this.listViewItems = new ArrayList<ListViewItemInterface>();
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return listViewItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listViewItems.indexOf(listViewItems.get(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View container = convertView;

        ListViewItemInterface item = listViewItems.get(position);

        if(item != null){
            if(item.isSection()){

                final ItemSection section = (ItemSection)item;

                container = layoutInflater.inflate(R.layout.section_layout_drawable,parent,false);
                container.setOnClickListener(null);
                container.setOnLongClickListener(null);

                final TextView titleSectionContainer = (TextView)container.findViewById(R.id.section_title);
                titleSectionContainer.setText(section.getSectionTitleFromResources());


            }else {
             ItemContentSection itemContentSection = (ItemContentSection)item;
                container = layoutInflater.inflate(R.layout.section_layout_drawable_icon, parent, false);

                final ImageView itemIcon = (ImageView) container.findViewById(R.id.icon_content_section);
                itemIcon.setImageResource(itemContentSection.getIcon());

                final TextView contentTitle = (TextView) container.findViewById(R.id.content_title);
                contentTitle.setText(itemContentSection.getContentTitleFromResources());
            }
        }

        return container;

    }
}
