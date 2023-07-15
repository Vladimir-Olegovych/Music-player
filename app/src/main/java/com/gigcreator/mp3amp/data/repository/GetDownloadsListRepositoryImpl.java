package com.gigcreator.mp3amp.data.repository;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.gigcreator.mp3amp.domain.models.AudioModel;
import com.gigcreator.mp3amp.domain.repository.GetDownloadsListRepository;

import java.util.ArrayList;

public class GetDownloadsListRepositoryImpl implements GetDownloadsListRepository {
    private final Context context;
    private final FragmentActivity activity;
    public final String[] EXTERNAL_PERMS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    public final int EXTERNAL_REQUEST = 138;
    public GetDownloadsListRepositoryImpl(Context context, FragmentActivity activity){
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ArrayList<AudioModel> getData() {
        ArrayList<AudioModel> list = new ArrayList<>();
        if (requestForPermission()) {
            String[] selectionArgs = new String[] {
                    MediaStore.Audio.Media.DISPLAY_NAME,
                    MediaStore.Audio.Media.DATA,
            };
            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            Cursor c = context.getContentResolver().query(uri, selectionArgs, null, null, null);

            while(c.moveToNext()) {

                @SuppressLint("Range") String name =
                        c.getString(c.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));

                @SuppressLint("Range") String data =
                        c.getString(c.getColumnIndex(MediaStore.Audio.Media.DATA));

                list.add(new AudioModel(name, data));
            }
            c.close();
        }
        return list;
    }
    private boolean requestForPermission() {
        boolean isPermissionOn = true;
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            if (!canAccessExternalSd()) {
                isPermissionOn = false;
                ActivityCompat.requestPermissions(activity, EXTERNAL_PERMS, EXTERNAL_REQUEST);
            }
        }
        return isPermissionOn;
    }
    private boolean canAccessExternalSd() {
        return (hasPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE));
    }

    private boolean hasPermission(String perm) {
        return (PackageManager.PERMISSION_GRANTED == context.checkSelfPermission(perm));
    }

}
