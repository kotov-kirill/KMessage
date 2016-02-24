package com.example.kirill.kmessage.Activities;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.kirill.kmessage.R;
import com.example.kirill.kmessage.Special.ApplicationThemeSetter;
import com.example.kirill.kmessage.Special.MenuReceiver;
import com.example.kirill.kmessage.Special.NavigationMenuReceiver;

public class ProfileActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    private RelativeLayout relativeLayout;
    private Button buttonOnLine;
    private Button buttonExitProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationThemeSetter.themeSetter(this);
        setContentView(R.layout.activity_profile);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            this.getWindow().setStatusBarColor(Color.BLACK);
        this.initComponents();
    }

    private void initComponents() {
        this.initToolbar();
        this.initNavigationView();
        this.initButtons();
    }

    private void initToolbar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.relativeLayout = (RelativeLayout) this.findViewById(R.id.profile_header_layout);
        ApplicationThemeSetter.setBackgroundLayoutHeader(this.relativeLayout);
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
                NavigationMenuReceiver.menuReceiver(ProfileActivity.this, item);
                return true;
            }
        });
        this.navigationView.setCheckedItem(R.id.menu_navigation_profile);

        NavigationMenuView menuView = (NavigationMenuView) this.navigationView.getChildAt(0);
        if(menuView != null)
            menuView.setVerticalScrollBarEnabled(false);
    }

    private void initButtons() {
        this.buttonExitProfile = (Button) this.findViewById(R.id.button_exit_profile);
        ApplicationThemeSetter.styleButtonExit(this, buttonExitProfile);

        this.buttonOnLine = (Button) this.findViewById(R.id.button_on_line);
        ApplicationThemeSetter.setBackgroundViewOnLine(this, buttonOnLine);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MenuReceiver.menuReceiver(this, item);
        return super.onOptionsItemSelected(item);
    }
}
