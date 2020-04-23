package com.calendar.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/**
 * The interface Slot booking request.
 */
@Value.Immutable
@JsonSerialize
@JsonDeserialize(as = ImmutableSlotBookingRequest.class)
public interface SlotBookingRequest {
    /**
     * Gets user id.
     *
     * @return the user id
     */
    UUID getUserId();

    /**
     * Gets slot date.
     *
     * @return the slot date
     */
    LocalDate getSlotDate();

    /**
     * Gets slot time.
     *
     * @return the slot time
     */
    LocalTime getSlotTime();

}
