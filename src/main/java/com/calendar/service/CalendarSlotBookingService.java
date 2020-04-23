package com.calendar.service;

import com.calendar.customexception.CalendarSlotBookingException;
import com.calendar.models.AvailableSlotsRequest;
import com.calendar.models.SlotBookingRequest;
import com.calendar.models.User;
import com.calendar.models.UserSlotsMapping;
import com.calendar.models.UserTokenInfo;

import java.util.Map;
import java.util.UUID;

/**
 * The interface Calendar slot booking service.
 */
public interface CalendarSlotBookingService {

    /**
     * Register user user token info.
     *
     * @param user the user
     * @return the user token info
     * @throws Exception the exception
     */
    UserTokenInfo registerUser(User user) throws Exception;

    /**
     * Add available slots user slots mapping.
     *
     * @param id             the id
     * @param slotsAvailable the slots available
     * @return the user slots mapping
     * @throws CalendarSlotBookingException the calendar slot booking exception
     */
    UserSlotsMapping addAvailableSlots(UUID id, AvailableSlotsRequest slotsAvailable) throws CalendarSlotBookingException;

    /**
     * Gets available slots for user.
     *
     * @param id the id
     * @return the available slots for user
     * @throws CalendarSlotBookingException the calendar slot booking exception
     */
    Map getAvailableSlotsForUser(UUID id) throws CalendarSlotBookingException;

    /**
     * Book slot boolean.
     *
     * @param bookingUserId      the booking user id
     * @param slotBookingRequest the slot booking request
     * @return the boolean
     * @throws CalendarSlotBookingException the calendar slot booking exception
     */
    boolean bookSlot(UUID bookingUserId, SlotBookingRequest slotBookingRequest) throws CalendarSlotBookingException;

}
