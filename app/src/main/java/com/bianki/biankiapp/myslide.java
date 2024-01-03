package com.bianki.biankiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;


public class myslide extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    List<Stringpath> list;

    public myslide(Context context, List<Stringpath> list) {
        this.context = context ;
        this.list = list ;
    }

//    int[] img = {R.drawable.gmail, R.drawable.faceboo, R.drawable.youtube};
//    String[] des = {"ddddddssssssssssssssssssssssssssssssssssssssssssssdddddddd", "sssssssssssaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaassssssss", "eeeeeeeeeeeeeeeee"};

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (ConstraintLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.desofviewpager, container, false);
        ImageView imageView = view.findViewById(R.id.imageofviewpager);
        TextView textView = view.findViewById(R.id.text);
        VideoView VideoView = view.findViewById(R.id.VideoView);
        CircularImageView circularImageView = view.findViewById(R.id.CircularImageView);
        TextView nameofuser = view.findViewById(R.id.nameofuser);
        TextView num = view.findViewById(R.id.num);

        imageView.setVisibility(View.GONE);
        VideoView.setVisibility(View.GONE);
        int x = position;

        textView.setText(list.get(position).getText());
        nameofuser.setText(list.get(position).getName());
        num.setText(String.valueOf(++x+ "  of  " + list.size()));

        try{
            Picasso.get().load(list.get(position).getPhoto()).placeholder(R.drawable.avatar).into(circularImageView);

        }catch (Exception e){}




            if (list.get(position).getText().equals("image")) {
                VideoView.setVisibility(View.GONE);

                imageView.setVisibility(View.VISIBLE);
                Picasso.get().load(list.get(position).getPath()).into(imageView);
            }
            else if (list.get(position).getText().equals("video"))

                {
                    imageView.setVisibility(View.GONE);

                    VideoView.setVisibility(View.VISIBLE);
                   VideoView.setVideoPath(list.get(position).getPath());
                    VideoView.start();

//                MediaController mediaController = new MediaController(view.getContext());
//                VideoView.setMediaController(mediaController);
//                mediaController.setAnchorView(VideoView);
            }


    //    imageView.setImageResource(img[position]);
     //   textView.setText(des[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
