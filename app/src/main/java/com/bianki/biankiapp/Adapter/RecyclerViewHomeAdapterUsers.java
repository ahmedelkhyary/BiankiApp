package com.bianki.biankiapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bianki.biankiapp.ClassModel.userSearch;
import com.bianki.biankiapp.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterUsers extends RecyclerView.Adapter<RecyclerViewHomeAdapterUsers.RecyclerViewHolder> {

    private List<userSearch.User> categories;
    private Context context;
    private static ClickListener clickListener;

    public RecyclerViewHomeAdapterUsers(List<userSearch.User> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterUsers.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.usersdesgin,
                viewGroup, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterUsers.RecyclerViewHolder viewHolder, int i) {


        String fullanme = categories.get(i).getFullName();

        String username = categories.get(i).getUserName();
        String photo = categories.get(i).getProfileImage();

        try {
            Picasso.get().load(photo).placeholder(R.drawable.avatar).into(viewHolder.photo);

        } catch (Exception ignored) {
        }


        viewHolder.fullname.setText(fullanme);
        viewHolder.username.setText(username);


    }


    @Override
    public int getItemCount() {
        return categories.size();
    }


    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.fullname)
        TextView fullname;

        @BindView(R.id.username)
        TextView username;


        @BindView(R.id.photo)
        CircularImageView photo;


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
        RecyclerViewHomeAdapterUsers.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }


    public void appenddata(List<userSearch.User> locals) {


        categories.addAll(locals);
        notifyDataSetChanged();

    }
}
