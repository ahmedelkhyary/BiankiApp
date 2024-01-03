package com.bianki.biankiapp.ClassModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class userSearch {

        @SerializedName("users")
        @Expose
        private List<User> users = null;
        @SerializedName("success")
        @Expose
        private Boolean success;

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }


    public class User {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("fullName")
        @Expose
        private String fullName;
        @SerializedName("profileImage")
        @Expose
        private String profileImage;

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

        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }

    }
}
