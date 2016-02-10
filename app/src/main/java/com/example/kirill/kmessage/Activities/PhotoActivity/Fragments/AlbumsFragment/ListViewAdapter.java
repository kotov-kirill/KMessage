package com.example.kirill.kmessage.Activities.PhotoActivity.Fragments.AlbumsFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kirill.kmessage.R;

import java.util.List;

/**
 * Created on 10.02.2016.
 * @author Rakov Kirill
 */

public class ListViewAdapter extends ArrayAdapter<Album> {
    public ListViewAdapter(Context context, int resource, List<Album> objects) {
        super(context, resource, objects);
    }
    private static class ViewHolder{
        private TextView title;
        private View drawable;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(this.getContext()).inflate(R.layout.layout_list_view_albums, parent, false);
            viewHolder.title = (TextView) view.findViewById(R.id.title);
            viewHolder.drawable = view.findViewById(R.id.drawable);
            view.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) view.getTag();
        viewHolder.title.setText(this.getItem(position).getTitle());
        viewHolder.drawable.setBackgroundResource(this.getItem(position).getDrawableId());
        return view;
    }
}
