package com.bianki.biankiapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.bianki.biankiapp.Adapter.ViewPagerLocal;
import com.bianki.biankiapp.ClassModel.uploadMedia;
import com.bianki.biankiapp.Database.Helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class uploadmedia extends AppCompatActivity {

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
    ArrayList<Uri> multiFiles;
    RecyclerView recyclerView ;
    List<Uri> uriList ;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploadmedia);


        helper = new Helper(getApplicationContext());
        cursor = helper.getiddata();

        save = findViewById(R.id.saveaqq);
        imageView = findViewById(R.id.imageView);
        constraintLayout = findViewById(R.id.constraintLayout);

        save.setVisibility(View.GONE);
        text = findViewById(R.id.text);
        back = findViewById(R.id.back);
        progressBar = findViewById(R.id.progressBar);
        multiFiles = new ArrayList<Uri>();
        recyclerView = findViewById(R.id.recyclerView);
        uriList = new ArrayList<>();

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
                    multiFiles.clear();
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0);

                }

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                uploadFile(multiFiles);
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

                ClipData clipData = data.getClipData();

                if (clipData == null) {
                    Uri uri = data.getData();
                    multiFiles.add(uri);
                } else {
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        Uri uri = item.getUri();
                        multiFiles.add(uri);

                    }
                }


                recyclerView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);

                ViewPagerLocal homeAdapter = new ViewPagerLocal(multiFiles, this );
                GridLayoutManager layoutManager = new GridLayoutManager(this, 1,
                        GridLayoutManager.HORIZONTAL, false);
                SnapHelper snapHelper = new PagerSnapHelper();
                snapHelper.attachToRecyclerView(recyclerView);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(homeAdapter);
                recyclerView.setNestedScrollingEnabled(true);
                homeAdapter.notifyDataSetChanged();





                type = "image";
                save.setVisibility(View.VISIBLE);


            } else {
                Toast.makeText(this, "You haven't picked Image/Video", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            Log.e("Message", e.getMessage() + e.getLocalizedMessage());
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
    private void uploadFile(List<Uri> uris) {

        List<MultipartBody.Part> map = new ArrayList<>();

        for (int i = 0; i < multiFiles.size(); i++) {



            map.add(getPath("", uris.get(i)));
//            RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
//
//            map.put("file\"; filename=\"" + file.getName() + "\"", requestBody);

        }


//
//        Log.e("F", mediaPath);
//
//        // Map is used to multipart the file using okhttp3.RequestBody
//        Map<String, RequestBody> map = new HashMap<>();
//        File file = new File(mediaPath);

        String get = text.getText().toString();

        // Parsing any Media type file
//        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
//
//        map.put("file\"; filename=\"" + file.getName() + "\"", requestBody);


        RequestBody typeoffile = RequestBody.create(MediaType.parse("text/plain"),
                "image");

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


        Call<uploadMedia> mealsCall = Utils.getApi().multipleImage(map, text, typeoffile, latidude, longtiude, name);
        mealsCall.enqueue(new Callback<uploadMedia>() {


            @Override
            public void onResponse(Call<uploadMedia> call, Response<uploadMedia> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {

                    Toast.makeText(getApplicationContext(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    multiFiles.clear();
                    finish();

                }
            }

            @Override
            public void onFailure(Call<uploadMedia> call, Throwable t) {

                progressBar.setVisibility(View.GONE);

                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();


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


                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0);


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


    public MultipartBody.Part getPath(String partName, Uri fileUri) {

        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile(this, fileUri);

        Log.e("File", file.toString());
        //compress the image using Compressor lib
        // create RequestBody instance from file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/image*"), file);

        Log.e("File", requestBody.toString());


        // Parsing any Media type file


        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestBody);


    }
}





