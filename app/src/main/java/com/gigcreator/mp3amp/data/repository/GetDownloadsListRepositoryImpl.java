package com.gigcreator.mp3amp.data.repository;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.gigcreator.mp3amp.R;
import com.gigcreator.mp3amp.domain.models.AudioModel;
import com.gigcreator.mp3amp.domain.repository.GetDownloadsListRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

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

            final Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

            final String[] cursor_cols = { MediaStore.Audio.Media._ID,
                    MediaStore.Audio.Media.TITLE,
                    MediaStore.Audio.Media.DATA,
                    MediaStore.Audio.Media.ALBUM_ID,
                    MediaStore.Audio.Media.DURATION};

            final String where = MediaStore.Audio.Media.IS_MUSIC + "=1";
            final Cursor cursor = context.getContentResolver().query(uri,
                    cursor_cols, where, null, null);

            while (cursor.moveToNext()) {
                String track = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));

                String data = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));

                int albumId = cursor.getInt(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));

                int duration = cursor.getInt(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));

                Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");

                Uri albumArtUri = ContentUris.withAppendedId(sArtworkUri, albumId);

                Log.d("MyLogNEWS", String.valueOf(albumArtUri));


                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(
                            context.getContentResolver(), albumArtUri);
                    bitmap = Bitmap.createScaledBitmap(bitmap, 150, 150, true);

                } catch (FileNotFoundException exception) {
                    exception.printStackTrace();
                    bitmap = BitmapFactory.decodeResource(context.getResources(),
                            R.drawable.music);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                list.add(new AudioModel(track, data, bitmap));
            }
            cursor.close();
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
