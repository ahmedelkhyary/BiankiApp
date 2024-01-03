package com.bianki.biankiapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bianki.biankiapp.R;
import com.bianki.biankiapp.Stringpath;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.RecyclerViewHolder> {

    List<Stringpath> categories;
    private Context context;
    private static ClickListener clickListener;

    public ViewPagerAdapter ( List<Stringpath> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewPagerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.desofviewpager,
                viewGroup, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerAdapter.RecyclerViewHolder viewHolder, int i) {




        viewHolder.imageView.setVisibility(View.GONE);
        viewHolder.VideoView.setVisibility(View.GONE);
        int x = i;

        viewHolder.textView.setText(categories.get(i).getText());
        viewHolder.nameofuser.setText(categories.get(i).getName());
        viewHolder.num.setText(String.valueOf(++x+ "  of  " + categories.size()));

        try{
            Picasso.get().load(categories.get(i).getPhoto()).placeholder(R.drawable.avatar).into(viewHolder.circularImageView);

        }catch (Exception e){}




        if (categories.get(i).getText().equals("image")) {
            viewHolder.VideoView.setVisibility(View.GONE);

            viewHolder.imageView.setVisibility(View.VISIBLE);
            Picasso.get().load(categories.get(i).getPath()).into(viewHolder.imageView);
        }
        else if (categories.get(i).getText().equals("video"))

        {
            viewHolder.imageView.setVisibility(View.GONE);

            viewHolder.VideoView.setVisibility(View.VISIBLE);
            viewHolder.VideoView.setVideoPath(categories.get(i).getPath());
            viewHolder.VideoView.start();

//                MediaController mediaController = new MediaController(view.getContext());
//                VideoView.setMediaController(mediaController);
//                mediaController.setAnchorView(VideoView);
        }



    }


    @Override
    public int getItemCount() {
        return categories.size();
    }



    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imageofviewpager)
        ImageView imageView;

        @BindView(R.id.text)
        TextView textView;


        @BindView(R.id.VideoView)
        VideoView VideoView;



        @BindView(R.id.CircularImageView)
        CircularImageView circularImageView;

        @BindView(R.id.nameofuser)
        TextView nameofuser;


        @BindView(R.id.num)
        TextView num;






        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            clickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        ViewPagerAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
