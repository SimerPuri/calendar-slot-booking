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
import com.calendar.models.UserTokenInfo;
import org.eclipse.jetty.server.Response;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public class CalendarSlotBookingServiceImpl implements CalendarSlotBookingService {
    private CalendarSlotBookingDataManagerImpl calendarSlotBookingDataManager;
    private AuthDataManager authDataManager;

    public CalendarSlotBookingServiceImpl(CalendarSlotBookingDataManagerImpl calendarSlotBookingDataManager,
                                          AuthDataManager authDataManager) {
        this.calendarSlotBookingDataManager = calendarSlotBookingDataManager;
        this.authDataManager = authDataManager;
    }


    @Override
    public UserTokenInfo registerUser(User user) throws Exception {
        UserInfo userInfo = calendarSlotBookingDataManager.registerUser(user);
        AuthToken authToken = authDataManager.generateToken(userInfo.getUserId());
        return new UserTokenInfo(userInfo.getUserName(), userInfo.getUserId(), authToken.getAuthToken());
    }

    @Override
    public UserSlotsMapping addAvailableSlots(UUID id, AvailableSlotsRequest slotsAvailable)
            throws CalendarSlotBookingException {
        if (!calendarSlotBookingDataManager.isUserIdExists(id)) {
            throw new CalendarSlotBookingException(Response.SC_NOT_FOUND, "User does not exists");
        }

        return calendarSlotBookingDataManager.addAvailableSlots(id, slotsAvailable);
    }

    @Override
    public Map getAvailableSlotsForUser(UUID id) throws CalendarSlotBookingException {
        if (!calendarSlotBookingDataManager.isUserIdExists(id)) {
            throw new CalendarSlotBookingException(Response.SC_NOT_FOUND, "User does not exists");
        }
        return calendarSlotBookingDataManager.getAvailableSlotsForUser(id);
    }

    @Override
    public boolean bookSlot(UUID bookingUserId, SlotBookingRequest slotBookingRequest)
            throws CalendarSlotBookingException {
        if (!calendarSlotBookingDataManager.isUserIdExists(bookingUserId) ||
                !calendarSlotBookingDataManager.isUserIdExists(slotBookingRequest.getUserId())) {
            throw new CalendarSlotBookingException(Response.SC_NOT_FOUND, "Booking User/ Booked User Id" +
                    " does not exists");
        }
        return calendarSlotBookingDataManager.bookSlot(bookingUserId, slotBookingRequest);
    }

}
