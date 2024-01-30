package com.example.notes;

import android.content.Context;
import android.content.Intent;

import static android.content.Intent.ACTION_PICK_ACTIVITY;

import static androidx.core.app.ActivityCompat.startActivityForResult;


import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;

public class AddImageOnClick implements View.OnClickListener {
    Activity activity;

    public AddImageOnClick(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View add_image_view) {

        Intent intent = new Intent(ACTION_PICK_ACTIVITY, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(activity, Intent.createChooser(intent, "Add Image"), 1, null);

    }


}
