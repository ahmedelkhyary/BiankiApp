package com.bianki.biankiapp.ClassModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class myGroups {


    @SerializedName("groups")
    @Expose
    private List<Group> groups = null;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public class Group {

        @SerializedName("members")
        @Expose
        private List<Member> members = null;
        @SerializedName("posts")
        @Expose
        private List<Object> posts = null;
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
        @SerializedName("__v")
        @Expose
        private Integer v;

        public List<Member> getMembers() {
            return members;
        }

        public void setMembers(List<Member> members) {
            this.members = members;
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

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }


        public class Member {

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
}