package com.bianki.biankiapp.ClassModel;

public class commentLike {

    private String status;

    private String message;

    public String getSuccess ()
    {
        return status;
    }

    public void setSuccess (String success)
    {
        this.status = success;
    }

    public String getMessages ()
    {
        return message;
    }

    public void setMessages (String messages)
    {
        this.message = messages;
    }
}
