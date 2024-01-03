package com.bianki.biankiapp.SpecificGroup;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bianki.biankiapp.Adapter.RecyclerViewHomeAdapterGroup;
import com.bianki.biankiapp.ClassModel.groupPosts;
import com.bianki.biankiapp.Database.Helper;
import com.bianki.biankiapp.R;
import com.bianki.biankiapp.uploadmediatogroup;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class SpecificGroup extends AppCompatActivity implements HomeView {

    Toolbar toolbar ;
    ImageView imageView ;
    CollapsingToolbarLayout cardInfo_collapsing;
    ImageView back  ;
    ImageView fab;
    LinearLayout LinearLayoutofhome;
    LinearLayout LinearLayoutofphotoAndvideo;
    LinearLayout LinearLayoutoftext;
    LinearLayout linearLayoutText ;
    LinearLayout backgroundtext ;
    Button exit, post;
    ImageView one, two, three, four, five;
    EditText gettext;
    boolean flag = true;
    Bitmap bitmap;
    String color;
    Helper helper ;
    String cursor ;
    AddGroupPostPresenter addGroupPostPresenter ;
    String idofgroup ;
    RecyclerView recyclerView ;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_group);

        toolbar = findViewById(R.id.toolbar);
        imageView = findViewById(R.id.image);
        cardInfo_collapsing = findViewById(R.id.cardInfo_collapsing);
         back = toolbar.findViewById(R.id.back);
        fab = findViewById(R.id.fab);
        linearLayoutText = findViewById(R.id.lineartext);


        LinearLayoutofhome = findViewById(R.id.LinearLayoutofhome);
        LinearLayoutofhome.setVisibility(View.GONE);
        LinearLayoutofphotoAndvideo = findViewById(R.id.LinearLayoutofphotoAndvideo);
        LinearLayoutoftext = findViewById(R.id.LinearLayoutoftext);
        backgroundtext = findViewById(R.id.backgroundtext);
        recyclerView = findViewById(R.id.recyclerView);
        addGroupPostPresenter = new AddGroupPostPresenter(this);

        exit = findViewById(R.id.exit);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        gettext = findViewById(R.id.gettext);
        post = findViewById(R.id.post);

        helper = new Helper(getApplicationContext());
        cursor = helper.getiddata();



        bitmap = ((BitmapDrawable) getDrawable(R.drawable.one)).getBitmap();
        color = "black";




        back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 finish();
             }
         });


        cardInfo_collapsing.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        cardInfo_collapsing.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);


        Intent intent = getIntent();
        String path = intent.getStringExtra("photo");
        String nameGroup = intent.getStringExtra("name");
        idofgroup = intent.getStringExtra("id");
        cardInfo_collapsing.setTitle(nameGroup);

        addGroupPostPresenter.groupPosts(idofgroup , cursor);

        try{
            Picasso.get().load(path).into(imageView);

        }catch (Exception e){}


        LinearLayoutofhome.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (flag) {
                    LinearLayoutofhome.setVisibility(View.VISIBLE);
                    flag = false;
                } else {

                    LinearLayoutofhome.setVisibility(View.GONE);
                    flag = true;


                }


                //   startActivity(new Intent(getApplicationContext(), uploadmedia.class));

            }
        });


        LinearLayoutofhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayoutofhome.setVisibility(View.GONE);
                flag = true;

            }
        });

        linearLayoutText.setVisibility(View.GONE);
        backgroundtext.setVisibility(View.GONE);
        LinearLayoutoftext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                LinearLayoutofhome.setVisibility(View.GONE);
                linearLayoutText.setVisibility(View.VISIBLE);
                backgroundtext.setVisibility(View.VISIBLE);
                fab.setVisibility(View.GONE);


            }
        });


