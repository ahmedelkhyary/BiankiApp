package com.bianki.biankiapp.CurrentProfile;


import androidx.annotation.NonNull;

import com.bianki.biankiapp.ClassModel.getMedia;
import com.bianki.biankiapp.ClassModel.getcurrentprofile;
import com.bianki.biankiapp.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CurrentprofilePresenter {

    private HomeView view;

    public CurrentprofilePresenter(HomeView view) {
        this.view = view;
    }

    void getcurrentprofile ( String token) {

        view.showLoading();

        Call<getcurrentprofile> mealsCall = Utils.getApi().getcurrentprofile( token);
        mealsCall.enqueue(new Callback<getcurrentprofile>() {
            @Override
            public void onResponse(@NonNull Call<getcurrentprofile> call, @NonNull Response<getcurrentprofile> response) {
                view.hideLoading();
                //  Log.e("a" , response.body().toString());

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.errorBody().string());
                        String message = jsonObject.getString("messages");
                        String message2 = jsonObject.getString("success");



                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


                if (response.isSuccessful() && response.body() != null) {

                    view.getcurrentprofile(response.body().getUser());
                    //   Log.e("RESPONSE" , response.body().toString());

                }

            }

            @Override
            public void onFailure(Call<getcurrentprofile> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


    void getcurrentprofilepost ( String token) {

        view.showLoading();

        Call<getMedia> mealsCall = Utils.getApi().getuserpost( token);
        mealsCall.enqueue(new Callback<getMedia>() {
            @Override
            public void onResponse(@NonNull Call<getMedia> call, @NonNull Response<getMedia> response) {
                view.hideLoading();
                //  Log.e("a" , response.body().toString());

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("messages");
                            String message2 = jsonObject.getString("success");



                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                if (response.isSuccessful() && response.body() != null) {

                    view.getPosts(response.body().getFiles());
                    //   Log.e("RESPONSE" , response.body().toString());

                }

            }

            @Override
            public void onFailure(Call<getMedia> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }




}

