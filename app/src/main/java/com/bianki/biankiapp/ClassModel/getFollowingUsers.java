package com.bianki.biankiapp.ClassModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getFollowingUsers {

    @SerializedName("users")
    @Expose
    private List<Story.User> users = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<Story.User> getUsers() {
        return users;
    }

    public void setUsers(List<Story.User> users) {
        this.users = users;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


    public class Story {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("path")
        @Expose
        private String path;
        @SerializedName("mediaType")
        @Expose
        private String mediaType;
        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("uploadDate")
        @Expose
        private String uploadDate;
        @SerializedName("sortDate")
        @Expose
        private String sortDate;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("viewsNum")
        @Expose
        private Integer viewsNum;
        @SerializedName("isViewed")
        @Expose
        private Boolean isViewed;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getMediaType() {
            return mediaType;
        }

        public void setMediaType(String mediaType) {
            this.mediaType = mediaType;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getUploadDate() {
            return uploadDate;
        }

        public void setUploadDate(String uploadDate) {
            this.uploadDate = uploadDate;
        }

        public String getSortDate() {
            return sortDate;
        }

        public void setSortDate(String sortDate) {
            this.sortDate = sortDate;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }

        public Integer getViewsNum() {
            return viewsNum;
        }

        public void setViewsNum(Integer viewsNum) {
            this.viewsNum = viewsNum;
        }

        public Boolean getIsViewed() {
            return isViewed;
        }

        public void setIsViewed(Boolean isViewed) {
            this.isViewed = isViewed;
        }


        public class User {

            @SerializedName("stories")
            @Expose
            private List<Story> stories = null;
            @SerializedName("posts")
            @Expose
            private List<Object> posts = null;
            @SerializedName("_id")
            @Expose
            private String id;
            @SerializedName("profileImage")
            @Expose
            private String profileImage;
            @SerializedName("userName")
            @Expose
            private String userName;
            @SerializedName("fullName")
            @Expose
            private String fullName;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("__v")
            @Expose
            private Integer v;

            public List<Story> getStories() {
                return stories;
            }

            public void setStories(List<Story> stories) {
                this.stories = stories;
            }

            public List<Object> getPosts() {
                return posts;
            }

            public void setPosts(List<Object> posts) {
                this.posts = posts;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProfileImage() {
                return profileImage;
            }

            public void setProfileImage(String profileImage) {
                this.profileImage = profileImage;
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

            public Integer getV() {
                return v;
            }

            public void setV(Integer v) {
                this.v = v;
            }

        }
    }
}