//        backgroundtext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                fab.setVisibility(View.VISIBLE);
//                linearLayoutText.setVisibility(View.GONE);
//                LinearLayoutofhome.setVisibility(View.GONE);
//                backgroundtext.setVisibility(View.GONE);
//                flag = true;
//
//
//            }
//        });


        LinearLayoutofphotoAndvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), uploadmediatogroup.class);
                intent1.putExtra("id" , idofgroup);
                startActivity(intent1);
                overridePendingTransition(R.anim.alphain, R.anim.alphaout);
                linearLayoutText.setVisibility(View.GONE);
                LinearLayoutofhome.setVisibility(View.GONE);
                backgroundtext.setVisibility(View.GONE);
                flag = true;

            }
        });



        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setVisibility(View.VISIBLE);
                linearLayoutText.setVisibility(View.GONE);
                LinearLayoutofhome.setVisibility(View.GONE);
                backgroundtext.setVisibility(View.GONE);
                flag = true;
                gettext.setText("");

            }
        });



        gettext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (null != gettext.getLayout() && gettext.getLayout().getLineCount() > 5) {
                    gettext.getText().delete(gettext.getText().length() - 1, gettext.getText().length());
                }
            }
        });



        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 5);

                        return;

                    }

                } else {


                    linearLayoutText.getBackground();
                    //     pathlocalimage =  linearLayoutText.getBackground();

                    //  Log.e("x" , pathlocalimage);
                    String get = gettext.getText().toString();

                    Map<String, RequestBody> mapp = null;
                    try {
                        mapp = getURLForResource(linearLayoutText.getBackground());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //    Log.e("x" , String.valueOf(map));

                    RequestBody gettext = RequestBody.create(MediaType.parse("text/plain"),
                            get);

                    RequestBody name = RequestBody.create(MediaType.parse("text/plain"),
                            cursor);

                    RequestBody posttype = RequestBody.create(MediaType.parse("text/plain"),
                            "text");
                    RequestBody latidude = RequestBody.create(MediaType.parse("text/plain"),
                            String.valueOf(0));
                    RequestBody longdude = RequestBody.create(MediaType.parse("text/plain"),
                            String.valueOf(0));


                    RequestBody textcolor = RequestBody.create(MediaType.parse("text/plain"),
                            color);

                    RequestBody id = RequestBody.create(MediaType.parse("text/plain"),
                            idofgroup);


                    addGroupPostPresenter.AddpostGroup(id , posttype , mapp , textcolor , gettext , latidude , longdude , name);


                    fab.setVisibility(View.VISIBLE);
                    linearLayoutText.setVisibility(View.GONE);
                    LinearLayoutofhome.setVisibility(View.GONE);
                    backgroundtext.setVisibility(View.GONE);
                    flag = true;

                }


            }
        });


        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                linearLayoutText.setBackgroundResource(R.drawable.onebackground);
                one.setForeground(getDrawable(R.drawable.shaplovalbluedown));


                two.setForeground(getDrawable(R.drawable.remove));
                three.setForeground(getDrawable(R.drawable.remove));
                four.setForeground(getDrawable(R.drawable.remove));
                five.setForeground(getDrawable(R.drawable.remove));


                gettext.setTextColor(getResources().getColor(R.color.black));
                gettext.setHintTextColor(getResources().getColor(R.color.black));


                // choose = "content://media/external/images/media/105981" ;
                bitmap = ((BitmapDrawable) getDrawable(R.drawable.one)).getBitmap();
                color = "black";


            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                linearLayoutText.setBackgroundResource(R.drawable.twobackground);
                two.setForeground(getDrawable(R.drawable.shaplovalbluedown));


                one.setForeground(getDrawable(R.drawable.remove));
                three.setForeground(getDrawable(R.drawable.remove));
                four.setForeground(getDrawable(R.drawable.remove));
                five.setForeground(getDrawable(R.drawable.remove));

                gettext.setTextColor(getResources().getColor(R.color.white));
                gettext.setHintTextColor(getResources().getColor(R.color.white));

                bitmap = ((BitmapDrawable) getDrawable(R.drawable.two)).getBitmap();
                color = "white";


            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                linearLayoutText.setBackgroundResource(R.drawable.threebackground);
                three.setForeground(getDrawable(R.drawable.shaplovalbluedown));


                two.setForeground(getDrawable(R.drawable.remove));
                one.setForeground(getDrawable(R.drawable.remove));
                four.setForeground(getDrawable(R.drawable.remove));
                five.setForeground(getDrawable(R.drawable.remove));


                bitmap = ((BitmapDrawable) getDrawable(R.drawable.three)).getBitmap();
                color = "white";

                gettext.setTextColor(getResources().getColor(R.color.white));
                gettext.setHintTextColor(getResources().getColor(R.color.white));
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                linearLayoutText.setBackgroundResource(R.drawable.fourbackground);
                four.setForeground(getDrawable(R.drawable.shaplovalbluedown));


                two.setForeground(getDrawable(R.drawable.remove));
                three.setForeground(getDrawable(R.drawable.remove));
                one.setForeground(getDrawable(R.drawable.remove));
                five.setForeground(getDrawable(R.drawable.remove));

                bitmap = ((BitmapDrawable) getDrawable(R.drawable.four)).getBitmap();
                color = "white";

                gettext.setTextColor(getResources().getColor(R.color.white));
                gettext.setHintTextColor(getResources().getColor(R.color.white));
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                linearLayoutText.setBackgroundResource(R.drawable.fivebackground);
                five.setForeground(getDrawable(R.drawable.shaplovalbluedown));


                two.setForeground(getDrawable(R.drawable.remove));
                three.setForeground(getDrawable(R.drawable.remove));
                four.setForeground(getDrawable(R.drawable.remove));
                one.setForeground(getDrawable(R.drawable.remove));

                bitmap = ((BitmapDrawable) getDrawable(R.drawable.five)).getBitmap();
                color = "white";

                gettext.setTextColor(getResources().getColor(R.color.white));
                gettext.setHintTextColor(getResources().getColor(R.color.white));

            }
        });





    }



    public Map<String, RequestBody> getURLForResource(Drawable resourceId) throws IOException {



        String path = getImageUri(this, bitmap);
        String s = getPath(Uri.parse(path));
        File f = new File(s);
        Map<String, RequestBody> map = new HashMap<>();
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), f);
        map.put("file\"; filename=\"" + f.getName() + "\"", requestBody);


        return map;
    }



    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) return null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s = cursor.getString(column_index);
        cursor.close();
        return s;
    }


    public String getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return path;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void Result(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void getMedia(List<groupPosts.File> getMedia) {

        Log.e("S" , getMedia.toString());





        RecyclerViewHomeAdapterGroup homeAdapter = new RecyclerViewHomeAdapterGroup(getMedia, this);
        recyclerView.setAdapter(homeAdapter);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 1,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

//        homeAdapter.setOnItemClickListener((view, position) -> {
//
//            if (getMedia.get(position).getPostType().equals("video"))
//            {
//                Intent intent = new Intent(getApplicationContext() , videoplyer.class);
//                intent.putExtra("video" , getMedia.get(position).getPath());
//
//                startActivity(intent);
//
//            }
//
//
//            else if (getMedia.get(position).getPostType().equals("image"))
//            {
//                Intent intent = new Intent(getApplicationContext() , Fullscreenimage.class);
//                intent.putExtra("photo" , getMedia.get(position).getPath());
//                startActivity(intent);
//            }});

    }

    }
