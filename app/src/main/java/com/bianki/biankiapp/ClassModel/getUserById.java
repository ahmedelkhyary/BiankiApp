package com.bianki.biankiapp.ClassModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getUserById {


    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public class Post {

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
        @SerializedName("postType")
        @Expose
        private String postType;
        @SerializedName("latidude")
        @Expose
        private Integer latidude;
        @SerializedName("longtiude")
        @Expose
        private Integer longtiude;
        @SerializedName("uploadDate")
        @Expose
        private String uploadDate;
        @SerializedName("sortDate")
        @Expose
        private String sortDate;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("commentsNumber")
        @Expose
        private Integer commentsNumber;
        @SerializedName("likesNumber")
        @Expose
        private Integer likesNumber;
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

        public String getPostType() {
            return postType;
        }

        public void setPostType(String postType) {
            this.postType = postType;
        }

        public Integer getLatidude() {
            return latidude;
        }

        public void setLatidude(Integer latidude) {
            this.latidude = latidude;
        }

        public Integer getLongtiude() {
            return longtiude;
        }

        public void setLongtiude(Integer longtiude) {
            this.longtiude = longtiude;
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

        public Boolean getIsLiked() {
            return isLiked;
        }

        public void setIsLiked(Boolean isLiked) {
            this.isLiked = isLiked;
        }
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
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("bio")
            @Expose
            private String bio;
            @SerializedName("profileImage")
            @Expose
            private String profileImage;
            @SerializedName("followingNum")
            @Expose
            private Integer followingNum;
            @SerializedName("followersNum")
            @Expose
            private Integer followersNum;
            @SerializedName("postsNum")
            @Expose
            private Integer postsNum;
            @SerializedName("posts")
            @Expose
            private List<Post> posts = null;
            @SerializedName("website")
            @Expose
            private String website;


            @SerializedName("isFollowed")
            @Expose
            private Boolean isFollowed;

            public Boolean getFollowed() {
                return isFollowed;
            }

            public void setFollowed(Boolean followed) {
                isFollowed = followed;
            }

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

            public String getBio() {
                return bio;
            }

            public void setBio(String bio) {
                this.bio = bio;
            }

            public String getProfileImage() {
                return profileImage;
            }

            public void setProfileImage(String profileImage) {
                this.profileImage = profileImage;
            }

            public Integer getFollowingNum() {
                return followingNum;
            }

            public void setFollowingNum(Integer followingNum) {
                this.followingNum = followingNum;
            }

            public Integer getFollowersNum() {
                return followersNum;
            }

            public void setFollowersNum(Integer followersNum) {
                this.followersNum = followersNum;
            }

            public Integer getPostsNum() {
                return postsNum;
            }

            public void setPostsNum(Integer postsNum) {
                this.postsNum = postsNum;
            }

            public List<Post> getPosts() {
                return posts;
            }

            public void setPosts(List<Post> posts) {
                this.posts = posts;
            }

            public String getWebsite() {
                return website;
            }

            public void setWebsite(String website) {
                this.website = website;


        }
    }
}
