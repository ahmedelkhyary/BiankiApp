package com.bianki.biankiapp.Singup;
import androidx.annotation.NonNull;

import com.bianki.biankiapp.ClassModel.googAndFaceAuth;
import com.bianki.biankiapp.ClassModel.localSignup;
import com.bianki.biankiapp.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SginupPresenter {

    private HomeView view;

    public SginupPresenter(HomeView view) {
        this.view = view;
    }

    void sginup (String fullname, String username, String email, String password) {

        view.showLoading();

        Call<localSignup> mealsCall = Utils.getApi().localSignup(fullname , username , email , password);
        mealsCall.enqueue(new Callback<localSignup>() {
            @Override
            public void onResponse(@NonNull Call<localSignup> call, @NonNull Response<localSignup> response) {
                view.hideLoading();
                //  Log.e("a" , response.body().toString());

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.errorBody().string());
                        String message = jsonObject.getString("Errors");
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

                    view.service(response.body().getToken() , response.body().getSuccess());
                    //   Log.e("RESPONSE" , response.body().toString());

                }

            }

            @Override
            public void onFailure(Call<localSignup> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }



    void googAndFaceAuth (String email, String fullname , String username , String photo) {

        view.showLoading();

        Call<googAndFaceAuth> mealsCall = Utils.getApi().googAndFaceAuth( email , fullname , username , photo);
        mealsCall.enqueue(new Callback<googAndFaceAuth>() {
            @Override
            public void onResponse(@NonNull Call<googAndFaceAuth> call, @NonNull Response<googAndFaceAuth> response) {
                view.hideLoading();
                //  Log.e("a" , response.body().toString());

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("ErrorMessages");
                            String message2 = jsonObject.getString("success");


                            view.servicefacebookandgoogle(message , Boolean.valueOf(message2));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                if (response.isSuccessful() && response.body() != null) {

                    view.servicefacebookandgoogle(response.body().getToken() , response.body().getSuccess());
                    //   Log.e("RESPONSE" , response.body().toString());

                }

            }

            @Override
            public void onFailure(Call<googAndFaceAuth> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


}

