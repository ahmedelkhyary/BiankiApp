package com.bianki.biankiapp.Fullimage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bianki.biankiapp.Adapter.RecyclerViewHomeAdapterComment;
import com.bianki.biankiapp.Adapter.RecyclerViewHomeAdapterLikes;
import com.bianki.biankiapp.ClassModel.getMedia;
import com.bianki.biankiapp.ClassModel.getcomment;
import com.bianki.biankiapp.ClassModel.getcurrentprofile;
import com.bianki.biankiapp.ClassModel.postLikedUsers;
import com.bianki.biankiapp.Database.Helper;
import com.bianki.biankiapp.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Fullimage extends AppCompatActivity implements HomeView {

    ImageView image ;
    TextView text ;
    TextView centertext ;
    TextView numoflike ;
    TextView numofcomment ;
    ImageView makelike ;
    FullimagePresenter fullimagePresenter;
    String id ;
    Helper helper ;
    String cursor ;
    LinearLayout linearoflike ;
    RecyclerView recyclerView ;
    ProgressBar progressBar ;
    LinearLayout commentlinear ;
    EditText write ;

    ImageView voice ;

    Boolean isplaying = true ;
    MediaRecorder mediaRecorder ;
    String pathofvoice ;
    TextView chronometer ;
    TextView cancel ;

    ImageView imagetext ;

    LinearLayout continer ;
    View v ;
    boolean flag = true;

    private static final long START_TIME_IN_MILLIS = 50000;
    private CountDownTimer countDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullimage);

        image = findViewById(R.id.image);
        text = findViewById(R.id.text);
        centertext = findViewById(R.id.textcenter);
        numoflike = findViewById(R.id.numberoflike);
        numofcomment = findViewById(R.id.numberofcomment);
        makelike = findViewById(R.id.makelike);
        fullimagePresenter = new FullimagePresenter(this);
        helper = new Helper(this);
        cursor = helper.getiddata();
        linearoflike = findViewById(R.id.linearoflike);
        commentlinear = findViewById(R.id.commentlinear);
        imagetext = findViewById(R.id.imagetext);

        continer = findViewById(R.id.continer);
        v = findViewById(R.id.view);

        Intent intent = getIntent();
        String path = intent.getStringExtra("path");
        String textofimage = intent.getStringExtra("text");
        String type = intent.getStringExtra("type");

         id = intent.getStringExtra("id");
        String like = String.valueOf(intent.getStringExtra("like"));
        String comment = String.valueOf(intent.getStringExtra("comment"));
        String islike = intent.getStringExtra("islike");


        numoflike.setText(like);
        numofcomment.setText(comment);

        if (islike.equals("true")) {

            makelike.setImageResource(R.drawable.readheart);

        } else {
            makelike.setImageResource(R.drawable.whiteheart);

        }


        if(type.toLowerCase().equals("image"))
        {
            centertext.setVisibility(View.GONE);

            if (textofimage.isEmpty())
            {
                text.setVisibility(View.GONE);

            }else
            {
                text.setVisibility(View.VISIBLE);
                text.setMovementMethod(new ScrollingMovementMethod());


            }
            text.setText(textofimage);
            imagetext.setVisibility(View.GONE);
            image.setVisibility(View.VISIBLE);


            try {
                Picasso.get().load(path).into(image);

            } catch (Exception ignored) {
            }
        }
        else if (type.toLowerCase().equals("text"))
        {
            imagetext.setVisibility(View.VISIBLE);
            image.setVisibility(View.GONE);

            centertext.setVisibility(View.VISIBLE);
            text.setVisibility(View.GONE);
            centertext.setText(textofimage);

            try {
                Picasso.get().load(path).into(imagetext);

            } catch (Exception ignored) {
            }


        }

        final Animation in = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.alphain);


        final Animation out = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.alphaout);


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag)
                {

                    continer.setVisibility(View.VISIBLE);
                    v.setVisibility(View.VISIBLE);
                    text.setVisibility(View.VISIBLE);

                    continer.startAnimation(out);
                    v.startAnimation(out);
                    text.startAnimation(out);

                    flag = false ;





                }else
                {
                    continer.setVisibility(View.GONE);
                    v.setVisibility(View.GONE);
                    text.setVisibility(View.GONE);

                    continer.startAnimation(in);
                    v.startAnimation(in);
                    text.startAnimation(in);
                    flag = true ;
                }
            }
        });


        makelike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fullimagePresenter.like(id, cursor, makelike, numoflike);

            }
        });

        linearoflike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog bottomSheetDialog = new Dialog(Fullimage.this, android.R.style.Theme_DeviceDefault_DialogWhenLarge_NoActionBar);
                View view1 = LayoutInflater.from(Fullimage.this).inflate(R.layout.likes, null);


                recyclerView = view1.findViewById(R.id.recyclerView);
                progressBar = view1.findViewById(R.id.progressBar);
                fullimagePresenter.postLikedUsers(id, cursor);

                bottomSheetDialog.setContentView(view1);
                bottomSheetDialog.show();
            }
        });


        commentlinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog bottomSheetDialog = new Dialog(Fullimage.this, android.R.style.Theme_DeviceDefault_DialogWhenLarge_NoActionBar);
                View view1 = LayoutInflater.from(Fullimage.this).inflate(R.layout.comments, null);


                write = view1.findViewById(R.id.writecomment);
                recyclerView = view1.findViewById(R.id.recyclerView);
                progressBar = view1.findViewById(R.id.progressBar);


                voice = view1.findViewById(R.id.voice);
                chronometer = view1.findViewById(R.id.Chronometer);
                cancel = view1.findViewById(R.id.cancel);

                cancel.setVisibility(View.GONE);
                chronometer.setVisibility(View.GONE);


                fullimagePresenter.getcomment(id, cursor);

                bottomSheetDialog.setContentView(view1);
                bottomSheetDialog.show();



                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        voice.setImageResource(R.drawable.microphoneback);

                        chronometer.setVisibility(View.GONE);
                        cancel.setVisibility(View.GONE);
                        isplaying = true;
                        //  chronometer.stop();
                        mediaRecorder.stop();

                        mediaRecorder = null;

                        countDownTimer.cancel();

                    }
                });

                voice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                        if (ContextCompat.checkSelfPermission(Fullimage.this,
                                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                ActivityCompat.requestPermissions((Activity)Fullimage.this, new String[]{Manifest.permission.RECORD_AUDIO}, 100);

                                return;

                            }


                        } else
                        {
                            if (isplaying) {
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss" , Locale.CANADA);
                                Date now = new Date();

                                pathofvoice  = Fullimage.this.getExternalFilesDir("/").getAbsolutePath()+"/"+simpleDateFormat.format(now)+".mp3";

                                mediaRecorder = new MediaRecorder();
                                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
                                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                                mediaRecorder.setOutputFile( pathofvoice );
                                Log.e("path" , pathofvoice );

                                try {
                                    mediaRecorder.prepare();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                voice.setImageResource(R.drawable.microphonered);
                                chronometer.setVisibility(View.VISIBLE);
                                cancel.setVisibility(View.VISIBLE);
                                mediaRecorder.start();
                                startTimer();

                                isplaying = false;
                            }else
                            {
//
                                countDownTimer.onFinish();
                            }



                        }

                    }
                });


                view1.findViewById(R.id.addcomment).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String content = write.getText().toString();
                        fullimagePresenter.addcomment(id, content, cursor);

