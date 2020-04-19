package com.calendar.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Set;

public class UserSlotsMapping {
    UserInfo userInfo;

    Map<LocalDate, Set<LocalTime>> availableSlots;

    public UserSlotsMapping(UserInfo userInfo, Map<LocalDate, Set<LocalTime>> availableSlots) {
        this.userInfo = userInfo;
        this.availableSlots = availableSlots;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Map<LocalDate, Set<LocalTime>> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(Map<LocalDate, Set<LocalTime>> availableSlots) {
        this.availableSlots = availableSlots;
    }

    public void setAvailableSlotForDate(LocalDate date, Set<LocalTime> time) {
        this.availableSlots.put(date, time);
    }


}
