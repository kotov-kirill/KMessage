package com.example.kirill.kmessage.Activities.FriendsActivity.Fragments.ListView;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kirill.kmessage.R;

import java.util.List;

/**
 * Created on 05.02.2016.
 * @author Rakov Kirill
 */

public class ListViewAdapter extends ArrayAdapter<Friend>{
    public ListViewAdapter(Context context, int resource, List<Friend> objects) {
        super(context, resource, objects);
    }
    static class ViewHolder{
        private View avatarId;
        //private TextView FIO;
        //private View isOnLine;
        private AppCompatCheckedTextView FIO;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder viewHolder;
        if(row == null){
            viewHolder = new ViewHolder();
            row = LayoutInflater.from(getContext()).inflate(R.layout.layout_list_view_friends, parent, false);
            viewHolder.avatarId = row.findViewById(R.id.avatar_id);
            //viewHolder.FIO = (TextView) row.findViewById(R.id.FIO);
            viewHolder.FIO = (AppCompatCheckedTextView) row.findViewById(R.id.FIO);
            //viewHolder.isOnLine = row.findViewById(R.id.is_on_line);
            row.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) row.getTag();
        viewHolder.avatarId.setBackgroundColor(this.getItem(position).getAvatarId());
        viewHolder.FIO.setText(this.getItem(position).getFIO());

        if(this.getItem(position).isOnLine())
            //viewHolder.isOnLine.setBackgroundResource(R.color.mainBackground);
            viewHolder.FIO.setChecked(true);
        else
            viewHolder.FIO.setChecked(false);
        return row;
    }
}