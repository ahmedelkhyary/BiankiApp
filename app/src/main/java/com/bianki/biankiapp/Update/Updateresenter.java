package com.bianki.biankiapp.Update;

import androidx.annotation.NonNull;

import com.bianki.biankiapp.ClassModel.deleteQuestion;
import com.bianki.biankiapp.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Updateresenter {

    private HomeView view;

    public Updateresenter(HomeView view) {
        this.view = view;
    }


    public void EditAskReplay (String text , String idreplay , String token) {

        view.showLoading();

        Call<deleteQuestion> Call = Utils.getApi().editAskReplay(text , idreplay  , token);
        Call.enqueue(new Callback<deleteQuestion>() {
            @Override
            public void onResponse(@NonNull Call<deleteQuestion> call, @NonNull Response<deleteQuestion> response) {
                view.hideLoading();



                if (response.isSuccessful() && response.body() != null) {

                    view.Result(response.body().getMessage());

                }

            }

            @Override
            public void onFailure(Call<deleteQuestion> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }
}

