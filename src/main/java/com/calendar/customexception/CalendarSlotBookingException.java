package com.calendar.customexception;

/**
 * The type Calendar slot booking exception.
 */
public class CalendarSlotBookingException extends Exception {

    private Integer statusCode;

    /**
     * Instantiates a new Calendar slot booking exception.
     *
     * @param statusCode the status code
     * @param message    the message
     */
    public CalendarSlotBookingException(Integer statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    /**
     * Gets status code.
     *
     * @return the status code
     */
    public Integer getStatusCode() {
        return statusCode;
    }

}
