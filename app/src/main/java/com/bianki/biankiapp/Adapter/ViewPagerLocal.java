package com.bianki.biankiapp.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bianki.biankiapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ViewPagerLocal extends RecyclerView.Adapter<ViewPagerLocal.RecyclerViewHolder> {

    private List<Uri> categories;
    private Context context;
    private static ClickListener clickListener;
    int index ;

    public ViewPagerLocal(List<Uri> categories, Context context ) {
        this.categories = categories;
        this.context = context;




    }

    @NonNull
    @Override
    public ViewPagerLocal.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.viewpagerogphoto,
                viewGroup, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerLocal.RecyclerViewHolder viewHolder, int i) {

        index = i ;

        Uri path = categories.get(i) ;
        viewHolder.imageView.setImageURI(path);
        viewHolder.numoflist.setText(""+(++index)+"/" + categories.size());




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

          //  clickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        ViewPagerLocal.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
