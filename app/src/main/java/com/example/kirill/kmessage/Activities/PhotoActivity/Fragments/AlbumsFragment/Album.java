package com.example.kirill.kmessage.Activities.PhotoActivity.Fragments.AlbumsFragment;

/**
 * Created on 10.02.2016.
 * @author Rakov Kirill
 */

public class Album {
    private int drawableId;
    private String title;
    public Album(int drawableId, String name) {
        this.drawableId = drawableId;
        this.title = name;
    }

    public int getDrawableId() {
        return drawableId;
    }
    public String getTitle() {
        return title;
    }
}
