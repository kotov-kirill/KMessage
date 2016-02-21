package com.example.kirill.kmessage.Activities.MessagesActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kirill.kmessage.Activities.FileDialog;
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
    public static final int REQUSET_WRITE_STORAGE = 1;
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
        mode.getMenuInflater().inflate(R.menu.menu_list_view_message, menu);
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
        AlertDialog dialog = builder.show();

        // Set title divider color
        int titleDividerId = this.context.getResources().getIdentifier("titleDivider", "id", "android");
        View titleDivider = dialog.findViewById(titleDividerId);
        if (titleDivider != null)
            titleDivider.setBackgroundResource(R.color.colorPrimaryDefault);
    }

    private ActionMode actionMode;
    private void actionMenuSave(ActionMode mode) {
        if((ContextCompat.checkSelfPermission(this.context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED)) {
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                Toast.makeText(this.context, R.string.toast_message_text_sd_card_not_available, Toast.LENGTH_SHORT).show();
                return;
            }
            this.startFileDialogActivity();
        }
        else
            ActivityCompat.requestPermissions((MessagesActivity)this.context,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUSET_WRITE_STORAGE);
        this.actionMode = mode;
    }

    private void startFileDialogActivity() {
        Intent intent = new Intent(this.context, FileDialog.class);
        intent.putExtra(FileDialog.START_PATH,
                Environment.getExternalStorageDirectory().getAbsolutePath());
        intent.putExtra(FileDialog.REQUEST_OPEN_MODE, FileDialog.REQUEST_FILE_SAVE);
        ((MessagesActivity) this.context).startActivityForResult(intent, REQUEST_FILE_DIALOG);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(requestCode == REQUSET_WRITE_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                startFileDialogActivity();
            else Toast.makeText(this.context, R.string.toast_message_text_permission_write_not_available, Toast.LENGTH_SHORT).show();
        }
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
