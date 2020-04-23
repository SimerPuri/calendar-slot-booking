package com.calendar.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The interface Auth token.
 */
@Value.Immutable
@JsonSerialize
@JsonDeserialize(as = ImmutableAuthToken.class)
public interface AuthToken {
    /**
     * Gets user id.
     *
     * @return the user id
     */
    UUID getUserId();

    /**
     * Gets auth token.
     *
     * @return the auth token
     */
    UUID getAuthToken();

    /**
     * Gets token validity.
     *
     * @return the token validity
     */
    LocalDateTime getTokenValidity();

}
