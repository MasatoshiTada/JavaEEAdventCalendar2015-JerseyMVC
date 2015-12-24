package com.example.rest.dto;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Created by tada on 2015/12/24.
 */
@Named
@RequestScoped
public class MessageDto {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
