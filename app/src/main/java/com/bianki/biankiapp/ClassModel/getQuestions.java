package com.bianki.biankiapp.ClassModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getQuestions {
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

    @SerializedName("questionReplayes")
    @Expose
    private List<Object> questionReplayes = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("questionCreator")
    @Expose
    private String questionCreator;
    @SerializedName("questionContent")
    @Expose
    private String questionContent;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("creator")
    @Expose
    private Creator creator;

    public List<Object> getQuestionReplayes() {
        return questionReplayes;
    }

    public void setQuestionReplayes(List<Object> questionReplayes) {
        this.questionReplayes = questionReplayes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionCreator() {
        return questionCreator;
    }

    public void setQuestionCreator(String questionCreator) {
        this.questionCreator = questionCreator;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
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

}

public class Creator {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("userName")
    @Expose
    private String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
}
