package com.calendar.service;

import com.calendar.customexception.CalendarSlotBookingException;
import com.calendar.models.AvailableSlotsRequest;
import com.calendar.models.SlotBookingRequest;
import com.calendar.models.User;
import com.calendar.models.UserInfo;
import com.calendar.models.UserSlotsMapping;
import com.calendar.models.UserTokenInfo;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public interface CalendarSlotBookingService {

    UserTokenInfo registerUser(User user) throws Exception;

    UserSlotsMapping addAvailableSlots(UUID id, AvailableSlotsRequest slotsAvailable) throws CalendarSlotBookingException;

    Map getAvailableSlotsForUser(UUID id) throws CalendarSlotBookingException;

    boolean bookSlot(UUID bookingUserId, SlotBookingRequest slotBookingRequest) throws CalendarSlotBookingException;

}
