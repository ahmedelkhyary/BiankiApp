package com.bianki.biankiapp.Adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.bianki.biankiapp.ClassModel.getFollowingUsers;
import com.bianki.biankiapp.ClassModel.getMedia;
import com.bianki.biankiapp.ClassModel.getcomment;
import com.bianki.biankiapp.ClassModel.getcurrentprofile;
import com.bianki.biankiapp.ClassModel.postLikedUsers;
import com.bianki.biankiapp.CurrentProfile.profilehome;
import com.bianki.biankiapp.Database.Helper;
import com.bianki.biankiapp.Fullimage.Fullimage;
import com.bianki.biankiapp.HomePage.HomeView;
import com.bianki.biankiapp.HomePage.getMediaPresenter;
import com.bianki.biankiapp.R;
import com.bianki.biankiapp.profile.profileUsers;
import com.bianki.biankiapp.videoplyer;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;


public class RecyclerViewHomeAdapterHome extends RecyclerView.Adapter<RecyclerViewHomeAdapterHome.RecyclerViewHolder> implements HomeView, ActivityCompat.OnRequestPermissionsResultCallback {

    private List<getMedia.File> categories;
    private final Context context;
    private static ClickListener clickListener;
    private String cursor;
    private Helper helper;
    boolean flag = true;
    EditText write;
    com.bianki.biankiapp.HomePage.getMediaPresenter getMediaPresenter;
    RecyclerView recyclerView;
    int index;
    ProgressBar progressBar;
    String like;
    boolean check;
    String numberoflike;
    String path;
    ImageView voice;

    Boolean isplaying = true;
    MediaRecorder mediaRecorder;
    String pathofvoice;
    TextView chronometer;
    TextView cancel;
    List<String> photos ;

    String username;


    private static final long START_TIME_IN_MILLIS = 50000;
    private CountDownTimer countDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;


    public RecyclerViewHomeAdapterHome(List<getMedia.File> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterHome.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.desginofhome,
                viewGroup, false);

        helper = new Helper(context);
        cursor = helper.getiddata();
        //   categories = new ArrayList<>();
        getMediaPresenter = new getMediaPresenter(this);
        getMediaPresenter.getcurrentprofile(cursor);


