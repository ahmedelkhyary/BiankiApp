package com.bianki.biankiapp.HomePage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.bianki.biankiapp.Adapter.RecyclerViewHomeAdapterHome;
import com.bianki.biankiapp.Adapter.RecyclerViewHomeAdapterViewPager;
import com.bianki.biankiapp.Adapter.ViewPagerAdapter;
import com.bianki.biankiapp.ClassModel.getFollowingUsers;
import com.bianki.biankiapp.ClassModel.getMedia;
import com.bianki.biankiapp.ClassModel.getcomment;
import com.bianki.biankiapp.ClassModel.getcurrentprofile;
import com.bianki.biankiapp.ClassModel.postLikedUsers;
import com.bianki.biankiapp.CurrentProfile.profilehome;
import com.bianki.biankiapp.Database.Helper;
import com.bianki.biankiapp.EditProfile.editprfile;
import com.bianki.biankiapp.EndlessParentScrollListener;
import com.bianki.biankiapp.Groups;
import com.bianki.biankiapp.Login.login;
import com.bianki.biankiapp.R;
import com.bianki.biankiapp.Search.SearchUser;
import com.bianki.biankiapp.Stringpath;
import com.bianki.biankiapp.uploadmedia;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import jp.shts.android.storiesprogressview.StoriesProgressView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HomeView, StoriesProgressView.StoriesListener {

    SharedPreferences sp;
    SharedPreferences.Editor speditor;
    Helper helper;
    String cursor;
    com.bianki.biankiapp.HomePage.getMediaPresenter getMediaPresenter;
    SwipeRefreshLayout SwipeRefreshLayout;
    RecyclerView recyclerView;
    ImageView search;
    ImageView search2;
    RecyclerView viewPager;
    AppBarLayout appBarLayout;
    LinearLayout linearLayout;
    LinearLayoutManager LinearLayoutManager;
    ImageView imagestory;
    ImageView imagehome;
    TextView nameofuserhome;
    LottieAnimationView progressBar;
    String mediaPath;
    RecyclerViewHomeAdapterHome homeAdapter;
    List<getMedia.File> getMedaiaLocal;
    ImageView fab;
    LinearLayout LinearLayoutofhome;
    ConstraintLayout ConstraintLayout;
    LinearLayout LinearLayoutofphotoAndvideo;
    LinearLayout LinearLayoutoftext;
    LinearLayout linearLayoutText;
    Button exit, post;
    ImageView one, two, three, four, five;
    EditText gettext;
    boolean flag = true;
    Bitmap bitmap;
    String pathlocalimage;
    int x = 1;
    String type = "";
    GridLayoutManager layoutManager;
    List<getMedia.File> list;
    int q;

    List<Stringpath> imageswitchers;
    ViewPagerAdapter myslide;

    TextView ask;

    String photoofuserintent;
    String usernameintent;
    String fullnameintent;
    String biointent;

    String color;

    Handler silehander = new Handler();
    androidx.viewpager2.widget.ViewPager2 ViewPager2;

    private StoriesProgressView storiesProgressView;

    int y = 0;

    LinearLayout backgroundtext;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint({"ResourceAsColor", "ResourceType", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getMedaiaLocal = new ArrayList<>();


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.textofbox));
        }

        Objects.requireNonNull(getSupportActionBar()).setTitle("BianKi");

        toolbar.setTitleTextColor(R.color.textofbox);
        toolbar.setSubtitleTextColor(R.color.textofbox);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        SwipeRefreshLayout = findViewById(R.id.SwipeRefreshLayout);

        imageswitchers = new ArrayList<>();


        recyclerView = findViewById(R.id.recyclerView);
        homeAdapter = new RecyclerViewHomeAdapterHome(getMedaiaLocal, this);
        recyclerView.setAdapter(homeAdapter);
        LinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LinearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        q = 0;

        progressBar = findViewById(R.id.progressBar);
        LinearLayoutofhome = findViewById(R.id.LinearLayoutofhome);
        LinearLayoutofhome.setVisibility(View.GONE);
        ConstraintLayout = findViewById(R.id.ConstraintLayout);
        LinearLayoutofphotoAndvideo = findViewById(R.id.LinearLayoutofphotoAndvideo);
        LinearLayoutoftext = findViewById(R.id.LinearLayoutoftext);
        linearLayoutText = findViewById(R.id.lineartext);
        exit = findViewById(R.id.exit);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        gettext = findViewById(R.id.gettext);
        ask = findViewById(R.id.ask);

        post = findViewById(R.id.post);
        //     appBarLayout = findViewById(R.id.appbarlayout);
        imagestory = findViewById(R.id.imagestory);
        fab = findViewById(R.id.fab);
        NestedScrollView nestedSV = findViewById(R.id.nestedScrollView2);
        backgroundtext = findViewById(R.id.backgroundtext);


        bitmap = ((BitmapDrawable) getDrawable(R.drawable.one)).getBitmap();
        color = "black";


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


        ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), com.bianki.biankiapp.ASK.ask.class));
                overridePendingTransition(R.anim.alphain, R.anim.alphaout);

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


                getMediaPresenter.uploadmedia(mapp, name, posttype, gettext, textcolor, latidude, longdude);


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


        imagestory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                    Intent mediaChooser = new Intent(MediaStore.ACTION_IMAGE_CAPTURE +MediaStore.ACTION_IMAGE_CAPTURE );