//                        index = i;


                    }
                });


            }
        });



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

    }

    @Override
    public void getcurrentprofile(getcurrentprofile.User users) {

    }

    @Override
    public void service(String result, ImageView v, TextView textView) {

        if (result.equals("likes added successfully")) {
            v.setImageResource(R.drawable.readheart);


            int x = Integer.parseInt(textView.getText().toString());
            x++;
            String y = String.valueOf(x);
            textView.setText(y);


        } else if (result.equals("likes removed successfully")) {
            v.setImageResource(R.drawable.whiteheart);



            int x = Integer.parseInt(textView.getText().toString());
            x--;
            String y = String.valueOf(x);
            textView.setText(y);


        }

    }

    @Override
    public void result(String message) {

    }

    @Override
    public void postLikedUsers(List<postLikedUsers.LikedUser> postLikedUsers) {

        RecyclerViewHomeAdapterLikes homeAdapter = new RecyclerViewHomeAdapterLikes(postLikedUsers, Fullimage.this);
        // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context , LinearLayoutManager.VERTICAL,false);

        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // recyclerView.setLayoutManager(new LinearLayoutManager(context , LinearLayoutManager.VERTICAL , false));
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

    }

    @Override
    public void getComment(List<getcomment.comments> getcomment) {
        RecyclerViewHomeAdapterComment homeAdapter = new RecyclerViewHomeAdapterComment(getcomment, Fullimage.this);
        // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context , LinearLayoutManager.VERTICAL,false);

        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // recyclerView.setLayoutManager(new LinearLayoutManager(context , LinearLayoutManager.VERTICAL , false));
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void comment(String result) {

        if (result.equals("comment added successfully")) {
            fullimagePresenter.getcomment(id, cursor);
            write.setText("");

        } else if (result.equals("record comment added successfully")) {
        fullimagePresenter.getcomment(id, cursor);
    }

    }

    @Override
    public void getPosts(List<getMedia.File> getPosts) {

    }


    private void startTimer() {
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDowntText();

            }

            @Override
            public void onFinish() {


                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;
                // Log.e("File" , file.toString());
                isplaying = true;

                voice.setImageResource(R.drawable.microphoneback);

                chronometer.setVisibility(View.GONE);
                cancel.setVisibility(View.GONE);


                Map<String, RequestBody> map = new HashMap<>();
                File file = new File(pathofvoice);


                // Parsing any Media type file
                RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
                map.put("file\"; filename=\"" + file.getName() + "\"", requestBody);

                RequestBody idofcomment = RequestBody.create(MediaType.parse("text/plain"),
                        id);

                RequestBody name = RequestBody.create(MediaType.parse("text/plain"),
                        cursor);


                fullimagePresenter.uploadRecordComment(map, name, idofcomment);
                chronometer.setText("");

                countDownTimer.cancel();

            }
        }.start();
        mTimerRunning = true;
    }

    private void updateCountDowntText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        chronometer.setText(timeLeft);
    }
}
