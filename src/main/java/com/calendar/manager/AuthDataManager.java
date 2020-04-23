package com.calendar.manager;

import com.calendar.customexception.CalendarSlotBookingException;
import com.calendar.models.AuthToken;
import com.calendar.models.ImmutableAuthToken;
import org.eclipse.jetty.server.Response;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.calendar.constants.CalendarServiceConstants.AUTH_TOKEN_EXPIRATION_TIME_MINUTES;

/**
 * The type Auth data manager.
 * Manages data for auth
 */
public class AuthDataManager {
    private static AuthDataManager authDataManager;
    private Map<UUID, AuthToken> usersAuthToken;

    private AuthDataManager() {
        this.usersAuthToken = new HashMap<>();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static AuthDataManager getInstance() {
        if (authDataManager == null) {
            synchronized (AuthDataManager.class) {
                if (authDataManager == null) {
                    authDataManager = new AuthDataManager();
                }
            }
        }

        return authDataManager;
    }


    /**
     * Generate token for existing user auth token.
     *
     * @param userId the user id
     * @return the auth token
     * @throws CalendarSlotBookingException the calendar slot booking exception
     */
    public AuthToken generateTokenForExistingUser(UUID userId) throws CalendarSlotBookingException {
        if (!isUserIdExists(userId)) {
            throw new CalendarSlotBookingException(Response.SC_NOT_FOUND,
                    "User Doesn't Exists");
        }
       return generateToken(userId);
    }

    /**
     * Generate token auth token.
     *
     * @param userId the user id
     * @return the auth token
     */
    public AuthToken generateToken(UUID userId) {
        ImmutableAuthToken authToken = ImmutableAuthToken.builder().userId(userId).tokenValidity(
                LocalDateTime.now().plusMinutes(AUTH_TOKEN_EXPIRATION_TIME_MINUTES))
                .authToken(UUID.randomUUID()).build();
        usersAuthToken.put(userId, authToken);

        return authToken;
    }


    /**
     * Is user id exists boolean.
     *
     * @param userid the userid
     * @return the boolean
     */
    public boolean isUserIdExists(UUID userid) {
        return usersAuthToken.containsKey(userid);
    }


    /**
     * Gets token.
     *
     * @param userId the user id
     * @return the token
     */
    public AuthToken getToken(UUID userId) {
       return usersAuthToken.get(userId);
    }



}