////comma-separated MIME types
//              //      mediaChooser.setType("video/*, image/*");
//
//                    startActivityForResult(mediaChooser, 0);

                startDialog();

            }


        });


        search = findViewById(R.id.search);
        search2 = findViewById(R.id.search2);

        viewPager = findViewById(R.id.viewPagerHeader);


        LinearLayoutofhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayoutofhome.setVisibility(View.GONE);
                flag = true;
            }
        });


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

        LinearLayoutofphotoAndvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), uploadmedia.class));
                overridePendingTransition(R.anim.alphain, R.anim.alphaout);
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
//
//            }
//        });


        try {
            if (Build.VERSION.SDK_INT >= 21) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        sp = getApplicationContext().getSharedPreferences("login", 0);
        speditor = sp.edit();

        helper = new Helper(getApplicationContext());
        cursor = helper.getiddata();

        getMediaPresenter = new getMediaPresenter(this);
        getMediaPresenter.getMedia(x, 100, cursor);
        getMediaPresenter.getcurrentprofile(cursor);
        getMediaPresenter.getFollowingUsers(cursor);


        SwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                Log.e("list" , list.toString());
//                list.removeAll(list);
//                Log.e("listttt" , list.toString());

                q = 1;
                x = 1;


                getMediaPresenter.getMedia(1, 50, cursor);
                getMediaPresenter.getFollowingUsers(cursor);

            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.toggle);


        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawer.openDrawer(GravityCompat.END);
            }
        });

        search2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SearchUser.class));
            }
        });


//...


        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);


        View headerone = navigationView.getHeaderView(0);
        CircularImageView imagehome = headerone.findViewById(R.id.imagehome);
        ImageView setting = headerone.findViewById(R.id.settingaccount);

        imagehome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), profilehome.class));

            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), editprfile.class);

                intent.putExtra("photo", photoofuserintent);
                intent.putExtra("fullname", fullnameintent);
                intent.putExtra("username", usernameintent);
                intent.putExtra("bio", biointent);

                startActivity(intent);
                overridePendingTransition(R.anim.alphain, R.anim.alphaout);
            }
        });


        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);


        NavigationView layout = findViewById(R.id.layout);

        View header = layout.getHeaderView(0);
        LinearLayout LinearLayout = header.findViewById(R.id.testhome);
        LinearLayout groupid = header.findViewById(R.id.groupid);


        groupid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Groups.class));
            }
        });

        LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawers();
                nestedSV.fullScroll(ScrollView.FOCUS_UP);


            }
        });


        layout.setNavigationItemSelectedListener(this);
        layout.setItemIconTintList(null);


//        if (Locale.getDefault().getLanguage().equals("ar")) {
//            Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/Tajawal-Medium.ttf");
//
//            linearLayout.setBackgroundResource(R.drawable.menuhomear);
//
//
//            //   nestedScrollView.setPadding(20, 0, 0, 0);
//
//
//        } else {
//            Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/SF-Pro-Display-Light.otf");
//            Typeface MLight2 = Typeface.createFromAsset(getAssets(), "fonts/SF-Pro-Display-Semibold.otf");
//
//
//        }