        return new RecyclerViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterHome.RecyclerViewHolder viewHolder, int i) {


        String photoofuser = categories.get(i).getPostUploader().getProfileImage();
        String nameofuser = categories.get(i).getPostUploader().getUserName();

        path = categories.get(i).getPath();

        String text = categories.get(i).getText();
        numberoflike = String.valueOf(categories.get(i).getLikesNumber());
        String numberofcomment = String.valueOf(categories.get(i).getCommentsNumber());
        String type = categories.get(i).getPostType();
        String color = categories.get(i).getTextColor();
        photos = categories.get(i).getFiles();

        viewHolder.texthome.setText(text);
        viewHolder.usernamehome.setText(nameofuser);
        viewHolder.numberofcomment.setText(numberofcomment);


        if (type.toLowerCase().equals("text")) {


            if (color.equals("black")) {
                viewHolder.typetext.setTextColor(context.getResources().getColor(R.color.black));
                Log.e("a", color);

            }


            viewHolder.typetext.setText(text);
            viewHolder.typetext.setVisibility(View.VISIBLE);
            viewHolder.imageView.setVisibility(View.GONE);
            viewHolder.imageViewhometext.setVisibility(View.VISIBLE);
            viewHolder.readmoretexthome.setVisibility(View.GONE);

            viewHolder.texthome.setVisibility(View.GONE);
            viewHolder.viedoplay.setVisibility(View.GONE);

            try {
                Picasso.get().load(path).into(viewHolder.imageViewhometext);

            } catch (Exception e) {
            }
            viewHolder.startviedo.setVisibility(View.GONE);


        } else if (type.toLowerCase().equals("image")) {


            if (text.length() > 200) {
                viewHolder.readmoretexthome.setVisibility(View.VISIBLE);
                viewHolder.texthome.setMaxLines(3);


            } else {
                viewHolder.readmoretexthome.setVisibility(View.GONE);
            }
            viewHolder.texthome.setText(text);
            viewHolder.texthome.setVisibility(View.VISIBLE);
            viewHolder.imageView.setVisibility(View.VISIBLE);
            viewHolder.imageViewhometext.setVisibility(View.GONE);


            viewHolder.typetext.setVisibility(View.GONE);
            viewHolder.viedoplay.setVisibility(View.GONE);

            try {
         //       Picasso.get().load(photos.get(0)).into(viewHolder.imageView);


                ViewPager homeAdapter = new ViewPager(photos, context ,
                        categories.get(i).getId() ,categories.get(i).getText() ,
                        categories.get(i).getPath() , categories.get(i).getPostType() ,
                        categories.get(i).getLikesNumber().toString() ,
                        String.valueOf(categories.get(i).getCommentsNumber()),
                                categories.get(i).getIsLiked().toString());


                GridLayoutManager layoutManager = new GridLayoutManager(context, 1,
                        GridLayoutManager.HORIZONTAL, false);

                SnapHelper snapHelper = new PagerSnapHelper();
                snapHelper.attachToRecyclerView(viewHolder.imageView);


                viewHolder.imageView.setLayoutManager(layoutManager);
                viewHolder.imageView.setAdapter(homeAdapter);
                viewHolder.imageView.setNestedScrollingEnabled(true);
                homeAdapter.notifyDataSetChanged();








            } catch (Exception e) {
            }
            viewHolder.startviedo.setVisibility(View.GONE);

            viewHolder.imageView.getHeight();

            //Log.e("hight" , String.valueOf(viewHolder.imageView.getHeight()));


        } else if (type.toLowerCase().equals("video")) {


            if (text.length() > 200) {
                viewHolder.readmoretexthome.setVisibility(View.VISIBLE);
                viewHolder.texthome.setMaxLines(3);


            } else {
                viewHolder.readmoretexthome.setVisibility(View.GONE);
            }

            viewHolder.texthome.setText(text);
            viewHolder.texthome.setVisibility(View.VISIBLE);


            viewHolder.typetext.setVisibility(View.GONE);
            viewHolder.imageView.setVisibility(View.GONE);
            viewHolder.imageViewhometext.setVisibility(View.GONE);
            viewHolder.startviedo.setVisibility(View.VISIBLE);
            viewHolder.viedoplay.setVisibility(View.VISIBLE);


        }


        viewHolder.startviedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(view.getContext(), videoplyer.class);
                intent.putExtra("video", categories.get(i).getPath());

                view.getContext().startActivity(intent);


            }
        });

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (categories.get(i).getPostType().equals("image")) {
                    Intent intent = new Intent(view.getContext(), Fullimage.class);
                    intent.putExtra("photo", categories.get(i).getPath());
                    intent.putExtra("id", categories.get(i).getId());
                    intent.putExtra("text", categories.get(i).getText());
                    intent.putExtra("path", categories.get(i).getPath());
                    intent.putExtra("type", categories.get(i).getPostType());
                    intent.putExtra("like", categories.get(i).getLikesNumber().toString());
                    intent.putExtra("comment", categories.get(i).getCommentsNumber().toString());
                    intent.putExtra("islike", categories.get(i).getIsLiked().toString());
                    view.getContext().startActivity(intent);


                }
            }
        });

        viewHolder.readmoretexthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (viewHolder.readmoretexthome.getText().equals("المزيد من القراءه")) {
                    viewHolder.texthome.setMaxLines(Integer.MAX_VALUE);
                    viewHolder.readmoretexthome.setText("اخفاء القراءة");
                } else {
                    viewHolder.texthome.setMaxLines(3);
                    viewHolder.readmoretexthome.setText("المزيد من القراءه");
                }


            }
        });


        //  if (!photoofuser.equals(" "))

        try {
            Picasso.get().load(photoofuser).placeholder(R.drawable.avatar).into(viewHolder.circularImageView);

        } catch (Exception e) {
        }

        check = categories.get(i).getIsLiked();


        index = i;
        if (check) {

            viewHolder.makelike.setImageResource(R.drawable.readheart);

        } else {
            viewHolder.makelike.setImageResource(R.drawable.heart);

        }


        if (Locale.getDefault().getLanguage().equals("ar")) {


            StringBuilder like = covert(numberoflike);
            StringBuilder comment = covert(numberofcomment);

            viewHolder.numberoflike.setText(like);
            viewHolder.numberofcomment.setText(comment);


        } else {

            viewHolder.numberoflike.setText(numberoflike);
            viewHolder.numberofcomment.setText(numberofcomment);


        }

        like = numberoflike;


        viewHolder.makelike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                getMediaPresenter.like(categories.get(i).getId(), cursor, viewHolder.makelike, viewHolder.numberoflike);

            }
        });


        viewHolder.commentlinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                commentfragment commentfragment = new commentfragment();
