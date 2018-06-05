package com.ardenolgundemir.espressotestexample.controller.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.widget.ImageView;

import com.ardenolgundemir.espressotestexample.R;
import com.ardenolgundemir.espressotestexample.controller.BaseActivity;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by ardenolgundemir on 4.06.2018.
 */

public class TakeAPhotoActivity extends BaseActivity {
    @VisibleForTesting
    public static final String KEY_IMAGE_DATA = "data";

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int RESULT_LOAD_IMAGE = 2;

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        mImageView = (ImageView) findViewById(R.id.imageView);

    }

    private void dispatchTakePictureIntent() {
        // Open the camera to take a photo.
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void onOpenCamera(View view) {
        dispatchTakePictureIntent();
    }

    public void onOpenGallery(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, TakeAPhotoActivity.RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // If an image is received, display it on the ImageView.
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras == null || !extras.containsKey(KEY_IMAGE_DATA)) {
                return;
            }
            Bitmap imageBitmap = (Bitmap) extras.get(KEY_IMAGE_DATA);
            mImageView.setImageBitmap(imageBitmap);
        }else if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                mImageView.setBackground(null);
                mImageView.setImageBitmap(bitmap);
                mImageView.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
