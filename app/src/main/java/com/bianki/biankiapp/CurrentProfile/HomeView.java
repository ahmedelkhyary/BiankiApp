package com.bianki.biankiapp.CurrentProfile;


import com.bianki.biankiapp.ClassModel.getMedia;
import com.bianki.biankiapp.ClassModel.getcurrentprofile;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void getcurrentprofile(getcurrentprofile.User users );

    void getPosts(List<getMedia.File> getPosts);

}
