package com.bianki.biankiapp.MyGroup;


import com.bianki.biankiapp.ClassModel.myGroups;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void Result(List<myGroups.Group> myGroups);


}
