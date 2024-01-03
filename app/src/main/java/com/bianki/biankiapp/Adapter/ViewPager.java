package com.bianki.biankiapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bianki.biankiapp.Fullimage.Fullimage;
import com.bianki.biankiapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ViewPager extends RecyclerView.Adapter<ViewPager.RecyclerViewHolder> {

    private List<String> categories;
    private Context context;
    private static ClickListener clickListener;
    int index ;

    String id ;
    String text ;
    String path ;
    String type ;
    String like ;
    String comment ;
    String islike ;
    public ViewPager(List<String> categories, Context context , String id ,
                     String text , String path , String type , String like , String comment , String islike) {
        this.categories = categories;
        this.context = context;
        this.id = id;
        this.text = text;
        this.path = path;
        this.type = type;
        this.like = like;
        this.comment = comment;
        this.islike = islike;



    }

    @NonNull
    @Override
    public ViewPager.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.viewpagerogphoto,
                viewGroup, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPager.RecyclerViewHolder viewHolder, int i) {

        String path = categories.get(i) ;
        index = i ;

            if (categories.size() == 1)
            {

                viewHolder.numoflist.setVisibility(View.GONE);

            }else
            {
                viewHolder.numoflist.setText(""+(++index)+"/" + categories.size());

            }

             Picasso.get().load(path).resize(800,0).into(viewHolder.imageView);


        int finalI = i;
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Fullimage.class);
                    intent.putExtra("photo", categories.get(finalI));
                    intent.putExtra("id", id);
                    intent.putExtra("text", text);
                    intent.putExtra("path", categories.get(finalI));
                    intent.putExtra("type", type);
                    intent.putExtra("like", like);
                    intent.putExtra("comment", comment);
                    intent.putExtra("islike", islike);
                    view.getContext().startActivity(intent);



            }
        });



    }


    @Override
    public int getItemCount() {
        return categories.size();
    }



    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imageViewhome)
        ImageView imageView;

        @BindView(R.id.numoflist)
        TextView numoflist;



        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

      //      clickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        ViewPager.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
