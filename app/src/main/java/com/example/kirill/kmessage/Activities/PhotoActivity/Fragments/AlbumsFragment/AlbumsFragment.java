package com.example.kirill.kmessage.Activities.PhotoActivity.Fragments.AlbumsFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.kirill.kmessage.Activities.PhotoActivity.Fragments.AbstractFragment;
import com.example.kirill.kmessage.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 10.02.2016.
 * @author Rakov Kirill
 */

public class AlbumsFragment extends AbstractFragment {
    private View view;
    private ListView listView;
    private List<Album> albums;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_photo_albums_layout, container, false);
        this.initComponents();
        return view;
    }

    private void initComponents() {
        this.albums = new ArrayList<Album>(){
            {
                add(new Album(R.drawable.about, "Material Design Wallpapers"));
                add(new Album(R.drawable.design_wallpaper, "Design Wallpapers"));
                add(new Album(R.drawable.design_background, "Design Backgrounds"));
            }
        };

        this.listView = (ListView) this.view.findViewById(R.id.list_view);
        this.listView.setAdapter(new ListViewAdapter(this.getContext(), R.layout.layout_list_view_albums, this.albums));
        this.listView.setMultiChoiceModeListener(new MultiChoiceModeListenerImpl(this.getContext(), this.listView));
    }

    public static AlbumsFragment getInstance(Context context){
        AlbumsFragment albumsFragment = new AlbumsFragment();
        albumsFragment.setTitle(context.getString(R.string.tab_item_albums));
        return albumsFragment;
    }
}
