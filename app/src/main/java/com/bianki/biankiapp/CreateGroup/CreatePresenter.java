package com.bianki.biankiapp.CreateGroup;


import androidx.annotation.NonNull;

import com.bianki.biankiapp.ClassModel.createGroup;
import com.bianki.biankiapp.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePresenter {

    private HomeView view;

    public CreatePresenter(HomeView view) {
        this.view = view;
    }

    void CreateGroup ( RequestBody name, RequestBody type, RequestBody perv , Map<String, RequestBody> map, RequestBody token) {

        view.showLoading();

        Call<createGroup> mealsCall = Utils.getApi().createGroup(name , type , perv , map , token);
        mealsCall.enqueue(new Callback<createGroup>() {
            @Override
            public void onResponse(@NonNull Call<createGroup> call, @NonNull Response<createGroup> response) {
                view.hideLoading();
                //  Log.e("a" , response.body().toString());

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("message");
                            String message2 = jsonObject.getString("success");


                            view.result(message);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                if (response.isSuccessful() && response.body() != null) {

                    view.result(response.body().getMessage());
                    //   Log.e("RESPONSE" , response.body().toString());

                }

            }

            @Override
            public void onFailure(Call<createGroup> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


}

