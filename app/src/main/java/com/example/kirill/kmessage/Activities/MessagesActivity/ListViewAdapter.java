package com.example.kirill.kmessage.Activities.MessagesActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kirill.kmessage.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created on 23.01.2016.
 * @author Rakov Kirill
 */

public class ListViewAdapter extends ArrayAdapter<Message>{
    public ListViewAdapter(Context context, int resource, List<Message> objects) {
        super(context, resource, objects);
    }
    static class ViewHolder{
        private View avatarId;
        private TextView senderName;
        private TextView messageDate;
        private TextView lastMessage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder viewHolder;
        if(row == null){
            viewHolder = new ViewHolder();
            row = LayoutInflater.from(getContext()).inflate(R.layout.layout_list_view_message, parent, false);
            viewHolder.avatarId = row.findViewById(R.id.avatar_id);
            viewHolder.senderName = (TextView) row.findViewById(R.id.FIO);
            viewHolder.messageDate = (TextView) row.findViewById(R.id.message_date);
            viewHolder.lastMessage = (TextView) row.findViewById(R.id.last_message);
            row.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) row.getTag();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        viewHolder.avatarId.setBackgroundColor(this.getItem(position).getAvatarId());
        viewHolder.senderName.setText(this.getItem(position).getSenderName());
        viewHolder.lastMessage.setText(this.getItem(position).getLastMessage());
        viewHolder.messageDate.setText(format.format(this.getItem(position).getMessageDate()));
        return row;
    }
}
