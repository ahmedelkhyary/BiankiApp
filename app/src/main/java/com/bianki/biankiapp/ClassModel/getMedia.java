package com.bianki.biankiapp.ClassModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getMedia {
    @SerializedName("files")
    @Expose
    private List<File> files = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


    public class File {


        @SerializedName("files")
        @Expose
        private List<String> files = null;


        public List<String> getFiles() {
            return files;
        }

        public void setFiles(List<String> files) {
            this.files = files;
        }

        @SerializedName("_id")
        @Expose
        private String id;

        @SerializedName("postType")
        @Expose
        private String postType;

        public String getPostType() {
            return postType;
        }

        public void setPostType(String postType) {
            this.postType = postType;
        }

        @SerializedName("path")
        @Expose
        private String path;
        @SerializedName("mediaType")
        @Expose
        private String mediaType;

        @SerializedName("textColor")
        @Expose
        private String textColor;

        public String getTextColor() {
            return textColor;
        }

        public void setTextColor(String textColor) {
            this.textColor = textColor;
        }

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("uploadDate")
        @Expose
        private String uploadDate;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("commentsNumber")
        @Expose
        private Integer commentsNumber;
        @SerializedName("likesNumber")
        @Expose
        private Integer likesNumber;
        @SerializedName("postUploader")
        @Expose
        private PostUploader postUploader;
        @SerializedName("isLiked")
        @Expose
        private Boolean isLiked;

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

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }

        public Integer getCommentsNumber() {
            return commentsNumber;
        }

        public void setCommentsNumber(Integer commentsNumber) {
            this.commentsNumber = commentsNumber;
        }

        public Integer getLikesNumber() {
            return likesNumber;
        }

        public void setLikesNumber(Integer likesNumber) {
            this.likesNumber = likesNumber;
        }

        public PostUploader getPostUploader() {
            return postUploader;
        }

        public void setPostUploader(PostUploader postUploader) {
            this.postUploader = postUploader;
        }

        public Boolean getIsLiked() {
            return isLiked;
        }

        public void setIsLiked(Boolean isLiked) {
            this.isLiked = isLiked;
        }


        public class PostUploader {

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

        }
    }
}
