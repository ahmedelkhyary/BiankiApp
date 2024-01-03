package com.bianki.biankiapp.ASK;

import android.util.Log;

import androidx.annotation.NonNull;

import com.bianki.biankiapp.ClassModel.deleteQuestion;
import com.bianki.biankiapp.ClassModel.getcurrentprofile;
import com.bianki.biankiapp.ClassModel.publicReplay;
import com.bianki.biankiapp.ClassModel.puplicAsk;
import com.bianki.biankiapp.ClassModel.puplicQuestion;
import com.bianki.biankiapp.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ASKPresenter {

    private HomeView view;

    public ASKPresenter(HomeView view) {
        this.view = view;
    }

    void puplicAsk ( String ques , String type , String token) {


        Call<puplicAsk> puplicAsk = Utils.getApi().puplicAsk( ques , type , token);
        puplicAsk.enqueue(new Callback<puplicAsk>() {
            @Override
            public void onResponse(@NonNull Call<puplicAsk> call, @NonNull Response<puplicAsk> response) {


                if (response.isSuccessful() && response.body() != null) {

                    view.Result(response.body().getSuccess());

                }

            }

            @Override
            public void onFailure(Call<puplicAsk> call, Throwable t) {

                view.onErrorLoading(t.getLocalizedMessage());
            }


        });
    }


    void puplicQuestion (String token) {

        view.showLoading();

        Call<puplicQuestion> Call = Utils.getApi().puplicQuestion(token);
        Call.enqueue(new Callback<puplicQuestion>() {
            @Override
            public void onResponse(@NonNull Call<puplicQuestion> call, @NonNull Response<puplicQuestion> response) {
                view.hideLoading();


                if (response.isSuccessful() && response.body() != null) {

                    view.puplicQuestion(response.body().getQuestions());

                }

            }

            @Override
            public void onFailure(Call<puplicQuestion> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
                Log.e("Message" , t.getLocalizedMessage());

            }


        });
    }


    void privateQuestion (String token) {

        view.showLoading();

        Call<puplicQuestion> Call = Utils.getApi().privateQuestion1(token);
        Call.enqueue(new Callback<puplicQuestion>() {
            @Override
            public void onResponse(@NonNull Call<puplicQuestion> call, @NonNull Response<puplicQuestion> response) {
                view.hideLoading();


                if (response.isSuccessful() && response.body() != null) {

                    view.puplicQuestion(response.body().getQuestions());

                }

            }

            @Override
            public void onFailure(Call<puplicQuestion> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


    public void publicReplay (String text , String id , String type , String token) {

        view.showLoading();

        Call<publicReplay> Call = Utils.getApi().publicReplay(text , id , type , token);
        Call.enqueue(new Callback<publicReplay>() {
            @Override
            public void onResponse(@NonNull Call<publicReplay> call, @NonNull Response<publicReplay> response) {
                view.hideLoading();

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("message");

                            view.Resultofreplay(message);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                if (response.isSuccessful() && response.body() != null) {

                    view.Resultofreplay(response.body().getMessage());

                }

            }

            @Override
            public void onFailure(Call<publicReplay> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


    public void getcurrentprofile(String token) {


        Call<getcurrentprofile> mealsCall = Utils.getApi().getcurrentprofile(token);
        mealsCall.enqueue(new Callback<getcurrentprofile>() {
            @Override
            public void onResponse(@NonNull Call<getcurrentprofile> call, @NonNull Response<getcurrentprofile> response) {

                if (response.isSuccessful() && response.body() != null) {

                    view.getcurrentprofile(response.body().getUser());

                }

            }

            @Override
            public void onFailure(Call<getcurrentprofile> call, Throwable t) {

                view.onErrorLoading(t.getLocalizedMessage());

            }


        });


    }


    public void privateReplay (String text , String id , String token) {

        view.showLoading();

        Call<publicReplay> Call = Utils.getApi().privateReplay(text , id  , token);
        Call.enqueue(new Callback<publicReplay>() {
            @Override
            public void onResponse(@NonNull Call<publicReplay> call, @NonNull Response<publicReplay> response) {
                view.hideLoading();

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("message");

                            view.Resultofreplay(message);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                if (response.isSuccessful() && response.body() != null) {

                    view.Resultofreplay(response.body().getMessage());

                }

            }

            @Override
            public void onFailure(Call<publicReplay> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


    public void deleteQuestion (String id , String token) {

        view.showLoading();

        Call<deleteQuestion> Call = Utils.getApi().deleteQuestion(id  , token);
        Call.enqueue(new Callback<deleteQuestion>() {
            @Override
            public void onResponse(@NonNull Call<deleteQuestion> call, @NonNull Response<deleteQuestion> response) {
                view.hideLoading();



                if (response.isSuccessful() && response.body() != null) {

                    view.Resultofreplay(response.body().getMessage());

                }

            }

            @Override
            public void onFailure(Call<deleteQuestion> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }



    public void deleteAskReplay (String idquestion , String idreplay , String token) {

        view.showLoading();

        Call<deleteQuestion> Call = Utils.getApi().deleteAskReplay(idquestion , idreplay  , token);
        Call.enqueue(new Callback<deleteQuestion>() {
            @Override
            public void onResponse(@NonNull Call<deleteQuestion> call, @NonNull Response<deleteQuestion> response) {
                view.hideLoading();



                if (response.isSuccessful() && response.body() != null) {

                    view.deleteQuestion(response.body().getMessage());

                }

            }

            @Override
            public void onFailure(Call<deleteQuestion> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }

    public void EditAskReplay (String text , String idreplay , String token) {

        view.showLoading();

        Call<deleteQuestion> Call = Utils.getApi().deleteAskReplay(text , idreplay  , token);
        Call.enqueue(new Callback<deleteQuestion>() {
            @Override
            public void onResponse(@NonNull Call<deleteQuestion> call, @NonNull Response<deleteQuestion> response) {
                view.hideLoading();



                if (response.isSuccessful() && response.body() != null) {

                    view.deleteQuestion(response.body().getMessage());

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

