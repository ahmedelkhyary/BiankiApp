package com.bianki.biankiapp.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bianki.biankiapp.ClassModel.getFollowingUsers;
import com.bianki.biankiapp.ClassModel.getMedia;
import com.bianki.biankiapp.ClassModel.getReplys;
import com.bianki.biankiapp.ClassModel.getcomment;
import com.bianki.biankiapp.ClassModel.getcurrentprofile;
import com.bianki.biankiapp.ClassModel.postLikedUsers;
import com.bianki.biankiapp.Database.Helper;
import com.bianki.biankiapp.HomePage.HomeView;
import com.bianki.biankiapp.HomePage.getMediaPresenter;
import com.bianki.biankiapp.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterComment extends RecyclerView.Adapter<RecyclerViewHomeAdapterComment.RecyclerViewHolder> implements HomeView {

    private List<getcomment.comments> categories;
    private Context context;
    private static ClickListener clickListener;
    private String cursor;
    private Helper helper;
    getMediaPresenter getMediaPresenter;
    boolean flag = true;
    EditText write;
    RecyclerView recyclerView;
    ArrayList<getReplys> arrayList;
    RecyclerViewHomeAdapterRply homeAdapter;
    int index;
    MediaPlayer mediaPlayer;
    int change;
    SeekBar seekbar;
    Runnable runnable;

    Handler seekHandler = new Handler();

    Runnable run;
     boolean flagplay = true ;


    public RecyclerViewHomeAdapterComment(List<getcomment.comments> categories, Context context) {
        Log.e("Comment", categories.toString());
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterComment.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.desofcomment,
                viewGroup, false);

        helper = new Helper(context);
        cursor = helper.getiddata();
        arrayList = new ArrayList<>();

        getMediaPresenter = new getMediaPresenter(this);
        return new RecyclerViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterComment.RecyclerViewHolder viewHolder, int i) {


        String name = categories.get(i).getContent();

        String nameofownercomment = categories.get(i).getCreator().getUserName();
        String imageofownercomment = categories.get(i).getCreator().getProfileImage();


        viewHolder.comment.setText(name);
        viewHolder.nameofownercomment.setText(nameofownercomment);

        String likenum = String.valueOf(categories.get(i).getLikeNum());
        String commentunm = String.valueOf(categories.get(i).getReplayesNum());

        seekbar = viewHolder.seekbar;
        if (name.isEmpty()) {
            viewHolder.comment.setVisibility(View.GONE);



        } else {

            viewHolder.comment.setVisibility(View.VISIBLE);

            viewHolder.seekbar.setVisibility(View.GONE);
            viewHolder.playvoice.setVisibility(View.GONE);


        }



        viewHolder.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                change = i;
                viewHolder.seekbar.setProgress(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                mediaPlayer.seekTo(change);

            }
        });


        viewHolder.playvoice.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View view) {



                if (flagplay) {


                    mediaPlayer = new MediaPlayer();



                    try {

                        mediaPlayer.setDataSource(categories.get(i).getRecordPath());
                        mediaPlayer.prepare();
                        viewHolder.seekbar.setMax(mediaPlayer.getDuration());
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    if (!mediaPlayer.isPlaying()) {
                        viewHolder.playvoice.setImageResource(R.drawable.pauserecord);
                        mediaPlayer.start();
                        flagplay = false ;
                        run = new Runnable() {
                            @Override
                            public void run() {
                                viewHolder.seekbar.setProgress(mediaPlayer.getCurrentPosition());
                                seekHandler.postDelayed(run, 100);


                            }


                        };
                        run.run();
                    } else {

                        mediaPlayer.pause();
                        viewHolder.playvoice.setImageResource(R.drawable.playrecord);

                    }
                } else {

                    mediaPlayer.pause();
                    viewHolder.playvoice.setImageResource(R.drawable.playrecord);
                    flagplay = true ;
                }
            }
        });


        boolean check = categories.get(i).getIsLiked();


        if (check) {

            viewHolder.likecomment.setImageResource(R.drawable.readheart);


        } else {
            viewHolder.likecomment.setImageResource(R.drawable.heart);

        }

        viewHolder.numoflike.setText(likenum);
        viewHolder.numofcomment.setText(commentunm);


        try {
            Picasso.get().load(imageofownercomment).placeholder(R.drawable.avatar).into(viewHolder.circularImageView);

        } catch (Exception e) {
        }


        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                getMediaPresenter.likecomment(categories.get(i).getId(), cursor, viewHolder.likecomment, viewHolder.numoflike);
            }
        });

        viewHolder.linearreply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog bottomSheetDialog = new Dialog(context, android.R.style.Theme_DeviceDefault_DialogWhenLarge_NoActionBar);
                View view1 = LayoutInflater.from(context).inflate(R.layout.replys, null);

                bottomSheetDialog.setContentView(view1);
                bottomSheetDialog.show();

                write = view1.findViewById(R.id.writecomment);
                recyclerView = view1.findViewById(R.id.recyclerView);


                arrayList.clear();


                for (int j = 0; j < categories.get(i).getRealReplayes().size(); j++) {


                    arrayList.add(new getReplys(categories.get(i).getRealReplayes().get(j).getId(),
                            categories.get(i).getRealReplayes().get(j).getContent(),
                            categories.get(i).getRealReplayes().get(j).getLikeNum(),
                            categories.get(i).getRealReplayes().get(j).getIsLiked(),
                            categories.get(i).getRealReplayes().get(j).getCreator().getUserName(),
                            categories.get(i).getRealReplayes().get(j).getCreator().getProfileImage()
                    ));




                    homeAdapter = new RecyclerViewHomeAdapterRply(arrayList, context);
                    /// RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context , LinearLayoutManager.VERTICAL,false);


//                    GridLayoutManager layoutManager = new GridLayoutManager(context, 1,
//                            GridLayoutManager.VERTICAL, false);
//                    recyclerView.setLayoutManager(layoutManager);
//
                    recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(homeAdapter);
                    recyclerView.setNestedScrollingEnabled(true);
                    homeAdapter.notifyDataSetChanged();


                }


                view1.findViewById(R.id.addcomment).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String content = write.getText().toString();

                        getMediaPresenter.writereply(categories.get(i).getId(), content, cursor);


                    }
                });

            }
        });


    }




    @Override
    public int getItemCount() {
        return categories.size();
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
    public void getMedia(List<getMedia.File> getMedia) {

    }

    @Override
    public void service(String result, ImageView v, TextView textView) {
        if (result.equals("likes added to comment successfully")) {
            v.setImageResource(R.drawable.readheart);


            int x = Integer.parseInt(textView.getText().toString());
            x++;
            String y = String.valueOf(x);
            textView.setText(y);


        } else if (result.equals("likes removed  from comment successfully")) {
            v.setImageResource(R.drawable.heart);


            int x = Integer.parseInt(textView.getText().toString());
            x--;
            String y = String.valueOf(x);
            textView.setText(y);

        }

    }

    @Override
    public void comment(String result) {


    }

    @Override
    public void reply(String result) {

        if (result.equals("replay added successfully ")) {
            write.setText("");


        } else if (result.equals("content can not be empty")) {
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
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


    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



        @BindView(R.id.playvoice)
        ImageView playvoice;

        @BindView(R.id.seekbar)
        SeekBar seekbar;

        @BindView(R.id.comment)
        TextView comment;

        @BindView(R.id.imageView)
        CircularImageView circularImageView;


        @BindView(R.id.nameofownercomment)
        TextView nameofownercomment;


        @BindView(R.id.likecommentlinear)
        LinearLayout linearLayout;

        @BindView(R.id.likecomment)
        ImageView likecomment;

        @BindView(R.id.numoflike)
        TextView numoflike;

        @BindView(R.id.numofcomment)
        TextView numofcomment;

        @BindView(R.id.linearreply)
        LinearLayout linearreply;


        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {


//            clickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewHomeAdapterComment.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }


    class mythread extends Thread {

        RecyclerViewHolder recyclerViewHolder;

        public void run() {

            while (mediaPlayer != null) {
                try {
                    Thread.sleep(1000);

                } catch (Exception e) {

                    recyclerViewHolder.seekbar.post(new Runnable() {
                        @Override
                        public void run() {
                            recyclerViewHolder.seekbar.setProgress(mediaPlayer.getCurrentPosition());
                        }
                    });

                }
            }
        }
    }


}