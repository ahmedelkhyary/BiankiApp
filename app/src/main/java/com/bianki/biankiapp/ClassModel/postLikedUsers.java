package com.bianki.biankiapp.ClassModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class postLikedUsers {

    @SerializedName("likedUsers")
    @Expose
    private List<LikedUser> likedUsers = null;
    @SerializedName("likesNum")
    @Expose
    private Integer likesNum;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public List<LikedUser> getLikedUsers() {
        return likedUsers;
    }

    public void setLikedUsers(List<LikedUser> likedUsers) {
        this.likedUsers = likedUsers;
    }

    public Integer getLikesNum() {
        return likesNum;
    }

    public void setLikesNum(Integer likesNum) {
        this.likesNum = likesNum;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public class LikedUser {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("fullName")
        @Expose
        private String fullName;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("profileImage")
        @Expose
        private String profileImage;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("bio")
        @Expose
        private String bio;
        @SerializedName("website")
        @Expose
        private String website;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

    }
}