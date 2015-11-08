package com.hackathonproject.Suggestion;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hackathonproject.NewsFeed.NewsFeedActivity;
import com.hackathonproject.R;
import com.hackathonproject.Routine.Routine;
import com.hackathonproject.Search.SearchResult;
import com.hackathonproject.Search.SearchService;
import com.hackathonproject.User.User;
import com.hackathonproject.User.UserService;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import lombok.Setter;

public class RoutineAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private SearchService searchService = new SearchService();

    @Setter
    private List<Routine> routineList;

    public RoutineAdapter(Context context) {
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public int getCount() {
        return routineList.size();
    }

    @Override
    public Object getItem(int i) {
        return routineList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = (convertView == null) ? setUpConvertView(parent) : convertView;
        final ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        Routine routine = routineList.get(position);
        SearchResult searchResult = searchService.getSearchResult(routine.getEntityID());
        int hourEnd = routine.getHour() + 1;
        viewHolder.timeTxtView.setText("Between " + routine.getHour() + " - " + hourEnd);
        viewHolder.titleTxtView.setText(searchResult.getName());

        return convertView;
    }

    private static class ViewHolder {
        public TextView timeTxtView;
        public TextView titleTxtView;
    }

    private View setUpConvertView(ViewGroup parent) {

        final ViewHolder holder = new ViewHolder();
        final View convertView = inflater.inflate(R.layout.item_routine, parent, false);

        holder.timeTxtView = (TextView) convertView.findViewById(R.id.timeTxtView);
        holder.titleTxtView = (TextView) convertView.findViewById(R.id.titleTxtView);

        convertView.setTag(holder);
        return convertView;
    }
}
