package com.example.kirill.kmessage.MessagesActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kirill.kmessage.FileDialog;
import com.example.kirill.kmessage.R;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created on 23.01.2016.
 * @author Rakov Kirill
 */

public class MultiChoiceModeListenerImpl implements AbsListView.MultiChoiceModeListener{
    public static final int REQUEST_FILE_DIALOG = 0;
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
        mode.getMenuInflater().inflate(R.menu.message_list_view_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_delete:
                this.actionMenuDelete(mode);
                break;
            case R.id.action_save:
                this.actionMenuSave(mode);
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {}

    private void actionMenuDelete(final ActionMode mode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle(R.string.alert_dialog_delete);
        builder.setMessage(R.string.alert_dialog_delete_checked_elements);
        builder.setPositiveButton(R.string.positive_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
                int deletedCount = 0;
                for(int i = 0; i < checkedItemPositions.size(); i++)
                    if(checkedItemPositions.valueAt(i)){
                        int position = checkedItemPositions.keyAt(i);
                        Object itemDeleted = listView.getAdapter().getItem(position - deletedCount);
                        ((ArrayAdapter)listView.getAdapter()).remove(itemDeleted);
                        deletedCount++;
                    }
                mode.finish();
            }
        });
        builder.setNegativeButton(R.string.negative_button, null);
        builder.show();
    }

    private ActionMode actionMode;
    private void actionMenuSave(ActionMode mode) {
        Intent intent = new Intent(this.context, FileDialog.class);
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            intent.putExtra(FileDialog.START_PATH,
                    Environment.getExternalStorageDirectory().getAbsolutePath());
            intent.putExtra(FileDialog.REQUEST_OPEN_MODE, FileDialog.REQUEST_FILE_SAVE);
        ((MessagesActivity)this.context).startActivityForResult(intent, REQUEST_FILE_DIALOG);
        this.actionMode = mode;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == MessagesActivity.RESULT_OK)
            if(requestCode == REQUEST_FILE_DIALOG){
                String path = data.getStringExtra(FileDialog.RESULT_PATH);
                if(!path.endsWith(".txt"))
                    path += ".txt";
                File file = new File(path);
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    SparseBooleanArray checkedItemPositions = this.listView.getCheckedItemPositions();
                    String str = "";
                    for(int i = 0; i < checkedItemPositions.size(); i++)
                        if(checkedItemPositions.valueAt(i)){
                            int position = checkedItemPositions.keyAt(i);
                            Message message = (Message) this.listView.getItemAtPosition(position);
                            str += message.getSenderName() + "\n";
                        }
                    writer.write(str);
                    writer.close();
                    this.actionMode.finish();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
}
