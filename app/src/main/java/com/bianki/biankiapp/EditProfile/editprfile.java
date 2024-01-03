package com.bianki.biankiapp.EditProfile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bianki.biankiapp.Database.Helper;
import com.bianki.biankiapp.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class editprfile extends AppCompatActivity implements HomeView {

    ImageView back;
    CircularImageView imageView;
    TextView name;
    EditText fullname;
    EditText username;
    EditText bio;
    EditText website;
    ImageView save;
    String mediaPath="";
    EditPresenter editPresenter ;
    Helper helper ;
    String cursor;

    String photo ;
    String localfullname;
    String localusername ;
    String bioloca ;
    ProgressBar progressBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprfile);

        back = findViewById(R.id.back);
        imageView = findViewById(R.id.imageView);
        name = findViewById(R.id.textfullname);
        fullname = findViewById(R.id.fullnameedit);
        username = findViewById(R.id.usernameedit);
        bio = findViewById(R.id.bioedit);
        website = findViewById(R.id.wbsiteedit);
        save = findViewById(R.id.saveinfo);
        editPresenter = new EditPresenter(this);

        helper = new Helper(getApplicationContext());
        cursor = helper.getiddata();

        progressBar = findViewById(R.id.progressBar);

        Intent intent = getIntent();
         photo = intent.getStringExtra("photo");
         localfullname = intent.getStringExtra("fullname");
         localusername = intent.getStringExtra("username");
        bioloca = intent.getStringExtra("bio");


       // if (!photo.equals(" "))
        try{
            Picasso.get().load(photo).placeholder(R.drawable.avatar).into(imageView);

        }catch (Exception e){}

        name.setText(localfullname);
        fullname.setText(localfullname);
        username.setText(localusername);
        bio.setText(bioloca);



        bio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (null != bio.getLayout() && bio.getLayout().getLineCount() > 5) {
                    bio.getText().delete(bio.getText().length() - 1, bio.getText().length());
                }
            }
        });




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


             Map<String, RequestBody> map = uploadFile();


                String locslbio = bio.getText().toString();
                String weblocal = website.getText().toString();
                String localfullname = fullname.getText().toString();
                String user = username.getText().toString();


//                RequestBody map = RequestBody.create(MediaType.parse("text/plain"),
//                        "");

                RequestBody name = RequestBody.create(MediaType.parse("text/plain"),
                        cursor);
                RequestBody fullname = RequestBody.create(MediaType.parse("text/plain"),
                        localfullname);
                RequestBody username = RequestBody.create(MediaType.parse("text/plain"),
                        user);
                RequestBody bio = RequestBody.create(MediaType.parse("text/plain"),
                        locslbio);
                RequestBody web = RequestBody.create(MediaType.parse("text/plain"),
                        weblocal);

                editPresenter.editProfile( map , name , fullname , username , bio , web);


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

            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }


    }


    private Map<String, RequestBody> uploadFile() {


        if (mediaPath.isEmpty())
        {
            Map<String, RequestBody> mapp = new HashMap<>();
            return  mapp;

        }else
        {
            // Map is used to multipart the file using okhttp3.RequestBody
            Map<String, RequestBody> map = new HashMap<>();
            File file = new File(mediaPath);

            // Parsing any Media type file
            RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
            map.put("file\"; filename=\"" + file.getName() + "\"", requestBody);

            return map ;
        }




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
                    Toast.makeText(editprfile.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this, "لا يوجد اتصال بالانترنت", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void Result(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        if(result.equals(" profile updated successfully"))
        {
            finish();
        }

    }
}
