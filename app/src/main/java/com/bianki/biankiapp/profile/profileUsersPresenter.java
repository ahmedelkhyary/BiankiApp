package com.bianki.biankiapp.profile;


import androidx.annotation.NonNull;

import com.bianki.biankiapp.ClassModel.follow;
import com.bianki.biankiapp.ClassModel.getMediaById;
import com.bianki.biankiapp.ClassModel.getUserById;
import com.bianki.biankiapp.ClassModel.publicReplay;
import com.bianki.biankiapp.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class profileUsersPresenter {

    private HomeView view;

    public profileUsersPresenter(HomeView view) {
        this.view = view;
    }

    void userSearch ( String id  ,  String token) {

        view.showLoading();

        Call<getUserById> mealsCall = Utils.getApi().getUserById(id , token);
        mealsCall.enqueue(new Callback<getUserById>() {
            @Override
            public void onResponse(@NonNull Call<getUserById> call, @NonNull Response<getUserById> response) {
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

                    view.Resultusers(response.body().getUser());

                    //   Log.e("RESPONSE" , response.body().toString());

                }

            }

            @Override
            public void onFailure(Call<getUserById> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }



    void getMediaById ( String id  ) {

        view.showLoading();

        Call<getMediaById> mealsCall = Utils.getApi().getMediaById(id );
        mealsCall.enqueue(new Callback<getMediaById>() {
            @Override
            public void onResponse(@NonNull Call<getMediaById> call, @NonNull Response<getMediaById> response) {
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

                    view.posts(response.body().getFiles());

                    //   Log.e("RESPONSE" , response.body().toString());

                }

            }

            @Override
            public void onFailure(Call<getMediaById> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }

    void follow ( String id , String token   ) {


        Call<follow> mealsCall = Utils.getApi().follow(id  , token);
        mealsCall.enqueue(new Callback<follow>() {
            @Override
            public void onResponse(@NonNull Call<follow> call, @NonNull Response<follow> response) {
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

                    view.follow(response.body().getMessage());

                    //   Log.e("RESPONSE" , response.body().toString());

                }

            }

            @Override
            public void onFailure(Call<follow> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


    public void privateAsk (String text , String id , String type , String token) {

        view.showLoading();

        Call<publicReplay> Call = Utils.getApi().privateAsk(text , id , type  , token);
        Call.enqueue(new Callback<publicReplay>() {
            @Override
            public void onResponse(@NonNull Call<publicReplay> call, @NonNull Response<publicReplay> response) {
                view.hideLoading();



                if (response.isSuccessful() && response.body() != null) {

                    view.ResultofAsk(response.body().getMessage());

                }

            }

            @Override
            public void onFailure(Call<publicReplay> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }



}

