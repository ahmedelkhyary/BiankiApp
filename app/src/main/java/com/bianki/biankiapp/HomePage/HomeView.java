package com.bianki.biankiapp.HomePage;


import android.widget.ImageView;
import android.widget.TextView;

import com.bianki.biankiapp.ClassModel.getFollowingUsers;
import com.bianki.biankiapp.ClassModel.getMedia;
import com.bianki.biankiapp.ClassModel.getcomment;
import com.bianki.biankiapp.ClassModel.getcurrentprofile;
import com.bianki.biankiapp.ClassModel.postLikedUsers;

import java.util.List;


public interface HomeView {
    void showLoading();

    void hideLoading();

    void onErrorLoading(String message);

    void getMedia(List<getMedia.File> getMedia);
    void service(String result , ImageView v ,  TextView textView);
    void comment(String result );
    void reply(String result );

    void getComment(List<getcomment.comments> getcomment);

    void postLikedUsers(List<postLikedUsers.LikedUser> postLikedUsers);

    void getcurrentprofile(getcurrentprofile.User users );

    void hideLoadingforcomment();

    void showLoadingforcommen( );

    void getFollowingUsers (List<getFollowingUsers.Story.User> getFollowingUsers );


}
