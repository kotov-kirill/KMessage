package com.example.kirill.kmessage.Activities.SettingsActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.kirill.kmessage.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class CommonSettingsActivity extends AppCompatActivity{
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
