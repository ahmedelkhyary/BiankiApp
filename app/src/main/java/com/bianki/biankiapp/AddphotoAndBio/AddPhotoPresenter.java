package com.bianki.biankiapp.AddphotoAndBio;

import androidx.annotation.NonNull;

import com.bianki.biankiapp.ClassModel.profileImage;
import com.bianki.biankiapp.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddPhotoPresenter {

    private HomeView view;

    public AddPhotoPresenter(HomeView view) {
        this.view = view;
    }

    void addphoto (RequestBody name ,    Map<String, RequestBody> map) {

        view.showLoading();

        Call<profileImage> mealsCall = Utils.getApi().profileImage( name , map);
        mealsCall.enqueue(new Callback<profileImage>() {
            @Override
            public void onResponse(@NonNull Call<profileImage> call, @NonNull Response<profileImage> response) {
                view.hideLoading();
                //  Log.e("a" , response.body().toString());

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.errorBody().string());
                        String message = jsonObject.getString("message");
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
            public void onFailure(Call<profileImage> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }

//

}

