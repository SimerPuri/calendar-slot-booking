package com.calendar.validator;

import java.util.UUID;

import static com.calendar.constants.CalendarServiceConstants.EMPTY_GUID;

public final class CalendarSlotBookingValidator {

    public static boolean isUserIdUUIDValid(UUID id) {
        return id != null && !EMPTY_GUID.equals(id.toString());
    }
}
