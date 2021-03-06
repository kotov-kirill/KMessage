package com.example.kirill.kmessage.Activities.SettingsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.kirill.kmessage.R;
import com.example.kirill.kmessage.Special.ApplicationThemeSetter;
import com.example.kirill.kmessage.Special.NavigationMenuReceiver;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private Button buttonEditInformation;
    private Button buttonCommonSettings;
    private Button buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationThemeSetter.themeSetter(this);
        setContentView(R.layout.activity_settings);
        this.initComponents();
    }

    private void initComponents() {
        this.initToolBar();
        this.initNavigationView();
        this.initButtons();
    }

    private void initToolBar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initNavigationView() {
        this.drawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, this.drawerLayout, this.toolbar,
                R.string.view_navigation_open, R.string.view_navigation_close);
        this.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        this.navigationView = (NavigationView) this.findViewById(R.id.navigation_view);
        ApplicationThemeSetter.styleNavigationHeaderView(navigationView);
        this.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                NavigationMenuReceiver.menuReceiver(SettingsActivity.this, item);
                return true;
            }
        });

        NavigationMenuView menuView = (NavigationMenuView) this.navigationView.getChildAt(0);
        if(menuView != null)
            menuView.setVerticalScrollBarEnabled(false);
    }

    private void initButtons() {
        this.buttonCommonSettings = (Button) this.findViewById(R.id.button_settings_common);
        this.buttonCommonSettings.setOnClickListener(this);

        this.buttonEditInformation = (Button) this.findViewById(R.id.button_edit_information);

        this.buttonExit = (Button) this.findViewById(R.id.button_exit);
        ApplicationThemeSetter.styleButtonExit(this, buttonExit);
    }

    @Override
    public void onClick(View v) {
        Class cl = null;
        switch (v.getId()){
            case R.id.button_settings_common:
                cl = CommonSettingsActivity.class;
                break;
        }
        Intent intent = new Intent(this, cl);
        startActivity(intent);
    }
}