//                FragmentManager manager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = manager.beginTransaction();
//                fragmentTransaction.replace(R.id.comment , commentfragment , "");
//                fragmentTransaction.commit();


                // commentfragment.beginTransaction().replace(R.id.comment,new commentfragment()).commit();

                Dialog bottomSheetDialog = new Dialog(context, android.R.style.Theme_DeviceDefault_DialogWhenLarge_NoActionBar);
                View view1 = LayoutInflater.from(context).inflate(R.layout.comments, null);

                bottomSheetDialog.setContentView(view1);
                bottomSheetDialog.show();

                write = view1.findViewById(R.id.writecomment);
                recyclerView = view1.findViewById(R.id.recyclerView);
                progressBar = view1.findViewById(R.id.progressBar);
                voice = view1.findViewById(R.id.voice);
                chronometer = view1.findViewById(R.id.Chronometer);
                cancel = view1.findViewById(R.id.cancel);

                cancel.setVisibility(View.GONE);
                chronometer.setVisibility(View.GONE);


                getMediaPresenter.getcomment(categories.get(i).getId(), cursor);

                index = i;


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


                        if (ContextCompat.checkSelfPermission(context,
                                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.RECORD_AUDIO}, 100);

                                return;

                            }


                        } else {
                            if (isplaying) {
                                //chronometer.start();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss", Locale.CANADA);
                                Date now = new Date();

                                pathofvoice = context.getExternalFilesDir("/").getAbsolutePath() + "/" + simpleDateFormat.format(now) + ".mp3";

                                mediaRecorder = new MediaRecorder();
                                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
                                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                                mediaRecorder.setOutputFile(pathofvoice);

                                try {
                                    mediaRecorder.prepare();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                voice.setImageResource(R.drawable.microphonered);
                                chronometer.setVisibility(View.VISIBLE);
                                cancel.setVisibility(View.VISIBLE);
                                // chronometer.setBase(SystemClock.elapsedRealtime());
                                startTimer();
                                mediaRecorder.start();


                                isplaying = false;
                            } else {

                                index = i;
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
                        getMediaPresenter.addcomment(categories.get(i).getId(), content, cursor);

                        index = i;


                    }
                });


            }
        });


        viewHolder.linearoflike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Dialog bottomSheetDialog = new Dialog(context, android.R.style.Theme_DeviceDefault_DialogWhenLarge_NoActionBar);
                View view1 = LayoutInflater.from(context).inflate(R.layout.likes, null);

                bottomSheetDialog.setContentView(view1);
                bottomSheetDialog.show();

                write = view1.findViewById(R.id.writecomment);
                recyclerView = view1.findViewById(R.id.recyclerView);
                progressBar = view1.findViewById(R.id.progressBar);

                getMediaPresenter.postLikedUsers(categories.get(i).getId(), cursor);


            }
        });

        viewHolder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.BottomSheetDialog);
                View view1 = LayoutInflater.from(context).inflate(R.layout.share, null);

                bottomSheetDialog.setContentView(view1);
                bottomSheetDialog.show();

            }
        });

        viewHolder.linearLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (categories.get(i).getPostUploader().getUserName().equals(username)) {
                    Intent intent = new Intent(view.getContext(), profilehome.class);
                    view.getContext().startActivity(intent);
                } else {
                    Intent intent = new Intent(view.getContext(), profileUsers.class);
                    intent.putExtra("id", categories.get(i).getPostUploader().getId());
                    view.getContext().startActivity(intent);
                }


            }
        });


    }


    @Override
    public int getItemCount() {
        return categories.size();
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
    public void getMedia(List<getMedia.File> getMedia) {

    }

    @Override
    public void service(String result, ImageView imageView, TextView textView) {

        if (result.equals("likes added successfully")) {
            imageView.setImageResource(R.drawable.readheart);
            Log.e("Y", String.valueOf(check));


            int x = Integer.parseInt(textView.getText().toString());
            x++;
            String y = String.valueOf(x);
            textView.setText(y);


        } else if (result.equals("likes removed successfully")) {
            imageView.setImageResource(R.drawable.heart);

            Log.e("c", String.valueOf(check));


            int x = Integer.parseInt(textView.getText().toString());
            x--;
            String y = String.valueOf(x);
            textView.setText(y);


        }

    }

    @Override
    public void comment(String result) {
        if (result.equals("comment added successfully")) {
            getMediaPresenter.getcomment(categories.get(index).getId(), cursor);
            write.setText("");


        } else if (result.equals("record comment added successfully")) {
            getMediaPresenter.getcomment(categories.get(index).getId(), cursor);
        }

    }

    @Override
    public void reply(String result) {

    }

    @Override
    public void getComment(List<getcomment.comments> getcomment) {

        RecyclerViewHomeAdapterComment homeAdapter = new RecyclerViewHomeAdapterComment(getcomment, context);
        // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context , LinearLayoutManager.VERTICAL,false);

        GridLayoutManager layoutManager = new GridLayoutManager(context, 1,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // recyclerView.setLayoutManager(new LinearLayoutManager(context , LinearLayoutManager.VERTICAL , false));
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();


    }

    @Override
    public void postLikedUsers(List<postLikedUsers.LikedUser> postLikedUsers) {


        RecyclerViewHomeAdapterLikes homeAdapter = new RecyclerViewHomeAdapterLikes(postLikedUsers, context);
        // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context , LinearLayoutManager.VERTICAL,false);

        GridLayoutManager layoutManager = new GridLayoutManager(context, 1,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // recyclerView.setLayoutManager(new LinearLayoutManager(context , LinearLayoutManager.VERTICAL , false));
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();


    }

    @Override
    public void getcurrentprofile(getcurrentprofile.User users) {

        username = users.getUserName();

    }

    @Override
    public void hideLoadingforcomment() {

    }

    @Override
    public void showLoadingforcommen() {

    }

    @Override
    public void getFollowingUsers(List<getFollowingUsers.Story.User> getFollowingUsers) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] strings, @NonNull int[] grantResults) {


        switch (requestCode) {
            case 100: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(context, "Permission denied ", Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }


    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//        @BindView(R.id.FrameLayout)
//        android.widget.FrameLayout FrameLayout;


        @BindView(R.id.share)
        LinearLayout share;


        @BindView(R.id.linearLayout5)
        LinearLayout linearLayout5;


        @BindView(R.id.readmoretexthome)
        TextView readmoretexthome;


        @BindView(R.id.imageViewhometext)
        ImageView imageViewhometext;

        @BindView(R.id.imageViewhome)
        RecyclerView imageView;

        @BindView(R.id.profilehome)
        CircularImageView circularImageView;

        @BindView(R.id.viedoplay)
        ImageView viedoplay;

        @BindView(R.id.startviedo)
        CircularImageView startviedo;

        @BindView(R.id.usernamehome)
        TextView usernamehome;

        @BindView(R.id.numberoflike)
        TextView numberoflike;

        @BindView(R.id.numberofcomment)
        TextView numberofcomment;

        @BindView(R.id.texthome)
        TextView texthome;

        @BindView(R.id.typetext)
        TextView typetext;


        @BindView(R.id.makelike)
        ImageView makelike;

        @BindView(R.id.comment)
        ImageView comment;

        @BindView(R.id.commentlinear)
        LinearLayout commentlinear;

        @BindView(R.id.linearoflike)
        LinearLayout linearoflike;

        @BindView(R.id.alllike)
        LinearLayout alllike;

        private final Context context;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            context = itemView.getContext();

        }

        @Override
        public void onClick(View v) {

           // clickListener.onClick(v, getAdapterPosition());

        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        // RecyclerViewHomeAdapterHome.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }

    public StringBuilder covert(String s) {
        StringBuilder builder = new StringBuilder();
        char[] arabicChars = {'٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩'};

        for (int j = 0; j < s.length(); j++) {

            if (Character.isDigit(s.charAt(j))) {
                builder.append(arabicChars[(int) (s.charAt(j)) - 48]);
            } else {
                builder.append(s.charAt(j));
            }
        }
        return builder;
    }


    public void appenddata(List<getMedia.File> locals) {

        //   categories = locals;

        categories.addAll(locals);
        // if (!locals.isEmpty())
        //  notifyItemRangeInserted(categories.size()-1 , categories.size()+locals.size() );
        notifyDataSetChanged();

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
                        categories.get(index).getId());

                RequestBody name = RequestBody.create(MediaType.parse("text/plain"),
                        cursor);


                getMediaPresenter.uploadRecordComment(map, name, idofcomment);
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


