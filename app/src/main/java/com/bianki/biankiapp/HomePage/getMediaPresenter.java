package com.bianki.biankiapp.HomePage;


import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bianki.biankiapp.ClassModel.addComment;
import com.bianki.biankiapp.ClassModel.addReplay;
import com.bianki.biankiapp.ClassModel.addStory;
import com.bianki.biankiapp.ClassModel.commentLike;
import com.bianki.biankiapp.ClassModel.getFollowingUsers;
import com.bianki.biankiapp.ClassModel.getMedia;
import com.bianki.biankiapp.ClassModel.getcomment;
import com.bianki.biankiapp.ClassModel.getcurrentprofile;
import com.bianki.biankiapp.ClassModel.like;
import com.bianki.biankiapp.ClassModel.postLikedUsers;
import com.bianki.biankiapp.ClassModel.replayLike;
import com.bianki.biankiapp.ClassModel.uploadMedia;
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


public class getMediaPresenter {

    private HomeView view;

    public getMediaPresenter(HomeView view) {
        this.view = view;
    }

        public void getMedia(int old, int newn, String token) {

            view.showLoading();

            Call<getMedia> Call = Utils.getApi().getMedia(old , newn ,  token);
            Call.enqueue(new Callback<getMedia>() {
                @Override
                public void onResponse(@NonNull Call<getMedia> call, @NonNull Response<getMedia> response) {
                    view.hideLoading();
                    Log.e("P", "ok");

                    if (response.code() == 400) {
                        if (!response.isSuccessful()) {
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response.errorBody().string());
                                String message = jsonObject.getString("message");
                                String message2 = jsonObject.getString("status");


                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    if (response.isSuccessful() && response.body() != null) {

                        view.getMedia(response.body().getFiles());


                        //   Log.e("RESPONSE" , response.body().toString());

                    } else {
                        Log.e("qq", "qq");

                    }
                }

                @Override
                public void onFailure(Call<getMedia> call, Throwable t) {

                    view.hideLoading();
                    view.onErrorLoading(t.getLocalizedMessage());
                    Log.e("Error", t.getLocalizedMessage());

                }


            });
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


                            view.comment(message);

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


    public void getcomment(String postid, String token) {

        view.showLoading();
        view.showLoadingforcommen();

        Call<getcomment> mealsCall = Utils.getApi().getComments(postid, token);
        mealsCall.enqueue(new Callback<getcomment>() {
            @Override
            public void onResponse(@NonNull Call<getcomment> call, @NonNull Response<getcomment> response) {
                view.hideLoading();
                view.hideLoadingforcomment();
                Log.e("P", "ok");

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("message");
                            String message2 = jsonObject.getString("status");


                            view.comment(message2);

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
                view.hideLoadingforcomment();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


    public void likecomment(String commentid, String token, ImageView view2 ,  TextView textView) {


        Call<commentLike> mealsCall = Utils.getApi().commentLike(commentid, token);
        mealsCall.enqueue(new Callback<commentLike>() {
            @Override
            public void onResponse(@NonNull Call<commentLike> call, @NonNull Response<commentLike> response) {
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
            public void onFailure(Call<commentLike> call, Throwable t) {

                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


    public void writereply(String commentid, String content, String token) {

        view.showLoading();

        Call<addReplay> mealsCall = Utils.getApi().addReplay(commentid, content, token);
        mealsCall.enqueue(new Callback<addReplay>() {
            @Override
            public void onResponse(@NonNull Call<addReplay> call, @NonNull Response<addReplay> response) {
                view.hideLoading();
                Log.e("P", "ok");

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("messages");
                            String message2 = jsonObject.getString("status");


                            view.reply(message);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                if (response.isSuccessful() && response.body() != null) {

                    view.reply(response.body().getMessages());
                    //   Log.e("RESPONSE" , response.body().toString());

                }

            }


            @Override
            public void onFailure(Call<addReplay> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }

    public void likereply(String replyid, String token, ImageView view2 ,  TextView textView) {


        Call<replayLike> mealsCall = Utils.getApi().replayLike(replyid, token);
        mealsCall.enqueue(new Callback<replayLike>() {
            @Override
            public void onResponse(@NonNull Call<replayLike> call, @NonNull Response<replayLike> response) {
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

                    view.service(response.body().getMessage(), view2 ,textView);
                    //   Log.e("RESPONSE" , response.body().toString());

                }

            }


            @Override
            public void onFailure(Call<replayLike> call, Throwable t) {

                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }

    public void postLikedUsers(String postid, String token) {

        view.showLoading();
        view.showLoadingforcommen();

        Call<postLikedUsers> mealsCall = Utils.getApi().postLikedUsers(postid, token);
        mealsCall.enqueue(new Callback<postLikedUsers>() {
            @Override
            public void onResponse(@NonNull Call<postLikedUsers> call, @NonNull Response<postLikedUsers> response) {
                view.hideLoading();
                view.hideLoadingforcomment();
                Log.e("P", "ok");

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("message");
                            String message2 = jsonObject.getString("status");


                            view.comment(message2);

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
                view.hideLoadingforcomment();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


    void addstory(Map<String, RequestBody> map, RequestBody token, RequestBody text, RequestBody latidude, RequestBody longtiude) {

        view.showLoading();

        Call<addStory> mealsCall = Utils.getApi().addStory(map, token, text, latidude, longtiude);
        mealsCall.enqueue(new Callback<addStory>() {
            @Override
            public void onResponse(@NonNull Call<addStory> call, @NonNull Response<addStory> response) {
                view.hideLoading();
                //  Log.e("a" , response.body().toString());

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("message");
                            String message2 = jsonObject.getString("success");


                            view.reply(message);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                if (response.isSuccessful() && response.body() != null) {

                    view.reply(response.body().getMessages());
                    //   Log.e("RESPONSE" , response.body().toString());

                }

            }

            @Override
            public void onFailure(Call<addStory> call, Throwable t) {

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
                //  Log.e("a" , response.body().toString());

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("messages");
                            String message2 = jsonObject.getString("success");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                if (response.isSuccessful() && response.body() != null) {

                    view.getcurrentprofile(response.body().getUser());
                    //   Log.e("RESPONSE" , response.body().toString());

                }

            }

            @Override
            public void onFailure(Call<getcurrentprofile> call, Throwable t) {

                view.onErrorLoading(t.getLocalizedMessage());

            }


        });


    }


    void getFollowingUsers(String token) {

        view.showLoading();

        Call<getFollowingUsers> mealsCall = Utils.getApi().getFollowingUsers(token);
        mealsCall.enqueue(new Callback<getFollowingUsers>() {
            @Override
            public void onResponse(@NonNull Call<getFollowingUsers> call, @NonNull Response<getFollowingUsers> response) {
                view.hideLoading();
                //  Log.e("a" , response.body().toString());



                if (response.isSuccessful() && response.body() != null) {

                    view.getFollowingUsers(response.body().getUsers());
                    //   Log.e("RESPONSE" , response.body().toString());

                }

            }

            @Override
            public void onFailure(Call<getFollowingUsers> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });


    }


    void uploadmedia(Map<String, RequestBody> map, RequestBody token, RequestBody posttype,  RequestBody text,   RequestBody color , RequestBody latidude, RequestBody longtiude) {


        Call<uploadMedia> mealsCall = Utils.getApi().uploadMedia(map, token  , posttype, text, color ,  latidude, longtiude);
        mealsCall.enqueue(new Callback<uploadMedia>() {
            @Override
            public void onResponse(@NonNull Call<uploadMedia> call, @NonNull Response<uploadMedia> response) {
                //  Log.e("a" , response.body().toString());

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("message");
                            String message2 = jsonObject.getString("success");


                            view.reply(message);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                if (response.isSuccessful() && response.body() != null) {

                    view.reply(response.body().getMessages());
                    //   Log.e("RESPONSE" , response.body().toString());

                }

            }

            @Override
            public void onFailure(Call<uploadMedia> call, Throwable t) {

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

