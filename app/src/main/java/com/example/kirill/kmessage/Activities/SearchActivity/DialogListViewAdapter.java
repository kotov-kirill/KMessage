package com.example.kirill.kmessage.Activities.SearchActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kirill.kmessage.R;

/**
 * Created on 09.02.2016.
 * @author Rakov Kirill
 */

public class DialogListViewAdapter extends ArrayAdapter<DialogListItem> {
    public DialogListViewAdapter(Context context, int resource, DialogListItem[] objects) {
        super(context, resource, objects);
    }
    private class ViewHolder{
        private TextView textView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder viewHolder;
        if(row == null){
            viewHolder = new ViewHolder();
            row = LayoutInflater.from(getContext()).inflate(R.layout.layout_list_view_dialog_search, parent, false);
            viewHolder.textView = (TextView) row.findViewById(R.id.dialog_item);
            row.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) row.getTag();
        viewHolder.textView.setText(this.getItem(position).getItem());
        viewHolder.textView.setCompoundDrawablesWithIntrinsicBounds(this.getItem(position).getDrawableId(), 0, 0, 0);
        return row;
    }
}
