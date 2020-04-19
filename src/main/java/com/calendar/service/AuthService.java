package com.calendar.service;

import com.calendar.customexception.CalendarSlotBookingException;
import com.calendar.manager.AuthDataManager;
import com.calendar.manager.CalendarSlotBookingDataManagerImpl;
import com.calendar.models.AuthToken;
import com.calendar.models.AvailableSlotsRequest;
import com.calendar.models.SlotBookingRequest;
import com.calendar.models.User;
import com.calendar.models.UserInfo;
import com.calendar.models.UserSlotsMapping;
import org.eclipse.jetty.server.Response;

import java.util.Map;
import java.util.UUID;

public class AuthService {
    private AuthDataManager authDataManager;

    public AuthService(AuthDataManager authDataManager) {
        this.authDataManager = authDataManager;
    }


    public AuthToken generateAuthToken(UUID userId) throws CalendarSlotBookingException {
        return authDataManager.generateTokenForExistingUser(userId);
    }

}
