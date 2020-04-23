package com.calendar.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

/**
 * The interface Available slots request.
 */
@Value.Immutable
@JsonSerialize
@JsonDeserialize(as = ImmutableAvailableSlotsRequest.class)
public interface AvailableSlotsRequest {
    /**
     * Gets slot date.
     *
     * @return the slot date
     */
    LocalDate getSlotDate();

    /**
     * Gets slots time.
     *
     * @return the slots time
     */
    Set<LocalTime> getSlotsTime();

}
