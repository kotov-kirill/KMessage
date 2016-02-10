package com.example.kirill.kmessage.Activities.PhotoActivity.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kirill.kmessage.R;

/**
 * Created on 10.02.2016.
 * @author Rakov Kirill
 */

public class AllPhotosFragment extends AbstractFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photos_all_layout, container, false);
        return view;
    }
    public static AllPhotosFragment getInstance(Context context){
        AllPhotosFragment allPhotosFragment = new AllPhotosFragment();
        allPhotosFragment.setTitle(context.getString(R.string.tab_item_all_photos));
        return allPhotosFragment;
    }
}
