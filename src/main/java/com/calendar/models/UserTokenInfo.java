package com.calendar.models;

import java.util.UUID;

public class UserTokenInfo extends UserInfo {
    UUID authToken;

    public UserTokenInfo(String userName, UUID userId, UUID authToken) {
        super(userName, userId);
        this.authToken = authToken;
    }

    public UUID getAuthToken() {
        return authToken;
    }

    public void setAuthToken(UUID authToken) {
        this.authToken = authToken;
    }


}
