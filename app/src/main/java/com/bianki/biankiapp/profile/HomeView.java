package com.bianki.biankiapp.profile;


import com.bianki.biankiapp.ClassModel.getMediaById;
import com.bianki.biankiapp.ClassModel.getUserById;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void Resultusers(getUserById.User users);
    void posts(List<getMediaById.File> post);
    void follow (String s) ;
    void ResultofAsk (String s) ;

}
