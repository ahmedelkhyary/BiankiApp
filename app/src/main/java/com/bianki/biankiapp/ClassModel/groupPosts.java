package com.bianki.biankiapp.ClassModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class groupPosts {

    @SerializedName("group1")
    @Expose
    private Group1 group1;
    @SerializedName("group")
    @Expose
    private Group group;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public Group1 getGroup1() {
        return group1;
    }

    public void setGroup1(Group1 group1) {
        this.group1 = group1;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public class Group1 {

        @SerializedName("members")
        @Expose
        private List<Object> members = null;
        @SerializedName("posts")
        @Expose
        private List<String> posts = null;
        @SerializedName("notacceptedPosts")
        @Expose
        private List<Object> notacceptedPosts = null;
        @SerializedName("files")
        @Expose
        private List<File> files = null;
        @SerializedName("BlockedUsers")
        @Expose
        private List<Object> blockedUsers = null;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("Type")
        @Expose
        private String type;
        @SerializedName("privacy")
        @Expose
        private String privacy;
        @SerializedName("photo")
        @Expose
        private String photo;
        @SerializedName("sortDate")
        @Expose
        private String sortDate;
        @SerializedName("__v")
        @Expose
        private Integer v;

        public List<Object> getMembers() {
            return members;
        }

        public void setMembers(List<Object> members) {
            this.members = members;
        }

        public List<String> getPosts() {
            return posts;
        }

        public void setPosts(List<String> posts) {
            this.posts = posts;
        }

        public List<Object> getNotacceptedPosts() {
            return notacceptedPosts;
        }

        public void setNotacceptedPosts(List<Object> notacceptedPosts) {
            this.notacceptedPosts = notacceptedPosts;
        }

        public List<File> getFiles() {
            return files;
        }

        public void setFiles(List<File> files) {
            this.files = files;
        }

        public List<Object> getBlockedUsers() {
            return blockedUsers;
        }

        public void setBlockedUsers(List<Object> blockedUsers) {
            this.blockedUsers = blockedUsers;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPrivacy() {
            return privacy;
        }

        public void setPrivacy(String privacy) {
            this.privacy = privacy;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
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

    }


    public class Group {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("photo")
        @Expose
        private String photo;
        @SerializedName("privacy")
        @Expose
        private String privacy;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getPrivacy() {
            return privacy;
        }

        public void setPrivacy(String privacy) {
            this.privacy = privacy;
        }


    }


    public class File {

        @SerializedName("postComments")
        @Expose
        private List<Object> postComments = null;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("groupId")
        @Expose
        private String groupId;
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
        @SerializedName("textColor")
        @Expose
        private String textColor;
        @SerializedName("accepted")
        @Expose
        private Boolean accepted;
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

        public List<Object> getPostComments() {
            return postComments;
        }

        public void setPostComments(List<Object> postComments) {
            this.postComments = postComments;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
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

        public String getTextColor() {
            return textColor;
        }

        public void setTextColor(String textColor) {
            this.textColor = textColor;
        }

        public Boolean getAccepted() {
            return accepted;
        }

        public void setAccepted(Boolean accepted) {
            this.accepted = accepted;
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
            @SerializedName("bio")
            @Expose
            private String bio;

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

            public String getBio() {
                return bio;
            }

            public void setBio(String bio) {
                this.bio = bio;
            }

        }
    }
}