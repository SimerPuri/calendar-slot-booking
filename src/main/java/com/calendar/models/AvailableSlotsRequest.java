package com.calendar.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Value.Immutable
@JsonSerialize
@JsonDeserialize(as = ImmutableAvailableSlotsRequest.class)
public interface AvailableSlotsRequest {
    LocalDate getSlotDate();

    Set<LocalTime> getSlotsTime();

}
