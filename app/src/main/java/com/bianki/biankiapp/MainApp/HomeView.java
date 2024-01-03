package com.bianki.biankiapp.MainApp;


public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void service(String result, Boolean message);
}