//


        nestedSV.setOnScrollChangeListener(new EndlessParentScrollListener(LinearLayoutManager) {

            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                q = 0;
                x++;
                getMediaPresenter.getMedia(x, 20, cursor);
            }
        });
//
//        if (nestedSV != null) {
//
//            nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//                @Override
//                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                    String TAG = "nested_sync";
//                    if (scrollY > oldScrollY) {
//                        Log.e(TAG, "Scroll DOWN");
//                    }
//                    if (scrollY < oldScrollY) {
//                        Log.e(TAG, "Scroll UP");
//                    }
//
//                    if (scrollY == 0) {
//                        Log.e(TAG, "TOP SCROLL");
//                    }
//
//                    if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
//                        Log.e(TAG, "BOTTOM SCROLL");
//                        q = 0;
//                        x++;
//                        getMediaPresenter.getMedia(x, 50, cursor);
//
//
//
//
////                        if (!isRecyclerViewWaitingtoLaadData) //check for scroll down
////                        {
////
////                            if (!loadedAllItems) {
////                                showUnSentData();
////                            }
////                        }
//
////                        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
////                            @Override
////                            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
////                                x++;
////                                getMediaPresenter.getMedia(x, 20, cursor);
////
////
////                            }
////                        });
//                    }
//                }
//            });
//        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.


        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        int id = menuItem.getItemId();


        if (id == R.id.logout) {


            speditor.putBoolean("logged", false);
            speditor.apply();
            finish();
            startActivity(new Intent(getApplicationContext(), login.class));
            overridePendingTransition(R.anim.alphain, R.anim.alphaout);

        } else if (id == R.id.myaccount) {
            startActivity(new Intent(getApplicationContext(), profilehome.class));
            overridePendingTransition(R.anim.alphain, R.anim.alphaout);
        } else if (id == R.id.home) {
            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void showLoading() {

        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        SwipeRefreshLayout.setRefreshing(false);


    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, "عفوا لايوجد اتصال بالانترنت", Toast.LENGTH_SHORT).show();
        Log.e("ss", message);

    }

    @Override
    public void getMedia(List<getMedia.File> getMedia) {

        if (!getMedia.isEmpty()) {


            if (q == 1) {
                homeAdapter = new RecyclerViewHomeAdapterHome(getMedia, this);
                recyclerView.setAdapter(homeAdapter);


            } else {


                homeAdapter.appenddata(getMedia);
                homeAdapter.notifyDataSetChanged();


            }


        }
    }


