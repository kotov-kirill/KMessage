package com.example.kirill.kmessage.Activities.PhotoActivity.Fragments;

import android.support.v4.app.Fragment;

/**
 * Created on 10.02.2016.
 * @author Rakov Kirill
 */

public abstract class AbstractFragment extends Fragment {
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
}
