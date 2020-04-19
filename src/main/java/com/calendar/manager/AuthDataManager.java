package com.calendar.manager;

import com.calendar.customexception.CalendarSlotBookingException;
import com.calendar.models.AuthToken;
import com.calendar.models.AvailableSlotsRequest;
import com.calendar.models.ImmutableAuthToken;
import com.calendar.models.SlotBookingRequest;
import com.calendar.models.User;
import com.calendar.models.UserInfo;
import com.calendar.models.UserSlotsMapping;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static com.calendar.constants.CalendarServiceConstants.AUTH_TOKEN_EXPIRATION_TIME_MINUTES;

public class AuthDataManager {
    private static AuthDataManager authDataManager;
    private Map<UUID, AuthToken> usersAuthToken;

    private AuthDataManager() {
        this.usersAuthToken = new HashMap<>();
    }

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


    public AuthToken generateTokenForExistingUser(UUID userId) throws CalendarSlotBookingException {
        if (!isUserIdExists(userId)) {
            throw new CalendarSlotBookingException(Response.SC_NOT_FOUND,
                    "User Doesn't Exists");
        }
       return generateToken(userId);
    }

    public AuthToken generateToken(UUID userId) {
        ImmutableAuthToken authToken = ImmutableAuthToken.builder().userId(userId).tokenValidity(
                LocalDateTime.now().plusMinutes(AUTH_TOKEN_EXPIRATION_TIME_MINUTES))
                .authToken(UUID.randomUUID()).build();
        usersAuthToken.put(userId, authToken);

        return authToken;
    }


    public boolean isUserIdExists(UUID userid) {
        return usersAuthToken.containsKey(userid);
    }

//    public String createToken(UUID userId) {
//        StringUtils.toEncodedString()
//
//        return generateToken(userId);
//    }

    public AuthToken getToken(UUID userId) {
       return usersAuthToken.get(userId);
    }



}
