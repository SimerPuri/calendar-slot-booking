package com.calendar.manager;

import com.calendar.customexception.CalendarSlotBookingException;
import com.calendar.models.AvailableSlotsRequest;
import com.calendar.models.SlotBookingRequest;
import com.calendar.models.User;
import com.calendar.models.UserInfo;
import com.calendar.models.UserSlotsMapping;

import java.util.Map;
import java.util.UUID;

/**
 * The interface Calendar slot booking data manager.
 */
public interface CalendarSlotBookingDataManager {

    /**
     * Register user user info.
     *
     * @param user the user
     * @return the user info
     * @throws Exception the exception
     */
    UserInfo registerUser(User user) throws Exception;

    /**
     * Is user exists boolean.
     *
     * @param user the user
     * @return the boolean
     */
    boolean isUserExists(User user) ;

    /**
     * Is user id exists boolean.
     *
     * @param userid the userid
     * @return the boolean
     */
    boolean isUserIdExists(UUID userid) ;


    /**
     * Add available slots user slots mapping.
     *
     * @param id             the id
     * @param slotsAvailable the slots available
     * @return the user slots mapping
     */
    UserSlotsMapping addAvailableSlots(UUID id, AvailableSlotsRequest slotsAvailable);

    /**
     * Gets available slots for user.
     *
     * @param id the id
     * @return the available slots for user
     */
    Map getAvailableSlotsForUser(UUID id);

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
