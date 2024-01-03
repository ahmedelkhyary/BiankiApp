package com.bianki.biankiapp.Singup;


public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void service(String result , Boolean message);
    void servicefacebookandgoogle(String result , Boolean message);

}
