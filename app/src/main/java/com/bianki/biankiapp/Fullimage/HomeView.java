package com.bianki.biankiapp.Fullimage;


import android.widget.ImageView;
import android.widget.TextView;

import com.bianki.biankiapp.ClassModel.getMedia;
import com.bianki.biankiapp.ClassModel.getcomment;
import com.bianki.biankiapp.ClassModel.getcurrentprofile;
import com.bianki.biankiapp.ClassModel.postLikedUsers;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void getcurrentprofile(getcurrentprofile.User users);
    void service(String result , ImageView v , TextView textView);

    void result(String message);

    void postLikedUsers(List<postLikedUsers.LikedUser> postLikedUsers);
    void getComment(List<getcomment.comments> getcomment);
    void comment(String result );

    void getPosts(List<getMedia.File> getPosts);

}
