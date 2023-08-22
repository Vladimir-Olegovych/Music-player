package com.gigcreator.mp3amp.data.repository;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.gigcreator.mp3amp.R;
import com.gigcreator.mp3amp.domain.models.AudioModel;
import com.gigcreator.mp3amp.domain.repository.GetDownloadsListRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class GetDownloadsListRepositoryImpl implements GetDownloadsListRepository {
    private final Context context;

    public GetDownloadsListRepositoryImpl(Context context) {
        this.context = context;
    }

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageEmulated();
        } else {
            int readCheck = ContextCompat.checkSelfPermission(context, READ_EXTERNAL_STORAGE);
            int writeCheck = ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE);
            return readCheck == PackageManager.PERMISSION_GRANTED && writeCheck == PackageManager.PERMISSION_GRANTED;
        }
    }

    @Override
    public ArrayList<AudioModel> getData() {

        ArrayList<AudioModel> list = new ArrayList<>();
        if (checkPermission()) {
            final Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

            final String[] cursor_cols = {MediaStore.Audio.Media._ID,
                    MediaStore.Audio.Media.TITLE,
                    MediaStore.Audio.Media.DATA,
                    MediaStore.Audio.Media.ALBUM_ID
            };

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


                Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");

                Uri albumArtUri = ContentUris.withAppendedId(sArtworkUri, albumId);


                Bitmap bitmap;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(
                            context.getContentResolver(), albumArtUri);
                    bitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);

                } catch (Throwable exception) {
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.music);
                }
                list.add(new AudioModel(track, data, bitmap));
            }
            cursor.close();
        }
        return list;
    }
}