//        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(LinearLayoutManager) {
//            @Override
//            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//
//                Log.e("totalItemsCount" , String.valueOf(totalItemsCount));
//                x++;
//                getMediaPresenter.getMedia(x, 10, cursor);
//
//
//            }
//        });


    @Override
    public void service(String result, ImageView v, TextView textView) {

    }

    @Override
    public void comment(String result) {

    }

    @Override
    public void reply(String result) {

        if (result.equals("media added successfully")) {
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            gettext.setText("");

        } else if (result.equals("story added successfully")) {
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void getComment(List<getcomment.comments> getcomment) {

    }

    @Override
    public void postLikedUsers(List<postLikedUsers.LikedUser> postLikedUsers) {

    }

    @Override
    public void getcurrentprofile(getcurrentprofile.User users) {


        imagehome = findViewById(R.id.imagehome);
        nameofuserhome = findViewById(R.id.nameofuserhome);

        String photo = users.getProfileImage();

        photoofuserintent = users.getProfileImage();
        fullnameintent = users.getFullName();
        usernameintent = users.getUserName();
        biointent = users.getBio();
        // if (!photoofuserintent.isEmpty()) {

        try {
            Picasso.get().load(photo).placeholder(R.drawable.avatar).into(imagehome);

        } catch (Exception e) {
        }
        //   }
        nameofuserhome.setText(users.getUserName());

    }

    @Override
    public void hideLoadingforcomment() {

    }

    @Override
    public void showLoadingforcommen() {

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void getFollowingUsers(List<getFollowingUsers.Story.User> getFollowingUsers) {

        RecyclerViewHomeAdapterViewPager headerAdapter = new RecyclerViewHomeAdapterViewPager(getFollowingUsers, this);
        viewPager.setAdapter(headerAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1,
                GridLayoutManager.HORIZONTAL, false);
        viewPager.setLayoutManager(layoutManager);
        viewPager.setNestedScrollingEnabled(true);
        headerAdapter.notifyDataSetChanged();

        headerAdapter.setOnItemClickListener((view, position) -> {

            y = 0;

            imageswitchers.clear();


            Dialog bottomSheetDialog = new Dialog(this, android.R.style.Theme_Material_NoActionBar_Fullscreen);
            View view1 = LayoutInflater.from(this).inflate(R.layout.desofviewpager, null);


//            for (int i = 0; i < getFollowingUsers.get(position).getStories().size(); i++) {
//
//                String photo = getFollowingUsers.get(position).getStories().get(i).getPath();
//                String type = getFollowingUsers.get(position).getStories().get(i).getMediaType();
//                String text = getFollowingUsers.get(position).getStories().get(i).getText();
//                String name = getFollowingUsers.get(position).getFullName();
//                String userphoto = getFollowingUsers.get(position).getProfileImage();
//
//                imageswitchers.add(new Stringpath(photo, type, text, name, userphoto));
//
//
//            }


            CircularImageView circularImageView1 = view1.findViewById(R.id.CircularImageView);
            TextView textView = view1.findViewById(R.id.nameofuser);
            LinearLayout next = view1.findViewById(R.id.next);
            LinearLayout prev = view1.findViewById(R.id.prev);
            ImageView imageView1 = view1.findViewById(R.id.imageofviewpager);
            storiesProgressView = view1.findViewById(R.id.stories);
            VideoView VideoView = view1.findViewById(R.id.VideoView);


            textView.setText(getFollowingUsers.get(position).getUserName());
            try {
                Picasso.get().load(getFollowingUsers.get(position).getProfileImage()).into(circularImageView1);

            } catch (Exception e) {
            }


            storiesProgressView.setStoriesCount(getFollowingUsers.get(position).getStories().size()); // <- set stories
            storiesProgressView.setStoryDuration(5000);

            if (getFollowingUsers.get(position).getStories().get(y).getText().equals("image")) {
                VideoView.setVisibility(View.GONE);
                imageView1.setVisibility(View.VISIBLE);
                try {
                    Picasso.get().load(getFollowingUsers.get(position).getStories().get(y).getPath()).into(imageView1);

                } catch (Exception e) {
                }
                storiesProgressView.startStories();


            } else {


                imageView1.setVisibility(View.GONE);
                VideoView.setVisibility(View.VISIBLE);
                VideoView.setVideoPath(getFollowingUsers.get(position).getStories().get(y).getPath());
                VideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        int videoDuration = VideoView.getDuration();
                        Log.e("Duration", String.valueOf(videoDuration));
                        storiesProgressView.setStoryDuration(videoDuration);
                        storiesProgressView.startStories();


                    }
                });


                VideoView.start();


            }


            storiesProgressView.setStoriesListener(new StoriesProgressView.StoriesListener() {
                @Override
                public void onNext() {
                    y++;
                    storiesProgressView.setStoryDuration(5000);

                    if (getFollowingUsers.get(position).getStories().get(y).getText().equals("image")) {

                        VideoView.setVisibility(View.GONE);
                        imageView1.setVisibility(View.VISIBLE);
                        try {
                            Picasso.get().load(getFollowingUsers.get(position).getStories().get(y).getPath()).into(imageView1);

                        } catch (Exception e) {
                        }
                    } else {


                        imageView1.setVisibility(View.GONE);
                        VideoView.setVisibility(View.VISIBLE);
                        VideoView.setVideoPath(getFollowingUsers.get(position).getStories().get(y).getPath());

                        VideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {


                            }
                        });


                        storiesProgressView.setStoryDuration(30000);
                        VideoView.start();


                    }


                }

                @Override
                public void onPrev() {


                    if (y == 0) {
                        return;
                    } else if (getFollowingUsers.get(position).getStories().get(--y).getText().equals("image")) {
                        storiesProgressView.setStoryDuration(5000);

                        VideoView.setVisibility(View.GONE);
                        imageView1.setVisibility(View.VISIBLE);


                        try {
                            Picasso.get().load(getFollowingUsers.get(position).getStories().get(y).getPath()).into(imageView1);

                        } catch (Exception e) {
                        }
                    } else {


                        imageView1.setVisibility(View.GONE);
                        VideoView.setVisibility(View.VISIBLE);
                        VideoView.setVideoPath(getFollowingUsers.get(position).getStories().get(y).getPath());
                        VideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {


                            }
                        });
                        storiesProgressView.setStoryDuration(30000);
                        VideoView.start();


                    }

                }


                @Override
                public void onComplete() {
                    y = 0;
                    storiesProgressView.destroy();
                    bottomSheetDialog.cancel();


                }
            }); // <- set listener

            imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });


            imageView1.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        // perform you task for button or view is hold
                        Log.i("TAG", "ACTION_DOWN");
                        storiesProgressView.pause();

                    }

                    if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                        // perform you task for button or view is released after hold
                        Log.i("TAG", "ACTION_UP---ACTION_CANCEL");
                        storiesProgressView.resume();

                    }
                    return false;
                }
            });


            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    storiesProgressView.skip();
                }
            });

            prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    storiesProgressView.reverse();
                }
            });
