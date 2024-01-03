package com.bianki.biankiapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bianki.biankiapp.ClassModel.getFollowingUsers;
import com.bianki.biankiapp.ClassModel.getMedia;
import com.bianki.biankiapp.ClassModel.getcomment;
import com.bianki.biankiapp.ClassModel.getcurrentprofile;
import com.bianki.biankiapp.ClassModel.postLikedUsers;
import com.bianki.biankiapp.HomePage.HomeView;
import com.bianki.biankiapp.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterLikes extends RecyclerView.Adapter<RecyclerViewHomeAdapterLikes.RecyclerViewHolder> implements HomeView {
    private List<postLikedUsers.LikedUser> categories;
    private Context context;
    private static ClickListener clickListener;


    public RecyclerViewHomeAdapterLikes(List<postLikedUsers.LikedUser> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterLikes.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.desoflike,
                viewGroup, false);

        return new RecyclerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterLikes.RecyclerViewHolder viewHolder, int i) {


        String profileImage = categories.get(i).getProfileImage();
        String name = categories.get(i).getUserName();


//        if (!profileImage.equals(" "))

        try{
            Picasso.get().load(profileImage).placeholder(R.drawable.avatar).into(viewHolder.circularImageView);

        }catch (Exception e){}
        viewHolder.nameofuser.setText(name);


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
    public void service(String result, ImageView v , TextView textView) {


    }

    @Override
    public void comment(String result) {

    }

    @Override
    public void reply(String result) {


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


        @BindView(R.id.imageView)
        CircularImageView circularImageView;


        @BindView(R.id.nameofuser)
        TextView nameofuser;


        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            //clickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewHomeAdapterLikes.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
