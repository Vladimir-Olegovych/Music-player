package com.gigcreator.data.repository;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import com.gigcreator.data.R;
import com.gigcreator.domain.models.AudioModel;
import com.gigcreator.domain.repository.GetDownloadsListRepository;

import java.util.ArrayList;

public class GetDownloadsListRepositoryImpl implements GetDownloadsListRepository {
    private final Context context;

    public GetDownloadsListRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public ArrayList<AudioModel> getData() {

    ArrayList<AudioModel> list = new ArrayList<>();
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
        return list;
    }
}
