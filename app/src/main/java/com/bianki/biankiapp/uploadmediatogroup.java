package com.bianki.biankiapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.bianki.biankiapp.ClassModel.addGroupPost;
import com.bianki.biankiapp.Database.Helper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class uploadmediatogroup extends AppCompatActivity {

    Button save;
    ImageView imageView;
    String[] mediaColumns = {MediaStore.Video.Media._ID};
    String mediaPath;
    Helper helper;
    String cursor;
    String type = "";
    ConstraintLayout constraintLayout;
    EditText text;
    ImageView back;
    ProgressBar progressBar;
    String idofgroup ;

    @SuppressLint("ClickableViewAccessibility")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadmediatogroup);


        helper = new Helper(getApplicationContext());
        cursor = helper.getiddata();

        save = findViewById(R.id.saveaqq);
        imageView = findViewById(R.id.imageView);
        constraintLayout = findViewById(R.id.constraintLayout);

        save.setVisibility(View.GONE);
        text = findViewById(R.id.text);
        back = findViewById(R.id.back);
        progressBar = findViewById(R.id.progressBar);
        Intent intent = getIntent();
        idofgroup = intent.getStringExtra("id");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                        return;

                    }

                } else {
                    Intent pictureActionIntent = new Intent(
                            Intent.ACTION_PICK);
                    pictureActionIntent.setType("video/*, image/*");

                    startActivityForResult(pictureActionIntent, 0);
                }

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                uploadFile();
            }
        });


        // Video must be low in Memory or need to be compressed before uploading...
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {


                Uri selectedImage = data.getData();


                ContentResolver cr = this.getContentResolver();
                String mime = cr.getType(selectedImage);

                Log.e("type", mime.toString());

                if (mime.equals("image/png") || mime.equals("image/jpg") || mime.equals("image/gif") || mime.equals("image/jpeg") || mime.equals("image/tif")) {


                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursor.getString(columnIndex);
                    // Set the Image in ImageView for Previewing the Media
                    imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    cursor.close();

                    type = "image";

                    save.setVisibility(View.VISIBLE);

                } // When an Video is picked
                else {

                    // Get the Video from data
                    Uri selectedVideo = data.getData();
                    String[] filePathColumn = {MediaStore.Video.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedVideo, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursor.getString(columnIndex);
                    // Set the Video Thumb in ImageView Previewing the Media
                    imageView.setImageBitmap(getThumbnailPathForLocalFile(uploadmediatogroup.this, selectedVideo));
                    cursor.close();

                    type = "video";
                    save.setVisibility(View.VISIBLE);

                }

            } else {
                Toast.makeText(this, "You haven't picked Image/Video", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

    }


    public Bitmap getThumbnailPathForLocalFile(Activity context, Uri fileUri) {
        long fileId = getFileId(context, fileUri);
        return MediaStore.Video.Thumbnails.getThumbnail(context.getContentResolver(),
                fileId, MediaStore.Video.Thumbnails.MICRO_KIND, null);
    }

    // Getting Selected File ID
    public long getFileId(Activity context, Uri fileUri) {
        Cursor cursor = context.managedQuery(fileUri, mediaColumns, null, null, null);
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
            return cursor.getInt(columnIndex);
        }
        return 0;
    }

    // Uploading Image/Video
    private void uploadFile() {


        Log.e("F", mediaPath);

        // Map is used to multipart the file using okhttp3.RequestBody
        Map<String, RequestBody> map = new HashMap<>();
        File file = new File(mediaPath);

        String get = text.getText().toString();

        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);

        map.put("file\"; filename=\"" + file.getName() + "\"", requestBody);


        RequestBody typeoffile = RequestBody.create(MediaType.parse("text/plain"),
                type);

        RequestBody name = RequestBody.create(MediaType.parse("text/plain"),
                cursor);

        RequestBody text = RequestBody.create(MediaType.parse("text/plain"),
                get);
        RequestBody latidude = RequestBody.create(MediaType.parse("text/plain"),
                String.valueOf(0));
        RequestBody longtiude = RequestBody.create(MediaType.parse("text/plain"),
                String.valueOf(0));


        RequestBody textcolor = RequestBody.create(MediaType.parse("text/plain"),
                "");

        RequestBody id = RequestBody.create(MediaType.parse("text/plain"),
                idofgroup);


        Call<addGroupPost> mealsCall = Utils.getApi().addGroupPost(id, typeoffile, map, textcolor, text, latidude, longtiude, name);
        mealsCall.enqueue(new Callback<addGroupPost>() {


            @Override
            public void onResponse(Call<addGroupPost> call, Response<addGroupPost> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {

                    Log.e("RESPONSE", response.body().toString());
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();

                } else {

                }
            }

            @Override
            public void onFailure(Call<addGroupPost> call, Throwable t) {

                progressBar.setVisibility(View.GONE);

                Log.e("RESPONSE", t.getLocalizedMessage().toString());
                Toast.makeText(getApplicationContext(), "عفوا لايوجد اتصال بالانترنت", Toast.LENGTH_SHORT).show();


            }

        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent pictureActionIntent = new Intent(
                            Intent.ACTION_PICK);
                    pictureActionIntent.setType("video/*, image/*");

                    startActivityForResult(pictureActionIntent, 0);


                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getApplicationContext(), "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }


                return;
            }



        }
    }
}
