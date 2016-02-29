package com.example.kirill.kmessage.Special;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.kirill.kmessage.Activities.SettingsActivity.CommonSettingsActivity;
import com.example.kirill.kmessage.R;

/**
 * Created on 22.02.2016.
 * @author Rakov Kirill
 */

public class ApplicationThemeSetter {
    public static final int APP_THEME_PURPLE = 0;
    public static final int APP_THEME_RED = 1;
    public static final int APP_THEME_DEEP_PURPLE = 2;
    public static final int APP_THEME_INDIGO = 3;
    public static final int APP_THEME_GREEN = 4;
    public static final int APP_THEME_ORANGE = 5;
    public static final int APP_THEME_DEEP_ORANGE = 6;

    private static SharedPreferences preferences;
    public static void themeSetter(Activity activity){
        preferences = activity.getSharedPreferences(
                CommonSettingsActivity.APP_COMMON_SETTINGS_PREFERENCES, Context.MODE_PRIVATE);
        int themeId = 0;
        switch (preferences.getInt(CommonSettingsActivity.APP_THEME_PREFERENCE, APP_THEME_PURPLE)){
            case APP_THEME_PURPLE:
                themeId = R.style.AppTheme_Purple;
                break;
            case APP_THEME_RED:
                themeId = R.style.AppTheme_Red;
                break;
            case APP_THEME_DEEP_PURPLE:
                themeId = R.style.AppTheme_DeepPurple;
                break;
            case APP_THEME_INDIGO:
                themeId = R.style.AppTheme_Indigo;
                break;
            case APP_THEME_GREEN:
                themeId = R.style.AppTheme_Green;
                break;
            case APP_THEME_ORANGE:
                themeId = R.style.AppTheme_Orange;
                break;
            case APP_THEME_DEEP_ORANGE:
                themeId = R.style.AppTheme_DeepOrange;
                break;
        }
        activity.setTheme(themeId);
    }
    public static void styleNavigationHeaderView(NavigationView navigationView){
        int resId = 0;
        switch (preferences.getInt(CommonSettingsActivity.APP_THEME_PREFERENCE, APP_THEME_PURPLE)){
            case APP_THEME_PURPLE:
                resId = R.drawable.navigation_header_purple;
                break;
            case APP_THEME_RED:
                resId = R.drawable.navigation_header_red;
                break;
            case APP_THEME_DEEP_PURPLE:
                resId = R.drawable.navigation_header_deep_purple;
                break;
            case APP_THEME_INDIGO:
                resId = R.drawable.navigation_header_indigo;
                break;
            case APP_THEME_GREEN:
                resId = R.drawable.navigation_header_green;
                break;
            case APP_THEME_ORANGE:
                resId = R.drawable.navigation_header_orange;
                break;
            case APP_THEME_DEEP_ORANGE:
                resId = R.drawable.navigation_header_deep_orange;
                break;
        }
        View headerView = navigationView.getHeaderView(0);
        if(headerView != null) {
            View headerContainer = headerView.findViewById(R.id.navigation_header_container);
            if (headerContainer != null)
                headerContainer.setBackgroundResource(resId);
        }
    }
    public static void styleActionModeMenuItems(Menu menu){
        int resIdSave = 0;
        int resIdDelete = 0;
        switch (preferences.getInt(CommonSettingsActivity.APP_THEME_PREFERENCE, APP_THEME_PURPLE)){
            case APP_THEME_PURPLE:
                resIdSave = R.drawable.ic_content_save_purple;
                resIdDelete = R.drawable.ic_delete_purple;
                break;
            case APP_THEME_RED:
                resIdSave = R.drawable.ic_content_save_red;
                resIdDelete = R.drawable.ic_delete_red;
                break;
            case APP_THEME_DEEP_PURPLE:
                resIdSave = R.drawable.ic_content_save_deep_purple;
                resIdDelete = R.drawable.ic_delete_deep_purple;
                break;
            case APP_THEME_INDIGO:
                resIdSave = R.drawable.ic_content_save_deep_purple;
                resIdDelete = R.drawable.ic_delete_deep_purple;
                break;
            case APP_THEME_GREEN:
                resIdSave = R.drawable.ic_content_save_green;
                resIdDelete = R.drawable.ic_delete_green;
                break;
            case APP_THEME_ORANGE:
                resIdSave = R.drawable.ic_content_save_orange;
                resIdDelete = R.drawable.ic_delete_orange;
                break;
            case APP_THEME_DEEP_ORANGE:
                resIdSave = R.drawable.ic_content_save_deep_orange;
                resIdDelete = R.drawable.ic_delete_deep_orange;
                break;
        }
        MenuItem itemSave = menu.findItem(R.id.action_save);
        if(itemSave != null)
            itemSave.setIcon(resIdSave);
        MenuItem itemDelete = menu.findItem(R.id.action_delete);
        if (itemDelete != null)
            itemDelete.setIcon(resIdDelete);
    }
    public static void styleButtonExit(Activity activity, Button button){
        Drawable drawable = null;
        switch (preferences.getInt(CommonSettingsActivity.APP_THEME_PREFERENCE, APP_THEME_PURPLE)){
            case APP_THEME_PURPLE:
                drawable = activity.getResources().getDrawable(R.drawable.button_exit_profile_background_purple);
                break;
            case APP_THEME_RED:
                drawable = activity.getResources().getDrawable(R.drawable.button_exit_profile_background_red);
                break;
            case APP_THEME_DEEP_PURPLE:
                drawable = activity.getResources().getDrawable(R.drawable.button_exit_profile_background_deep_purple);
                break;
            case APP_THEME_INDIGO:
                drawable = activity.getResources().getDrawable(R.drawable.button_exit_profile_background_deep_purple);
                break;
            case APP_THEME_GREEN:
                drawable = activity.getResources().getDrawable(R.drawable.button_exit_profile_background_green);
                break;
            case APP_THEME_ORANGE:
                drawable = activity.getResources().getDrawable(R.drawable.button_exit_profile_background_orange);
                break;
            case APP_THEME_DEEP_ORANGE:
                drawable = activity.getResources().getDrawable(R.drawable.button_exit_profile_background_deep_orange);
                break;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            button.setBackground(drawable);
    }

    public static void styleAlertDialogDivider(Activity activity, AlertDialog alertDialog){
        int resId = 0;
        switch (preferences.getInt(CommonSettingsActivity.APP_THEME_PREFERENCE, APP_THEME_PURPLE)){
            case APP_THEME_PURPLE:
                resId = R.color.colorPrimaryPurple;
                break;
            case APP_THEME_RED:
                resId = R.color.colorPrimaryRed;
                break;
            case APP_THEME_DEEP_PURPLE:
                resId = R.color.colorPrimaryDeepPurple;
                break;
            case APP_THEME_INDIGO:
                resId = R.color.colorPrimaryIndigo;
                break;
            case APP_THEME_GREEN:
                resId = R.color.colorPrimaryGreen;
                break;
            case APP_THEME_ORANGE:
                resId = R.color.colorPrimaryOrange;
                break;
            case APP_THEME_DEEP_ORANGE:
                resId = R.color.colorPrimaryDeepOrange;
                break;
        }
        int titleDividerId = activity.getResources().getIdentifier("titleDivider", "id", "android");
        View titleDivider = alertDialog.findViewById(titleDividerId);
        if (titleDivider != null)
            titleDivider.setBackgroundResource(resId);
    }
    public static void setBackgroundLayoutHeader(RelativeLayout relativeLayout){
        int resId = 0;
        switch (preferences.getInt(CommonSettingsActivity.APP_THEME_PREFERENCE, APP_THEME_PURPLE)){
            case APP_THEME_PURPLE:
                resId = R.drawable.navigation_header_purple;
                break;
            case APP_THEME_RED:
                resId = R.drawable.navigation_header_red;
                break;
            case APP_THEME_DEEP_PURPLE:
                resId = R.drawable.navigation_header_deep_purple;
                break;
            case APP_THEME_INDIGO:
                resId = R.drawable.navigation_header_indigo;
                break;
            case APP_THEME_GREEN:
                resId = R.drawable.navigation_header_green;
                break;
            case APP_THEME_ORANGE:
                resId = R.drawable.navigation_header_orange;
                break;
            case APP_THEME_DEEP_ORANGE:
                resId = R.drawable.navigation_header_deep_orange;
                break;
        }
        relativeLayout.setBackgroundResource(resId);
    }
    public static void setBackgroundViewOnLine(Activity activity, Button button){
        Drawable drawable = null;
        switch (preferences.getInt(CommonSettingsActivity.APP_THEME_PREFERENCE, APP_THEME_PURPLE)){
            case APP_THEME_PURPLE:
                drawable = activity.getResources().getDrawable(R.drawable.ic_cisco_webex_purple);
                break;
            case APP_THEME_RED:
                drawable = activity.getResources().getDrawable(R.drawable.ic_cisco_webex_red);
                break;
            case APP_THEME_DEEP_PURPLE:
                drawable = activity.getResources().getDrawable(R.drawable.ic_cisco_webex_deep_purple);
                break;
            case APP_THEME_INDIGO:
                drawable = activity.getResources().getDrawable(R.drawable.ic_cisco_webex_deep_purple);
                break;
            case APP_THEME_GREEN:
                drawable = activity.getResources().getDrawable(R.drawable.ic_cisco_webex_green);
                break;
            case APP_THEME_ORANGE:
                drawable = activity.getResources().getDrawable(R.drawable.ic_cisco_webex_orange);
                break;
            case APP_THEME_DEEP_ORANGE:
                drawable = activity.getResources().getDrawable(R.drawable.ic_cisco_webex_deep_orange);
                break;
        }
        button.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
    }
}
