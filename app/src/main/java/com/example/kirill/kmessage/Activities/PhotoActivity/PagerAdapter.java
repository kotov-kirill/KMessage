package com.example.kirill.kmessage.Activities.PhotoActivity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kirill.kmessage.Activities.PhotoActivity.Fragments.AbstractFragment;
import com.example.kirill.kmessage.Activities.PhotoActivity.Fragments.AlbumsFragment.AlbumsFragment;
import com.example.kirill.kmessage.Activities.PhotoActivity.Fragments.AllPhotosFragment.AllPhotosFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 10.02.2016.
 * @author Rakov Kirill
 */

public class PagerAdapter extends FragmentPagerAdapter{
    private Map<Integer, AbstractFragment> tabs;
    private Context context;
    public PagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.initTabs();
    }

    @Override
    public Fragment getItem(int position) {
        return this.tabs.get(position);
    }

    @Override
    public int getCount() {
        return this.tabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.tabs.get(position).getTitle();
    }

    private void initTabs() {
        this.tabs = new HashMap<>();
        this.tabs.put(0, AllPhotosFragment.getInstance(this.context));
        this.tabs.put(1, AlbumsFragment.getInstance(this.context));
    }
}
