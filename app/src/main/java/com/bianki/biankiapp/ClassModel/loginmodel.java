package com.bianki.biankiapp.ClassModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class loginmodel {


    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public class User {

        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("fullName")
        @Expose
        private String fullName;
        @SerializedName("email")
        @Expose
        private String email;

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

    }
}
