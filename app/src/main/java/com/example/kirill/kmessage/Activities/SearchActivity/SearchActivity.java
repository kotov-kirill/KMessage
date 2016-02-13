package com.example.kirill.kmessage.Activities.SearchActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.kirill.kmessage.R;
import com.example.kirill.kmessage.Special.MenuReceiver;

public class SearchActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.initComponents();
    }

    private void initComponents() {
        this.initToolbar();
        this.initNavigationView();
    }

    private void initToolbar() {
        this.toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);
    }

    private void initNavigationView() {
        this.drawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, this.drawerLayout, this.toolbar,
                R.string.view_navigation_open, R.string.view_navigation_close);
        this.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        this.navigationView = (NavigationView) this.findViewById(R.id.navigation_view);
        this.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                MenuReceiver.menuReceiver(SearchActivity.this, item);
                return true;
            }
        });
        this.navigationView.setCheckedItem(R.id.menu_navigation_search);

        NavigationMenuView menuView = (NavigationMenuView) this.navigationView.getChildAt(0);
        if(menuView != null)
            menuView.setVerticalScrollBarEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_new_search);
        menuItem.expandActionView();
        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return false;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                SearchActivity.this.finish();
                return false;
            }
        });
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint(this.getResources().getString(R.string.action_search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            private TextView textView = (TextView) findViewById(R.id.text_query_search);

            @Override
            public boolean onQueryTextSubmit(String query) {
                textView.setText(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                textView.setText(newText);
                return true;
            }
        });
        this.getTypeSearch(searchView);
        return true;
    }

    private void getTypeSearch(final SearchView searchView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alert_dialog_title_type_search);
        final String[] items = this.getResources().getStringArray(R.array.alert_dialog_menu_items);
        DialogListItem[] dialogListItems = new DialogListItem[]{
                new DialogListItem(R.drawable.ic_account_star_variant, items[0]),
                new DialogListItem(R.drawable.ic_music_box, items[1]),
                new DialogListItem(R.drawable.ic_filmstrip, items[2]),
                new DialogListItem(R.drawable.ic_account_multiple, items[3]),
                new DialogListItem(R.drawable.ic_email_dark, items[4])
        };
        builder.setAdapter(new DialogListViewAdapter(this, R.layout.layout_list_view_dialog_search, dialogListItems),
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                searchView.setQueryHint(items[which]);
            }
        });
        AlertDialog alertDialog = builder.show();

        int titleDividerId = this.getResources().getIdentifier("titleDivider", "id", "android");
        View titleDivider = alertDialog.findViewById(titleDividerId);
        if (titleDivider != null)
            titleDivider.setBackgroundResource(R.color.colorPrimaryDefault);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_new_search) {
            this.getTypeSearch((SearchView) item.getActionView());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
