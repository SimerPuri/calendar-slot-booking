package com.calendar.customexception;

public class CalendarSlotBookingException extends Exception {

    private Integer statusCode;

    public CalendarSlotBookingException(Integer statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

}
