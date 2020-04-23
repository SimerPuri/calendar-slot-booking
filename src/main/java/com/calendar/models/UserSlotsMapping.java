package com.calendar.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Set;

/**
 * The type User slots mapping.
 */
public class UserSlotsMapping {
    /**
     * The User info.
     */
    UserInfo userInfo;

    /**
     * The Available slots.
     */
    Map<LocalDate, Set<LocalTime>> availableSlots;

    /**
     * Instantiates a new User slots mapping.
     *
     * @param userInfo       the user info
     * @param availableSlots the available slots
     */
    public UserSlotsMapping(UserInfo userInfo, Map<LocalDate, Set<LocalTime>> availableSlots) {
        this.userInfo = userInfo;
        this.availableSlots = availableSlots;
    }

    /**
     * Gets user info.
     *
     * @return the user info
     */
    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * Sets user info.
     *
     * @param userInfo the user info
     */
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * Gets available slots.
     *
     * @return the available slots
     */
    public Map<LocalDate, Set<LocalTime>> getAvailableSlots() {
        return availableSlots;
    }

    /**
     * Sets available slots.
     *
     * @param availableSlots the available slots
     */
    public void setAvailableSlots(Map<LocalDate, Set<LocalTime>> availableSlots) {
        this.availableSlots = availableSlots;
    }

    /**
     * Sets available slot for date.
     *
     * @param date the date
     * @param time the time
     */
    public void setAvailableSlotForDate(LocalDate date, Set<LocalTime> time) {
        this.availableSlots.put(date, time);
    }


}
