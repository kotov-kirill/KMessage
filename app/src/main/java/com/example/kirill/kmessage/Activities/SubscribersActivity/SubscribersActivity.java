package com.example.kirill.kmessage.Activities.SubscribersActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kirill.kmessage.R;
import com.example.kirill.kmessage.Special.NavigationMenuReceiver;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class SubscribersActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribers);
        this.initComponents();
    }

    private void initComponents() {
        this.initToolBar();
        this.initNavigationView();
        this.initGridView();
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
        this.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                NavigationMenuReceiver.menuReceiver(SubscribersActivity.this, item);
                return true;
            }
        });
        this.navigationView.setCheckedItem(R.id.menu_navigation_subscribers);

        NavigationMenuView menuView = (NavigationMenuView) this.navigationView.getChildAt(0);
        if(menuView != null)
            menuView.setVerticalScrollBarEnabled(false);
    }

    private void initGridView() {
        this.gridView = (GridView) this.findViewById(R.id.grid_view);
        this.gridView.setAdapter(new ImageAdapter(this));
    }
    private class ImageAdapter extends BaseAdapter{
        private final String[] IMAGE_URLS = Constants.IMAGES;
        private final String[] IMAGES_NAMES = Constants.NAMES;
        private Context context;
        private final DisplayImageOptions options;
        public ImageAdapter(Context context) {
            this.context = context;

            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(android.R.color.darker_gray)
                    .showImageForEmptyUri(R.drawable.ic_empty)
                    .showImageOnFail(R.drawable.ic_error)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
        }
        public int getCount() {
            return IMAGE_URLS.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            final ViewHolder viewHolder;
            if(view == null){
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(this.context).inflate(R.layout.layout_grid_view_subscribers, parent, false);
                viewHolder.imageView = (ImageView) view.findViewById(R.id.image);
                viewHolder.textView = (TextView) view.findViewById(R.id.text_name);
                view.setTag(viewHolder);
            }
            else
                viewHolder = (ViewHolder) view.getTag();
            ImageLoader imageLoader = ImageLoader.getInstance();
            if(!imageLoader.isInited())
                imageLoader.init(ImageLoaderConfiguration.createDefault(this.context));
            imageLoader.displayImage(IMAGE_URLS[position], viewHolder.imageView, options);
            viewHolder.textView.setText(IMAGES_NAMES[position]);
            return view;
        }
    }
    private static class ViewHolder{
        private ImageView imageView;
        private TextView textView;
    }
}
