package com.bianki.biankiapp.CreateGroup;


public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);


    void result (String s) ;

}
