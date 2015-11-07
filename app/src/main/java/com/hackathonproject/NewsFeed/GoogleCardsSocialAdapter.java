package com.hackathonproject.NewsFeed;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackathonproject.R;
import com.hackathonproject.Routine.Routine;
import com.hackathonproject.Search.SearchActivity;
import com.hackathonproject.User.User;

import lombok.Setter;


public class GoogleCardsSocialAdapter extends BaseAdapter{

	private LayoutInflater inflater;
	private Context context;
	@Setter
	private List<Routine> routineList;

	public GoogleCardsSocialAdapter(Context context) {
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
		final ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(
					R.layout.list_item_google_cards_social, parent, false);
			holder = new ViewHolder();
			holder.image = (ImageView) convertView
					.findViewById(R.id.list_item_google_cards_social_image);
			holder.text = (TextView) convertView
					.findViewById(R.id.list_item_google_cards_social_text);
			holder.detailBtn = (TextView) convertView.findViewById(R.id.detailBtn);
			holder.detailBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(context, DetailNewsActivity.class);
					context.startActivity(intent);
				}
			});

			holder.suggestBtn = (TextView) convertView.findViewById(R.id.suggestBtn);
			holder.suggestBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(context, SearchActivity.class);
					context.startActivity(intent);
				}
			});

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}




		Routine routine = routineList.get(position);
		holder.text.setText(R.string.lorem_ipsum_short);
		holder.like.setTag(position);
		holder.follow.setTag(position);

		return convertView;
	}

	private static class ViewHolder {
		public TextView detailBtn;
		public TextView suggestBtn;


		public ImageView profileImage;
		public ImageView image;
		public TextView username;
		public TextView place;
		public TextView text;
		public TextView like;
		public TextView follow;
	}
}
