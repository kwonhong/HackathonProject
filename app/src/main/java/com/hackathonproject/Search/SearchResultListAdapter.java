package com.hackathonproject.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.hackathonproject.R;

import java.util.List;

import lombok.Setter;

/**
 * Created by james on 15-08-20.
 */
public class SearchResultListAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private Context context;
    @Setter private List<SearchResult> searchResultList;

    public SearchResultListAdapter(Context context) {
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public int getCount() {
        return searchResultList.size();
    }

    @Override
    public Object getItem(int i) {
        return searchResultList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView =  (convertView == null) ? setUpConvertView(parent) : convertView;
        final ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        SearchResult searchResult = searchResultList.get(position);
        viewHolder.distance.setText(Double.toString(searchResult.getDistance()));
        viewHolder.title.setText(searchResult.getName());

        return convertView;
    }

    private static class ViewHolder {
        public TextView distance;
        public TextView title;
    }

    private View setUpConvertView(ViewGroup parent) {

        final ViewHolder holder = new ViewHolder();
        final View convertView = inflater.inflate(R.layout.list_item_icon_and_title, parent, false);

        holder.distance = (TextView) convertView.findViewById(R.id.distance);
        holder.title = (TextView) convertView.findViewById(R.id.name);
        convertView.setTag(holder);
        return convertView;
    }
}
