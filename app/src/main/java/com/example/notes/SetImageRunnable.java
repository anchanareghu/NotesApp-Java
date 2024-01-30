package com.example.notes;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

public class SetImageRunnable implements Runnable {
    ImageView imageView;
    Bitmap bitmap;

    public SetImageRunnable(Bitmap bitmap, ImageView imageView) {
        this.imageView = imageView;
        this.bitmap = bitmap;

    }

    @Override
    public void run() {
        imageView.setImageBitmap(bitmap);
    }
}
