package com.example.notes;

import static android.content.Intent.ACTION_PICK;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class NewImageNoteActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_image);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode > 0 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = BitmapFactory.decodeFile(String.valueOf(selectedImage));
            ImageView imageView = findViewById(R.id.image_view);
            this.runOnUiThread(new SetImageRunnable(bitmap, imageView));
        }
    }
}