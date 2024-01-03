package com.bianki.biankiapp.AddphotoAndBio;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bianki.biankiapp.Bio.Addbio;
import com.bianki.biankiapp.Database.Helper;
import com.bianki.biankiapp.R;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddPhotoAndBio extends AppCompatActivity implements HomeView {

    ImageView imageView ;
    Button next ;
    TextView skip ;
    AddPhotoPresenter addPhotoPresenter;
    String mediaPath ;

    Helper helper;
    String cursor;
    ProgressBar progressBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo_and_bio);

        imageView = findViewById(R.id.imageView);
        next = findViewById(R.id.next );
        skip = findViewById(R.id.skip);

        addPhotoPresenter = new AddPhotoPresenter(this);
        progressBar = findViewById(R.id.progressbar);

        helper = new Helper(getApplicationContext());
        cursor = helper.getiddata();


        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext() , Addbio.class));


            }
        });

        TextView addphotoname = findViewById(R.id.addphotoname);
        TextView details = findViewById(R.id.details);





        if (Locale.getDefault().getLanguage().equals("ar")) {
            Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/Tajawal-Medium.ttf");
            addphotoname.setTypeface(MLight);
            details.setTypeface(MLight);
            next.setTypeface(MLight);
            skip.setTypeface(MLight);


        } else {
            Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/SF-Pro-Display-Light.otf");
            addphotoname.setTypeface(MLight);
            details.setTypeface(MLight);
            next.setTypeface(MLight);
            skip.setTypeface(MLight);

        }


        next.setVisibility(View.GONE);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestBody name = RequestBody.create(MediaType.parse("text/plain"),
                        cursor);

                Map<String, RequestBody> map = uploadFile();

                addPhotoPresenter.addphoto(name , map);

            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                        return ;

                    }

                }
                else {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, 0);
                }
            }


        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {

                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);
                // Set the Image in ImageView for Previewing the Media
                imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                cursor.close();

                next.setVisibility(View.VISIBLE);
            }
        }catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }


    }


    private Map<String, RequestBody> uploadFile() {

        // Map is used to multipart the file using okhttp3.RequestBody
        Map<String, RequestBody> map = new HashMap<>();
        File file = new File(mediaPath);

        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        map.put("file\"; filename=\"" + file.getName() + "\"", requestBody);

            return map ;


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, 0);



                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(AddPhotoAndBio.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }


        }
    }


    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void service(String result, Boolean message) {
        if(message)
        {
            startActivity(new Intent(getApplicationContext() , Addbio.class));
        }else
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onBackPressed() {


                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

    }



}
