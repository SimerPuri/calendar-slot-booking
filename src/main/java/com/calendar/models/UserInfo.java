package com.calendar.models;

import java.util.UUID;

/**
 * The type User info.
 */
public class UserInfo {
    /**
     * The User name.
     */
    String userName;

    /**
     * The User id.
     */
    UUID userId;

    /**
     * Instantiates a new User info.
     *
     * @param userName the user name
     * @param userId   the user id
     */
    public UserInfo(String userName, UUID userId) {
        this.userName = userName;
        this.userId = userId;
    }


    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(UUID userId) {
        this.userId = userId;
    }


}
