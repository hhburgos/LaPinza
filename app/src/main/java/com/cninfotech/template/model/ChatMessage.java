package com.cninfotech.template.model;

public class ChatMessage {
    String userName;
    String message;
    String timeOfMessage;
    boolean status;

    public ChatMessage(String userName, String message, String timeOfMessage, boolean status) {
        this.userName = userName;
        this.message = message;
        this.timeOfMessage = timeOfMessage;
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeOfMessage() {
        return timeOfMessage;
    }

    public boolean isStatus() {
        return status;
    }
}


