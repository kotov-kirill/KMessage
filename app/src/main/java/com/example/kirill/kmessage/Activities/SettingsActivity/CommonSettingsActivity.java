package com.example.kirill.kmessage.Activities.SettingsActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kirill.kmessage.Activities.MainActivity;
import com.example.kirill.kmessage.R;
import com.example.kirill.kmessage.Special.ApplicationThemeSetter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class CommonSettingsActivity extends AppCompatActivity{
    public static final String APP_COMMON_SETTINGS_PREFERENCES = "app_common_setting_preferences";
    public static final String APP_THEME_PREFERENCE = "app_theme_preference";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationThemeSetter.themeSetter(this);
        setContentView(R.layout.activity_common_settings);
        this.initComponents();
    }

    private void initComponents() {
        this.initToolbar();
    }

    private void initToolbar() {
        this.toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void buttonClearCacheImages(View view) {
        if(!ImageLoader.getInstance().isInited())
            ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
        ImageLoader.getInstance().clearMemoryCache();
        ImageLoader.getInstance().clearDiskCache();
        Toast.makeText(this, "Successful clear", Toast.LENGTH_SHORT).show();
    }

    public void buttonDisplaySizeApplication(View view) {
        Toast.makeText(this.getApplicationContext(), String.format("Total memory = %s",
                (int) (Runtime.getRuntime().totalMemory() / 1024)), Toast.LENGTH_SHORT).show();
    }

    public void buttonChangeApplicationTheme(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] items = this.getResources().getStringArray(R.array.alert_dialog_common_settings_menu_items);
        DialogListItem[] dialogListItems = new DialogListItem[]{
            new DialogListItem(R.color.colorPrimaryPurple, items[0]),
            new DialogListItem(R.color.colorPrimaryRed, items[1]),
            new DialogListItem(R.color.colorPrimaryDeepPurple, items[2]),
            new DialogListItem(R.color.colorPrimaryIndigo, items[3]),
            new DialogListItem(R.color.colorPrimaryGreen, items[4]),
            new DialogListItem(R.color.colorPrimaryOrange, items[5]),
            new DialogListItem(R.color.colorPrimaryDeepOrange, items[6])
        };
        builder.setAdapter(new CustomAdapter(this, R.layout.layout_list_view_dialog_common_settings, dialogListItems),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor edit
                                = getSharedPreferences(APP_COMMON_SETTINGS_PREFERENCES, MODE_PRIVATE).edit();
                        edit.putInt(APP_THEME_PREFERENCE, which);
                        edit.apply();
                        //Toast.makeText(CommonSettingsActivity.this, R.string.alert_dialog_toast_restart_application, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CommonSettingsActivity.this, SettingsActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
        AlertDialog dialog = builder.show();
        dialog.getListView().setDivider(null);
    }

    private class DialogListItem{
        private int color;
        private String item;
        public DialogListItem(int color, String item) {
            this.color = color;
            this.item = item;
        }

        public int getColor() {
            return color;
        }
        public String getItem() {
            return item;
        }
    }
    private class ViewHolder{
        private View viewColor;
        private TextView textView;
    }
    private class CustomAdapter extends ArrayAdapter<DialogListItem>{
        public CustomAdapter(Context context, int resource, DialogListItem[] objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder viewHolder;
            if(row == null){
                viewHolder = new ViewHolder();
                row = LayoutInflater.from(getContext()).inflate(R.layout.layout_list_view_dialog_common_settings, parent, false);
                viewHolder.viewColor = row.findViewById(R.id.color);
                viewHolder.textView = (TextView) row.findViewById(R.id.dialog_item);
                row.setTag(viewHolder);
            }
            else
                viewHolder = (ViewHolder) row.getTag();
            viewHolder.viewColor.setBackgroundResource(this.getItem(position).getColor());
            viewHolder.textView.setText(this.getItem(position).getItem());
            return row;
        }
    }
}
