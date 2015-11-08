package com.hackathonproject.Suggestion;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackathonproject.R;
import com.hackathonproject.Search.SearchCategory;
import com.hackathonproject.Search.SearchResult;

import org.joda.time.DateTime;

import java.util.List;
import java.util.Random;

import lombok.Setter;

public class SuggestionListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    @Setter
    private List<SearchResult> searchResultList;

    public SuggestionListAdapter(Context context) {
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

        final SearchResult searchResult = searchResultList.get(position);
        if (searchResult != null) {

            viewHolder.imageView.setImageDrawable(context.getResources().getDrawable(SearchCategory.sampleDrawbles.get(new Random().nextInt(SearchCategory.sampleDrawbles.size()))));
            viewHolder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            String string = searchResult.getName() + ":\nPlease consider our alternative option. " +
                    "The alternative has been chosen based on your routine and preferences. Press for more information";
            viewHolder.titleTxtView.setText(string);

        }



        return convertView;
    }

    private static class ViewHolder {
        public ImageView imageView;
        public TextView titleTxtView;
    }

    private View setUpConvertView(ViewGroup parent) {

        final ViewHolder holder = new ViewHolder();
        final View convertView = inflater.inflate(R.layout.list_item_suggestion, parent, false);

        holder.titleTxtView = (TextView) convertView.findViewById(R.id.titleTxtView);
        holder.imageView = (ImageView) convertView.findViewById(R.id.imgView);
        convertView.setTag(holder);
        return convertView;
    }
}

