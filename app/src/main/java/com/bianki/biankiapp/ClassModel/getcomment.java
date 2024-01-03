package com.bianki.biankiapp.ClassModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getcomment {

    @SerializedName("comments")
    @Expose
    private List<comments> comments = null;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public List<comments> getFiles() {
        return comments;
    }

    public void setFiles(List<comments> files) {
        this.comments = files;
    }

    public Boolean getSuccess() {
        return status;
    }

    public void setSuccess(Boolean success) {
        this.status = success;
    }


    public class comments {



        @SerializedName("recordPath")
        @Expose

        private String recordPath;

        public String getRecordPath() {
            return recordPath;
        }

        public void setRecordPath(String recordPath) {
            this.recordPath = recordPath;
        }

        @SerializedName("realReplayes")
        @Expose
        private List<RealReplaye> realReplayes = null;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("content")
        @Expose




        private String content;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("creator")
        @Expose
        private Creator creator;
        @SerializedName("likeNum")
        @Expose
        private Integer likeNum;
        @SerializedName("isLiked")
        @Expose
        private Boolean isLiked;
        @SerializedName("replayesNum")
        @Expose
        private Integer replayesNum;
        @SerializedName("uploadDate")
        @Expose
        private String uploadDate;

        public List<RealReplaye> getRealReplayes() {
            return realReplayes;
        }

        public void setRealReplayes(List<RealReplaye> realReplayes) {
            this.realReplayes = realReplayes;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }

        public Creator getCreator() {
            return creator;
        }

        public void setCreator(Creator creator) {
            this.creator = creator;
        }

        public Integer getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(Integer likeNum) {
            this.likeNum = likeNum;
        }

        public Boolean getIsLiked() {
            return isLiked;
        }

        public void setIsLiked(Boolean isLiked) {
            this.isLiked = isLiked;
        }

        public Integer getReplayesNum() {
            return replayesNum;
        }

        public void setReplayesNum(Integer replayesNum) {
            this.replayesNum = replayesNum;
        }

        public String getUploadDate() {
            return uploadDate;
        }

        public void setUploadDate(String uploadDate) {
            this.uploadDate = uploadDate;
        }


        public class Creator {

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


        }
    }
    public class RealReplaye {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("replayCreator")
        @Expose
        private String replayCreator;
        @SerializedName("content")
        @Expose
        private String content;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("creator")
        @Expose
        private getcomment.comments.Creator creator;
        @SerializedName("likeNum")
        @Expose
        private Integer likeNum;
        @SerializedName("isLiked")
        @Expose
        private Boolean isLiked;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getReplayCreator() {
            return replayCreator;
        }

        public void setReplayCreator(String replayCreator) {
            this.replayCreator = replayCreator;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }

        public getcomment.comments.Creator getCreator() {
            return creator;
        }

        public void setCreator(getcomment.comments.Creator creator) {
            this.creator = creator;
        }

        public Integer getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(Integer likeNum) {
            this.likeNum = likeNum;
        }

        public Boolean getIsLiked() {
            return isLiked;
        }

        public void setIsLiked(Boolean isLiked) {
            this.isLiked = isLiked;
        }

    }
}