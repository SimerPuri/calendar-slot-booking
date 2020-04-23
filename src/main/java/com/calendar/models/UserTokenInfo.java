package com.calendar.models;

import java.util.UUID;

/**
 * The type User token info.
 */
public class UserTokenInfo extends UserInfo {
    /**
     * The Auth token.
     */
    UUID authToken;

    /**
     * Instantiates a new User token info.
     *
     * @param userName  the user name
     * @param userId    the user id
     * @param authToken the auth token
     */
    public UserTokenInfo(String userName, UUID userId, UUID authToken) {
        super(userName, userId);
        this.authToken = authToken;
    }

    /**
     * Gets auth token.
     *
     * @return the auth token
     */
    public UUID getAuthToken() {
        return authToken;
    }

    /**
     * Sets auth token.
     *
     * @param authToken the auth token
     */
    public void setAuthToken(UUID authToken) {
        this.authToken = authToken;
    }


}
