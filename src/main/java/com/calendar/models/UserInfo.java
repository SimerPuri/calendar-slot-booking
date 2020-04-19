package com.calendar.models;

import java.util.UUID;

public class UserInfo {
    String userName;

    UUID userId;

    public UserInfo(String userName, UUID userId) {
        this.userName = userName;
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }


}
