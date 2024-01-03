package com.bianki.biankiapp.Search;


import com.bianki.biankiapp.ClassModel.userSearch;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void Result(List<userSearch.User> searches);


}
