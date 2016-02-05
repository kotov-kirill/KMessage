package com.example.kirill.kmessage.FriendsActivity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.example.kirill.kmessage.FriendsActivity.Fragments.AbstractFragment;
import com.example.kirill.kmessage.FriendsActivity.Fragments.AllFriendsFragment;
import com.example.kirill.kmessage.FriendsActivity.Fragments.OnLineFriendsFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 22.01.2016.
 * @author Rakov Kirill
 */

public class PagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private Map<Integer, AbstractFragment> tabs;
    public PagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.tabs = new HashMap<>();
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
        this.tabs.put(0, AllFriendsFragment.getInstance(this.context));
        this.tabs.put(1, OnLineFriendsFragment.getInstance(this.context));
    }
}
