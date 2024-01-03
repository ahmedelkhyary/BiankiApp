package com.bianki.biankiapp.Fullimage;


import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bianki.biankiapp.ClassModel.addComment;
import com.bianki.biankiapp.ClassModel.getcomment;
import com.bianki.biankiapp.ClassModel.like;
import com.bianki.biankiapp.ClassModel.postLikedUsers;
import com.bianki.biankiapp.ClassModel.uploadRecordComment;
import com.bianki.biankiapp.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullimagePresenter {

    private HomeView view;

    public FullimagePresenter(HomeView view) {
        this.view = view;
    }


    public void like(String postid, String token, ImageView view2 , TextView textView) {


        Call<like> mealsCall = Utils.getApi().like(postid, token);
        mealsCall.enqueue(new Callback<like>() {
            @Override
            public void onResponse(@NonNull Call<like> call, @NonNull Response<like> response) {
                Log.e("P", "ok");

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("message");
                            String message2 = jsonObject.getString("status");


                            view.service(message, view2 , textView);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                if (response.isSuccessful() && response.body() != null) {

                    view.service(response.body().getMessages(), view2 , textView);
                    //   Log.e("RESPONSE" , response.body().toString());

                }

            }


            @Override
            public void onFailure(Call<like> call, Throwable t) {

                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }

    public void postLikedUsers(String postid, String token) {

        view.showLoading();

        Call<postLikedUsers> mealsCall = Utils.getApi().postLikedUsers(postid, token);
        mealsCall.enqueue(new Callback<postLikedUsers>() {
            @Override
            public void onResponse(@NonNull Call<postLikedUsers> call, @NonNull Response<postLikedUsers> response) {
                view.hideLoading();
                Log.e("P", "ok");

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("message");
                            String message2 = jsonObject.getString("status");


                            view.result(message2);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                if (response.isSuccessful() && response.body() != null) {

                    view.postLikedUsers(response.body().getLikedUsers());

                }

            }


            @Override
            public void onFailure(Call<postLikedUsers> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


    public void getcomment(String postid, String token) {

        view.showLoading();

        Call<getcomment> mealsCall = Utils.getApi().getComments(postid, token);
        mealsCall.enqueue(new Callback<getcomment>() {
            @Override
            public void onResponse(@NonNull Call<getcomment> call, @NonNull Response<getcomment> response) {
                view.hideLoading();
                Log.e("P", "ok");

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("message");
                            String message2 = jsonObject.getString("status");


                            view.onErrorLoading(message2);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                if (response.isSuccessful() && response.body() != null) {

                    view.getComment(response.body().getFiles());

                }

            }


            @Override
            public void onFailure(Call<getcomment> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }



    public void addcomment(String postid, String content, String token) {

        view.showLoading();

        Call<addComment> mealsCall = Utils.getApi().addComment(postid, content, token);
        mealsCall.enqueue(new Callback<addComment>() {
            @Override
            public void onResponse(@NonNull Call<addComment> call, @NonNull Response<addComment> response) {
                view.hideLoading();
                Log.e("P", "ok");

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("message");
                            String message2 = jsonObject.getString("status");


                            view.onErrorLoading(message);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                if (response.isSuccessful() && response.body() != null) {

                    view.comment(response.body().getMessages());
                    //   Log.e("RESPONSE" , response.body().toString());

                }

            }


            @Override
            public void onFailure(Call<addComment> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }
    public void uploadRecordComment(Map<String, RequestBody> map, RequestBody token, RequestBody postid) {

        view.showLoading();

        Call<uploadRecordComment> mealsCall = Utils.getApi().uploadRecordComment(map, token, postid);
        mealsCall.enqueue(new Callback<uploadRecordComment>() {
            @Override
            public void onResponse(@NonNull Call<uploadRecordComment> call, @NonNull Response<uploadRecordComment> response) {
                view.hideLoading();
                //  Log.e("a" , response.body().toString());

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("message");
                            String message2 = jsonObject.getString("success");



                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                if (response.isSuccessful() && response.body() != null) {

                    //   Log.e("RESPONSE" , response.body().toString());
                    view.comment(response.body().getMessage());

                }

            }

            @Override
            public void onFailure(Call<uploadRecordComment> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }
}