//
//             ViewPager2 = view1.findViewById(R.id.ViewPager);
//            myslide = new ViewPagerAdapter( imageswitchers , this);
//            ViewPager2.setAdapter(myslide);
//
//
//            ViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//                @Override
//                public void onPageSelected(int position) {
//                    silehander.removeCallbacks(sliderunnable);
//                    silehander.postDelayed(sliderunnable , 5000);
//                    super.onPageSelected(position);
//                }
//            });


            bottomSheetDialog.setContentView(view1);
            bottomSheetDialog.show();


        });


    }


    Runnable sliderunnable = new Runnable() {
        @Override
        public void run() {


            ViewPager2.setCurrentItem(ViewPager2.getCurrentItem() + 1);

        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {


                // Get the Image from data
                Uri selectedImage = data.getData();


                ContentResolver cr = this.getContentResolver();
                String mime = cr.getType(selectedImage);

                Log.e("mmmmmmmmmmm", mime.toString());

                if (mime.equals("image/png") || mime.equals("image/jpg") || mime.equals("image/gif") || mime.equals("image/jpeg") || mime.equals("image/tif")) {
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};


                    Log.e("ok", mime.toString());

                    Cursor cursorr = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursorr != null;
                    cursorr.moveToFirst();

                    int columnIndex = cursorr.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursorr.getString(columnIndex);
                    // Set the Image in ImageView for Previewing the Media
                    //   imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));


                    Map<String, RequestBody> map = new HashMap<>();
                    File file = new File(mediaPath);

                    // Parsing any Media type file
                    RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
                    map.put("file\"; filename=\"" + file.getName() + "\"", requestBody);


                    RequestBody text = RequestBody.create(MediaType.parse("text/plain"),
                            "image");
                    RequestBody latidude = RequestBody.create(MediaType.parse("text/plain"),
                            String.valueOf(0));
                    RequestBody longtiude = RequestBody.create(MediaType.parse("text/plain"),
                            String.valueOf(0));

                    RequestBody name = RequestBody.create(MediaType.parse("text/plain"),
                            cursor);


                    getMediaPresenter.addstory(map, name, text, latidude, longtiude);
                    cursorr.close();

                } else {
                    String[] filePathColumn = {MediaStore.Video.Media.DATA};

                    Cursor cursorr = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursorr != null;
                    cursorr.moveToFirst();

                    int columnIndex = cursorr.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursorr.getString(columnIndex);
                    // Set the Video Thumb in ImageView Previewing the Media

                    Log.e("ok", mime.toString());

                    Map<String, RequestBody> map = new HashMap<>();
                    File file = new File(mediaPath);

                    // Parsing any Media type file
                    RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
                    map.put("file\"; filename=\"" + file.getName() + "\"", requestBody);


                    RequestBody text = RequestBody.create(MediaType.parse("text/plain"),
                            "video");
                    RequestBody latidude = RequestBody.create(MediaType.parse("text/plain"),
                            String.valueOf(0));
                    RequestBody longtiude = RequestBody.create(MediaType.parse("text/plain"),
                            String.valueOf(0));

                    RequestBody name = RequestBody.create(MediaType.parse("text/plain"),
                            cursor);


                    getMediaPresenter.addstory(map, name, text, latidude, longtiude);
                    cursorr.close();

                }


            }
        } catch (Exception e) {
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

        return map;


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
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
                    Toast.makeText(Home.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            case 5:

                // post text
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

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


                    getMediaPresenter.uploadmedia(mapp, name, posttype, gettext, textcolor, latidude, longdude);


                    fab.setVisibility(View.VISIBLE);
                    linearLayoutText.setVisibility(View.GONE);
                    LinearLayoutofhome.setVisibility(View.GONE);
                    backgroundtext.setVisibility(View.GONE);
                    flag = true;

                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(Home.this, "Permission denied to write your External storage", Toast.LENGTH_SHORT).show();
                }
                return;


            case 10:

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    Intent intent = new Intent(
                            MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,
                            0);

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(Home.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
        }


    }


    private Map<String, RequestBody> uploadmedia() {

        // Map is used to multipart the file using okhttp3.RequestBody
        Map<String, RequestBody> map = new HashMap<>();
        File file = new File(pathlocalimage);


        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        map.put("file\"; filename=\"" + file.getName() + "\"", requestBody);


        return map;

    }


    @Override
    public void onBackPressed() {


        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Map<String, RequestBody> getURLForResource(Drawable resourceId) throws IOException {

        String pathurl = getImageUri(this, bitmap);
        Log.e("FFF" , pathurl);
        String path = getPath(Uri.parse(pathurl));
        File f = new File(path);


        Map<String, RequestBody> map = new HashMap<>();


        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), f);

        map.put("file\"; filename=\"" + f.getName() + "\"", requestBody);


        return map;
    }


