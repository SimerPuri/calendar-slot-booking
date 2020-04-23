package com.calendar.validator;

import java.util.UUID;

import static com.calendar.constants.CalendarServiceConstants.EMPTY_GUID;

/**
 * The type Calendar slot booking validator.
 */
public final class CalendarSlotBookingValidator {

    /**
     * Is user id uuid valid boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public static boolean isUserIdUUIDValid(UUID id) {
        return id != null && !EMPTY_GUID.equals(id.toString());
    }
}
