package com.bianki.biankiapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterRply extends RecyclerView.Adapter<RecyclerViewHomeAdapterRply.RecyclerViewHolder> implements HomeView {
    private List<getReplys> categories;
    private Context context;
    private static ClickListener clickListener;
    private String cursor;
    private Helper helper;
    getMediaPresenter getMediaPresenter;
    boolean flag = true;

    public RecyclerViewHomeAdapterRply(List<getReplys> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterRply.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.desofreply,
                viewGroup, false);


        helper = new Helper(context);
        cursor = helper.getiddata();

        getMediaPresenter = new getMediaPresenter(this);
        return new RecyclerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterRply.RecyclerViewHolder viewHolder, int i) {


        String content = categories.get(i).getContent();
        String profileImage = categories.get(i).getProfileImage();
        String name = categories.get(i).getFullName();
        Log.e("name", name);

        String getLikeNum = String.valueOf(categories.get(i).getLikeNum());

        boolean check = categories.get(i).getLiked();

        if (check) {
            viewHolder.likecomment.setImageResource(R.drawable.readheart);
        }else
        {
            viewHolder.likecomment.setImageResource(R.drawable.heart);

        }


        viewHolder.comment.setText(content);
        Log.e("m",content);
        try{
            Picasso.get().load(profileImage).placeholder(R.drawable.avatar).into(viewHolder.circularImageView);

        }catch (Exception e){}
        viewHolder.nameofownercomment.setText(name);
        viewHolder.numoflike.setText(getLikeNum);


        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                getMediaPresenter.likereply(categories.get(i).getId(), cursor, viewHolder.likecomment , viewHolder.numoflike);

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
    public void service(String result, ImageView v , TextView textView) {

        if (result.equals("likes added to replay successfully")) {
            v.setImageResource(R.drawable.readheart);


            int x = Integer.parseInt(textView.getText().toString());
            x++ ;
            String y = String.valueOf(x);
            textView.setText(y);

        } else if (result.equals("likes removed  from replay successfully")) {
            v.setImageResource(R.drawable.heart);

            int x = Integer.parseInt(textView.getText().toString());
            x-- ;
            String y = String.valueOf(x);
            textView.setText(y);

        }

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
        RecyclerViewHomeAdapterRply.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }

//    public void x (){
//
//        categories.removeAll(categories);
//    }
//
//
//    public void x (List<getReplys> list){
//
//     //   categories = list ;
//       categories.addAll(list);
//       notifyDataSetChanged();
//       Log.e("2",list.toString());
//    }
}
