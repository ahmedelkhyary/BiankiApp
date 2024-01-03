package com.bianki.biankiapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bianki.biankiapp.ClassModel.getFollowingUsers;
import com.bianki.biankiapp.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterViewPager extends RecyclerView.Adapter<RecyclerViewHomeAdapterViewPager.RecyclerViewHolder> {

    private List<getFollowingUsers.Story.User> categories;
    private Context context;
    private static ClickListener clickListener;

    public RecyclerViewHomeAdapterViewPager(List<getFollowingUsers.Story.User> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterViewPager.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_view_pager_header,
                viewGroup, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterViewPager.RecyclerViewHolder viewHolder, int i) {


        String name = categories.get(i).getUserName();

        for (int j = 0; j < categories.get(i).getStories().size(); j++) {

            String photo = categories.get(i).getStories().get(j).getPath();


            if (categories.get(i).getStories().get(j).getText().equals("image")) {

                try {
                    Picasso.get().load(photo).placeholder(R.drawable.avatar).into(viewHolder.imagestory);

                } catch (Exception e) {
                }

            } else {
                viewHolder.imagestory.setImageResource(R.drawable.black);
            }

            if (name.length() > 10) {
                String substr = name.substring(0, 9);

                viewHolder.nameofstory.setText(substr);


            } else {
                viewHolder.nameofstory.setText(name);

            }


        }


    }


    @Override
    public int getItemCount() {
        return categories.size();
    }


    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.nameofstory)
        TextView nameofstory;

        @BindView(R.id.imagestory)
        CircularImageView imagestory;


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
        RecyclerViewHomeAdapterViewPager.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
