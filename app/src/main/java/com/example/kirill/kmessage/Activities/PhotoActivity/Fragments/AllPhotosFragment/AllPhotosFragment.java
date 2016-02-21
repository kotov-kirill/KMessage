package com.example.kirill.kmessage.Activities.PhotoActivity.Fragments.AllPhotosFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.kirill.kmessage.Activities.PhotoActivity.Fragments.AbstractFragment;
import com.example.kirill.kmessage.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * Created on 10.02.2016.
 * @author Rakov Kirill
 */

public class AllPhotosFragment extends AbstractFragment {
    private View view;
    private GridView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_photos_all_layout, container, false);
        this.initComponents();
        return this.view;
    }

    private void initComponents() {
        listView = (GridView) this.view.findViewById(R.id.grid);
        listView.setAdapter(new ImageAdapter(AllPhotosFragment.this.getContext()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AllPhotosFragment.this.getContext(), PagerImageActivity.class);
                intent.putExtra(Constants.Extra.IMAGE_POSITION, position);
                startActivity(intent);
            }
        });
    }

    private static class ImageAdapter extends BaseAdapter {
        private static final String[] IMAGE_URLS = Constants.IMAGES;
        private LayoutInflater inflater;
        private DisplayImageOptions options;
        private Context context;

        ImageAdapter(Context context) {
            inflater = LayoutInflater.from(context);

            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(android.R.color.darker_gray)
                    .showImageForEmptyUri(R.drawable.ic_empty)
                    .showImageOnFail(R.drawable.ic_error)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();

            this.context = context;
        }

        @Override
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
            final ViewHolder holder;
            View view = convertView;
            if (view == null) {
                view = inflater.inflate(R.layout.layout_grid_view_image, parent, false);
                holder = new ViewHolder();
                assert view != null;
                holder.imageView = (ImageView) view.findViewById(R.id.image);
                holder.progressBar = (ProgressBar) view.findViewById(R.id.progress);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            ImageLoader imageLoader = ImageLoader.getInstance();
            if(!imageLoader.isInited())
                imageLoader.init(ImageLoaderConfiguration.createDefault(this.context));
            imageLoader.displayImage(IMAGE_URLS[position], holder.imageView, options, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    holder.progressBar.setProgress(0);
                    holder.progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    holder.progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    holder.progressBar.setVisibility(View.GONE);
                }
            }, new ImageLoadingProgressListener() {
                @Override
                public void onProgressUpdate(String imageUri, View view, int current, int total) {
                    holder.progressBar.setProgress(Math.round(100.0f * current / total));
                }
            });

            return view;
        }
    }

    static class ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;
    }

    public static AllPhotosFragment getInstance(Context context){
        AllPhotosFragment allPhotosFragment = new AllPhotosFragment();
        allPhotosFragment.setTitle(context.getString(R.string.tab_item_all_photos));
        return allPhotosFragment;
    }
}
