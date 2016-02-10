package com.example.kirill.kmessage.Activities.PhotoActivity.Fragments.AlbumsFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kirill.kmessage.R;

import java.util.Objects;

/**
 * Created on 10.02.2016.
 * @author Rakov Kirill
 */

public class MultiChoiceModeListenerImpl implements AbsListView.MultiChoiceModeListener {
    private Context context;
    private ListView listView;
    public MultiChoiceModeListenerImpl(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        mode.setTitle(String.valueOf(this.listView.getCheckedItemCount()));
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.menu_list_view_albums, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete:
                this.menuItemDelete(mode);
                break;
        }
        return false;
    }

    private void menuItemDelete(final ActionMode mode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle(R.string.alert_dialog_delete);
        builder.setMessage(R.string.alert_dialog_delete_checked_elements);
        builder.setPositiveButton(R.string.positive_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
                int count = 0;
                for(int i = 0; i < checkedItemPositions.size(); i++)
                    if(checkedItemPositions.valueAt(i)){
                        int index = checkedItemPositions.keyAt(i);
                        Object deletedObject = listView.getItemAtPosition(index - count);
                        ((ArrayAdapter)listView.getAdapter()).remove(deletedObject);
                        count++;
                    }
                mode.finish();
            }
        });
        builder.setNegativeButton(R.string.negative_button, null);

        AlertDialog dialog = builder.show();
        int titleId = this.context.getResources().getIdentifier("titleDivider", "id", "android");
        View divider = dialog.findViewById(titleId);
        if(divider != null)
            divider.setBackgroundResource(R.color.colorPrimaryDefault);
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {}
}
