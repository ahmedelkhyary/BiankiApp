package com.bianki.biankiapp.MyGroup;


import androidx.annotation.NonNull;

import com.bianki.biankiapp.ClassModel.myGroups;
import com.bianki.biankiapp.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MygroupPresenter {

    private HomeView view;

    public MygroupPresenter(HomeView view) {
        this.view = view;
    }

    void myGroups ( String token) {

        view.showLoading();

        Call<myGroups> mealsCall = Utils.getApi().myGroups(token);
        mealsCall.enqueue(new Callback<myGroups>() {
            @Override
            public void onResponse(@NonNull Call<myGroups> call, @NonNull Response<myGroups> response) {
                view.hideLoading();
                //  Log.e("a" , response.body().toString());

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.errorBody().string());
                        String message = jsonObject.getString("message");
                        String message2 = jsonObject.getString("success");

                        view.onErrorLoading(message);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


                if (response.isSuccessful() && response.body() != null) {

                    view.Result(response.body().getGroups());
                    //   Log.e("RESPONSE" , response.body().toString());

                }

            }

            @Override
            public void onFailure(Call<myGroups> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }



}

