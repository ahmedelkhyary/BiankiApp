package com.bianki.biankiapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bianki.biankiapp.ClassModel.getMedia;
import com.bianki.biankiapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterprofiletwoimage extends RecyclerView.Adapter<RecyclerViewHomeAdapterprofiletwoimage.RecyclerViewHolder>  {

    private List<getMedia.File > categories;
    private static RecyclerViewHomeAdapterprofiletwoimage.ClickListener clickListener;

    private Context context;
    public RecyclerViewHomeAdapterprofiletwoimage(List<getMedia.File > categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterprofiletwoimage.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.profilepostimagetwo,
                viewGroup, false);


        return new RecyclerViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterprofiletwoimage.RecyclerViewHolder viewHolder, int i) {


        String path = categories.get(i).getPath();
        String type = categories.get(i).getPostType();
        String text = categories.get(i).getText();





        if (type.toLowerCase().equals("text")) {
            viewHolder.typetext.setVisibility(View.VISIBLE);
            viewHolder.typetext.setText(text);
            viewHolder.imageView.setVisibility(View.VISIBLE);

            viewHolder.viedoplay.setVisibility(View.GONE);

            try {
                Picasso.get().load(path).into(viewHolder.imageView);

            } catch (Exception e) {
            }
            viewHolder.startviedo.setVisibility(View.GONE);


        } else if (type.toLowerCase().equals("image")) {
            viewHolder.imageView.setVisibility(View.VISIBLE);


            viewHolder.typetext.setVisibility(View.GONE);
            viewHolder.viedoplay.setVisibility(View.GONE);

            try {
                Picasso.get().load(path).into(viewHolder.imageView);

            } catch (Exception e) {
            }
            viewHolder.startviedo.setVisibility(View.GONE);

        } else if (type.toLowerCase().equals("video")) {
            viewHolder.viedoplay.setVisibility(View.VISIBLE);


            viewHolder.typetext.setVisibility(View.GONE);
            viewHolder.imageView.setVisibility(View.GONE);
            viewHolder.startviedo.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        @BindView(R.id.typetext)
        TextView typetext;


        @BindView(R.id.viedoplay)
        ImageView viedoplay;

        @BindView(R.id.startviedo)
        ImageView startviedo;

        @BindView(R.id.imageViewhome)
        ImageView imageView;









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
        RecyclerViewHomeAdapterprofiletwoimage.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }


}
