package com.carnot.testapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Camera Activity to take images and photos
 */
public class CameraActivity extends AppCompatActivity {

    /**
     * Button that invokes the camera to take pictures
     */
    private Button cameraButton;
    private Button ImageToText;
    private ImageView displayImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        cameraButton = (Button)findViewById(R.id.camera_activity_action_button);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start the image capturing process using the camera helper activity
                dispatchTakeFullPictureIntent();
            }
        });

        ImageToText = (Button)findViewById(R.id.imageToText);
        ImageToText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertImageToText();
            }
        });

        displayImageView = (ImageView)findViewById(R.id.camera_activity_display_bitmap);
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void convertImageToText()
    {

    }
    private void dispatchTakeFullPictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
        {
            // Yes! There is a camera activity present to handle such intent from the app.
            // Create the File where the photo should go
            File photoFile = null;
            try
            {
                photoFile = createImageFile();
            }
            catch (IOException ex)
            {
                // Error occurred while creating the File. Print the stack trace output.
                ex.printStackTrace();
            }

            // Continue only if the File was successfully created
            if (photoFile != null)
            {
                // File was created successfully.
                // Now we can store the captured image in that file.
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name.
        // 1. Setting up the name of the file.
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        // 2. Get the external storage public / private path
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        Log.d(TAG, "Current Photo Path: " + mCurrentPhotoPath);
        return image;
    }

    public static final String TAG = "CameraActivity";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //After the camera activity successfully exits after taking a picture
        //1. Display the captured bitmap within the application to the user
        //2. Other steps.
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null)
        {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            displayImageView.setImageBitmap(imageBitmap);
        }
    }
}
