package com.example.kirill.kmessage.Activities.SearchActivity;

/**
 * Created on 09.02.2016.
 * @author Rakov Kirill
 */

public class DialogListItem {
    private int drawableId;
    private String item;
    public DialogListItem(int drawableId, String item) {
        this.drawableId = drawableId;
        this.item = item;
    }

    public int getDrawableId() {
        return drawableId;
    }
    public String getItem() {
        return item;
    }
}
