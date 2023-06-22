package com.example.mp3amp.data.repository;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.mp3amp.domain.models.AudioModel;
import com.example.mp3amp.domain.repository.GetDownloadsListRepository;
import com.example.mp3amp.presentation.activity.MainActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetDownloadsListRepositoryImpl implements GetDownloadsListRepository {
    private final Context context;
    private final FragmentActivity activity;
    public final String[] EXTERNAL_PERMS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    public final int EXTERNAL_REQUEST = 138;
    public GetDownloadsListRepositoryImpl(Context context, FragmentActivity activity){
        this.context = context;
        this.activity = activity;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public ArrayList<AudioModel> getData() {
        ArrayList<AudioModel> list = new ArrayList<>();
        if (requestForPermission()) {
            String[] selectionArgs = new String[] {
                    MediaStore.Audio.Media.DISPLAY_NAME,
                    MediaStore.Audio.Media.DATA,
                    MediaStore.Audio.Media.VOLUME_NAME
            };

            Cursor c = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, selectionArgs, null, null, null);

            while(c.moveToNext()) {

                @SuppressLint("Range") String name =
                        c.getString(c.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));

                @SuppressLint("Range") String data =
                        c.getString(c.getColumnIndex(MediaStore.Audio.Media.DATA));
                @SuppressLint("Range") String uri =
                        c.getString(c.getColumnIndex(MediaStore.Audio.Media.VOLUME_NAME));

                Uri music = MediaStore.Audio.Media.getContentUri(uri);
                list.add(new AudioModel(name, data, music));
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
