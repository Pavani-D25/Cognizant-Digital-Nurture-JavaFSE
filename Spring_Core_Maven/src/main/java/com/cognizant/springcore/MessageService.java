package com.cognizant.springcore;

public class MessageService {

    private String message;

    public MessageService() {
        this.message = "Default Hello from Spring!";
    }

    public MessageService(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
