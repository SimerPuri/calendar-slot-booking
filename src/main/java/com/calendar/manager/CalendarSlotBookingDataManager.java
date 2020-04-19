package com.calendar.manager;

import com.calendar.customexception.CalendarSlotBookingException;
import com.calendar.models.AvailableSlotsRequest;
import com.calendar.models.SlotBookingRequest;
import com.calendar.models.User;
import com.calendar.models.UserInfo;
import com.calendar.models.UserSlotsMapping;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public interface CalendarSlotBookingDataManager {

    UserInfo registerUser(User user) throws Exception;

    boolean isUserExists(User user) ;

    boolean isUserIdExists(UUID userid) ;


    UserSlotsMapping addAvailableSlots(UUID id, AvailableSlotsRequest slotsAvailable);

    Map getAvailableSlotsForUser(UUID id);

    boolean bookSlot(UUID bookingUserId, SlotBookingRequest slotBookingRequest) throws CalendarSlotBookingException;
}
