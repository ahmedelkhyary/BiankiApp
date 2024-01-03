package com.bianki.biankiapp.SpecificGroup;


import androidx.annotation.NonNull;

import com.bianki.biankiapp.ClassModel.addGroupPost;
import com.bianki.biankiapp.ClassModel.groupPosts;
import com.bianki.biankiapp.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddGroupPostPresenter {

    private HomeView view;

    public AddGroupPostPresenter(HomeView view) {
        this.view = view;
    }

    void AddpostGroup (RequestBody groupid, RequestBody posttype,  Map<String, RequestBody> map,
                       RequestBody textcolor , RequestBody text, RequestBody latidude, RequestBody longtiude, RequestBody token) {

        view.showLoading();

        Call<addGroupPost> mealsCall = Utils.getApi().addGroupPost(groupid , posttype , map , textcolor , text , latidude , longtiude , token);
        mealsCall.enqueue(new Callback<addGroupPost>() {
            @Override
            public void onResponse(@NonNull Call<addGroupPost> call, @NonNull Response<addGroupPost> response) {
                view.hideLoading();
                //  Log.e("a" , response.body().toString());

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("message");
                            String message2 = jsonObject.getString("success");


                            view.Result(message);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                if (response.isSuccessful() && response.body() != null) {

                    view.Result(response.body().getMessage());

                }

            }

            @Override
            public void onFailure(Call<addGroupPost> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }

    void groupPosts (String groupid , String token) {

        view.showLoading();

        Call<groupPosts> mealsCall = Utils.getApi().groupPosts(groupid , token);
        mealsCall.enqueue(new Callback<groupPosts>() {
            @Override
            public void onResponse(@NonNull Call<groupPosts> call, @NonNull Response<groupPosts> response) {
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

                    view.getMedia(response.body().getGroup1().getFiles());

                }

            }

            @Override
            public void onFailure(Call<groupPosts> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }

}

