package com.bianki.biankiapp.Api;


import com.bianki.biankiapp.ClassModel.addBio;
import com.bianki.biankiapp.ClassModel.addComment;
import com.bianki.biankiapp.ClassModel.addGroupPost;
import com.bianki.biankiapp.ClassModel.addReplay;
import com.bianki.biankiapp.ClassModel.addStory;
import com.bianki.biankiapp.ClassModel.commentLike;
import com.bianki.biankiapp.ClassModel.createGroup;
import com.bianki.biankiapp.ClassModel.deleteQuestion;
import com.bianki.biankiapp.ClassModel.editProfile;
import com.bianki.biankiapp.ClassModel.follow;
import com.bianki.biankiapp.ClassModel.getFollowingUsers;
import com.bianki.biankiapp.ClassModel.getMedia;
import com.bianki.biankiapp.ClassModel.getMediaById;
import com.bianki.biankiapp.ClassModel.getQuestions;
import com.bianki.biankiapp.ClassModel.getUserById;
import com.bianki.biankiapp.ClassModel.getcomment;
import com.bianki.biankiapp.ClassModel.getcurrentprofile;
import com.bianki.biankiapp.ClassModel.googAndFaceAuth;
import com.bianki.biankiapp.ClassModel.groupPosts;
import com.bianki.biankiapp.ClassModel.like;
import com.bianki.biankiapp.ClassModel.localSignup;
import com.bianki.biankiapp.ClassModel.loginmodel;
import com.bianki.biankiapp.ClassModel.myGroups;
import com.bianki.biankiapp.ClassModel.postLikedUsers;
import com.bianki.biankiapp.ClassModel.profileImage;
import com.bianki.biankiapp.ClassModel.publicReplay;
import com.bianki.biankiapp.ClassModel.puplicAsk;
import com.bianki.biankiapp.ClassModel.puplicQuestion;
import com.bianki.biankiapp.ClassModel.replayLike;
import com.bianki.biankiapp.ClassModel.uploadMedia;
import com.bianki.biankiapp.ClassModel.uploadRecordComment;
import com.bianki.biankiapp.ClassModel.userSearch;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface biankiApi {

    // Insert new student

    @POST("localSignup")
    @FormUrlEncoded
    Call<localSignup> localSignup
            (
                    @Field("fullName") String fullName,
                    @Field("userName") String userName,
                    @Field("email") String email,
                    @Field("password") String password

            );


    @POST("login")
    @FormUrlEncoded
    Call<loginmodel> loginmodel
            (
                    @Field("email") String email,
                    @Field("password") String password


            );


    @POST("googAndFaceAuth")
    @FormUrlEncoded
    Call<googAndFaceAuth> googAndFaceAuth
            (
                    @Field("email") String email,
                    @Field("fullName") String fullName,
                    @Field("userName") String userName,
                    @Field("profileImage") String profileImage

            );


    @POST("addBio")
    @FormUrlEncoded
    Call<addBio> addBio
            (
                    @Field("bio") String bio,
                    @Field("token") String token


            );

    @POST("profileImage")
    @Multipart
    Call<profileImage> profileImage
            (
                    @Part("token") RequestBody token,
                    @PartMap Map<String, RequestBody> map
            );


    @POST("uploadMedia")
    @Multipart
    Call<uploadMedia> uploadMedia
            (@PartMap Map<String, RequestBody> map,

             @Part("token") RequestBody token,
             @Part("postType") RequestBody postType,
             @Part("text") RequestBody text,
             @Part("textColor") RequestBody textColor,
             @Part("latidude") RequestBody latidude,
             @Part("longtiude") RequestBody longtiude

            );

// 43- Add multiple or single media (images)

//    @POST("multipleImage")
//    @Multipart
//    Call<uploadMedia> multipleImage
//            (@Part List<MultipartBody.Part> map,
//             @Part("text") RequestBody text,
//             @Part("postType") RequestBody postType,
//             @Part("latidude") RequestBody latidude,
//             @Part("longtiude") RequestBody longtiude,
//             @Part("token") RequestBody token
//
//            );


    @POST("multipleImage")
    @Multipart
    Call<uploadMedia> multipleImage
            (@Part List<MultipartBody.Part> map,
             @Part("text") RequestBody text,
             @Part("postType") RequestBody postType,
             @Part("latidude") RequestBody latidude,
             @Part("longtiude") RequestBody longtiude,
             @Part("token") RequestBody token
            );


    // https://binki.herokuapp.com/api/users/getMedia/token


//    @GET("posts/{token}")
//    Call<getMedia> getMedia(
//
//            @Path("token") String token
//    );


    @POST("like")
    @FormUrlEncoded
    Call<like> like
            (
                    @Field("postId") String postId,
                    @Field("token") String token


            );


    @POST("addComment")
    @FormUrlEncoded
    Call<addComment> addComment
            (
                    @Field("postId") String postId,
                    @Field("content") String content,
                    @Field("token") String token


            );


    @POST("getComments")
    @FormUrlEncoded
    Call<getcomment> getComments
            (
                    @Field("postId") String postId,
                    @Field("token") String token


            );


    @POST("commentLike")
    @FormUrlEncoded
    Call<commentLike> commentLike
            (
                    @Field("commentId") String commentId,
                    @Field("token") String token


            );


    @POST("addReplay")
    @FormUrlEncoded
    Call<addReplay> addReplay
            (
                    @Field("commentId") String postId,
                    @Field("content") String content,
                    @Field("token") String token


            );


    @POST("replayLike")
    @FormUrlEncoded
    Call<replayLike> replayLike
            (
                    @Field("replayId") String replayId,
                    @Field("token") String token


            );


    @POST("postLikedUsers")
    @FormUrlEncoded
    Call<postLikedUsers> postLikedUsers
            (
                    @Field("postId") String replayId,
                    @Field("token") String token


            );


    @POST("me")
    @FormUrlEncoded
    Call<getcurrentprofile> getcurrentprofile
            (
                    @Field("token") String token


            );


    @GET("getMedia/{token}")
    Call<getMedia> getuserpost(

            @Path("token") String token
    );


    @POST("editProfile")
    @Multipart
    Call<editProfile> editProfile
            (@PartMap Map<String, RequestBody> map,

             @Part("token") RequestBody token,

             @Part("fullName") RequestBody fullName,

             @Part("userName") RequestBody userName,
             @Part("bio") RequestBody bio,
             @Part("website") RequestBody website

            );

    @POST("postsByNum")
    @FormUrlEncoded
    Call<getMedia> getMedia(

            @Field("page") int page,
            @Field("limit") int limit,
            @Field("token") String token
    );


    @POST("addStory")
    @Multipart
    Call<addStory> addStory
            (@PartMap Map<String, RequestBody> map,

             @Part("token") RequestBody token,

             // @Part("postType") RequestBody  postType,

             @Part("text") RequestBody text,
             @Part("latidude") RequestBody latidude,
             @Part("longtiude") RequestBody longtiude

            );


    @GET("getFollowingUsers/{token}")
    Call<getFollowingUsers> getFollowingUsers(

            @Path("token") String token
    );


    @POST("uploadRecordComment")
    @Multipart
    Call<uploadRecordComment> uploadRecordComment

            (@PartMap Map<String, RequestBody> map,
             @Part("token") RequestBody token,
             @Part("postId") RequestBody postId
            );


    @GET("getQuestions/{token}")
    Call<getQuestions> getQuestions(

            @Path("token") String token
    );


    @POST("userSearch")
    @FormUrlEncoded
    Call<userSearch> userSearch
            (
                    @Field("input") String input,
                    @Field("limit") String limit,
                    @Field("page") String page,
                    @Field("token") String token


            );


    @GET("getUserById/{userId}/{token}")
    Call<getUserById> getUserById(


            @Path("userId") String userId,
            @Path("token") String token
    );


    @GET("getMediaById/{userId}")
    Call<getMediaById> getMediaById(


            @Path("userId") String userId

    );


    @POST("follow")
    @FormUrlEncoded
    Call<follow> follow
            (
                    @Field("userId") String userId,
                    @Field("token") String token


            );


    @POST("createGroup")
    @Multipart
    Call<createGroup> createGroup


            (
                    @Part("Name") RequestBody Name,
                    @Part("type") RequestBody type,
                    @Part("privacy") RequestBody privacy,
                    @PartMap Map<String, RequestBody> map,
                    @Part("token") RequestBody token

            );


    @GET("myGroups/{token}")
    Call<myGroups> myGroups(


            @Path("token") String token
    );


    @POST("addGroupPost")
    @Multipart
    Call<addGroupPost> addGroupPost

            (
                    @Part("groupId") RequestBody groupId,

                    @Part("postType") RequestBody postType,


                    @PartMap Map<String, RequestBody> map,

                    @Part("textColor") RequestBody textColor,


                    @Part("text") RequestBody text,


                    @Part("latidude") RequestBody latidude,
                    @Part("longtiude") RequestBody longtiude,

                    @Part("token") RequestBody token


            );


    @GET("groupPosts/{groupId}/{token}")
    Call<groupPosts> groupPosts(

            @Path("groupId") String groupId,
            @Path("token") String token
    );

    // 44- Add new Public Quesion

    @POST("puplicAsk")
    @FormUrlEncoded
    Call<puplicAsk> puplicAsk(

            @Field("question") String question,
            @Field("questionUserType") String questionUserType,
            @Field("token") String token
    );

    //46- Get user public Questions sorted by upload date

    @GET("puplicQuestion1/{token}")
    Call<puplicQuestion> puplicQuestion(

            @Path("token") String token
    );

    // 48- Get user private Questions (for tefa) sorted by upload date

    @GET("privateQuestion1/{token}")
    Call<puplicQuestion> privateQuestion1(

            @Path("token") String token
    );


   //  45- Add new Replay to public Question
   @POST("publicReplay")
   @FormUrlEncoded
   Call<publicReplay> publicReplay(

           @Field("replayText") String replayText,
           @Field("questionId") String questionId,
           @Field("replayUserType") String replayUserType,
           @Field("token") String token
   );

   // 47- add Private Question To another user

    @POST("privateAsk")
    @FormUrlEncoded
    Call<publicReplay> privateAsk(

            @Field("question") String question,
            @Field("userId") String userId,
            @Field("questionUserType") String questionUserType,
            @Field("token") String token
    );


   // 49- Add new Replay to Private Question

    @POST("privateReplay")
    @FormUrlEncoded
    Call<publicReplay> privateReplay(

            @Field("replayText") String replayText,
            @Field("questionId") String questionId,
            @Field("token") String token
    );





 // 50- delete AsK

    @POST("deleteQuestion")
    @FormUrlEncoded
    Call<deleteQuestion> deleteQuestion(

            @Field("questionId") String questionId,
            @Field("token") String token
    );



    // 50- delete Replay (public || private)
    @POST("deleteAskReplay")
    @FormUrlEncoded
    Call<deleteQuestion> deleteAskReplay (

            @Field("questionId") String questionId,
            @Field("replayId") String replayId,
            @Field("token") String token
    );

    // 50- Edit Replay (public || private)

    @POST("editAskReplay")
    @FormUrlEncoded
    Call<deleteQuestion> editAskReplay (

            @Field("updatedText") String updatedText,
            @Field("replayId") String replayId,
            @Field("token") String token
    );


}


