package com.example.kirill.kmessage.FriendsActivity.Fragments;

import android.support.v4.app.Fragment;

/**
 * Created on 22.01.2016.
 * @author Rakov Kirill
 */

abstract public class AbstractFragment extends Fragment {
    private String title;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
