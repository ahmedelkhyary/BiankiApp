package com.bianki.biankiapp.AddphotoAndBio;


public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void service(String result, Boolean message);
}
