package com.example.kirill.kmessage.Special;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;

import com.example.kirill.kmessage.Activities.FriendsActivity.NewFriendActivity;
import com.example.kirill.kmessage.Activities.MessagesActivity.NewMessageActivity;
import com.example.kirill.kmessage.Activities.NewNoteActivity;
import com.example.kirill.kmessage.Activities.SearchActivity.SearchActivity;
import com.example.kirill.kmessage.R;

/**
 * Created on 18.02.2016.
 * @author Rakov Kirill
 */

public class MenuReceiver {
    public static void menuReceiver(Activity activity, MenuItem item){
        Class aClass = null;
        switch (item.getItemId()) {
            case R.id.action_search:
                aClass = SearchActivity.class;
                break;
            case R.id.action_new_note:
                aClass = NewNoteActivity.class;
                break;
            case R.id.action_new_message:
                aClass = NewMessageActivity.class;
                break;
            case R.id.action_new_friend:
                aClass = NewFriendActivity.class;
                break;
        }

        Intent intent = new Intent(activity, aClass);
        activity.startActivity(intent);
    }
}
