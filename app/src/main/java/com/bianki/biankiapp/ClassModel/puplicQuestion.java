package com.bianki.biankiapp.ClassModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class puplicQuestion {


    @SerializedName("questions")
    @Expose
    private List<Question> questions = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public class Question {

        @SerializedName("replaies")
        @Expose
        private List<Replaie> replaies = null;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("questionContent")
        @Expose
        private String questionContent;
        @SerializedName("questionType")
        @Expose
        private String questionType;
        @SerializedName("userType")
        @Expose
        private String userType;
        @SerializedName("uploadDate")
        @Expose
        private String uploadDate;
        @SerializedName("sortDate")
        @Expose
        private String sortDate;
        @SerializedName("to")
        @Expose
        private String to;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("creator")
        @Expose
        private Creator_ creator;
        @SerializedName("isMine")
        @Expose
        private Boolean isMine;
        @SerializedName("isLiked")
        @Expose
        private Boolean isLiked;
        @SerializedName("isRepliedBefore")
        @Expose
        private Boolean isRepliedBefore;

        public List<Replaie> getReplaies() {
            return replaies;
        }

        public void setReplaies(List<Replaie> replaies) {
            this.replaies = replaies;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getQuestionContent() {
            return questionContent;
        }

        public void setQuestionContent(String questionContent) {
            this.questionContent = questionContent;
        }

        public String getQuestionType() {
            return questionType;
        }

        public void setQuestionType(String questionType) {
            this.questionType = questionType;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
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

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }

        public Creator_ getCreator() {
            return creator;
        }

        public void setCreator(Creator_ creator) {
            this.creator = creator;
        }

        public Boolean getIsMine() {
            return isMine;
        }

        public void setIsMine(Boolean isMine) {
            this.isMine = isMine;
        }

        public Boolean getIsLiked() {
            return isLiked;
        }

        public void setIsLiked(Boolean isLiked) {
            this.isLiked = isLiked;
        }

        public Boolean getIsRepliedBefore() {
            return isRepliedBefore;
        }

        public void setIsRepliedBefore(Boolean isRepliedBefore) {
            this.isRepliedBefore = isRepliedBefore;
        }


        public class    Replaie {

            @SerializedName("_id")
            @Expose
            private String id;
            @SerializedName("content")
            @Expose
            private String content;
            @SerializedName("uploadDate")
            @Expose
            private String uploadDate;
            @SerializedName("replayCreatorId")
            @Expose
            private String replayCreatorId;

            @SerializedName("userType")
            @Expose
            private String userType;

            public String getUserType() {
                return userType;
            }

            public void setUserType(String userType) {
                this.userType = userType;
            }


            @SerializedName("isMine")
            @Expose
            private Boolean isMine;


            public Boolean getMine() {
                return isMine;
            }

            public void setMine(Boolean mine) {
                isMine = mine;
            }

            @SerializedName("__v")
            @Expose
            private Integer v;
            @SerializedName("creator")
            @Expose
            private Creator creator;

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

            public String getUploadDate() {
                return uploadDate;
            }

            public void setUploadDate(String uploadDate) {
                this.uploadDate = uploadDate;
            }

            public String getReplayCreatorId() {
                return replayCreatorId;
            }

            public void setReplayCreatorId(String replayCreatorId) {
                this.replayCreatorId = replayCreatorId;
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


            public class Creator {

                @SerializedName("_id")
                @Expose
                private String id;
                @SerializedName("userName")
                @Expose
                private String userName;
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


        public class Creator_ {

            @SerializedName("_id")
            @Expose
            private String id;
            @SerializedName("userName")
            @Expose
            private String userName;
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
