package com.example.kirill.kmessage.Special;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;

import com.example.kirill.kmessage.FriendsActivity.FriendsActivity;
import com.example.kirill.kmessage.MainActivity;
import com.example.kirill.kmessage.MessagesActivity.MessagesActivity;
import com.example.kirill.kmessage.ProfileActivity;
import com.example.kirill.kmessage.R;
import com.example.kirill.kmessage.SettingsActivity;

/**
 * Created on 04.02.2016.
 * @author Rakov Kirill
 */

public class MenuReceiver {
    public static void menuReceiver(Activity activity, MenuItem item){
        Class aClass = null;
        switch (item.getItemId()) {
            case R.id.menu_navigation_profile:
                aClass = ProfileActivity.class;
                break;
            case R.id.menu_navigation_news:
                aClass = MainActivity.class;
                break;
            case R.id.menu_navigation_messages:
                aClass = MessagesActivity.class;
                break;
            case R.id.menu_navigation_friends:
                aClass = FriendsActivity.class;
                break;
            case R.id.menu_navigation_settings:
                aClass = SettingsActivity.class;
                break;
        }
        if(aClass == activity.getClass())
            return;
        Intent intent = new Intent(activity, aClass);
        activity.startActivity(intent);
    }
}
