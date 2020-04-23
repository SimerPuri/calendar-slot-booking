package com.calendar.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

/**
 * The interface User.
 */
@Value.Immutable
@JsonSerialize
@JsonDeserialize(as = ImmutableUser.class)
public interface User {
    /**
     * Gets user name.
     *
     * @return the user name
     */
    String getUserName();
}
