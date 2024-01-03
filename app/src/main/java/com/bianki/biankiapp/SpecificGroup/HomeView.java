package com.bianki.biankiapp.SpecificGroup;


import com.bianki.biankiapp.ClassModel.groupPosts;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void Result(String s);
    void getMedia(List<groupPosts.File> getMedia);


}
