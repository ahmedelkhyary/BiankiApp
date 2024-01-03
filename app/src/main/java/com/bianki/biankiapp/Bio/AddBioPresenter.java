package com.bianki.biankiapp.Bio;

import androidx.annotation.NonNull;

import com.bianki.biankiapp.ClassModel.addBio;
import com.bianki.biankiapp.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddBioPresenter {

    private HomeView view;

    public AddBioPresenter(HomeView view) {
        this.view = view;
    }

    void addBio ( String bio, String token) {

        view.showLoading();

        Call<addBio> mealsCall = Utils.getApi().addBio( bio , token);
        mealsCall.enqueue(new Callback<addBio>() {
            @Override
            public void onResponse(@NonNull Call<addBio> call, @NonNull Response<addBio> response) {
                view.hideLoading();
                //  Log.e("a" , response.body().toString());

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.errorBody().string());
                        String message = jsonObject.getString("messages");
                        String message2 = jsonObject.getString("success");


                        view.service(message , Boolean.valueOf(message2));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


                if (response.isSuccessful() && response.body() != null) {

                    view.service(response.body().getMessages() , Boolean.valueOf(response.body().getSuccess()));
                    //   Log.e("RESPONSE" , response.body().toString());

                }

            }

            @Override
            public void onFailure(Call<addBio> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


}

