package com.bianki.biankiapp.MainApp;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bianki.biankiapp.ClassModel.profileImage;
import com.bianki.biankiapp.Database.Helper;
import com.bianki.biankiapp.GetMedia.Test2;
import com.bianki.biankiapp.Login.login;
import com.bianki.biankiapp.R;
import com.bianki.biankiapp.uploadmedia;
import com.bianki.biankiapp.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements HomeView  {

    Helper helper;
    String cursor;
    TextView token;
    Button logout;
    SharedPreferences sp;
    SharedPreferences.Editor speditor;
    EditText bio;
    Button publish;
    MainappPresenter mainappPresenter;
    ImageView imageView;
    static final int id = 0;
    Bitmap bitmap;
    Button save;
    Uri selectimguri;
    private static final int READ_REQUEST_CODE = 300;
    String mediaPath;
    String[] mediaColumns = { MediaStore.Video.Media._ID };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        sp = getApplicationContext().getSharedPreferences("login", 0);
        speditor = sp.edit();

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                speditor.putBoolean("logged", false);
                speditor.apply();
                finish();
                startActivity(new Intent(getApplicationContext(), login.class));


            }
        });

        Button button = findViewById(R.id.test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , uploadmedia.class));
            }
        });
        Button button2 = findViewById(R.id.test2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , Test2.class));
            }
        });

        bio = findViewById(R.id.bio);
        publish = findViewById(R.id.publish);
        imageView = findViewById(R.id.imageView);
        save = findViewById(R.id.sava);

        mainappPresenter = new MainappPresenter(this);


        helper = new Helper(getApplicationContext());
        cursor = helper.getiddata();
        token = findViewById(R.id.token);
        token.setText(cursor);

        save.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {



                uploadFile();



//                MultipartBody.Part pathimage = path();
//                Log.e("path", pathimage.toString());
//                mainappPresenter.profileImage(pathimage, cursor);
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



        publish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String localbio = bio.getText().toString();

                mainappPresenter.addBio(localbio, cursor);
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
                Toast.makeText(this, "k", Toast.LENGTH_SHORT).show();

            }
        }catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }


}




    // Providing Thumbnail For Selected Image
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

        // Map is used to multipart the file using okhttp3.RequestBody
        Map<String, RequestBody> map = new HashMap<>();
        File file = new File(mediaPath);

        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        map.put("file\"; filename=\"" + file.getName() + "\"", requestBody);


        RequestBody name = RequestBody.create(MediaType.parse("text/plain"),
                cursor);


                Call<profileImage> mealsCall = Utils.getApi().profileImage(name, map);
        mealsCall.enqueue(new Callback<profileImage>() {


            @Override
            public void onResponse(Call<profileImage> call, Response<profileImage> response) {

                if (response.isSuccessful() && response.body() != null) {

                    Log.e("RESPONSE", response.body().toString());
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "False", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<profileImage> call, Throwable t) {
                Log.e("RESPONSE", t.getLocalizedMessage().toString());
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();


            }

        });
    }


//
//    public MultipartBody.Part path (){
//
//
//
//        File file = new File(selectimguri.getPath());
//
//        Log.e("xxxS" , file.toString());
//
//
//        RequestBody requestBodyFile = RequestBody.create(MediaType.parse("image/*"), file);
//
//
//// MultipartBody.Part is used to send also the actual file name
//        MultipartBody.Part body =
//                MultipartBody.Part.createFormData("image", file.getName(), requestBodyFile);
//
//        Log.e("S" , body.toString());
//
//        return body ;
//
//    }
//
////    public String imaageTostring()
////    {
////
////        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
////
////            bitmap.compress(Bitmap.CompressFormat.JPEG , 0 , byteArrayOutputStream);
////            byte [] imagebyte = byteArrayOutputStream.toByteArray();
////
////
////        return Base64.encodeToString(imagebyte,Base64.DEFAULT);
////    }
//




    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void service(String result, Boolean message) {

        Toast.makeText(this, result , Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }


        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(R.drawable.exit).setTitle("Exit from application")
                .setMessage("Are you sure")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                }).setNegativeButton("no", null).show();
    }
}
