package com.bianki.biankiapp.EditProfile;


import androidx.annotation.NonNull;

import com.bianki.biankiapp.ClassModel.editProfile;
import com.bianki.biankiapp.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditPresenter {

    private HomeView view;

    public EditPresenter(HomeView view) {
        this.view = view;
    }

    void editProfile ( Map<String, RequestBody> map , RequestBody token , RequestBody fullname ,
                       RequestBody username , RequestBody bio , RequestBody website) {

        view.showLoading();

        Call<editProfile> mealsCall = Utils.getApi().editProfile(map , token , fullname , username , bio , website);
        mealsCall.enqueue(new Callback<editProfile>() {
            @Override
            public void onResponse(@NonNull Call<editProfile> call, @NonNull Response<editProfile> response) {
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
                    //   Log.e("RESPONSE" , response.body().toString());

                }

            }

            @Override
            public void onFailure(Call<editProfile> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }



}

