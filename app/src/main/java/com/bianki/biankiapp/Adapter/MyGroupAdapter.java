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

import com.bianki.biankiapp.ClassModel.myGroups;
import com.bianki.biankiapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyGroupAdapter extends RecyclerView.Adapter<MyGroupAdapter.RecyclerViewHolder> {

    private List<myGroups.Group > categories;
    private Context context;
    private static ClickListener clickListener;

       public MyGroupAdapter(List<myGroups.Group > categories, Context context) {
        this.categories = categories;
        this.context = context;
    }


    @NonNull
    @Override
    public MyGroupAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mygroupdes,
                viewGroup, false);

        return new RecyclerViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull MyGroupAdapter.RecyclerViewHolder viewHolder, int i) {

           String image = categories.get(i).getPhoto();
           String name = categories.get(i).getName();

        try{
            Picasso.get().load(image).into(viewHolder.imageView);

        }catch (Exception e){}

        viewHolder.text.setText(name);

    }


    @Override
    public int getItemCount() {
        return categories.size();
    }




    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        @BindView(R.id.image)
        ImageView imageView;


        @BindView(R.id.text)
        TextView text;




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
        MyGroupAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }




}
