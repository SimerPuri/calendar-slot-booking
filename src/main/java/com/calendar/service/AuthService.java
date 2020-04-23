package com.calendar.service;

import com.calendar.customexception.CalendarSlotBookingException;
import com.calendar.manager.AuthDataManager;
import com.calendar.models.AuthToken;

import java.util.UUID;

/**
 * The type Auth service.
 */
public class AuthService {
    private AuthDataManager authDataManager;

    /**
     * Instantiates a new Auth service.
     *
     * @param authDataManager the auth data manager
     */
    public AuthService(AuthDataManager authDataManager) {
        this.authDataManager = authDataManager;
    }


    /**
     * Generate auth token auth token.
     *
     * @param userId the user id
     * @return the auth token
     * @throws CalendarSlotBookingException the calendar slot booking exception
     */
    public AuthToken generateAuthToken(UUID userId) throws CalendarSlotBookingException {
        return authDataManager.generateTokenForExistingUser(userId);
    }

}
