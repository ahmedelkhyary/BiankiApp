package com.bianki.biankiapp.ClassModel;

public class getMedaiaLocal {




        private String id;
        private String path;
        private String mediaType;
        private String text;
        private String uploadDate;
        private Integer v;
        private Integer commentsNumber;
        private Integer likesNumber;
        private Boolean isLiked;

            private String idofuser;
            private String userName;
            private String fullName;
            private String email;
            private String profileImage;

    String postType ;

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public getMedaiaLocal(String id, String path, String mediaType, String text, String uploadDate, Integer v, Integer commentsNumber, Integer likesNumber,
                          Boolean isLiked, String idofuser, String userName, String fullName, String email, String profileImage , String postType) {
        this.id = id;
        this.path = path;
        this.mediaType = mediaType;
        this.text = text;
        this.uploadDate = uploadDate;
        this.v = v;
        this.commentsNumber = commentsNumber;
        this.likesNumber = likesNumber;
        this.isLiked = isLiked;
        this.idofuser = idofuser;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.profileImage = profileImage;
        this.postType = postType ;
    }


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

    public Boolean getLiked() {
        return isLiked;
    }

    public void setLiked(Boolean liked) {
        isLiked = liked;
    }

    public String getIdofuser() {
        return idofuser;
    }

    public void setIdofuser(String idofuser) {
        this.idofuser = idofuser;
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