//    private String getRealPathFromURI(Uri contentUri) {
//        String[] proj = { MediaStore.Images.Media.DATA };
//        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
//        Cursor cursor = loader.loadInBackground();
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        String result = cursor.getString(column_index);
//        cursor.close();
//        return result;
//    }


//    private File savebitmap(Bitmap bmp) {
//        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
//        OutputStream outStream = null;
//        // String temp = null;
//        File file = new File(extStorageDirectory, "image.png");
//        if (file.exists()) {
//            file.delete();
//            file = new File(extStorageDirectory, "image.png");
//
//        }
//
//        try {
//            outStream = new FileOutputStream(file);
//            bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
//            outStream.flush();
//            outStream.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return file;
//    }

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


    private File saveBitmap(Bitmap bitmap, String path) {
        File file = null;
        if (bitmap != null) {
            file = new File(path);
            try {
                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(path); //here is set your file path where you want to save or also here you can set file object directly

                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream); // bitmap is your Bitmap instance, if you want to compress it you can compress reduce percentage
                    // PNG is a lossless format, the compression factor (100) is ignored
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }


    private void startDialog() {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(
                this);
        myAlertDialog.setTitle("Upload Pictures Option");
        myAlertDialog.setMessage("How do you want to set your picture?");

        myAlertDialog.setPositiveButton("Gallery",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {


                        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                                return;

                            }


                        } else {
                            Intent pictureActionIntent = null;

                            pictureActionIntent = new Intent(
                                    Intent.ACTION_PICK);
                            pictureActionIntent.setType("video/*, image/*");
                            startActivityForResult(
                                    pictureActionIntent,
                                    0);
                        }


                    }
                });

        myAlertDialog.setNegativeButton("Camera",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {


                        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(new String[]{Manifest.permission.CAMERA}, 10);

                                return;

                            }

                        } else {

                            Intent intent = new Intent(
                                    MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent,
                                    0);
                        }


                    }
                });
        myAlertDialog.show();
    }


    @Override
    public void onNext() {

    }

    @Override
    public void onPrev() {

    }

    @Override
    public void onComplete() {

    }
}
