package com.example.kirill.kmessage.Activities.PhotoActivity.Fragments.AlbumsFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kirill.kmessage.Activities.PhotoActivity.Fragments.AllPhotosFragment.Constants;
import com.example.kirill.kmessage.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created on 10.02.2016.
 * @author Rakov Kirill
 */

public class ListViewAdapter extends ArrayAdapter<Album> {
    private static final String[] IMAGE_URLS = Constants.IMAGES;
    private final DisplayImageOptions options;

    public ListViewAdapter(Context context, int resource, List<Album> objects) {
        super(context, resource, objects);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(android.R.color.darker_gray)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }
    private static class ViewHolder{
        private TextView title;
        private ImageView drawable;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(this.getContext()).inflate(R.layout.layout_list_view_albums, parent, false);
            viewHolder.title = (TextView) view.findViewById(R.id.title);
            viewHolder.drawable = (ImageView) view.findViewById(R.id.drawable);
            view.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) view.getTag();
        viewHolder.title.setText(this.getItem(position).getTitle());
        ImageLoader.getInstance().displayImage(IMAGE_URLS[position], viewHolder.drawable, options);
        return view;
    }
}
