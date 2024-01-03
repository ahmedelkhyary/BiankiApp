package com.bianki.biankiapp.Search;


import androidx.annotation.NonNull;

import com.bianki.biankiapp.ClassModel.userSearch;
import com.bianki.biankiapp.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchPresenter {

    private HomeView view;

    public SearchPresenter(HomeView view) {
        this.view = view;
    }

    void userSearch ( String input , String limt , String page , String token) {

        view.showLoading();

        Call<userSearch> mealsCall = Utils.getApi().userSearch(input , limt , page , token);
        mealsCall.enqueue(new Callback<userSearch>() {
            @Override
            public void onResponse(@NonNull Call<userSearch> call, @NonNull Response<userSearch> response) {
                view.hideLoading();
                //  Log.e("a" , response.body().toString());

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.errorBody().string());
                        String message = jsonObject.getString("message");
                        String message2 = jsonObject.getString("success");

                        view.onErrorLoading(message2);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


                if (response.isSuccessful() && response.body() != null) {

                    view.Result(response.body().getUsers());
                    //   Log.e("RESPONSE" , response.body().toString());

                }

            }

            @Override
            public void onFailure(Call<userSearch> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }



}

