package com.hackathonproject.Login;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hackathonproject.Routine.NewsFeedActivity;
import com.hackathonproject.Suggestion.SuggestionActivity;
import com.hackathonproject.R;
import com.hackathonproject.User.User;
import com.hackathonproject.User.UserService;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import lombok.Setter;

public class SelectUserListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    @Setter
    private List<User> userList;

    public SelectUserListAdapter(Context context) {
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = (convertView == null) ? setUpConvertView(parent) : convertView;
        final ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        final User user = userList.get(position);
        viewHolder.name.setText("Name: " + user.getUserName());
        viewHolder.type.setText("Type: " + User.LoginType.getLoginType(user.getLoggedInType()).toString());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User.LoginType loginType = User.LoginType.getLoginType(user.getLoggedInType());
                switch (loginType) {
                    case FOLLOWING:
                        Intent intent = new Intent(context, NewsFeedActivity.class);
                        UserService.selectedUser = user;
                        context.startActivity(intent);
                        break;

                    case USER:
                        Intent suggestionIntent = new Intent(context, SuggestionActivity.class);
                        UserService.selectedUser = user;
                        context.startActivity(suggestionIntent);
                        break;
                }

            }
        });

        return convertView;
    }

    private static class ViewHolder {
        public CircleImageView imageView;
        public TextView type;
        public TextView name;
        public TextView btnLogin;
    }

    private View setUpConvertView(ViewGroup parent) {

        final ViewHolder holder = new ViewHolder();
        final View convertView = inflater.inflate(R.layout.list_item_expandable_dashboard_group, parent, false);

        holder.imageView = (CircleImageView) convertView.findViewById(R.id.imageView);
        holder.type = (TextView) convertView.findViewById(R.id.typeTxtView);
        holder.name = (TextView) convertView.findViewById(R.id.nameTxtView);
        holder.btnLogin = (TextView) convertView.findViewById(R.id.loginBtn);

        convertView.setTag(holder);
        return convertView;
    }
}
